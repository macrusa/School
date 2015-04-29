package view;

import gui.NumberSlider;
import controller.Controller;

public class View
{

  public  static final int TRACK_HEIGHT = 40; // this must be public
  private static final int LEFT_MARGIN  = 20;
  private static final int TOP_MARGIN   = 20;

  private BeatNumbers _beatNumbers;
  private SoundBank _soundBank;

  public View(Controller controller, int numTracks, int numBeats)
  {
    Window window = new Window(controller, "Rhythminator");

    Header header = new Header(controller);
    header.setLocation(LEFT_MARGIN, TOP_MARGIN);
    window.add(header);

    _soundBank = new SoundBank(controller, numTracks);
    _soundBank.setLocation(LEFT_MARGIN, header.getY() + header.getHeight() + 20);
    window.add(_soundBank);

    Tracks tracks = new Tracks(controller, numTracks, numBeats);
    tracks.setLocation(LEFT_MARGIN + _soundBank.getWidth() + 20, _soundBank.getY());
    window.add(tracks);

    // the model will need to be able to ask the View to show the active beat number,
    // so the BeatNumbers instance needs to be a member variable so we can have
    // access to it later
    _beatNumbers = new BeatNumbers(numBeats);
    _beatNumbers.setLocation(tracks.getX(), tracks.getY() + tracks.getHeight());
    window.add(_beatNumbers);

    ControlButtons controlButtons = new ControlButtons(controller);
    controlButtons.setLocation(LEFT_MARGIN, _soundBank.getY() + _soundBank.getHeight());
    window.add(controlButtons);
    
    NumberSlider tempoSlider = new NumberSlider(controller, "Tempo", 1, 200, 60);
    tempoSlider.setLocation(_beatNumbers.getX(), _beatNumbers.getY() + _beatNumbers.getHeight() + 20);
    window.add(tempoSlider);
    
    window.setVisible(true);
  }

  public void clearBeatNumbers()
  {
    _beatNumbers.clear();
  }

  public void setBeatNumber(int beatNumber)
  {
    _beatNumbers.setBeatNumber(beatNumber);
  }

  public void setSoundName(int trackNumber, String soundName)
  {
    _soundBank.setSoundName(trackNumber, soundName);
  }

}
