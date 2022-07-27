def main(): Unit = {
  var a = 0;
  a = 56;
  readInt(a)
  var i :Int = 0;
  while (i < 5) {
    printInt(i)
    i = i + 1;
  }
  printLine("You enter: ")
  printInt(a);
  return 0
}