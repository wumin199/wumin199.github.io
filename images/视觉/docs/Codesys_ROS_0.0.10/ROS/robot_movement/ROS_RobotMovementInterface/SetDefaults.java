METHOD SetDefaults
VAR_INPUT
   min_acc	: DINT := 10;
	min_vel	: DINT := 5;
   set_jerk : DINT := 30;
END_VAR


*********************


THIS^.min_acc := min_acc;
THIS^.min_vel := min_vel;
THIS^.set_jerk := set_jerk;