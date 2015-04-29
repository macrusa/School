package message;


public class Sequencer_old implements IPublisher, ISubscriber
{
  private ISubscriber[] _subscribers;
  private int _nextSubscriber = 0;
  private int _nextNotify = 0;

  public Sequencer_old(int numSubscribers)
  {
    _subscribers = new ISubscriber[numSubscribers];
  }

  public int getStepNumber()
  {
    return _nextNotify;
  }

  @Override
  public void notify(Message message)
  {
    if(_nextNotify == _subscribers.length)
      _nextNotify = 0;
    SequencerMessage msg = new SequencerMessage(this, _nextNotify);
    System.out.println("Sequencer.notify " + _nextNotify);
    ISubscriber subscriber = _subscribers[_nextNotify++];
    if(subscriber != null)
      subscriber.notify(msg);
  }

  public void setStep(int stepNumber, ISubscriber subscriber)
  {
    _subscribers[stepNumber] = subscriber;
  }

  @Override
  public void subscribe(ISubscriber subscriber)
  {
    _subscribers[_nextSubscriber++] = subscriber;
  }

  @Override
  public void unsubscribe(ISubscriber subscriber)
  {
  }

}
