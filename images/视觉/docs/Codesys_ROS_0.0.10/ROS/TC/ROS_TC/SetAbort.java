METHOD SetAbort : BOOL
VAR_INPUT
	Abort	: BOOL;
END_VAR
VAR_OUTPUT
	//Done	 : BOOL;
END_VAR


****************************

SetAbort := FALSE;
IF mpTcIECAbortInterface <> 0 THEN
	mpTcIECAbortInterface^.Abort := Abort;
	SetAbort := TRUE;
END_IF