// A LIFO queue (aka a stack) with limited capacity.
class LimitedStack{

var capacity : int; // capacity, max number of elements allowed on the stack.
var arr : array<int>; // contents of stack.
var top : int; // The index of the top of the stack, or -1 if the stack is empty

// This predicate express a class invariant: All objects of this calls should satisfy this.

predicate Valid()
reads this, this.arr;
{
      arr != null && capacity > 0 && arr.Length == capacity && top >= -1 && top < capacity  // TOP should be at least -1 and strictly less than CAPACITY
}

predicate Empty()
reads this;
{
  top == -1
}

predicate Full()
reads this;
{
  top == capacity -1 
}


method Init(c : int)
modifies this;
requires c > 0;
ensures fresh(arr);
ensures arr.Length == c;
ensures Empty();
ensures Valid();
{
capacity := c;
arr := new int[c];
top := -1;
}


method isEmpty() returns (res : bool)
ensures Empty() ==> res;
{
  return top == -1;
}

// Returns the top element of the stack, without removing it.
method Peek() returns (elem : int)
//reads this, this.arr;
requires this.Valid();
requires !Empty();
ensures elem == arr[top];
{
  return arr[top];
}



// Pushed an element to the top of a (non full) stack.
method Push(elem : int)
modifies arr, `top;
requires Valid();
requires !Full();
ensures Valid();
ensures top == old(top)+1;
ensures arr[top] == elem;
//skriv om detta!!!
ensures forall k : int :: 0 <= k < top ==> arr[k] == old(arr[k]);
{
  top := top + 1;
  arr[top] := elem;
}

// Pops the top element off the stack.

method Pop() returns (elem : int)
modifies `top;
requires Valid();
requires !Empty();
ensures Valid();
ensures top == old(top)-1;
ensures elem == old(arr[top]);
ensures !Full();
{
  top := top -1;
  return arr[top + 1];
}


method Shift()
requires Valid() && !Empty();
ensures Valid();
ensures forall i : int :: 0 <= i < capacity - 1 ==> arr[i] == old(arr[i + 1]);
ensures top == old(top) - 1;
modifies this.arr, this`top;
{
var i : int := 0;
while (i < capacity - 1 )
invariant 0 <= i < capacity;
invariant top == old(top);
invariant forall j : int :: 0 <= j < i ==> arr[j] == old(arr[j + 1]);
invariant forall j : int :: i <= j < capacity ==> arr[j] == old(arr[j]);
{
arr[i] := arr[i + 1];
i := i + 1;
}
top := top - 1;
}


//Push onto full stack, oldest element is discarded.
method Push2(elem : int)
modifies `arr;
      requires Valid();
      requires Full();
      ensures Valid();
      ensures Full();
      ensures arr.Length == old(arr).Length;
      ensures forall k : int :: 0 <= k < capacity - 1 ==> arr[k] == old(arr)[k+1];
      ensures arr[top] == elem;
{
   var temp_arr := new int[capacity];
          forall(i | 0 <= i < arr.Length - 1)
          {
              temp_arr[i] := arr[i+1];
          }
          temp_arr[top] := elem;
          
          arr := temp_arr;
}




// When you are finished,  all the below assertions should be provable.
// Feel free to add extra ones as well.
method Main(){
var s := new LimitedStack;
s.Init(3);

assert s.Empty() && !s.Full();

s.Push(27);
assert !s.Empty();

var e := s.Pop();
assert e == 27;

s.Push(5);
s.Push(32);
s.Push(9);
assert s.Full();

var e2 := s.Pop();
assert e2 == 9 && !s.Full();
assert s.arr[0] == 5;

s.Push(e2);
s.Push2(99);

var e3 := s.Peek();
assert e3 == 99;
assert s.arr[0] == 32;

}

}
