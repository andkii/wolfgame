
package animals;

public class Bear extends Animal {
    private int counter = -1;
    public Bear() { super('B'); }
    public Attack fight(char c) { return Attack.PAPER; }
    public Move move() {
        if (++counter == 16) counter = 0;
        switch (counter / 4) {
            case 0: return Move.DOWN;
            case 1: return Move.RIGHT;
            case 2: return Move.UP;
            default: return Move.LEFT;
        }
    }
}
