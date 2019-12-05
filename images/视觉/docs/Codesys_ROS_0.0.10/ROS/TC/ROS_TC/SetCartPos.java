METHOD SetCartPos : BOOL
VAR_IN_OUT CONSTANT
	cp	: TCartesianPosition;
END_VAR


*******************

SetCartPos := FALSE;
IF mpApIec1 <> 0 THEN
	mpCpIec1^ := cp;
	SetCartPos := TRUE;
END_IF
