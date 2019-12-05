FUNCTION_BLOCK FB_Setting_Flex
VAR_IN_OUT
	RosTC	: ROS_TC;
END_VAR
VAR_INPUT
	pDyn	: POINTER TO TDynamic;
	pOvl	: POINTER TO TOvlIEC;	
	Execute	: BOOL;		
END_VAR
VAR_OUTPUT
	ExecuteLocked	: BOOL;
	Error	: BOOL;
END_VAR
VAR
	iState	: DINT;
	iCmdNum_	: DINT;	
END_VAR


****************************

IF Execute THEN
	CASE iState OF
	0:
		ExecuteLocked := FALSE;
		IF RosTC.Busy THEN
			RETURN;
		END_IF
		
		
		
		//Check that a dynamic or overlap
		Error := Error OR ((pDyn = 0) AND (pOvl = 0));
		IF Error THEN
			iState := 40;
			ExecuteLocked := TRUE;
			RETURN;
		END_IF
		
		RosTC.ResetFlags();
		
		IF pDyn <> 0 THEN
			RosTC.SetDyn(pDyn^);		
			RosTC.SetHasDyn(TRUE);	
			//iCmdNum_ := TTcPlcCommandDyn;
		END_IF
		IF pOvl <> 0 THEN
			RosTC.SetOvl(pOvl^);
			RosTC.SetHasOvl(TRUE);	
			//iCmdNum_ := TTcPlcCommandOvl;	
		END_IF						
		
		//RosTC.SetCartPos(cp);
		RosTC.SetCommandNum(CommandNum := iCmdNum_);
		RosTC.SetExecute(TRUE);
		iState := 10;
	10:
		ExecuteLocked := RosTC.ExecuteLocked;
		IF ExecuteLocked THEN
			iState := 40;
		END_IF
	END_CASE
ELSE
	iState := 0;
	ExecuteLocked := FALSE;
	Error := FALSE;
END_IF



