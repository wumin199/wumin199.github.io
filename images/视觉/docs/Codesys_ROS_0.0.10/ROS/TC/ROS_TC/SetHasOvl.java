METHOD SetHasOvl : BOOL
VAR_INPUT
	HasOvl	: BOOL;
END_VAR
VAR_OUTPUT
	//Done	 : BOOL;
END_VAR


**********************

SetHasOvl := FALSE;
IF mpTcIECInterface <> 0 THEN
	mpTcIECInterface^.HasOvl := HasOvl;	
	SetHasOvl := TRUE;	
END_IF
