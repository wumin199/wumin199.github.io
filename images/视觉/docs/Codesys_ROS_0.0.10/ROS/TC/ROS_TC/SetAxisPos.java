METHOD SetAxisPos : BOOL
VAR_IN_OUT CONSTANT
	ap	: TAxesPosition;
END_VAR


******************

SetAxisPos := FALSE;
IF mpApIec1 <> 0 THEN
	mpApIec1^ := ap;	
	SetAxisPos := TRUE;
END_IF
