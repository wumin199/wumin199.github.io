METHOD processRobotStatus : STRING
VAR_IN_OUT
	Robot	: AXES_GROUP_REF;
END_VAR
VAR_INPUT
	EStopActive	: BOOL;
	ErrorCode	: DINT;
END_VAR
VAR
	rArr : ARRAY [1..7] OF SHARED_REAL;
	bControlAuth	: BOOL;
END_VAR

**************


(*
 *   drives_powered      (industrial::shared_types::shared_int)    4  bytes
 *   e_stopped           (industrial::shared_types::shared_int)    4  bytes
 *   error_code          (industrial::shared_types::shared_int)    4  bytes
 *   in_error            (industrial::shared_types::shared_int)    4  bytes
 *   in_motion           (industrial::shared_types::shared_int)    4  bytes
 *   mode                (industrial::shared_types::shared_int)    4  bytes
 *   motion_possible     (industrial::shared_types::shared_int)    4  bytes
 *)
 
//processRobotStatus := TO_STRING(TO_INT(Robot.PowerStatus));
//processRobotStatus := CONCAT(processRobotStatus, ' ');

bControlAuth := IsRobotControlAuthorityGranted(AxesGroup := Robot);

rArr[1] := TO_REAL(Robot.PowerStatus);
rArr[2] := TO_REAL(EStopActive);
rArr[3] := TO_REAL(ErrorCode);
rArr[4] := TO_REAL(Robot.HasError);
rArr[5] := TO_REAL(Robot.IsMoving);
rArr[6] := TO_REAL(bControlAuth);
rArr[7] := TO_REAL(Robot.PowerStatus (*AND TC itf ready and connected, etc*) ); 

processRobotStatus := SharedRealArrayToString(rArr, 7, 0);



	