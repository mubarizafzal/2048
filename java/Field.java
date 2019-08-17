
/**
 * what exactly happens:
 * - key is pressed
 * - key press is detected
 * - calls method that does calculation for that type of key
 * - after field array changes (do we not want to use the array?)
 * - redraw scene (use current strings as is)
 * - keep wating for a key press
 * 
 */

import java.util.ArrayList;
import java.util.Random;


import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Field extends JPanel implements Runnable {

  private int fieldSize = 5;

  private ArrayList<ArrayList<Square>> fieldSquares = new ArrayList<>();

  private int emptySquares = newField(fieldSquares, fieldSize);

  public Field () {

    initField();
  }

  private void initField () {

    /*
    int fieldSize = 5;

    ArrayList<ArrayList<Square>> fieldSquares = new ArrayList<>();

    int emptySquares = newField(fieldSquares, fieldSize);
    */

    addKeyListener(new KeyAdapter() {

      @Override
      public void keyPressed (KeyEvent e) {
        super.keyPressed(e);

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {

          emptySquares = moveUp(fieldSquares, emptySquares);
          

        }
        if (key == KeyEvent.VK_DOWN) {

          emptySquares = moveDown(fieldSquares, emptySquares);

        }
        if (key == KeyEvent.VK_LEFT) {

          emptySquares = moveLeft(fieldSquares, emptySquares);

        }
        if (key == KeyEvent.VK_RIGHT) {

          emptySquares = moveRight(fieldSquares, emptySquares);

        }

        emptySquares = addRandom(fieldSquares, emptySquares);

        //redraw
        repaint();
      }
    });
    setBackground(Color.BLACK);
    // set Pref size?
    setFocusable(true);

    Thread t = new Thread(this);
    t.start();
  
  }

  @Override
  public void run () {
    
  }

  @Override
  public void paintComponent (Graphics g) {
    super.paintComponent(g);

    drawBoard(g);
    
  }

  public void drawBoard (Graphics g) {

    g.setColor(Color.WHITE);
    g.drawString(drawField(fieldSquares, fieldSize), 0, 0);
    
  }

  public static String drawField (ArrayList<ArrayList<Square>> squares, int n) {
    String output = "";
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
        output = output + "\n" + "\n";
      }
    }
    return output;
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
          current.setVal(2);
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

}