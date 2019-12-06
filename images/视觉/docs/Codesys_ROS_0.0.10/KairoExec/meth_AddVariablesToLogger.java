{attribute 'object_name' := 'meth_AddVariablesToLogger'}
METHOD PRIVATE meth_AddVariablesToLogger
VAR
	mpLogger: POINTER TO SignalLogger;
END_VAR


******************

mpLogger := GetSignalLoggerForCurrentTask();
IF mpLogger <> 0 THEN
	mpLogger^.PushName(CONCAT('AE.KAIROExecuter.', msRobotName));
		mpLogger^.PushName('Commands');
			mpLogger^.AddBOOL('Enable', Enable, eLogBoth);
			mpLogger^.AddBOOL('Power', Power, eLogBoth);
			mpLogger^.AddBOOL('StartExec', StartExec, eLogBoth);
			mpLogger^.AddBOOL('ContinueExec', ContinueExec, eLogBoth);
			mpLogger^.AddBOOL('InterruptExec', InterruptExec, eLogBoth);
			mpLogger^.AddBOOL('Reset', Reset, eLogBoth);
			mpLogger^.AddBOOL('HardStopRequired', HardStopRequired, eLogBoth);			
		mpLogger^.PopName('Commands');
		mpLogger^.PushName('Status');
			mpLogger^.AddBOOL('Active', Active, elogBoth);
			mpLogger^.AddBOOL('RobotIsPowered', RobotIsPowered, eLogBoth);
			mpLogger^.AddBOOL('RobotIsMoving', RobotIsMoving, eLogBoth);
			mpLogger^.AddBOOL('RobotIsRepositioning', RobotIsRepositioning, eLogBoth);
			mpLogger^.AddBOOL('ProgramIsRunning', ProgramIsRunning, eLogBoth);
			mpLogger^.AddBOOL('ProgramIsInterrupted', ProgramIsInterrupted, eLogBoth);
			mpLogger^.AddBOOL('ProgramFinished', ProgramFinished, eLogBoth);
			mpLogger^.AddBOOL('ResetDone', ResetDone, eLogBoth);
			mpLogger^.AddBOOL('Error', Error, eLogBoth);
			mpLogger^.AddDINT('ErrorId', ErrorID, eLogBoth);
		mpLogger^.PopName('Status');
		
//		mpLogger^.PushName('Internal');
		
//		mpLogger^.PopName('Internal');
	
	mpLogger^.PopName(CONCAT('AE.KAIROExecuter.', msRobotName));
	mbLoggerInitFinished := TRUE;
	
END_IF
	