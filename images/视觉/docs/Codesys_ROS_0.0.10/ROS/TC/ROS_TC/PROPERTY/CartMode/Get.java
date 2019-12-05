VAR
END_VAR


******************************

IF mpCpIec1 <> 0 THEN
	CartMode := mpCpIec1^.Mode;
ELSE
	CartMode := -9999;
END_IF