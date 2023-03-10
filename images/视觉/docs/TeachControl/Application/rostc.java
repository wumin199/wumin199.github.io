//author: smith

NAME ROSTC; 
{
   category="ROS" |
   macro="ROS"
}
USER;
INHERIT TRcObject;

VAR  
   //Maptos
   Version  : MAPTO String16;
   CommandNum  : MAPTO DINT;
   Execute  : MAPTO BOOL;
   ExecuteLocked  : MAPTO BOOL;   
   Abort : MAPTO BOOL;
   AbortLocked : MAPTO BOOL;
   HasDyn   : MAPTO BOOL;
   HasOvl   : MAPTO BOOL;
   
   Ready  : MAPTO BOOL;
   HasError : MAPTO BOOL;
   HasCartPos  : MAPTO BOOL;
	HasAxisPos  : MAPTO BOOL;
   OvlType	: MAPTO DINT;	//0 = None, 1 = OVLREL, 2 = OVLABS, 3 = OVLSUPPOS
	OvlPercent	: MAPTO DINT;
   
   apIec1 : AXISPOSIEC;
   
   
   cpIec2   : CARTPOSIEC;
   
   dynIec1  : DYNAMICIEC;
   
   ovlIec   : OVLIEC;//这个是IEC和RC都自定义的
   
   toolFrameIec   : CARTFRAMEIEC;
   
   digitalIn   : MAPTO LWORD;
   digitalOut  : MAPTO LWORD;
END_VAR

VAR USER
   cpIec1   : CARTPOSIEC READONLY;
   rosTool  : TOOL READONLY;
   
   DIn   : ARRAY [0..15] OF MAPTO BOOL;
   DOut   : ARRAY [0..15] OF MAPTO BOOL;
   //cp1   : CARTPOSIEC READONLY;
END_VAR

ROUTINE NEW()
   
END_ROUTINE


ROUTINE IsReady() : BOOL
   IF NOT mbEnabled THEN
      Error("TC Interface is not enabled!  Startup probably failed!");      
   END_IF;
   
   RETURN mbEnabled AND IS_MAPPED(Ready) AND Ready;
END_ROUTINE


ROUTINE Begin() USER
   VAR
      str   : STRING;
   END_VAR
   
   IF Version <> gROS_Version THEN
      str := "Version string does not match!  Expected: " + gROS_Version + " got: " + Version;
      RcSetGenericMsg(eRcMsgApplError, 144, INSTANCE_ID(), 1, str);
   END_IF;
   
   SELECT SELF;   
   Ready := TRUE;     
END_ROUTINE

ROUTINE RELEASE()
   PRINT("Releasing TC itf");
   Ready := FALSE;
   Execute := FALSE;
   ExecuteLocked := FALSE;
   
   gROS_bSync := FALSE;
END_ROUTINE
   


ROUTINE AbortMonitor()
   VAR
      bAbortChanged  : BOOL;
   END_VAR   
   
   
   bAbortChanged := IS_CHANGED(Abort);
   WHILE TRUE DO
      WAIT bAbortChanged;
      bAbortChanged := FALSE;
      IF Abort THEN
         PRINT("ROS Abort received");
         mRob.Stop();//参考：KeMotion KAIRO Expert
         mRob._robot.progPath.cancelFlipFlop := NOT mRob._robot.progPath.cancelFlipFlop;//cancelFlipFlop state transition indicates cancellation of all active motion commands
         gROS_bSync := FALSE;
         //myRobot.Clear();
         //StopRobot();
         
         AbortLocked := TRUE;
      END_IF;  
   END_WHILE;
   
END_ROUTINE


