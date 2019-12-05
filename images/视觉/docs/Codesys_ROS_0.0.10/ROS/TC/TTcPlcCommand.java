{attribute 'strict'}
TYPE TTcPlcCommand :
(
	TTcPlcCommandInvalid := 0,
	TTcPlcCommandLin := 1,
	TTcPlcCommandOvl := 2,
	TTcPlcCommandDyn := 3,
	TTcPlcCommandWaitIsFinished := 4,
	TTcPlcCommandPTP := 5,
	TTcPlcCommandSync999 := 6,
	TTcPlcCommandSetting := 7,
	TTcPlcCommandTool := 8,
   TTcPlcCommandSetDO := 9,
   TTcPlcCommandReadDI := 10
);
END_TYPE
