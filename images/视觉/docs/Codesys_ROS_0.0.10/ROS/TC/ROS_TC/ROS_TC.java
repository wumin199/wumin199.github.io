FUNCTION_BLOCK ROS_TC //EXTENDS TcItfObject
VAR_INPUT
END_VAR
VAR_OUTPUT
END_VAR
VAR
	mInitDone : BOOL;
	mInstanceName      : K_Utils.GetInstanceName;	
	
	 // connection to RC	
	mTcIECInterfaceSharedMem      : K_Base.KSharedMemory;	
	mpTcIECInterface	: POINTER TO ROS_TRosControlIEC;
	
	mTcIECAbortInterfaceSharedMem      : K_Base.KSharedMemory;	
	mpTcIECAbortInterface	: POINTER TO ROS_TRosControlAbortIEC;
	
	mApIec1SharedMem	: K_Base.KSharedMemory;
	mpApIec1	: POINTER TO TAxesPosition;
	
	mDynIec1SharedMem	: K_Base.KSharedMemory;
	mpDynIec1	: POINTER TO TDynamic;
	
	mCpIec1SharedMem	: K_Base.KSharedMemory;
	mpCpIec1	: POINTER TO TCartesianPosition;
	
	mOvlIEcSharedMem	: K_Base.KSharedMemory;
	mpOvlIec	: POINTER TO TOvlIEC;
	
	mToolFrameSharedMem	: K_Base.KSharedMemory;
	mpToolFrameIec	: POINTER TO TCartFrameIEC;
	
   mTcDigitalIOSharedMem  : K_Base.KSharedMemory;
   mpTcDigitalIOSharedMem  : POINTER TO ROS_TRosDigitalIO;
END_VAR



**************

IF NOT mInitDone THEN
	Init();
END_IF
