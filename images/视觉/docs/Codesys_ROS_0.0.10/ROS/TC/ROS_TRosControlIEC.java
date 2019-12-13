//{ attribute 'teachcontroldatatype' := 'ROSCONTROLIEC' }//注释掉是因为TC中是在程序内部直接建立了个私有数据成员
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
