METHOD GetDyn
VAR_IN_OUT
	dyn	: TDynamic;
END_VAR
VAR_INPUT
END_VAR
VAR_OUTPUT
	HasDyn	: BOOL;
END_VAR

VAR
	pParams: POINTER TO TRMI_Command;
END_VAR



**************************


pParams := GetParams('dyn');
IF pParams = 0 OR_ELSE pParams^.Num_Params <> 12 THEN
	HasDyn := FALSE;
	RETURN;
END_IF

dyn.velAxis := TO_DINT(pParams^.Parameters[1]);
dyn.accAxis := TO_DINT(pParams^.Parameters[2]);
dyn.decAxis := TO_DINT(pParams^.Parameters[3]);
dyn.jerkAxis := TO_DINT(pParams^.Parameters[4]);

dyn.vel := TO_REAL(pParams^.Parameters[5]);
dyn.acc := TO_REAL(pParams^.Parameters[6]);
dyn.dec := TO_REAL(pParams^.Parameters[7]);
dyn.jerk := TO_REAL(pParams^.Parameters[8]);

dyn.velOri := TO_REAL(pParams^.Parameters[9]);
dyn.accOri := TO_REAL(pParams^.Parameters[10]);
dyn.decOri := TO_REAL(pParams^.Parameters[11]);
dyn.jerkOri := TO_REAL(pParams^.Parameters[12]);	


HasDyn := TRUE;
