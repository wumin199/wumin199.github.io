METHOD getJointPosition : TText
VAR_IN_OUT
	Transformer	: ROS_BasicTransformer;
	Robot	: AXES_GROUP_REF;	
END_VAR
VAR_INPUT
END_VAR
VAR_INST
	robPos: TAxesPosition;
	posRos: REAL_VECT;
END_VAR


*******************************

getJointPosition := '';
//robPos := Robot.AxesPosition;
robPos := Robot.AxesReadBackPosition;



Transformer.PosKebaToROS(InKeba := robPos, OutROS := posRos);
		
getJointPosition := SharedRealArrayToString(RealArray := posRos, NumFields := Transformer.NumMainJoints + Transformer.NumAuxJoints, 4); 		
getJointPosition := CONCAT(getJointPosition, ';');		

