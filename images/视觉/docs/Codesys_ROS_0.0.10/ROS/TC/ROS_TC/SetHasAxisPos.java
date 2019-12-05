METHOD SetHasAxisPos : BOOL
VAR_INPUT
	HasAxisPos	: BOOL;
END_VAR
VAR_OUTPUT
	//Done	 : BOOL;
END_VAR


*************************

SetHasAxisPos := FALSE;
IF mpTcIECInterface <> 0 THEN
	mpTcIECInterface^.HasAxisPos := HasAxisPos;	
	SetHasAxisPos := TRUE;	
END_IF
