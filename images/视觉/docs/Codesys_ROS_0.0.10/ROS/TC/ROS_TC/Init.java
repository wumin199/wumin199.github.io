METHOD Init : BOOL
VAR_INPUT
END_VAR
VAR
	strMemName	: STRING;
	
	ovlDefault	: TOvlIEC;
END_VAR


**************************

Init := FALSE;
IF mInitDone THEN
	Init := TRUE;
	RETURN;
END_IF

strMemName := CONCAT('IEC.ROS.', mInstanceName.InstanceName);

IF NOT mTcIECInterfaceSharedMem.IsValid THEN	
   	mTcIECInterfaceSharedMem.InitShMem(strMemName, SIZEOF(ROS_TRosControlIEC));
END_IF

IF mTcIECInterfaceSharedMem.IsValid THEN
	mpTcIECInterface := mTcIECInterfaceSharedMem.Addr;
ELSE
	RETURN;
END_IF


IF NOT mTcIECAbortInterfaceSharedMem.IsValid THEN	
   	mTcIECAbortInterfaceSharedMem.InitShMem(CONCAT(strMemName, '.Abort'), SIZEOF(ROS_TRosControlAbortIEC));
END_IF

IF mTcIECAbortInterfaceSharedMem.IsValid THEN
	mpTcIECAbortInterface := mTcIECAbortInterfaceSharedMem.Addr;
ELSE
	RETURN;
END_IF


IF NOT mApIec1SharedMem.IsValid THEN 
	mApIec1SharedMem.InitShMem(CONCAT(strMemName, '.apIec1'), SIZEOF(TAxesPosition));
END_IF

IF mApIec1SharedMem.IsValid THEN
	mpApIec1 := mApIec1SharedMem.Addr;
ELSE
	RETURN;
END_IF


IF NOT mDynIec1SharedMem.IsValid THEN
	mDynIec1SharedMem.InitShMem(CONCAT(strMemName, '.dynIec1'), SIZEOF(TDynamic));	
	IF mDynIec1SharedMem.IsValid THEN
		mpDynIec1 := mDynIec1SharedMem.Addr;
	ELSE
		RETURN;
	END_IF
END_IF

IF NOT mCpIec1SharedMem.IsValid THEN
	mCpIec1SharedMem.InitShMem(CONCAT(strMemName, '.cpIec1'), SIZEOF(TCartesianPosition));	
	IF mCpIec1SharedMem.IsValid THEN
		mpCpIec1 := mCpIec1SharedMem.Addr;
	ELSE
		RETURN;
	END_IF
END_IF

IF NOT mOvlIEcSharedMem.IsValid THEN
	mOvlIEcSharedMem.InitShMem(CONCAT(strMemName, '.ovlIec'), SIZEOF(TOvlIEC));	
	IF mOvlIEcSharedMem.IsValid THEN
		mpOvlIec := mOvlIEcSharedMem.Addr;
		mpOvlIec^ := ovlDefault;	//Set the defaults
	ELSE
		RETURN;
	END_IF
END_IF

IF NOT mToolFrameSharedMem.IsValid THEN
	mToolFrameSharedMem.InitShMem(CONCAT(strMemName, '.toolFrameIec'), SIZEOF(TCartFrameIEC));
	IF mToolFrameSharedMem.IsValid THEN
		mpToolFrameIec := mToolFrameSharedMem.Addr;
	ELSE
		RETURN;
	END_IF
END_IF

IF NOT mTcDigitalIOSharedMem.IsValid THEN
	mTcDigitalIOSharedMem.InitShMem(CONCAT(strMemName, '.digitalIO'), SIZEOF(ROS_TRosDigitalIO));	
	IF mTcDigitalIOSharedMem.IsValid THEN
		mpTcDigitalIOSharedMem := mTcDigitalIOSharedMem.Addr;
	ELSE
		RETURN;
	END_IF
END_IF

mInitDone := TRUE;
Init := TRUE;