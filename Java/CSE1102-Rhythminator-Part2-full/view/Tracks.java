package view;

import javax.swing.JPanel;

import controller.Controller;

public class Tracks extends JPanel
{

  private static final int SQUARE_SIZE = 30;
  private static final int GAP_SIZE = 10;

  private static final long serialVersionUID = 1L;

  public Tracks(Controller controller, int numTracks, int numBeats)
  {
    super();
    this.setLayout(null);
    int x = 0, y = 0;
    for(int trackNum=0; trackNum<numTracks; trackNum++)
    {
      for(int beatNum=0; beatNum<numBeats; beatNum++)
      {
        NoteSquare note = new NoteSquare(controller, trackNum, beatNum);
        note.setSize(SQUARE_SIZE, SQUARE_SIZE);
        note.setLocation(x, y);
        this.add(note);
        x += SQUARE_SIZE + GAP_SIZE;
      }
      x = 0;
      y += View.TRACK_HEIGHT;
    }
    int width = numBeats * (SQUARE_SIZE + GAP_SIZE);
    int height = numTracks * (SQUARE_SIZE + GAP_SIZE);
    this.setSize(width, height);
  }

}
