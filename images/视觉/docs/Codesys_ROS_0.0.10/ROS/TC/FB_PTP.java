FUNCTION_BLOCK FB_PTP
VAR_IN_OUT
	RosTC	: ROS_TC;
END_VAR
VAR_IN_OUT CONSTANT
	ap	: TAxesPosition;
END_VAR
VAR_INPUT
	pDyn	: POINTER TO TDynamic;	
	Execute	: BOOL;		
END_VAR
VAR_OUTPUT
	ExecuteLocked	: BOOL;
END_VAR
VAR
	iState	: DINT;
END_VAR



***********************

IF Execute THEN
	CASE iState OF
	0:
		ExecuteLocked := FALSE;
		IF RosTC.Busy THEN
			RETURN;
		END_IF
		
		RosTC.ResetFlags();
		
		IF pDyn <> 0 THEN
			RosTC.SetDyn(pDyn^);
			RosTC.SetHasDyn(TRUE);		
		END_IF
		
		//RosTC.SetExecuteLocked(FALSE);
		RosTC.SetAxisPos(ap);
		RosTC.SetHasAxisPos(TRUE);
		RosTC.SetCommandNum(CommandNum := TTcPlcCommand.TTcPlcCommandPTP);
		RosTC.SetExecute(TRUE);
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
END_IF



