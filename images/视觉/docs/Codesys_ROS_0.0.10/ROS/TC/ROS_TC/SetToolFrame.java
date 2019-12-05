METHOD SetToolFrame : BOOL
VAR_IN_OUT CONSTANT
	ToolFrame	: TCartFrameIEC;
END_VAR
VAR_INPUT
END_VAR


********************

SetToolFrame := FALSE;
IF mpTcIECInterface <> 0 THEN
	mpToolFrameIec^ := ToolFrame;
	SetToolFrame := TRUE;
END_IF