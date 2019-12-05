METHOD getJointVel : TText
VAR_IN_OUT
	Transformer	: ROS_BasicTransformer;
	Robot	: AXES_GROUP_REF;	
END_VAR
VAR_INPUT
END_VAR
VAR_INST
	robVel: TAxesDynamic;
	velRos: REAL_VECT;
END_VAR


*******************************

getJointVel := '';
//robPos := Robot.AxesPosition;
robVel := Robot.AxesDynamic;


Transformer.DynKebaToROS(InKeba := robVel.Velocity, OutROS := velRos);

		
getJointVel := SharedRealArrayToString(RealArray := velRos, NumFields := Transformer.NumMainJoints + Transformer.NumAuxJoints, 3); 		
getJointVel := CONCAT(getJointVel, ';');		

