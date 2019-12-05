FUNCTION SharedRealArrayToString : STRING(255)
VAR_IN_OUT
	RealArray	: ARRAY[*] OF SHARED_REAL;
END_VAR
VAR_INPUT
	NumFields	: DINT;
	FractionalDigits	: UINT;
END_VAR
VAR
	lower	: DINT;
	idx	: DINT;
	lrTemp	: LREAL;
END_VAR


****************

SharedRealArrayToString := '';

lower := LOWER_BOUND(RealArray, 1);

IF NumFields < 1 OR (NumFields > UPPER_BOUND(RealArray, 1) - idx) THEN
	RETURN;
END_IF


//SharedRealArrayToString := LRealToStringDigits(RealArray[lower], FractionalDigits);
lrTemp := RealArray[lower];
SharedRealArrayToString := LRealToStringDigits(K_Math.RoundDigits(lrTemp, FractionalDigits), FractionalDigits);
FOR idx := 1 TO NumFields - 1 DO
	SharedRealArrayToString := CONCAT(SharedRealArrayToString, ' ');	
	//SharedRealArrayToString := CONCAT(SharedRealArrayToString, LRealToStringDigits(RealArray[lower + idx], FractionalDigits));
	lrTemp := RealArray[lower + idx];
	SharedRealArrayToString := CONCAT(SharedRealArrayToString, LRealToStringDigits(K_Math.RoundDigits(lrTemp, FractionalDigits), FractionalDigits));
END_FOR


	