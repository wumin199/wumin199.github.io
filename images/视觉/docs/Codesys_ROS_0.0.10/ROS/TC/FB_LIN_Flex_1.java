FUNCTION_BLOCK FB_LIN_Flex_1
VAR_IN_OUT
	RosTC	: ROS_TC;
END_VAR
VAR_INPUT
	pCp	: POINTER TO TCartesianPosition;
	pAp	: POINTER TO TAxesPosition;
	pDyn	: POINTER TO TDynamic;	
	Execute	: BOOL;		
END_VAR
VAR_OUTPUT
	ExecuteLocked	: BOOL;
	Error	: BOOL;
END_VAR
VAR
	iState	: DINT;
END_VAR



*******************************************

IF Execute THEN
	CASE iState OF
	0:
		ExecuteLocked := FALSE;
		IF RosTC.Busy THEN
			RETURN;
		END_IF
		
		//Check that a cartesian position xor an axis position have been set
		Error := NOT((pCp <> 0) XOR (pAp <> 0));
		IF Error THEN
			iState := 40;
			ExecuteLocked := TRUE;
			RETURN;
		END_IF
		
		RosTC.ResetFlags();
		
		IF pCp <> 0 THEN
			RosTC.SetHasCartPos(TRUE);
			RosTC.SetCartPos(pCp^);
		ELSIF pAp <> 0 THEN
			RosTC.SetHasAxisPos(TRUE);
			RosTC.SetAxisPos(pAp^);
		END_IF				
		
		IF pDyn <> 0 THEN
			RosTC.SetDyn(pDyn^);
			RosTC.SetHasDyn(TRUE);		
		END_IF
		
		
		//RosTC.SetCartPos(cp);
		RosTC.SetCommandNum(CommandNum := TTcPlcCommand.TTcPlcCommandLin);
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



