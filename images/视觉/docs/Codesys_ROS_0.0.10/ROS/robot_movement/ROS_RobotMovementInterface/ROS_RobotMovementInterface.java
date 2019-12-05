FUNCTION_BLOCK ROS_RobotMovementInterface
VAR_INPUT
	Port	: UINT := 30000;
END_VAR
VAR_IN_OUT
	Transformer	: ROS_BasicTransformer;
	Robot	: AXES_GROUP_REF;
	TC	:  ROS_TC;	
END_VAR	
VAR_OUTPUT
END_VAR
VAR
	fbServer	: TcpServer;
	
	fbRecvLine	: TcpRecvLine;
	
	
	
	fbSend	: TcpSendTxt;

	flags_	: TRMI_Flags;	//All the random loose flags are getting out of control.
	
	fbSetting	: FB_Setting_Flex;

	
	fbDyn	: FB_Dyn;

	fbOvl	: FB_Ovl;
	
	
	fbLinFlex	: FB_LIN_Flex;
	fbPTPFlex	: FB_PTP_Flex;	

	fbWaitIsFinished	: FB_WaitIsFinished;
	
	
	fbAbort	: FB_TcAbort;
	
	fbSync	: FB_Sync999;
	
	fbTool	: FB_Tool;
	toolFrame	: TCartFrameIEC;

   fbSetDO  : FB_SetDO;
   doutValue   : LWORD;
   
   
	bSend	: BOOL;
	
	bConnect: BOOL := TRUE;
	bConnected: BOOL;

	recvText: TText;
	
	command	: TText;
	

	
	
	strSend : TText;

	
	

	robPos: TAxesPosition;
	posRos: REAL_VECT;
	
	
	strUnknown: TText;
	
	
	
	
	
	rosPtpCmd	: REAL_VECT;
	
	cartData	: ARRAY[1..20] OF SHARED_REAL;
	
	
	
	apItf: TAxesPosition;
	cpItf: TCartesianPosition;
	
	pApItf	: POINTER TO TAxesPosition;
	pCpItf	: POINTER TO TCartesianPosition;
	
	
	Quat : K_Math.TQuaternion;	
	
	oriFromQuat: TVector3;
	rotMat: TMatrix33;
	
	dyn_	: TDynamic := (velAxis := 20, accAxis := 20, decAxis := 20, jerkAxis := 80, 
			vel := 3000, acc := 300000, dec := 300000, jerk := 1000000, 
			velOri := 10000, accOri := 100000, decOri := 100000, jerkOri := 1000000);
			
	ovl_	: TOvlIEC;
			
	pDyn_	: POINTER TO TDynamic;		
	pOvl_	: POINTER TO TOvlIEC;
	
	
	curCartPos: TCartesianPosition;
	iSent: DINT;
   
   
	
	
	
	active_command_	: RMI_Command;//RMI_Command是个类，专门处理从ros发过来的一条command（一条命令只包含如一个PTP或者一个Lin等，不会包含几条命令），用来提取其中的Dyn,Ovl,Tool等信息
	//dynJntStateGrp	: ROS_DynamicJointStateGrp;
	pParams: POINTER TO TRMI_Command;
	bHasDynamic: BOOL;
	commandLine: TText;
	
	ros_dyn_data_ : REAL_VECT;
	
	vel_axis	: DINT(0..100);
	acc_axis	: DINT(0..100);
	bAbort: BOOL;
	
	
	
	bHasOvl: BOOL;
	bHasTool	: BOOL;
	
	min_acc	: DINT := 10;
	min_vel	: DINT := 5;
   set_jerk : DINT := 30;
END_VAR



******************************

fbServer(
	Connect:= bConnect AND NOT fbServer.Error, 
	Port:= Port, 
	Timeout:= T#5S, 
	Options:= , 
	Connected=> bConnected, 
	Busy=> , 
	Error=> , 
	ErrorID=> );
	
	
fbRecvLine(
	Enable:= bConnected AND NOT fbRecvLine.Error, 
	SkipEmptyLines:= , 
	Text=> recvText, 
	Busy=> , 
	BufferFull=> , 
	Done=> , 
	Error=> , 
	ErrorID=> , 
	Conn:= fbServer);
	
IF NOT fbServer.Connected THEN
	bSend := FALSE;
	
	resetFlags();	
END_IF

