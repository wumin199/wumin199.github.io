{ attribute 'teachcontroldatatype' := 'OVLIEC' }//指定在TC中的变量名称为OVLIEC
TYPE TOvlIEC :
STRUCT
	ovlType	  : TOvlIecType := 0;		//Overlap type. 0 = None, 1 = OVLABS, 2 = OVLREL, 3 = OVLSUPPOS
	ovlPercent	: DINT(0..200) := 100;	//% used for ovlrel,ovlsuppos
	posDist   : REAL := 0.0;     // overlap-distance of TCP
	oriDist   : REAL := 360.0;   // orientation-distance 
	linAxDist : REAL := 10000.0; // dist. for linear-axis & PTP as well as AuxAxes
	rotAxDist : REAL := 360.0;   // dist. for rot.-axis & PTP as well as AuxAxes
	vConst    : BOOL := FALSE;   // constant velocity flag
END_STRUCT
END_TYPE
