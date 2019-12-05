FUNCTION ROS_ParamsToRealArray : BOOL
VAR_IN_OUT
	
	RealArray	: ARRAY[*] OF SHARED_REAL;
END_VAR
VAR_INPUT
	Cmd	: POINTER TO TRMI_Command;
END_VAR
VAR
	lower	: DINT;
	upper	: DINT;
	num_items	: DINT;
	idx	: DINT;
END_VAR


********************


lower := LOWER_BOUND(RealArray, 1);
upper := UPPER_BOUND(RealArray, 1);

num_items := upper - lower + 1;	//Max number of items that can actually be converted

//Check if the number of params is larger than the array size
IF num_items < Cmd^.Num_Params THEN	
	ROS_ParamsToRealArray := FALSE;
ELSE
	num_items := Cmd^.Num_Params;	//Array count >= num of param entries
	ROS_ParamsToRealArray := TRUE;
END_IF

FOR idx := 0 TO num_items - 1 DO
	RealArray[lower + idx] := TO_REAL(Cmd^.Parameters[idx + 1]);
END_FOR
