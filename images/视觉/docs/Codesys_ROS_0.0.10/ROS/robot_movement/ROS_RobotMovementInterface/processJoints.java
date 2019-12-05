resetPointers();

pParams := active_command_.GetParams('');

//Get the joint positions and let the Transformer fix them	
ROS_ParamsToRealArray(RealArray := rosPtpCmd, Cmd := pParams);
Transformer.PosROSToKeba(InROS := rosPtpCmd, OutKeba := apItf);	

pApItf := ADR(apItf);

active_command_.GetOvl(HasOvl => bHasOvl, ovl := ovl_);
IF bHasOvl THEN
	pOvl_ := ADR(ovl_);
END_IF

active_command_.GetDyn(HasDyn => bHasDynamic, dyn := dyn_);

IF bHasDynamic THEN
	pDyn_ := ADR(dyn_);
ELSE
	//Hacky way to handle both keba dyn and ros velros/accros.  
	//I should move this into the DynROSToKebaVel/DynROSToKebaAcc functions
	pParams := active_command_.GetParams('velros');
	IF pParams <> 0 THEN			
		ROS_ParamsToRealArray(RealArray := ros_dyn_data_, Cmd := pParams);				
		Transformer.DynROSToKebaVel(ros_dyn_data_, vel_axis);
		
		IF vel_axis >= min_vel THEN
			dyn_.velAxis := vel_axis;
		ELSIF dyn_.velAxis < min_vel THEN
			dyn_.velAxis := min_vel;
		END_IF
		
		pParams := active_command_.GetParams('accros');
		IF pParams <> 0 THEN
			ROS_ParamsToRealArray(RealArray := ros_dyn_data_, Cmd := pParams);				
			Transformer.DynROSToKebaAcc(ros_dyn_data_, acc_axis);
			
			IF acc_axis >= min_acc THEN
				dyn_.accAxis := acc_axis;
			//ELSIF dyn_.accAxis < 4 THEN
			ELSIF dyn_.accAxis > min_acc THEN
				dyn_.accAxis := min_acc;
			END_IF
			dyn_.decAxis := dyn_.accAxis;
         
         dyn_.jerkAxis := set_jerk;
			pDyn_ := ADR(dyn_);	//Only setting if it has both vel and accel
		END_IF
	END_IF	
END_IF	