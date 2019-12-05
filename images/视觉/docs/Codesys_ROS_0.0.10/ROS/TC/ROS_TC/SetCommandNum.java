METHOD SetCommandNum : BOOL
VAR_INPUT
	CommandNum	: DINT;
END_VAR
VAR_OUTPUT
	Done	 : BOOL;
END_VAR


************

SetCommandNum := FALSE;
Done := FALSE;
IF mpTcIECInterface <> 0 THEN
	mpTcIECInterface^.CommandNum := CommandNum;
	SetCommandNum := TRUE;
	Done := TRUE;
END_IF