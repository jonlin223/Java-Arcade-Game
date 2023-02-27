package unsw.dungeon;

public interface PlayerMovementSubject {
    public void attach(PlayerMovementObserver o);
	public void detach(PlayerMovementObserver o);
	public void notifyObservers();
}