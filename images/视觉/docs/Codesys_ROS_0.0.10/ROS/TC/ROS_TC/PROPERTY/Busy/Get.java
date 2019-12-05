VAR
END_VAR



******************


Busy := (Execute AND_THEN NOT ExecuteLocked) OR_ELSE NOT Ready;