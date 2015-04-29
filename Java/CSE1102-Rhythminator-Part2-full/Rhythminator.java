import javax.swing.SwingUtilities;

import model.Sound;
import controller.Controller;
import view.View;

public class Rhythminator
{

  private static final int NUM_TRACKS = 6;
  private static final int NUM_BEATS  = 16;

  public static void main(String[] args)
  {
    // In order to use Swing graphics effectively, the program should be started
    // running in a concurrent thread controlled by Swing. This is how you do it:
    SwingUtilities.invokeLater(new Runnable()
    {
      // the main program should appear inside this run method
      @Override
      public void run()
      {
        _main();
      }
    });
  }

  // I put all the interesting stuff in a separate method here so that you don't
  // have to keep looking at the Swing threading stuff in the main method above.
  private static void _main()
  {
    Sound.scanSoundDir();
    //Conductor conductor = new Conductor();
    //Model model = new Model(conductor, NUM_TRACKS, NUM_BEATS);
    Controller controller = new Controller();
    View view = new View(controller, NUM_TRACKS, NUM_BEATS);
    controller.setView(view);
  }

}