TC();


	
IF fbRecvLine.Done THEN
	commandLine := recvText;
	
	active_command_.ParseCommand(commandLine);
	
	command := active_command_.CommandStr;
	
	IF command = 'get joint position' THEN	//Send joint positions in ROS units
		(*strSend := '';
		robPos := Robot.AxesPosition;
		Transformer.PosKebaToROS(InKeba := robPos, OutROS := posRos);
		
		strSend := SharedRealArrayToString(RealArray := posRos, NumFields := Transformer.NumMainJoints + Transformer.NumAuxJoints, 4); 
		
		strSend := CONCAT(strSend, '$n');		
		bSend := TRUE;*)
		strSend := getJointPosition(Transformer, Robot);
		bSend := TRUE;
	ELSIF command = 'get status' THEN
		strSend := getJointPosition(Transformer, Robot);
      strSend := CONCAT(strSend, getJointVel(Transformer, Robot));
		strSend := CONCAT(strSend, getToolFrame(TRUE, Robot));
		bSend := TRUE;
	ELSIF command = 'get joint torque' THEN	//Todo
		strSend := '0.0 0.1 0.2 0.3 0.4 0.5 0.6';
		strSend := CONCAT(strSend, '$n');
		bSend := TRUE;
		
	ELSIF command = 'get cartesian force' THEN	//Todo, might not be possible
		strSend := '0.0 0.1 0.2 0.3 0.4 0.5';
		strSend := CONCAT(strSend, '$n');
		bSend := TRUE;	

	ELSIF command = 'get tool frame ros' THEN	//Use ROS units (M/RAD)
		
		strSend := getToolFrame(TRUE, Robot);
		bSend := TRUE;
		(*curCartPos := Robot.CartesianPosition;		
		
		posRos[1] := TO_REAL(curCartPos.X / 1000.0);
		posRos[2] := TO_REAL(curCartPos.Y / 1000.0);
		posRos[3] := TO_REAL(curCartPos.Z / 1000.0);
		posRos[4] := RAD(TO_REAL(curCartPos.A));
		posRos[5] := RAD(TO_REAL(curCartPos.B));
		posRos[6] := RAD(TO_REAL(curCartPos.C));
		
		strSend := SharedRealArrayToString(RealArray := posRos, NumFields := 6, 4);		
		strSend := CONCAT(strSend, '$n');
	
		bSend := TRUE;	*)
	
	ELSIF command = 'get tool frame' THEN //Use Keba units (mm/deg)
		curCartPos := Robot.CartesianPosition;		
		
		posRos[1] := TO_REAL(curCartPos.X);
		posRos[2] := TO_REAL(curCartPos.Y);
		posRos[3] := TO_REAL(curCartPos.Z);
		posRos[4] := TO_REAL(curCartPos.A);
		posRos[5] := TO_REAL(curCartPos.B);
		posRos[6] := TO_REAL(curCartPos.C);
		
		strSend := SharedRealArrayToString(RealArray := posRos, NumFields := 6, 4);		
		strSend := CONCAT(strSend, '$n');
	
		bSend := TRUE;
	ELSIF command = 'get flange frame' THEN
		strSend := '0.0 0.1 0.2 0.3 0.4 0.5';
		strSend := CONCAT(strSend, '$n');
		bSend := TRUE;	
	(*ELSIF command = 'get status' THEN
		strSend := processRobotStatus(Robot := Robot, EStopActive := FALSE, ErrorCode := 0);
		
		strSend := CONCAT(strSend, '$n');
		bSend := TRUE;*)
	ELSIF command = 'get version' THEN
		strSend := GVL_ROS.g_RMILibVersion;
		bSend := TRUE;		
	ELSIF command = 'sync' THEN
		flags_.bExecSync := TRUE;
	ELSIF command = 'abort' THEN
		bAbort := TRUE;
	ELSIF FIND(command, 'wait') = 1 THEN
		IF FIND(command, 'is_finished') > 0 THEN
			flags_.bExecWaitIsFinished := TRUE;	
		END_IF	
		
	//New command format.
	ELSIF FIND(command, 'ptp') = 1 THEN	//Check at the start of the command
		resetPointers();
		IF FIND(command, 'joints') > 0 THEN
			processJoints();
		ELSIF FIND(command, 'quaternion') > 0 THEN
			processQuaternion(Robot);
		ELSIF FIND(command, 'euler_intrinsic_zyx') > 0 THEN
			processEuler();
		END_IF		
	
		flags_.bExecPTPFlex := TRUE;
		
	ELSIF FIND(command, 'lin') = 1 THEN //Check at the start of the command
		resetPointers();
		
		IF FIND(command, 'joints') > 0 THEN
			processJoints();
		ELSIF FIND(command, 'quaternion') > 0 THEN
			processQuaternion(Robot);
		ELSIF FIND(command, 'euler_intrinsic_zyx') > 0 THEN
			processEuler();
		END_IF		
			
		flags_.bExecLinFlex := TRUE;
		
	ELSIF (command = 'setting') THEN
		resetPointers();
		
		active_command_.GetDyn(HasDyn => bHasDynamic, dyn := dyn_);
		IF bHasDynamic THEN
			pDyn_ := ADR(dyn_);
			flags_.bExecSetting := TRUE;
		END_IF
		
		active_command_.GetOvl(HasOvl => bHasOvl, ovl := ovl_);
		IF bHasOvl THEN
			pOvl_ := ADR(ovl_);
			flags_.bExecSetting := TRUE;
		END_IF
		
		IF NOT (bHasDynamic OR bHasOvl) THEN
			strSend := 'error no setting specified$n';
			bSend := TRUE;
		END_IF		
	ELSIF (command = 'ping') THEN
		strSend := 'pong$n';
		bSend := TRUE;
		
	ELSIF (command = 'frame') THEN
		active_command_.GetTool(ToolFrame := toolFrame, HasTool => bHasTool);
		IF bHasTool THEN 
			flags_.bExecTool := TRUE;
		END_IF
		
		IF NOT bHasTool THEN
			strSend := 'error no frame specified$n';
			bSend := TRUE;
		END_IF
	
   ELSIF (command = 'set do') THEN
      doutValue := STRING_TO_LWORD(active_command_.GetParams('')^.Parameters[1]);//active_command_.GetParams('')返回值是个POINTER TO TRMI_Command，最后的doutValue是个doutValue值，比如1,8等值
	   flags_.bExecSetDO := TRUE;//这里设置为True，会造成下面对应的FB上升沿，执行相关的功能
	ELSIF FIND(command, 'test') = 1 THEN
		strSend := 'done$n';
		bSend := TRUE;		
	ELSE
		//Unknown command, fix it.  Should I return someone like 'error unknown' or just let it get stuck?
		strSend := 'error unknown command$n';
		bSend := TRUE;
		strUnknown := command; 
	END_IF	
