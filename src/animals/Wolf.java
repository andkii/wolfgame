
package animals;

public class Wolf extends Animal {
    public Wolf() { super('W'); }
    public Attack fight(char c) { 
        switch (c) {
            case 'B':
                return Attack.SCISSORS;
            case 'L':
                return Attack.SCISSORS;
            case 'S':
                return Attack.PAPER;
            default:
                return Attack.ROCK;
        } 
    }
    public Move move() {
        if (surroundings[0][1] == ' ')
            return Move.UP;
        else if (surroundings[1][2] == ' ')
            return Move.RIGHT;
        else if (surroundings[1][0] == ' ')
            return Move.LEFT;
        else
            return Move.DOWN;
    }
}
