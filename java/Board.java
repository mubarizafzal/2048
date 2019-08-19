

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Board extends JPanel {

  int fieldSize = 5;

  ArrayList<ArrayList<Square>> fieldSquares = new ArrayList<>();

  int emptySquares = newField(fieldSquares, fieldSize);
  
  //emptySquares = addRandom(fieldSquares, emptySquares);

  
  ArrayList<String> field = drawField(fieldSquares, fieldSize);




  public Board () {

    
    setBackground(Color.RED);
    emptySquares = addRandom(fieldSquares, emptySquares);


  }

  public void step (int n) {

        

    if (n == 38) {

      emptySquares = moveUp(fieldSquares, emptySquares);

    } else if (n == 40) {

      emptySquares = moveDown(fieldSquares, emptySquares);

    } else if (n == 39) {

      emptySquares = moveRight(fieldSquares, emptySquares);

    } else if (n == 37) {

      emptySquares = moveLeft(fieldSquares, emptySquares);

    }

    emptySquares = addRandom(fieldSquares, emptySquares);


  }





  @Override
  public void paintComponent (Graphics g) {
    super.paintComponent(g);


    
    setBackground(Color.WHITE);

    field = drawField(fieldSquares, fieldSize);

    for (int i = 0; i < field.size(); i++) {
      g.drawString(field.get(i), 10, i*10 + 10);
    }


  }

  public ArrayList<String> drawField (ArrayList<ArrayList<Square>> squares, int n) {
    String output = "";

    ArrayList<String> lines = new ArrayList<String>();

    for (int i = 1; i <= n+2; i++) {

      for (int j = 1; j <= n+2; j++) {
        
        if ( i == 1 || i == n+2 || j == 1 || j == n+2) {
          output = output + " * ";
        } else {
          String val = squares.get(i - 2).get(j - 2).toString();
          if (val.length() > 2) {
            output = output + val;
          } else if (val.length() > 1) {
            output = output + " " + val;
          } else {
            output = output + " " + val + " ";
          }
        }
      }
      if (i != n+2) {

        lines.add(output);
        output = "";
        //output = output + "\n" + "\n";
      }
    }
    lines.add(output);
    return lines;
  }

  public int moveUp (ArrayList<ArrayList<Square>> squares, int emptySquares) {
    
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

  public int moveDown (ArrayList<ArrayList<Square>> squares, int emptySquares) {
    
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

  public int moveRight (ArrayList<ArrayList<Square>> squares, int emptySquares) {
    
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

  public int moveLeft (ArrayList<ArrayList<Square>> squares, int emptySquares) {
    
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

  public int addRandom (ArrayList<ArrayList<Square>> squares, int emptySquares) {

    Random random = new Random();
    int position = random.nextInt(emptySquares) + 1;
    int count = 0;
    for (ArrayList<Square> list : squares) {
      for (Square current : list) {
        if (current.getVal() == 0){
          count++;
        }
        if (count == position) {
          current.setVal(2);
          return emptySquares - 1;
        }
      }
    }
    return emptySquares;
  }

  public int newField(ArrayList<ArrayList<Square>> squares, int n) {
    squares.clear();
    for (int i = 0; i < n; i++){
      squares.add(new ArrayList<Square>());
      for (int j = 0; j < n; j++) {
        squares.get(i).add(new Square(j, i));
      }
    }
    return n*n;
  }






  


}