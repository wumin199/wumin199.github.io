{attribute 'hide_all_locals' := ''}
FUNCTION_BLOCK FBExecuteKairo_0008
VAR_INPUT
	/// Enable Function Block
	Enable: BOOL;
	/// Robot Power, rising edge enable power, falling edge disable power
	Power: BOOL;
	/// Project Name, where the program is located
	ProjectName: STRING;
	/// Program Name, which should be started
	ProgramName: STRING;
	/// Start program execution
	StartExec: BOOL;
	/// Interrupt program execution
	InterruptExec: BOOL;
	/// Continue program execution, after interrupted
	ContinueExec: BOOL;
	/// reset robot errors and function block errors
	Reset: BOOL;
	/// true if a hardstop is required
	HardStopRequired: BOOL;
END_VAR
VAR_OUTPUT
	/// function block is active
	Active: BOOL := FALSE;
	/// robot is powered on or off 
	RobotIsPowered: BOOL := FALSE;
	/// robot is not moving but program execution could be still active
	RobotIsMoving: BOOL := FALSE;
	/// repositioning is active
	RobotIsRepositioning: BOOL := FALSE;
	/// robot program is running (play), Running =! Active 
	ProgramIsRunning: BOOL := FALSE;
	/// robot is interrupted, this flag is true as long as InterruptExec input is high
	ProgramIsInterrupted: BOOL := FALSE;
	/// robot program execution is finished
	ProgramFinished: BOOL := FALSE;
	/// reset robot and function block errors done
	ResetDone: BOOL := FALSE;
	/// robot error or function block error detected
	Error: BOOL := FALSE;
	/// error id of the function block which has an error
	ErrorID: K_RC.TErrorId;
END_VAR
VAR_IN_OUT
	Robot: AXES_GROUP_REF;
END_VAR
VAR_STAT
	mIncstanceId: DINT;
END_VAR
VAR
	{attribute 'hide' := ''}
	mInstanceName: K_Utils.GetInstanceName;
	{attribute 'hide' := ''}
	mfbRobotPower: RobotPower;
	{attribute 'hide' := ''}
	mfbRobotStartProgram: RobotStartProgram;
	{attribute 'hide' := ''}
	mfbRobotReset: RobotReset;
	{attribute 'hide' := ''}
	mfbRobotContinueProgram: RobotContinueProgram;
	{attribute 'hide' := ''}
	mfbRobotInterruptProgram: RobotInterruptProgram;
	{attribute 'hide' := ''}
	mfbTrigStart: R_TRIG;
	{attribute 'hide' := ''}
	mfbFTrigStart: F_TRIG;
	{attribute 'hide' := ''}
	mfbTrigContinue: R_TRIG;
	{attribute 'hide' := ''}
	mfbTrigInterrupt: R_TRIG;
	{attribute 'hide' := ''}
	mfbRTrigPower: R_TRIG;
	{attribute 'hide' := ''}
	mfbFTrigPower: F_TRIG;
	{attribute 'hide' := ''}
	mfbTrigReset: R_TRIG;
	{attribute 'hide' := ''}
	mfbTrigStop: R_TRIG;
	{attribute 'hide' := ''}
	mbFunctionBlockError: BOOL;
	{attribute 'hide' := ''}
	mbDoEnable: BOOL;
	{attribute 'hide' := ''}
	mbDoDisable: BOOL;
	{attribute 'hide' := ''}
	mbOldEnable: BOOL;
	///
	///	{attribute 'hide' := ''}
	///	mFbRSProgramFinished: RS;
	///	{attribute 'hide' := ''}
	///	mfbFTrigProgrammRunning: F_TRIG;
	///	
	{attribute 'hide' := ''}
	mFbFTrigStopOrInterrupt: F_TRIG;
	{attribute 'hide' := ''}
	mbLoggerInitFinished: BOOL;
	{attribute 'hide' := ''}
	msRobotName: STRING(32);
END_VAR


*****************

(* edge detection for input signals *)
mfbTrigStart(CLK:=StartExec);
mfbFTrigStart(CLK:=StartExec); 
mfbTrigContinue(CLK:=ContinueExec);
mfbTrigInterrupt(CLK:=InterruptExec);
mfbTrigReset(CLK:=Reset); 
mfbRTrigPower(CLK:= Power); // detect rising edge of power signal
mfbFTrigPower(CLK:= Power); // detect falling edge of power signal

IF NOT mbLoggerInitFinished THEN
	msRobotName	:= Robot.Name;
	meth_AddVariablesToLogger();
END_IF

