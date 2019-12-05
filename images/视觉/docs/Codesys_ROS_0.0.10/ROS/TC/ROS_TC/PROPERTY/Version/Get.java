VAR
END_VAR


***********

IF ITFMapped THEN
	Version := mpTcIECInterface^.Version;
ELSE
	Version := 'ERROR';
END_IF
