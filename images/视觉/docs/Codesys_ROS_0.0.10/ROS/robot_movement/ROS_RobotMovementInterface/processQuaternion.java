METHOD processQuaternion
VAR_IN_OUT
	Robot	: AXES_GROUP_REF;
END_VAR
VAR_INPUT
END_VAR
VAR
	pAux	: POINTER TO TRMI_Command;
	rob_current_pos: TAxesVector;
END_VAR


***************************

resetPointers();

pParams := active_command_.GetParams('');		

ROS_ParamsToRealArray(RealArray := cartData, Cmd := pParams);

cpItf.X := cartData[1];
cpItf.Y := cartData[2];
cpItf.Z := cartData[3];

Quat[1] := cartData[4];
Quat[2] := cartData[5];
Quat[3] := cartData[6];
Quat[4] := cartData[7];

rotMat := QuatToMatrix(Quat);

oriFromQuat := RotMxToEuler(rotMat);
cpItf.A := oriFromQuat[1];
cpItf.B := oriFromQuat[2];
cpItf.C := oriFromQuat[3];	

rob_current_pos := Robot.AxesReadBackPosition;


cpItf.Aux1 := rob_current_pos.Aux1;
	
//cpItf.Aux1 := -100;

pParams := active_command_.GetParams('aux1');
IF pParams <> 0 THEN
	cpItf.Aux1 := TO_REAL(pParams^.Parameters[1]);
END_IF

pParams := active_command_.GetParams('aux2');
IF pParams <> 0 THEN
	cpItf.Aux1 := TO_REAL(pParams^.Parameters[1]);
END_IF



pCpItf := ADR(cpItf);

active_command_.GetDyn(HasDyn => bHasDynamic, dyn := dyn_);
IF bHasDynamic THEN 
	pDyn_ := ADR(dyn_);			
END_IF		

active_command_.GetOvl(HasOvl => bHasOvl, ovl := ovl_);
IF bHasOvl THEN
	pOvl_ := ADR(ovl_);
END_IF