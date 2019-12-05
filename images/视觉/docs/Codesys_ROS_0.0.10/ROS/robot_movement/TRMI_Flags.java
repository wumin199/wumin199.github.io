//Flags used to exec the TC blocks in ROS_RobotMovementInterface.  Abort is special and gets to exist outside of this.
TYPE TRMI_Flags :
STRUCT
	bExecLinFlex : BOOL;
	bExecPTPFlex : BOOL;
	bExecSync : BOOL;
	//bExecDyn : BOOL;	//Not used
	bExecSetting	: BOOL;
	bExecWaitIsFinished : BOOL;
	bExecTool	: BOOL;
   bExecSetDO   : BOOL;
END_STRUCT
END_TYPE
