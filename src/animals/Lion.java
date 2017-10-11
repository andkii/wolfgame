
package animals;

public class Lion extends Animal {
    private boolean toggle = true;
    public Lion() { super('L'); }
    public Attack fight(char c) { return Math.random() > 0.5 ? Attack.PAPER : Attack.SCISSORS; }
    public Move move() { toggle = !toggle; return toggle ? Move.DOWN : Move.RIGHT; }
}
