FUNCTION_BLOCK FB_TcAbort
VAR_IN_OUT
	RosTC	: ROS_TC;
END_VAR
VAR_INPUT
	Execute	: BOOL;
END_VAR
VAR_OUTPUT
	Done	: BOOL;
END_VAR
VAR
	iState	: DINT;
END_VAR


*******************************

IF Execute THEN
	CASE iState OF
	0:
		RosTC.SetExecute(FALSE);
		RosTC.SetExecuteLocked(FALSE);
		RosTC.SetAbortLocked(FALSE);
		RosTC.SetAbort(TRUE);
		iState := 10;
	10:
		Done := RosTC.AbortLocked;				
	END_CASE
ELSE
	IF iState = 10 THEN
		RosTC.SetAbort(FALSE);
	END_IF
	Done := FALSE;
	iState := 0;	
END_IF
