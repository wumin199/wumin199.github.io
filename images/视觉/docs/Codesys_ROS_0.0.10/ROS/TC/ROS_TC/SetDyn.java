METHOD SetDyn : BOOL
VAR_IN_OUT CONSTANT
	dyn	: TDynamic;
END_VAR


*********************

SetDyn := FALSE;
IF mpDynIec1 <> 0 THEN
	mpDynIec1^ := dyn;
	SetDyn := TRUE;
END_IF