IF Enable THEN
	
	(* power on or off, only called with an rising or falling edge to prevent problems with the MOT key on HT *)
	IF mfbRTrigPower.Q OR mbDoEnable THEN
		(* enable power of the robot *)
		mbDoEnable := TRUE;
		mbDoDisable := FALSE;
		mfbRobotPower(AxesGroup:= Robot, Enable := TRUE);
		IF mfbRobotPower.Status THEN
			mbDoEnable := FALSE;
		ELSIF mfbRobotPower.Error THEN
			mbDoEnable := FALSE;
		END_IF
	END_IF
	IF mfbFTrigPower.Q OR mbDoDisable THEN
		(* disable power of the robot *)
		mbDoDisable := TRUE;
		mbDoEnable := FALSE;
		mfbRobotPower(AxesGroup:= Robot, Enable := FALSE);
		IF mfbRobotPower.Status = FALSE THEN
			mbDoDisable := FALSE;
		END_IF
	END_IF
	
	(* reset *)
	IF mfbTrigReset.Q THEN
		mfbRobotStartProgram.Execute 		:= FALSE;
		mfbRobotContinueProgram.Execute 	:= FALSE;
		mfbRobotInterruptProgram.Execute	:= FALSE;
		mfbRobotReset.Execute 				:= TRUE;
	END_IF
	
	IF mfbFTrigStart.Q THEN
		mfbRobotStartProgram.Execute := FALSE;
	END_IF
	IF mfbTrigStart.Q THEN
		mfbRobotStartProgram.ProjectName := ProjectName;
		mfbRobotStartProgram.ProgramName := ProgramName;
		mfbRobotStartProgram.Execute := TRUE;
	END_IF
	
	(* interrupt / continue *)
	IF mfbTrigInterrupt.Q THEN
		(* check if pathdeselect is necessary or only an interrupt *)
		IF HardStopRequired THEN
			mfbRobotInterruptProgram.StopMode := eStopModeHardStop;
		ELSE
			mfbRobotInterruptProgram.StopMode := eStopModeStopAllAxes;
		END_IF
		mfbRobotInterruptProgram.Execute := TRUE;
		mfbRobotContinueProgram.Execute := FALSE;
	END_IF
	
	IF mfbTrigContinue.Q THEN
		mfbRobotContinueProgram.Execute := TRUE;
		mfbRobotInterruptProgram.Execute := FALSE;
	END_IF
	
	(* update all function blocks *)
	mfbRobotReset(AxesGroup:= Robot);
	mfbRobotContinueProgram(AxesGroup:= Robot);
	mfbRobotInterruptProgram(AxesGroup:= Robot);
	mfbRobotStartProgram(AxesGroup:= Robot);

	(* update output status of this function block *)
	mbFunctionBlockError 	:= mfbRobotContinueProgram.Error OR mfbRobotInterruptProgram.Error OR mfbRobotPower.Error OR mfbRobotReset.Error OR mfbRobotStartProgram.Error; 
	Active 					:= TRUE;
	RobotIsPowered			:= Robot.PowerStatus;
	RobotIsMoving			:= Robot.IsMoving;
	RobotIsRepositioning	:= Robot.ReposActive;
	ProgramIsRunning 		:= mfbRobotStartProgram.Active; (* KAIRO program running also when no robot Movement. FALSE when Program active but interrupted --> Active =! ProgramIsActive*)
	ProgramIsInterrupted   	:= mfbRobotInterruptProgram.Done AND (ProgramIsRunning = FALSE);
	ProgramFinished			:= mfbRobotStartProgram.Done; 
	ResetDone				:= mfbRobotReset.Done AND (mbFunctionBlockError = FALSE);
	Error					:= mbFunctionBlockError OR Robot.HasError;
	
	(* error handling *)
	IF Error = FALSE THEN
		ErrorID 	:= K_RC.eOk;
	ELSIF mfbRobotReset.Error THEN
		ErrorID 	:= mfbRobotReset.ErrorID;
	ELSIF mfbRobotPower.Error THEN
		ErrorID 	:= mfbRobotPower.ErrorID;
	ELSIF mfbRobotStartProgram.Error THEN
		ErrorID 	:= mfbRobotStartProgram.ErrorID;
	ELSIF mfbRobotInterruptProgram.Error THEN
		ErrorID 	:= mfbRobotInterruptProgram.ErrorID;
	ELSIF mfbRobotContinueProgram.Error THEN
		ErrorID 	:= mfbRobotContinueProgram.ErrorID;
	ELSIF Robot.HasError THEN
		ErrorID		:= 9999;
	ELSE
		ErrorID		:= K_RC.eErrInternal;
	END_IF
	
	(* reset execute flags *)
	IF (StartExec = FALSE) THEN
		mfbRobotStartProgram.Execute := FALSE;
	END_IF
	IF (Reset = FALSE) THEN
		mfbRobotReset.Execute := FALSE;
	END_IF
	mFbFTrigStopOrInterrupt(CLK:= InterruptExec);
	IF mFbFTrigStopOrInterrupt.Q THEN
		mfbRobotInterruptProgram.Execute := FALSE;
	END_IF
	IF (ContinueExec = FALSE) THEN
		mfbRobotContinueProgram.Execute := FALSE;
	END_IF
ELSE
	IF mbOldEnable <> Enable THEN
		(* update all function blocks *)
		mfbRobotReset(AxesGroup:= Robot, Execute:= FALSE);
		mfbRobotContinueProgram(AxesGroup:= Robot, Execute:= FALSE);
		mfbRobotInterruptProgram(AxesGroup:= Robot, Execute:= FALSE, StopMode:=	eStopModeStopAllAxes);
		mfbRobotStartProgram(AxesGroup:= Robot, Execute:= FALSE);
		mbDoDisable := TRUE;
	END_IF
	
	IF mbDoDisable THEN
		(* disable power of the robot *)
		mbDoDisable := TRUE;
		mbDoEnable := FALSE;
		mfbRobotPower(AxesGroup:= Robot, Enable := FALSE);
		IF mfbRobotPower.Status = FALSE THEN
			mbDoDisable := FALSE;
		END_IF
	END_IF
	
	Active 					:= FALSE;
	RobotIsPowered			:= FALSE;
	RobotIsMoving			:= FALSE;
	ProgramIsRunning 		:= FALSE;
	ProgramIsInterrupted   	:= FALSE;
	ResetDone				:= FALSE;
	Error					:= FALSE;
	ErrorID 				:= K_RC.eOk;	
END_IF

mbOldEnable := Enable;