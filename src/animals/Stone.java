
package animals;

public class Stone extends Animal {
    public Stone() { super('S'); }
    public Attack fight(char c) { return Attack.ROCK; } 
    public Move move() { return Move.HOLD; }
}
