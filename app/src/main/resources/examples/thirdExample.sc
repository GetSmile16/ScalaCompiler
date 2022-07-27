def fib(n: Int): Int = {
  if (n <= 1) {
//    printLine("<= 1")
//    printInt(n)
    return 0
  }
  if (n <= 2)  {
//    printLine("<= 2")
//    printInt(n)
    return 1
  }
//  var a = fib(n - 2)
//  var b = fib(n - 1)
//  var c = a + b;
  return fib(n - 2) + fib(n - 1);
}

def main() : Unit = {
  var a = 0;
  readInt(a);
  printInt(fib(a));
}