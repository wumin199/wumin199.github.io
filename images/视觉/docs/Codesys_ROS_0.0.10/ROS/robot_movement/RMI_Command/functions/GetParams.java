//Get the parameters for this command/segment
METHOD GetParams : POINTER TO TRMI_Command
VAR_INPUT
	Command	: STRING;
END_VAR
VAR
	idx	: DINT;
END_VAR



**********************


GetParams := 0;

IF Command = '' THEN
	GetParams := ADR(entries_[1]);
	RETURN;
END_IF

FOR idx := 1 TO num_entries_ DO
	IF entries_[idx].CmdStr = Command THEN
		GetParams := ADR(entries_[idx]);
		RETURN;
	END_IF
END_FOR