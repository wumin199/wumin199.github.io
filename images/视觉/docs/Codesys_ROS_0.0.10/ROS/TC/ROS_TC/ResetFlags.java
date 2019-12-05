METHOD ResetFlags : BOOL
VAR_IN_OUT CONSTANT

END_VAR



**************************


ResetFlags := FALSE;
IF SetExecute(FALSE) AND_THEN SetExecuteLocked(FALSE) (*AND_THEN SetAbort(FALSE)*) AND_THEN SetHasDyn(FALSE) 
	AND_THEN SetHasCartPos(FALSE) AND_THEN SetHasAxisPos(FALSE) AND_THEN SetHasOvl(FALSE) THEN 
	
	ResetFlags := TRUE;
END_IF

