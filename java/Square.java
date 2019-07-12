

class Square {


  private int val = -1;
  private int xcord;
  private int ycord;
  public Square (int val) {
    this.val = val;
  }
  public Square (int x, int y) {
    this.xcord = x;
    this.ycord = y;
  }

  public int getVal () {
    return this.val;
  }

  public String toString () {
    if (val == -1) {
      return "0";
    } else {
      return "|" + val + "|";
    }
  }

}