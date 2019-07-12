

class Square {

  private int val = 0;
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

  public void setVal (int val) {
    this.val = val;
  }

  public String getCord () {
    return this.xcord + " " + this.ycord;
  }

  public String toString () {
    return val + "";
  }

}