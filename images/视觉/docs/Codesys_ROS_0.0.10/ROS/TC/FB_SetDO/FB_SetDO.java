FUNCTION_BLOCK FB_SetDO
VAR_IN_OUT
	RosTC	: ROS_TC;
END_VAR
VAR_INPUT
	DOut  : LWORD;
	Execute	: BOOL;		
END_VAR
VAR_OUTPUT
	ExecuteLocked	: BOOL;
	Error	: BOOL;
END_VAR
VAR
	iState	: DINT;
	iCmdNum_	: DINT;	
END_VAR


****************************

IF Execute THEN
	CASE iState OF
	0:
		ExecuteLocked := FALSE;
		IF RosTC.Busy THEN
			RETURN;
		END_IF		
		
		//Error := Error OR ((pDyn = 0) AND (pOvl = 0));
		IF Error THEN
			iState := 40;
			ExecuteLocked := TRUE;
			RETURN;
		END_IF
		
		RosTC.ResetFlags();
      
      RosTC.SetDO(DOut);//把DOut的值，如1,8,5,6的值传递给示教器的rostc
						
		
		//RosTC.SetCartPos(cp);
		RosTC.SetCommandNum(CommandNum := iCmdNum_);//给示教器发送命令TTcPlcCommandSetDO
		RosTC.SetExecute(TRUE);//设置ROS_TC状态为busy，避免其他命令的执行
		iState := 10;
	10:
		ExecuteLocked := RosTC.ExecuteLocked;
		IF ExecuteLocked THEN
			iState := 40;
		END_IF
	END_CASE
ELSE
	iState := 0;
	ExecuteLocked := FALSE;
	Error := FALSE;
END_IF



