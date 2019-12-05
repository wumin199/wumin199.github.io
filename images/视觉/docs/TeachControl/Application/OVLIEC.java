NAME OVLIEC;
{
   no_var
}
USER;
INHERIT OVLIEC_;
(*
VAR
	ovlType : DINT;   //Overlap type. 0 = None, 1 = OVLABS, 2 = OVLREL, 3 = OVLSUPPOS
   ovlPercent	: DINT := 100;	//% used for ovlrel,ovlsuppos
   
	posDist   : REAL := 0.0;     // overlap-distance of TCP
	oriDist   : REAL := 360.0;   // orientation-distance 
	linAxDist : REAL := 10000.0; // dist. for linear-axis & PTP as well as AuxAxes
	rotAxDist : REAL := 360.0;   // dist. for rot.-axis & PTP as well as AuxAxes
	vConst    : BOOL := FALSE;   // constant velocity flag
END_VAR
*)

VAR
   mOvlAbs  : OVLABS;
   mOvlRel  : OVLREL;
   mOvlSuppos  : OVLSUPPOS;
   
   mOvl  : MAPTO OVERLAP_;
END_VAR

ROUTINE GetOvl() : BOOL  
   
   CASE ovlType OF
   1: //ovlabs
      mOvlAbs.posDist := posDist;
      mOvlAbs.oriDist := oriDist;
      mOvlAbs.linAxDist := linAxDist;
      mOvlAbs.rotAxDist := rotAxDist;
      mOvlAbs.vConst := vConst;
      mOvl := MAP(mOvlAbs);
   2: //ovlrel
      mOvlRel.ovl := ovlPercent;
      mOvl := MAP(mOvlRel);
   3:
      mOvlSuppos.ovl := ovlPercent;
      mOvl := MAP(mOvlSuppos);
   ELSE
      mOvl := MAPX("");
   END_CASE;
   
   RETURN IS_MAPPED(mOvl);
  
END_ROUTINE
   