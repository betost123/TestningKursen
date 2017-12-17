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


ghost method IsSorted (s: seq<int>)
requires sorted2(s)
ensures sorted(s)
{
//...
}

ghost method IsSorted2 (s: seq<int>)
requires sorted(s);
ensures sorted2(s)
{
//...
}

predicate p2(a : seq<int>, b : seq<int>)
reads this;
requires |a| == |b|;
{
forall i :: 0 <= i <|a| ==> a[i] == b[i]
}

method sortArray(a : array<int>)
requires a != null;
ensures sorted(a[..]);
ensures p2(old(a[..]), a[..])
{

}


method badSpec(a: array<int>)
requires a != null;
ensures forall sorted(a[..]);
{

}
}
