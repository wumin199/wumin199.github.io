METHOD getToolFrame : TText
VAR_INPUT
	RosUnits	: BOOL;
END_VAR
VAR_IN_OUT
	Robot	: AXES_GROUP_REF;	
END_VAR
VAR_INST
	bInitDone : BOOL;
	bHasReadback	: BOOL;
	{attribute 'noinit'} str : STRING(255);
END_VAR
VAR_INPUT
END_VAR
VAR_INST
	posRos: REAL_VECT;
	curCartPos: TCartesianPosition;
	//curCartPosRb: TCartesianPosition;
END_VAR

*****************************

//curCartPos := Robot.CartesianPosition;		

IF NOT bInitDone THEN
	bInitDone := TRUE;
	IF K_SystemCallLibrary.KSys_GetCat('MC.showReadBackValues', ADR(str)) THEN
		IF str = '2' THEN
			bHasReadback := TRUE;
		END_IF
	END_IF
END_IF
IF bHasReadback THEN
	curCartPos := Robot.CartesianReadBackPosition;
ELSE
	curCartPos := Robot.CartesianPosition;
END_IF


IF RosUnits THEN
	posRos[1] := TO_REAL(curCartPos.X / 1000.0);
	posRos[2] := TO_REAL(curCartPos.Y / 1000.0);
	posRos[3] := TO_REAL(curCartPos.Z / 1000.0);
ELSE
	posRos[1] := TO_REAL(curCartPos.X);
	posRos[2] := TO_REAL(curCartPos.Y);
	posRos[3] := TO_REAL(curCartPos.Z);
END_IF
posRos[4] := RAD(TO_REAL(curCartPos.A));
posRos[5] := RAD(TO_REAL(curCartPos.B));
posRos[6] := RAD(TO_REAL(curCartPos.C));

getToolFrame := SharedRealArrayToString(RealArray := posRos, NumFields := 6, 4);		
getToolFrame := CONCAT(getToolFrame, ';');