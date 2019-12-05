METHOD SplitParams
VAR_IN_OUT
	Command	: TRMI_Command;
END_VAR
VAR_INPUT
END_VAR
VAR
	
	bIsEnd	: BOOL;
END_VAR
VAR_INST
	tok_param	: UtilTokenizer;
	
	str_tok : STRING;
	
	
END_VAR



*******************************


tok_param.Initialize(ADR(Command.ParamStr), ' ');

Command.Num_Params := 0;

bIsEnd := FALSE;

WHILE NOT bIsEnd DO
	Command.Num_Params := Command.Num_Params + 1;
	str_tok := tok_param.NextToken(bEnd => bIsEnd);
	Command.Parameters[Command.Num_Params] := str_tok;
	
	
	//str_tok := tok_param.NextToken(bEnd => bIsEnd);
	
END_WHILE