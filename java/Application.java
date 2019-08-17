/**
 * events for arrow keys, event for quit/reset
 * 
 * tile image
 * 
 * colour change as tile size grows
 * 
 * visualize board based on array
 */

import javax.swing.JFrame;
import java.awt.EventQueue;


public class Application extends JFrame {

  public Application () {

    initUI ();
  }

  private void initUI () {

    add(new Field());
    setResizable(false);
    //pack();
    setSize(300,300);

    setTitle("5x5 2048");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  public static void main (String[] args) {

    EventQueue.invokeLater(() -> {

      Application game = new Application();
      game.setVisible(true);
    
    });

  }




}