ROUTINE PlcControl() USER// { macro="ROS" }
   VAR
      iCommandNum : DINT;
      lastPosMcu  : TMcuPosition;
      cartEndPos : TMcuPosition;
      
      pos1  : MAPTO POSITION_;      
   END_VAR
   
   WHILE NOT IsReady() DO
      
      Error("Call Begin() first!");
   END_WHILE;
   
   WAIT Execute AND NOT Abort;
   
   iCommandNum := CommandNum;
   Execute := FALSE; //Reset the Execute flag immediately   
   
   IF HasCartPos THEN
      pos1 := MAP(cpIec1);
   ELSIF HasAxisPos THEN
      pos1 := MAP(apIec1);
   END_IF;
   
   IF iCommandNum = TTcPlcCommandPTP THEN
      
      IF HasDyn AND HasOvl THEN
         ovlIec.GetOvl();
         PTP(pos1, dynIec1, ovlIec.mOvl);
      ELSIF HasDyn THEN         
         PTP(pos1, dynIec1);
      ELSIF HasOvl THEN
         PTP(pos1, , ovlIec.mOvl);
      ELSE
         PTP(pos1);         
      END_IF;
      
   ELSIF (iCommandNum = TTcPlcCommandWaitIsFinished) THEN
      WaitMainrunOrCancel(NOT IS_DOPART);
      
   ELSIF(iCommandNum = TTcPlcCommandLin) THEN
      IF NOT HasError THEN         
         //Get the mode of the last point in the path or current point
         //lastPosMcu是个集合类型的数据，包含了ap，cp等坐标值
         mRob.GetLastPos(lastPosMcu);
         //lastPosMcu是souce，会被转换到cartEndPos(eMcuPosCart类型)，第一个Ture表示前瞻，第2个Ture表示输出错误
         WHILE NOT mRob._robot.TransformPos(cartEndPos, lastPosMcu, eMcuPosCart, TRUE, TRUE) DO
         END_WHILE;
         
         cpIec1.mode := cartEndPos.mPosCart.mMode.mConfig;//获得Mode值
         
         IF HasDyn AND HasOvl THEN
            ovlIec.GetOvl();
            Lin(pos1, dynIec1, ovlIec.mOvl);
         ELSIF HasDyn THEN            
            Lin(pos1, dynIec1);
         ELSIF HasOvl THEN
            ovlIec.GetOvl();
            Lin(pos1, ,ovlIec.mOvl);
         ELSE
            //Lin(cpIec1);
            Lin(pos1);
         END_IF;
      END_IF;

   //LinRelTCP (dist : CARTDIST OPTIONAL dyn : DYNAMIC_ OPTIONAL ovl : OVERLAP_ )
   //TODO
   ELSE (iCommandNum = TTcPlcCommandLinRelTCP) THEN
      IF NOT HasError THEN                
         IF HasDyn AND HasOvl THEN
            ovlIec.GetOvl();
            LinRelTCP(pos1, dynIec1, ovlIec.mOvl);
         ELSIF HasDyn THEN            
            LinRelTCP(pos1, dynIec1);
         ELSIF HasOvl THEN
            ovlIec.GetOvl();
            LinRelTCP(pos1, ,ovlIec.mOvl);
         ELSE
            LinRelTCP(pos1);
         END_IF;
      END_IF;
   
   ELSIF (iCommandNum = TTcPlcCommandDyn) THEN
      Dyn(dynIec1);
      
   ELSIF (iCommandNum = TTcPlcCommandOvl) THEN
      IF ovlIec.GetOvl() THEN
         Ovl(ovlIec.mOvl);
      END_IF;
      
   ELSIF (iCommandNum = TTcPlcCommandSync999) THEN
      WaitMainrunOrCancel(NOT IS_DOPART);   
         
      gROS_Sync999.Sync(999);         
   ELSIF (iCommandNum = TTcPlcCommandSetting) THEN
      IF HasDyn THEN
         Dyn(dynIec1);
      END_IF;
      IF HasOvl THEN
         IF ovlIec.GetOvl() THEN
            Ovl(ovlIec.mOvl);
         END_IF;
      END_IF;
   
   ELSIF (iCommandNum = TTcPlcCommandTool) THEN
      rosTool.x := toolFrameIec.x;
      rosTool.y := toolFrameIec.y;
      rosTool.z := toolFrameIec.z;
      rosTool.a := toolFrameIec.a;
      rosTool.b := toolFrameIec.b;
      rosTool.c := toolFrameIec.c;
      Tool(rosTool);
   
   

   ELSIF (iCommandNum = TTcPlcCommandSetDO) THEN
      SetDO();
      
      
   END_IF;
   
   ExecuteLocked := TRUE;   
   
END_ROUTINE

ROUTINE SetDO()
   VAR
      iCnt   : DINT;
   END_VAR
   
   FOR iCnt := 0 TO gROS_MaxDI - 1 DO  //gROS_MaxDI := 16
      IF IS_MAPPED(DOut[iCnt]) THEN  //必须在示教器上先调用MapDO，来先map一下，否则这一句就不执行
         DOut[iCnt] := CheckBit(digitalOut, iCnt);  
      END_IF;
      
   END_FOR; 
END_ROUTINE


