//1.
method Q2(x : int, y : int) returns (big : int, small : int)
requires x != y;
ensures big > small;
{
if (x > y)
{big, small := x, y;}
else
{big, small := y, x;}
}

//2.
method Q2(x : int, y : int) returns (big : int, small : int)
ensures big >= small;
{
if (x > y)
{big, small := x, y;}
else
{big, small := y, x;}
}
