METHOD SetExecute : BOOL
VAR_INPUT
	Execute	: BOOL;
END_VAR
VAR_OUTPUT
	//Done	 : BOOL;
END_VAR



********************

SetExecute := FALSE;
IF mpTcIECInterface <> 0 THEN
	mpTcIECInterface^.Execute := Execute;
	SetExecute := TRUE;	
END_IF
