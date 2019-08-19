import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Board extends JPanel {

  boolean canDraw = true;

  int fieldSize = 5;

  ArrayList<ArrayList<Square>> fieldSquares = new ArrayList<>();

  int emptySquares = newField(fieldSquares, fieldSize);
  

  public Board () {
    emptySquares = addRandom(fieldSquares, emptySquares);

  }


  public int getFieldSize () {
    return fieldSize;
  }

  public boolean allowed () {
    return canDraw;
  }

  public void setAllow (boolean val) {

    canDraw = val;
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

    g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
    
    setBackground(Color.WHITE);

    draw(g,fieldSquares, fieldSize);
    
  }

  public void draw (Graphics g, ArrayList<ArrayList<Square>> squares, int n) {

    for (int i = 0; i < n; i++) {

      for (int j = 0; j < n; j++) {

        int val = squares.get(i).get(j).getVal();
        int x = squares.get(i).get(j).getXCord();
        int y = squares.get(i).get(j).getYCord();
        x = (x*50) + x*10;
        y = (y*50) + y*10;

        if (val != 0) {        
          float scale = (float)(Math.log(val) / Math.log(2));

          float mult1 = 1 - ((scale*4)/100);

          float mult2 = ((scale*9)/100);
        
          g.setColor(Color.getHSBColor(0.531f - mult2, 0.54f, 0.73f*mult1));
          g.fillRoundRect(x, y, 50, 50, 20, 15);

          String tempVal = Integer.toString(val);
          int shift = tempVal.length();
          shift = shift*6;

          g.setColor(Color.WHITE);
          g.drawString(tempVal, x + 25 - shift, y + 30);

          

        } else {

          g.setColor(Color.GRAY);
          g.fillRoundRect(x, y, 50, 50, 20, 15);

        }

        
        
        
      }
    }
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


    if (emptySquares == 0) {
      return 0;
    }

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