FUNCTION_BLOCK FB_Dyn
VAR_IN_OUT
	RosTC	: ROS_TC;
	Dyn	: TDynamic;
END_VAR
VAR_INPUT
	Execute	: BOOL;
END_VAR
VAR_OUTPUT
	ExecuteLocked	: BOOL;
END_VAR
VAR
	iState	 : DINT;
END_VAR


******************************************

IF Execute THEN
	CASE iState OF
	0:				
		ExecuteLocked := FALSE;
		IF RosTC.Busy THEN
			RETURN;
		END_IF
		
		RosTC.ResetFlags();
		
		RosTC.SetExecuteLocked(FALSE);		
		RosTC.SetDyn(Dyn);
		
		RosTC.SetCommandNum(CommandNum := TTcPlcCommand.TTcPlcCommandDyn);
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