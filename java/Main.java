public class Main {
  public static void main (String args[]) {
    int fieldSize = 5;
    drawField(fieldSize);
    // *|3|4| | | |*
  }

  public static void drawField (int n) {
    // use a 2d map?
    String output = "";
    for (int i = 1; i <= n+2; i++) {
      output = output + "*******" + "\n";
    }
    System.out.println(output);
  }
}