END_IF

//If it isn't ready and there was some exec flag set, reset the flags and send error.
//It's OK to handle gets but not commands that set flags.
IF NOT TC.Ready OR Robot.HasError THEN
	IF AnyExecSet OR bAbort THEN		
		resetFlags();
		bAbort := FALSE;	//AbortMonitor doesn't seem to be able to stop the robot when it's not auto extern
		strSend := 'error$n';
		bSend := TRUE;
	END_IF
END_IF

//Abort can be set by other FBs so check the TC flag.  
IF AnyExecSet AND TC.Abort THEN
	resetFlags();
	strSend := 'error aborting$n';
	bSend := TRUE;
END_IF

//Disable everything else on abort
IF bAbort THEN
	resetFlags();
END_IF

fbAbort(RosTC:= TC, Execute:= bAbort, Done=> );

IF fbAbort.Done THEN
	bAbort := FALSE;
	
	strSend := 'aborted';
	strSend := CONCAT(strSend, '$n');
	bSend := TRUE;
END_IF



(*
fbDyn(RosTC:= TC, Dyn:= dyn_, Execute:= flags_.bExecDyn, ExecuteLocked=> );

IF fbDyn.ExecuteLocked THEN
	flags_.bExecDyn := FALSE;
	strSend := 'done$n';
	bSend := TRUE;
	
	fbDyn(RosTC:= TC, Dyn:= dyn_, Execute:= flags_.bExecDyn, ExecuteLocked=> );
END_IF
*)

fbSetting(
	RosTC:= TC, 
	pDyn:= pDyn_, 
	pOvl:= pOvl_, 
	Execute:= flags_.bExecSetting, 
	ExecuteLocked=> , 
	Error=> );
	
IF fbSetting.ExecuteLocked THEN
	resetPointers();
	
	flags_.bExecSetting := FALSE;
	
	IF fbSetting.Error THEN
		strSend := 'error$n';
	ELSE
		strSend := 'done$n';			
	END_IF		
	
	fbSetting(
		RosTC:= TC, 
		pDyn:= pDyn_, 
		pOvl:= pOvl_, 
		Execute:= flags_.bExecSetting, 
		ExecuteLocked=> , 
		Error=> );
	
	bSend := TRUE;
END_IF


