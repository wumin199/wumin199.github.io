FUNCTION_BLOCK FB_PTP_Flex EXTENDS FB_MoveFlexBase
VAR
	
END_VAR


*********************************


SUPER^(RosTC:= RosTC, 
	pCp:= pCp, 
	pAp:= pAp, 
	pDyn:= pDyn, 
	pOvl := pOvl,
	Execute:= Execute, 
	ExecuteLocked=> ExecuteLocked, 
	Error=> Error);