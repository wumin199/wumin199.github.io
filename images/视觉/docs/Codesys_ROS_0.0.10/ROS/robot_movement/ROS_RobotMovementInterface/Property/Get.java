VAR
	idx	: DINT;	
	sz: UINT;	
	pBool	: POINTER TO BOOL;
END_VAR


*************************

sz := SIZEOF(flags_);
pBool := ADR(flags_);

AnyExecSet := FALSE;

FOR idx := 0 TO sz - 1 DO
	AnyExecSet := AnyExecSet OR pBool[idx];	
END_FOR

//AnyExecSet := flags_.bExecLinFlex OR_ELSE flags_.bExecPTPFlex OR_ELSE flags_.bExecSync OR_ELSE flags_.bExecDyn OR_ELSE flags_.bExecWaitIsFinished;