def fact(n: Int) : Int =
{
  var ret = 1;
  var i = 1;
  while (i <= n) {
    ret = ret * i;
    i = i + 1;
  }
  return ret;
}

def main() : Unit = {
  var a = 0;
  readInt(a);
  printInt(fact(a));
}