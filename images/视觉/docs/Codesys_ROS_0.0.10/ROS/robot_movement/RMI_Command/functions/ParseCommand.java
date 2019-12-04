METHOD ParseCommand
VAR_IN_OUT
	Command	: STRING(255);
END_VAR
VAR_INPUT
	
END_VAR
VAR_INST
	tok_entries	: UtilTokenizer;
	tok_each_entry	: UtilTokenizer;
	
	str_entry	: STRING(255);
	str_params : STRING(255);
END_VAR

VAR
	isEndEntries: BOOL;
	isEndThisEntry: BOOL;
	str_cmd: STRING(256);
	bInitTok: BOOL;
END_VAR



***************************


num_entries_ := 0;

StrToLowerA(ADR(Command));

//Return if no full commands were found
IF FIND(Command, ';') <= 0 THEN
	RETURN;
END_IF

isEndEntries := FALSE;

bInitTok := tok_entries.Initialize(pstr := ADR(Command), strDelims := ';');

WHILE NOT isEndEntries DO	
	num_entries_ := num_entries_ + 1;
	str_entry := tok_entries.NextToken(bEnd => isEndEntries);
	
	entries_[num_entries_].Num_Params := 0;
	entries_[num_entries_].ParamStr := '';	

	//IF FIND(str_entry, ':') > 0 THEN	The tokenizer will return the whole command if the delim doesn't exist
	//This was blocking commands without parameters from running.
	tok_each_entry.Initialize(pstr := ADR(str_entry), strDelims := ':');
	
	//Get the command before the :
	str_cmd := tok_each_entry.NextToken(bEnd => isEndThisEntry);
	str_cmd := K_Utils.TrimString(Str := str_cmd);
	entries_[num_entries_].CmdStr := str_cmd;		
	
	//Get the pararms after the :
	IF NOT isEndThisEntry THEN
		str_params := tok_each_entry.NextToken(bEnd => isEndThisEntry);
		str_params := K_Utils.TrimString(Str := str_params);		
		entries_[num_entries_].ParamStr := str_params;			
		SplitParams(Command := entries_[num_entries_]);		
	END_IF					
	//END_IF
	
	
	
	
	//str_entry := tok_entries.NextToken(bEnd => isEndEntries);
	
END_WHILE