/* CheckBit说明

   Checks if a specific bit is set in the specified word.
   ```
   CheckBit (
   val : LWORD
   bitNr : DINT
   ) : BOOL
   ```

   Parameter:

   |版本|内容介绍|
   |--|--|
   |valBit| Value which shall be checked for the given bit|
   |bitNr|Bit to be checked (least significant bit = bit 0)|

   Example:

   ```
   d :=17 // 16#0011  2#0001 0001
   IF CheckBit(d, 4) THEN     // check bit 4
   Info("Bit 4 is set")    // display result as info
   END_IF
   ```
*/


/*
   在示教器上使用MapDO方法：

   gROS_TC1.MapDO(0, IEC.mybool)  //表明将这里的DOut[0]关联到IEC下的mybool

   使用说明：需要在Codesys中 TC Symbol Configuration中先勾选上变量

   这样，示教器上才能直接索引到IEC（就是工程中）自定义的变量，比如这里的mybool，否则示教器上索引不到IEC的变量


   IEC：工程
   RC：实时的部分，一般是插补等指令或变量
   TC：示教器

*/


ROUTINE MapDO(CONST DONum : DINT;
   BoolVar  : BOOL  
) USER  //USER表示在示教器上，这个函数是可选的

   DOut[DONum] := MAP(BoolVar);

END_ROUTINE


ROUTINE INIT()
   VAR
      strInst  : STRING;    
   END_VAR
   
   WAIT gEquipmentReady; 
   
   strInst := "IEC.ROS." + INSTANCE_NAME(); //和IEC中的ROS_TC取得通讯
   //INSTANCE_NAME 是由示教器中中定义的变量名称觉得的，我们的案例中，示教器上面定义了一个gROS_TC1 : ROSTC，所以此时INSTANCE_NAME就为gROS_TC1
  
  //和IEC的ROS_TC的共享内存交互，用instanceName交互，这就要求两边定义的名字必须一样，比如此例中必须都叫gROS_TC1
  
  //IEC的ROS_TC必须是全局变量，示教器的ROSTC必须是Machine变量
  
   mIecControl := MAPX(strInst); ////这个是IEC和TC都自定义的，IEC：ROS_TRosControlIEC，  TC：TROS_TRosControlIEC
   //mIecControl : MAPTO TROS_TRosControlIEC;
   //TROS_TRosControlIEC比较简单，直接在这下面建立的，并没有新开一个tts
   /*
   TYPE PRIVATE
   TROS_TRosControlIEC : STRUCT    
      Version  : String16;
      CommandNum : DINT;
		Execute : BOOL;
		ExecuteLocked : BOOL;		
		HasDyn   : BOOL;
		HasOvl   : BOOL;
		Ready : BOOL;
		HasError : BOOL;
		HasCartPos  : BOOL;
		HasAxisPos  : BOOL;	
		
      DOut  : LWORD;
      DIn   : LWORD;
   END_STRUCT;   
   
   ROS_TRosControlAbortIEC : STRUCT
      Abort	: BOOL;
      AbortLocked	: BOOL;	
   END_STRUCT; 
   END_TYPE
   */

   
   IF IS_MAPPED(mIecControl) THEN
      Version := MAP(mIecControl.Version);
      CommandNum := MAP(mIecControl.CommandNum);
      Execute := MAP(mIecControl.Execute);
      ExecuteLocked := MAP(mIecControl.ExecuteLocked);
      //Abort := MAP(mIecControl.Abort);
      //AbortLocked := MAP(mIecControl.AbortLocked);
      HasDyn := MAP(mIecControl.HasDyn);
      HasOvl := MAP(mIecControl.HasOvl);
      Ready := MAP(mIecControl.Ready);
      HasError := MAP(mIecControl.HasError);
      HasCartPos := MAP(mIecControl.HasCartPos);
      HasAxisPos := MAP(mIecControl.HasAxisPos);
      
      digitalIn := MAP(mIecControl.DIn);
      digitalOut := MAP(mIecControl.DOut);//可能是5,6,1,8,0等值
   ELSE
      mbEnabled := FALSE;
      Error("Failed to map IEC interface mIecControl struct " + strInst);
      RETURN;
   END_IF;   
   
   mIecControlAbort := MAPX(strInst + ".Abort");//这个是IEC和TC都自定义的，IEC：ROS_TRosControlAbortIEC，  TC：ROS_TRosControlAbortIEC(本文件下定义了)
   IF IS_MAPPED(mIecControlAbort) THEN
      Abort := MAP(mIecControlAbort.Abort);
      AbortLocked := MAP(mIecControlAbort.AbortLocked);
   ELSE
      mbEnabled := FALSE;
      Error("Failed to map IEC interface mIecControlAbort struct " + strInst);
      RETURN;
   END_IF;
   
   (*mDigitalIO := MAPX(strInst + ".digitalIO");
   IF IS_MAPPED(mDigitalIO) THEN
      
   END_IF;*)
   
   
   apIec1.MapToIecTarget(strInst + ".apIec1");//IEC已经有的(TAxesPosition)，不是自定义的
   dynIec1.MapToIecTarget(strInst + ".dynIec1");//IEC已经有的(TDynamic)，不是自定义的
   cpIec1.MapToIecTarget(strInst + ".cpIec1");//IEC已经有的(TCartesianPosition)，不是自定义的
   
   ovlIec.MapToIecTarget(strInst + ".ovlIec");//这个是IEC和TC都自定义的，IEC：TOvlIEC，  TC：OVLIEC 
   
   toolFrameIec.MapToIecTarget(strInst + ".toolFrameIec");//这个是IEC和TC都自定义的，IEC：TCartFrameIEC，  TC：CARTFRAMEIEC 
   
   //cp1.MapToIecTarget(strInst + ".cpIec1");
   
   ApplicationInterface.GetRob(mRob, VAR_NAME(SELF), FALSE);
   
   HasError := UPDATE(mRob.anyErrorPending);
   
   mbEnabled := TRUE;
   
   IF NOT apIec1.CheckIsMapped() THEN
      mbEnabled := FALSE;
      Error("Failed to map IEC interface apIec1 " + strInst);
   END_IF;
   
   IF NOT dynIec1.CheckIsMapped() THEN
      mbEnabled := FALSE;
      Error("Failed to map IEC interface dynIec1 " + strInst);
   END_IF;
   
   IF NOT cpIec1.CheckIsMapped() THEN
      mbEnabled := FALSE;
      Error("Failed to map IEC interface cpIec1 " + strInst);
   END_IF;
   
   IF NOT ovlIec.CheckIsMapped() THEN
      mbEnabled := FALSE;
      Error("Failed to map IEC interface ovlIec " + strInst);
   END_IF;
   
   IF NOT toolFrameIec.CheckIsMapped() THEN
      mbEnabled := FALSE;
      Error("Failed to map IEC interface toolFrameIec " + strInst);
   END_IF;  
   
   
   IF NOT mbEnabled THEN
      RETURN;
   END_IF;
   
   Print("IEC TC interface mapped successfully " + + strInst);
   START AbortMonitor();
   
