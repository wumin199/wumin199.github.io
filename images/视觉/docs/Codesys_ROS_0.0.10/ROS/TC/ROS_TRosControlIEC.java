//{ attribute 'teachcontroldatatype' := 'ROSCONTROLIEC' }
TYPE ROS_TRosControlIEC :
STRUCT
	Version	: STRING(16);
	CommandNum	: DINT;
	Execute	: BOOL;
	ExecuteLocked: BOOL;	
	HasDyn	: BOOL;
	HasOvl	: BOOL;
	Ready	: BOOL;	//Set by TC when Begin is called.  
	HasError	: BOOL;	//Set by TC
	HasCartPos  : BOOL;
	HasAxisPos  : BOOL;	
   
   DOut  : LWORD;
   DIn   : LWORD;
END_STRUCT
END_TYPE
