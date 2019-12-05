METHOD SetExecuteLocked : BOOL
VAR_INPUT
	ExecuteLocked	: BOOL;
END_VAR
VAR_OUTPUT
	//Done	 : BOOL;
END_VAR



*****************


SetExecuteLocked := FALSE;
IF mpTcIECInterface <> 0 THEN
	mpTcIECInterface^.ExecuteLocked := ExecuteLocked;
	SetExecuteLocked := TRUE;	
END_IF

