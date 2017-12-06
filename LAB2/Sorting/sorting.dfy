class sorting{

predicate sorted(s: seq<int>)
reads this;
{
forall i,j :: 0 <= i < j < |s| ==> s[i] <= s[j]
}

predicate sorted2(s: seq<int>)
reads this;
{
0 < |s| ==> (forall i :: 0 < i < |s| ==> s[0] <= s[i]) &&
sorted2(s[1..])
}

//wtf does "if a sequence is sorted, then it is sorted" two times mean?????????`???????
ghost method IsSorted (s: seq<int>)
requires |s| < 2
ensures sorted(s)
{
//...
}

ghost method IsSorted2 (s: seq<int>)
requires |s| < 2
ensures sorted2(s)
{
//...
}

predicate p2(a : seq<int>, b : seq<int>)
reads this;
{
|a+b| == |a| + |b|
}
}
