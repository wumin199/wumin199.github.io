METHOD GetOvl
VAR_IN_OUT
	ovl	: TOvlIEC;
END_VAR
VAR_INPUT
END_VAR
VAR_OUTPUT
	HasOvl	: BOOL;
END_VAR

VAR
	pParams: POINTER TO TRMI_Command;
END_VAR


************




pParams := GetParams('ovlrel');
IF pParams <> 0 AND_THEN pParams^.Num_Params = 1 THEN
	HasOvl := TRUE;
	ovl.ovlType := TOvlIecType.OVLREL;
	ovl.ovlPercent := TO_DINT(pParams^.Parameters[1]);
	RETURN;
END_IF

pParams := GetParams('ovlsuppos');
IF pParams <> 0 AND_THEN pParams^.Num_Params = 1 THEN
	HasOvl := TRUE;
	ovl.ovlType := TOvlIecType.OVLSUPPOS;
	ovl.ovlPercent := TO_DINT(pParams^.Parameters[1]);
	RETURN;
END_IF

pParams := GetParams('ovlabs');
IF pParams <> 0 AND_THEN pParams^.Num_Params = 5 THEN
	HasOvl := TRUE;
	ovl.ovlType := TOvlIecType.OVLABS;
	
	ovl.posDist := TO_REAL(pParams^.Parameters[1]);
	ovl.oriDist := TO_REAL(pParams^.Parameters[2]);
	ovl.linAxDist := TO_REAL(pParams^.Parameters[3]);
	ovl.rotAxDist := TO_REAL(pParams^.Parameters[4]);
	ovl.vConst := TO_BOOL(TO_INT(pParams^.Parameters[5]));	
	RETURN;
END_IF

HasOvl := FALSE;
