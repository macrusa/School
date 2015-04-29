package controller;

import view.NoteSquare;
import view.View;
import gui.Button;
import gui.Dialog;

public class Controller
{

  private View _view;

  public Controller()
  {
  }

  public void buttonPressed(Button button)
  {
    if(button.getText().equals("Save"))
      System.out.println("Controller.buttonPressed Save button pressed");
    else if(button.getText().equals("Load"))
      System.out.println("Controller.buttonPressed Load button pressed");
    else if(button.getText().equals("Play"))
      System.out.println("Controller.buttonPressed Play button pressed");
    else if(button.getText().equals("Stop"))
      System.out.println("Controller.buttonPressed Stop button pressed");
    else if(button.getText().equals("Quit"))
      _buttonQuit();
    else
      System.out.println("Controller.buttonPressed " + button + " pressed");
  }

  private void _buttonQuit()
  {
    if(Dialog.askYesNo("Exiting program", "Really quit?"))
      System.exit(0);
  }

  public void keyPressed(int keyCode)
  {
    System.out.println("Controller.keyPressed " + keyCode);
  }

  public void keyReleased(int keyCode)
  {
    System.out.println("Controller.keyReleased " + keyCode);
  }

  public void keyTyped(char keyChar)
  {
    System.out.println("Controller.keyTyped '" + keyChar + "'");
  }

  public void sliderChange(String name, int _value)
  {
    System.out.println("Controller.sliderChange " + name + " = " + _value);
  }

  public void noteSquareClicked(NoteSquare noteSquare)
  {
    System.out.println("Controller.noteSquareClicked on " + noteSquare);
  }

  public void setView(View view)
  {
    _view = view;
  }

  public void soundNameSelected(int _trackNumber, String soundName)
  {
    System.out.println("Controller.soundNameSelected track " + _trackNumber + ", sound " + soundName);
  }

}
