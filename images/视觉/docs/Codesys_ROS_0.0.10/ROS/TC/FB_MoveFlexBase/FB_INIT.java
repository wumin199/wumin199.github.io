METHOD FB_INIT : BOOL
VAR_INPUT
	bInitRetains : BOOL; // TRUE: the Retain-variables are initialized (reset warm / reset cold)
 	bInCopyCode : BOOL;  // TRUE  the instance will be copied to the copy-code afterward (online change)
END_VAR



*************************

iCmdNum_ := 0;