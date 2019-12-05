FUNCTION_BLOCK RMI_Command
VAR_INPUT	
END_VAR
VAR_OUTPUT
END_VAR
VAR
	//fbRegex	: RMI_Regex;	
	
	//bInitDone	: BOOL;
	
	//strSplitCommands	: STRING(255) := '([a-z ]*:?.+?;)';	//Used to split up the full string into segments.  'blah : 1 2 3;blah2 : 4 5 6;' =>  ['blah : 1 2 3', 'blah2 : 4 5 6']
//	strSplitParams	: STRING(255) := '.*: (.*);';
	
	num_entries_	: DINT;
	entries_	: ARRAY[1..10] OF TRMI_Command;
	
	//tok_	: UtilTokenizer;
	
END_VAR



****************

Init();