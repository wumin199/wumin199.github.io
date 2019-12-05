METHOD SetHasDyn : BOOL
VAR_INPUT
	HasDyn	: BOOL;
END_VAR
VAR_OUTPUT
	//Done	 : BOOL;
END_VAR


*************************

SetHasDyn := FALSE;
IF mpTcIECInterface <> 0 THEN
	mpTcIECInterface^.HasDyn := HasDyn;	
	SetHasDyn := TRUE;	
END_IF
