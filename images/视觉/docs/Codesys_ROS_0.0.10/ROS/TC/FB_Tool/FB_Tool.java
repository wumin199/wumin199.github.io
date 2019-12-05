FUNCTION_BLOCK FB_Tool
VAR_IN_OUT
	RosTC	: ROS_TC;
	ToolFrame	: TCartFrameIEC;
END_VAR
VAR_INPUT	
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



************************


IF Execute THEN
	CASE iState OF
	0:
		ExecuteLocked := FALSE;
		IF RosTC.Busy THEN
			RETURN;
		END_IF			
		
		
		RosTC.ResetFlags();
		
		RosTC.SetToolFrame(ToolFrame);
		iCmdNum_ := TTcPlcCommandTool;		
	
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



