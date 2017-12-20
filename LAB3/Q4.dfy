method ComputeFact(n : nat) returns (res : nat)
  requires n > 0;
  ensures res == fact(n);
 {
  res := 1;
  var i := 2;
  while (i <= n)
  invariant i <= n + 1;
  invariant res == fact(i-1);
  {
    res := res * i;
    i := i + 1;
  }
 }

 function fact(m: int): int
  requires 0 <= m;
  ensures 1 <= fact(m);
{
 if m == 0 then 1 else fact(m-1) * m
 } 
