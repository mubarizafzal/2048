import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class Screen extends JFrame {

  public Screen () {

    Board game = new Board();
    add(game);

    addKeyListener(new KeyAdapter () {

      public void keyPressed (KeyEvent e) {

        if (game.allowed()) {
          int key = e.getKeyCode();
        
          if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
            game.step(key);
            game.repaint();      
          }
        }

        game.setAllow(false);
        
      }

      public void keyReleased (KeyEvent e) {
        game.setAllow(true);
      }
    });
    
    int windowSize = game.getFieldSize()*50 + game.getFieldSize()*10;

    setSize(windowSize,windowSize + 30);
    setVisible(true);
    setResizable(false);
    setFocusable(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  public static void main (String[] args) {
    new Screen();
  }

}
