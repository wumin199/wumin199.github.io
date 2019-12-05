TYPE TRMI_Command :
STRUCT
	CmdStr	: STRING;
	Num_Params	: DINT;
	ParamStr	: STRING;	
	Parameters	: ARRAY[1..20] OF STRING;
END_STRUCT
END_TYPE