fbPTPFlex(
	RosTC:= TC, 
	pCp:= pCpItf, 
	pAp:= pApItf, 
	pDyn:= pDyn_, 
	pOvl := pOvl_,
	Execute:= flags_.bExecPTPFlex, 
	ExecuteLocked=> , 
	Error=> );
	
IF fbPTPFlex.ExecuteLocked THEN
	flags_.bExecPTPFlex := FALSE;
	
	IF fbPTPFlex.Error THEN
		strSend := 'error$n';
	ELSE
		strSend := 'done$n';			
	END_IF
	
	fbPTPFlex(
		RosTC:= TC, 
		pCp:= 0, 
		pAp:= 0, 
		pDyn:= 0, 
		Execute:= flags_.bExecPTPFlex, 
		ExecuteLocked=> , 
		Error=> );
	
	bSend := TRUE;
END_IF

fbLinFlex(
	RosTC:= TC, 
	pCp:= pCpItf, 
	pAp:= pApItf, 
	pDyn:= pDyn_, 
	pOvl := pOvl_,
	Execute:= flags_.bExecLinFlex, 
	ExecuteLocked=> , 
	Error=> );
	
IF fbLinFlex.ExecuteLocked THEN	
	flags_.bExecLinFlex := FALSE;
	
	IF fbPTPFlex.Error THEN
		strSend := 'error$n';
	ELSE
		strSend := 'done$n';			
	END_IF
	
	fbLinFlex(
		RosTC:= TC, 
		pCp:= 0, 
		pAp:= 0, 
		pDyn:= 0, 
		pOvl := 0,
		Execute:= flags_.bExecLinFlex, 
		ExecuteLocked=> , 
		Error=> );
	
	bSend := TRUE;
END_IF


fbSync(RosTC:= TC, Execute:= flags_.bExecSync, ExecuteLocked=> );

IF fbSync.ExecuteLocked THEN
	flags_.bExecSync := FALSE;
	fbSync(RosTC:= TC, Execute:= flags_.bExecSync, ExecuteLocked=> );
	strSend := 'done';
	strSend := CONCAT(strSend, '$n');
	bSend := TRUE;	
END_IF

fbWaitIsFinished(RosTC:= TC, Execute := flags_.bExecWaitIsFinished, ExecuteLocked=> );

IF fbWaitIsFinished.ExecuteLocked THEN
	flags_.bExecWaitIsFinished := FALSE;
	fbWaitIsFinished(RosTC:= TC, Execute := flags_.bExecWaitIsFinished, ExecuteLocked=> );
	
	strSend := 'done';
	strSend := CONCAT(strSend, '$n');
	bSend := TRUE;	
END_IF


fbTool(
	RosTC:= TC, 
	ToolFrame:= toolFrame, 
	Execute:= flags_.bExecTool, 
	ExecuteLocked=> , 
	Error=> );
	
IF fbTool.ExecuteLocked THEN
	flags_.bExecTool := FALSE;
	fbTool(
		RosTC:= TC, 
		ToolFrame:= toolFrame, 
		Execute:= flags_.bExecTool);		
		
	strSend := 'done';
	strSend := CONCAT(strSend, '$n');
	bSend := TRUE;		
END_IF


fbSetDO(
	RosTC:= TC, 
	DOut:= doutValue, 
	Execute:= flags_.bExecSetDO, //上面接受到set do命令后，会置位flags_.bExecSetDO，从而造成上升沿
	ExecuteLocked=> , 
	Error=> );
	
IF fbSetDO.ExecuteLocked THEN
	flags_.bExecSetDO := FALSE;//复位的意思
	fbSetDO(
      RosTC:= TC, 
      DOut:= doutValue, 
      Execute:= flags_.bExecSetDO, 
      ExecuteLocked=> , 
      Error=> );	
		
	strSend := 'done';//返回完成信息给ros
	strSend := CONCAT(strSend, '$n');
	bSend := TRUE;		
END_IF

IF bSend AND_THEN LEN(strSend) > 0 AND_THEN RIGHT(strSend, 1) <> '$n' THEN
	strSend := CONCAT(strSend, '$n');
END_IF


fbSend(
	Enable:= bSend AND fbServer.Connected, 
	Text:= strSend, 
	Delim:= , 
	Done=> , 
	Error=> , 
	ErrorID=> , 
	Conn:= fbServer);
	
IF fbSend.Done THEN
	iSent := iSent + 1;
	bSend := FALSE;
	fbSend(
		Enable:= bSend AND fbServer.Connected, 
		Text:= strSend, 
		Delim:= , 
		Done=> , 
		Error=> , 
		ErrorID=> , 
		Conn:= fbServer);
END_IF
