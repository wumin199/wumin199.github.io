METHOD SetHasCartPos : BOOL
VAR_INPUT
	HasCartPos	: BOOL;
END_VAR
VAR_OUTPUT
	//Done	 : BOOL;
END_VAR


************************

SetHasCartPos := FALSE;
IF mpTcIECInterface <> 0 THEN
	mpTcIECInterface^.HasCartPos := HasCartPos;	
	SetHasCartPos := TRUE;	
END_IF
