def main(): Unit = {
  var a: Int = 10
  if (a >= 10) {
    var b: String = "Hello"
    printLine(b)
    a = 0
  } else if (a == 0) {
    printInt(2 + 4);
  } else {
    printInt(a)
  }
  var c = 0
  readInt(c)
  printInt(c);
}