{attribute 'qualified_only'}
VAR_GLOBAL
	g_bROSControlRunning	: BOOL;
	//g_bROSStreamerConnected	: BOOL;
	//g_bROSStatusConnected	: BOOL;
	
	g_iMode	: DINT;	//used for testing Lin moves	
	g_rAux1	: LREAL;	//used for testing Lin moves
	
	g_bDumpCartMoveOnError	: BOOL;	//If a cart move encounters and error, force Busy to FALSE so that all points get "executed" 
				// This is because theres no good way to about a move aside from a timeout and I have no idea how long a cart move will take.
	
	
END_VAR

VAR_GLOBAL CONSTANT
	g_RMILibVersion	: STRING := '0.0.10';
END_VAR