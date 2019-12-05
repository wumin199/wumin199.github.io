METHOD SetAbortLocked : BOOL
VAR_INPUT
	AbortLocked	: BOOL;
END_VAR
VAR_OUTPUT
	//Done	 : BOOL;
END_VAR


******************************

SetAbortLocked := FALSE;
IF mpTcIECAbortInterface <> 0 THEN
	mpTcIECAbortInterface^.AbortLocked := AbortLocked;
	SetAbortLocked := TRUE;
END_IF