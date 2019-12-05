METHOD SetOvl : BOOL
VAR_IN_OUT CONSTANT
	ovl	: TOvlIEC;
END_VAR



********************

SetOvl := FALSE;
IF mpOvlIec <> 0 THEN
	mpOvlIec^ := ovl;
	SetOvl := TRUE;
END_IF
