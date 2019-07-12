import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
  public static void main (String args[]) {
    int fieldSize = 5;

    ArrayList<ArrayList<Square>> fieldSquares = new ArrayList<>();

    int emptySquares = newField(fieldSquares, fieldSize);
    
    
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
    
    emptySquares = addRandom(fieldSquares, emptySquares);
    drawField(fieldSquares, fieldSize);

    while (!line.equals("q")) {
      line = in.nextLine();
      if (line.equals("up")) {
        emptySquares = moveUp(fieldSquares, emptySquares);
        emptySquares = addRandom(fieldSquares, emptySquares);
        System.out.println("empties: " + emptySquares);
        drawField(fieldSquares, fieldSize);
      }
      if (line.equals("down")) {
        emptySquares = moveDown(fieldSquares, emptySquares);
        emptySquares = addRandom(fieldSquares, emptySquares);
        System.out.println("empties: " + emptySquares);
        drawField(fieldSquares, fieldSize);

      }
      if (line.equals("right")) {
        emptySquares = moveRight(fieldSquares, emptySquares);
        emptySquares = addRandom(fieldSquares, emptySquares);
        System.out.println("empties: " + emptySquares);
        drawField(fieldSquares, fieldSize);

      }
      if (line.equals("left")) {
        emptySquares = moveLeft(fieldSquares, emptySquares);
        emptySquares = addRandom(fieldSquares, emptySquares);
        System.out.println("empties: " + emptySquares);
        drawField(fieldSquares, fieldSize);

      }
      if (line.equals("reset")) {
        emptySquares = newField(fieldSquares, fieldSize);
        emptySquares = addRandom(fieldSquares, emptySquares);
        System.out.println("empties: " + emptySquares);
        drawField(fieldSquares, fieldSize);
      }
    }

  }

  public static int moveUp (ArrayList<ArrayList<Square>> squares, int emptySquares) {
    
    for (int i = 0; i < squares.size(); i++) {

      for (int j = 0; j < squares.size(); j++) {
        

        int val = squares.get(j).get(i).getVal();

        if (val > 0) {
          int newPos = j;

          for (int k = j - 1; k >= 0; k--) {
            int pathVal = squares.get(k).get(i).getVal();
            if (pathVal == 0) {
               
              newPos = k;
            } else if (pathVal == val) {
              emptySquares++;
              newPos = k;
              k = -1;
            } else {
              k = -1;
            }
          }
          if (newPos != j) {
            int oldVal = squares.get(newPos).get(i).getVal();
            squares.get(newPos).get(i).setVal(val + oldVal);
            squares.get(j).get(i).setVal(0);
          }
          

        }


      }
    }
    return emptySquares;
  }

  public static int moveDown (ArrayList<ArrayList<Square>> squares, int emptySquares) {
    
    for (int i = 0; i < squares.size(); i++) {

      for (int j = squares.size() - 1; j >= 0 ; j--) {
        

        int val = squares.get(j).get(i).getVal();

        if (val > 0) {
          int newPos = j;

          for (int k = j + 1; k < squares.size(); k++) {
            int pathVal = squares.get(k).get(i).getVal();
            if (pathVal == 0) {
               
              newPos = k;
            } else if (pathVal == val) {
              emptySquares++;
              newPos = k;
              k = squares.size();
            } else {
              k = squares.size();
            }
          }
          if (newPos != j) {
            int oldVal = squares.get(newPos).get(i).getVal();
            squares.get(newPos).get(i).setVal(val + oldVal);
            squares.get(j).get(i).setVal(0);
          }
          

        }


      }
    }
    return emptySquares;

  }

  public static int moveRight (ArrayList<ArrayList<Square>> squares, int emptySquares) {
    
    for (int i = 0; i < squares.size(); i++) {

      for (int j = squares.size() - 1; j >= 0 ; j--) {
        

        int val = squares.get(i).get(j).getVal();

        if (val > 0) {
          int newPos = j;

          for (int k = j + 1; k < squares.size(); k++) {
            int pathVal = squares.get(i).get(k).getVal();
            if (pathVal == 0) {
               
              newPos = k;
            } else if (pathVal == val) {
              emptySquares++;
              newPos = k;
              k = squares.size();
            } else {
              k = squares.size();
            }
          }
          if (newPos != j) {
            int oldVal = squares.get(i).get(newPos).getVal();
            squares.get(i).get(newPos).setVal(val + oldVal);
            squares.get(i).get(j).setVal(0);
          }
          

        }


      }
    }
    return emptySquares;
  }

  public static int moveLeft (ArrayList<ArrayList<Square>> squares, int emptySquares) {
    
    for (int i = 0; i < squares.size(); i++) {

      for (int j = 0; j < squares.size(); j++) {
        

        int val = squares.get(i).get(j).getVal();

        if (val > 0) {
          int newPos = j;

          for (int k = j - 1; k >= 0; k--) {
            int pathVal = squares.get(i).get(k).getVal();
            if (pathVal == 0) {
               
              newPos = k;
            } else if (pathVal == val) {
              emptySquares++;
              newPos = k;
              k = -1;
            } else {
              k = -1;
            }
          }
          if (newPos != j) {
            int oldVal = squares.get(i).get(newPos).getVal();
            squares.get(i).get(newPos).setVal(val + oldVal);
            squares.get(i).get(j).setVal(0);
          }
          

        }


      }
    }
    return emptySquares;
  }


  public static int addRandom (ArrayList<ArrayList<Square>> squares, int emptySquares) {

    Random random = new Random();
    int position = random.nextInt(emptySquares) + 1;
    int count = 0;
    for (ArrayList<Square> list : squares) {
      for (Square current : list) {
        if (current.getVal() == 0){
          count++;
        }
        if (count == position) {
          current.setVal(random.nextInt(1) + 1);
          return emptySquares - 1;
        }
      }
    }
    return emptySquares;
  }

  public static int newField(ArrayList<ArrayList<Square>> squares, int n) {
    squares.clear();
    for (int i = 0; i < n; i++){
      squares.add(new ArrayList<Square>());
      for (int j = 0; j < n; j++) {
        squares.get(i).add(new Square(j, i));
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