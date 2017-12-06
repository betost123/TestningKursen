datatype securityClearance = Lv1 | Lv2

class Tokeneer{
  var uID : int;
  var clearLv : securityClearance;
  var valid : bool;
  
  constructor(n : int, Lv : securityClearance)
    ensures uID == n;
    ensures valid;
    ensures clearLv == Lv;
    {
      uID := n;
      clearLv := Lv;
      valid := true;
    }
    
    method invalidID()
    modifies `valid;
    requires valid == true;
    ensures valid == false;
    {
      valid := false;
    }
}

class IDstn{
  var doorState : securityClearance;
  
  constructor (d : securityClearance)
  ensures doorState == d;
  {
    doorState := d;
  }
}

class EnrollmentStn{
  var user : map<int, Tokeneer>;
  
  constructor ()
  ensures |user| == 0; 
  {
    user := map[];
  }
  
  method addUser(name: int, c : securityClearance) returns (t : Tokeneer)
  modifies `user;
  requires name !in user;
  ensures name in user;
  ensures user[name] == t;
  ensures t != null;
  ensures t.uID == name;
  {
    var token := new Tokeneer(name, c);
    user := user[name := token];
    return (token);
  }
}

class Actions{
  var e : EnrollmentStn;
  var i : IDstn;
  var closed : bool;
  var Alarm : bool;
  
  constructor(green : securityClearance )
  ensures i != null;
  ensures e != null;
  {
    e := new EnrollmentStn();
    i := new IDstn(green);
  }
  
  method OpensDoor(identity : int, t : Tokeneer)
  modifies t;
  modifies `closed;
  modifies `Alarm;
  requires t != null;
  requires t.valid == true;
  requires i != null;
  requires e != null;
  ensures(((t.uID == identity)&&(t.clearLv == i.doorState)) ==> !closed)||(!((t.uID == identity)&&(t.clearLv == i.doorState)) ==> ((Alarm == true) && (t.valid == false)));
  {
    if (t.uID == identity){
             closed := false;
    }
    else {
      t.invalidID();
      Alarm := true;
    }
  }
}
