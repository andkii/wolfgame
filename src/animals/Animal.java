
package animals;

public abstract class Animal {
    public final char letter;
    public char[][] surroundings;
    public final int MAP_SIZE;
    
    public static enum Attack { ROCK, PAPER, SCISSORS, SUICIDE }
    public static enum Move { UP, RIGHT, DOWN, LEFT, HOLD }

    public Animal(char letter){ this.letter = letter; MAP_SIZE = wild.Wild.MAP_SIZE;}
    
    public abstract Attack fight(char c);
    public abstract Move move();
}
