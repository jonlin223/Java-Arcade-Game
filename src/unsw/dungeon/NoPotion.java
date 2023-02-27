package unsw.dungeon;

public class NoPotion implements Potion {

    @Override
    public int getDuration() {
        return 1;
    }

    @Override
    public void lowerDuration() {
        // Hi this doesn't do anything
        // How's your day?
    }

    @Override
    public void addEffect(Player player) {
        // My favourite food is pizza
    }

    @Override
    public void removeEffect(Player player) {
        // How's this for extensibility
    }

}