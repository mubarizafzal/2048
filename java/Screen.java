import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import javax.swing.JFrame;

public class Screen extends JFrame {

  public Screen () {

    Board game = new Board();
    add(game);

    // only take checks when not drawing

    addKeyListener(new KeyAdapter () {
      public void keyPressed (KeyEvent e) {

        int key = e.getKeyCode();
        

        if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {

          game.step(key);
          game.repaint();      
        }


      }
    });
    
    setSize(300,300);
    setVisible(true);

  }

  public static void main (String[] args) {
    new Screen();
  }



}