END_ROUTINE

TYPE
   TTcPlcCommand :(
      TTcPlcCommandInvalid := 0,
      TTcPlcCommandLin := 1,
      TTcPlcCommandOvl := 2,
      TTcPlcCommandDyn := 3,
      TTcPlcCommandWaitIsFinished := 4,
      TTcPlcCommandPTP := 5,
      TTcPlcCommandSync999 := 6,
      TTcPlcCommandSetting := 7,
      TTcPlcCommandTool := 8,
      TTcPlcCommandSetDO := 9,
      TTcPlcCommandReadDI := 10,
      TTcPlcCommandLinRelTCP := 11 //TODO
   );
END_TYPE

TYPE PRIVATE
   TROS_TRosControlIEC : STRUCT    
      Version  : String16;
      CommandNum : DINT;
		Execute : BOOL;
		ExecuteLocked : BOOL;		
		HasDyn   : BOOL;
		HasOvl   : BOOL;
		Ready : BOOL;
		HasError : BOOL;
		HasCartPos  : BOOL;
		HasAxisPos  : BOOL;	
		
      DOut  : LWORD;
      DIn   : LWORD;
   END_STRUCT;   
   
   ROS_TRosControlAbortIEC : STRUCT
      Abort	: BOOL;
      AbortLocked	: BOOL;	
   END_STRUCT;
   
   
END_TYPE

VAR PRIVATE
   mIecControl : MAPTO TROS_TRosControlIEC;
   mIecControlAbort  : MAPTO ROS_TRosControlAbortIEC;
   mApIec1   : AXISPOSIEC;
   mbEnabled   : BOOL;
   //myRobot : MAPTO TRcRobot;
   mRob  : MAPTO RobotInterface;//参考KeMotion KAIRO Expert
   
   //mDigitalIO  : MAPTO ROS_TRosDigitalIO;
END_VAR