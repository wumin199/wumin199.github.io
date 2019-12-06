{attribute 'object_name' := 'fb_init'}
METHOD fb_init : BOOL
VAR_INPUT
	/// if TRUE, the retain variables are initialized (warm start / cold start)
	bInitRetains: BOOL;
	/// if TRUE, the instance afterwards gets moved into the copy code (online change)
	bInCopyCode: BOOL;
END_VAR


********************

mbLoggerInitFinished := FALSE;
KTrace(CONCAT('AE feature used: ', mInstanceName.InstanceName));
mIncstanceId := mIncstanceId + 1;

 
	