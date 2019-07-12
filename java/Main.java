import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
  public static void main (String args[]) {
    int fieldSize = 5;

    ArrayList<ArrayList<Square>> fieldSquares = new ArrayList<>();
    
    int emptySquares = newField(fieldSquares, fieldSize);
    
    System.out.println(emptySquares);

    addRandom(emptySquares);
    drawField(fieldSquares, fieldSize);

    System.out.println("Welcome to terminal 2048 made in Java. The size of the board is " + fieldSize + "x" + fieldSize +".");
    System.out.println("To push the square up, type 'up'");
    System.out.println("To push the square down, type 'down'");
    System.out.println("To push the square right, type 'right'");
    System.out.println("To push the square left, type 'left'");
    System.out.println("To start a new game, type 'reset'");
    System.out.println("To quit, type 'q'");
    System.out.println("---------------------------");

    Scanner in = new Scanner (System.in);
    String line = "";

    while (!line.equals("q")) {
      line = in.nextLine();
      if (line == "up") {

      }
    }

    // if current val *2 == hit value then collide
    // *|3|4| | | |*
  }


  public static void addRandom (int emptySquares) {
    // size of list of squares
    // val1 = area of field - amouont of squares
    Random random = new Random();
    int place = random.nextInt(emptySquares - 1) + 1;
    System.out.println(place);
    // got through squares, increment only when empty square, and create new square at location from random
  }

  public static int newField(ArrayList<ArrayList<Square>> squares, int n) {
    for (int i = 2; i < n+2; i++){
      squares.add(new ArrayList<Square>());
      for (int j = 2; j < n+2; j++) {
        squares.get(i - 2).add(new Square(i, j));
      }
    }
    return n*n;
  }

  public static void drawField (ArrayList<ArrayList<Square>> squares, int n) {
    String output = "";
    for (int i = 1; i <= n+2; i++) {
      for (int j = 1; j <= n+2; j++) {
        if ( i == 1 || i == n+2 || j == 1 || j == n+2) {
          output = output + "*";
        } else {
          output = output + squares.get(i - 2).get(j - 2).toString();
        }

      }
      if (i != n+2) {
        output = output + "\n";
      }
    }
    System.out.println(output);
  }
}

// empty field
// place first random
// up down left right
// random 1 or 2 on empty spot
// add colliding values
