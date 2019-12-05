METHOD GetTool
VAR_IN_OUT
	ToolFrame	: TCartFrameIEC;
END_VAR
VAR_INPUT
END_VAR
VAR_OUTPUT
	HasTool	: BOOL;
END_VAR
VAR
	pParams: POINTER TO TRMI_Command;
END_VAR


*****************************


pParams := GetParams('tool');

IF pParams <> 0 AND_THEN pParams^.Num_Params = 6 THEN
	HasTool := TRUE;
	ToolFrame.x := TO_LREAL(pParams^.Parameters[1]);
	ToolFrame.y := TO_LREAL(pParams^.Parameters[2]);
	ToolFrame.z := TO_LREAL(pParams^.Parameters[3]);
	ToolFrame.a := TO_LREAL(pParams^.Parameters[4]);
	ToolFrame.b := TO_LREAL(pParams^.Parameters[5]);
	ToolFrame.c := TO_LREAL(pParams^.Parameters[6]);
	RETURN;
END_IF

HasTool := FALSE;