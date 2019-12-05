resetPointers();

pParams := active_command_.GetParams('');
		
ROS_ParamsToRealArray(RealArray := cartData, Cmd := pParams);

cpItf.X := cartData[1];
cpItf.Y := cartData[2];
cpItf.Z := cartData[3];

cpItf.A := cartData[4];
cpItf.B := cartData[5];
cpItf.C := cartData[6];

cpItf.Aux1 := -100;


active_command_.GetDyn(HasDyn => bHasDynamic, dyn := dyn_);
IF bHasDynamic THEN
	pDyn_ := ADR(dyn_);			
END_IF			

active_command_.GetOvl(HasOvl => bHasOvl, ovl := ovl_);
IF bHasOvl THEN
	pOvl_ := ADR(ovl_);
END_IF