//{ attribute 'teachcontroldatatype' := 'ROSCONTROLIEC' }//注释掉是因为TC中是在程序内部直接建立了个私有数据成员
TYPE ROS_TRosControlAbortIEC :
STRUCT	
	Abort	: BOOL;
	AbortLocked	: BOOL;	
END_STRUCT
END_TYPE
