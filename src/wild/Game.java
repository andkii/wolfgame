
package wild;

import animals.Animal;
import java.util.ArrayList;
import java.util.Random;
import animals.Animal.Attack;
import animals.Animal.Move;

public class Game {
	
	private ArrayList<ArrayList<ArrayList<Animal>>> board;
	private final Random gen = new Random();
	protected final int SIZE;
	
	protected Game(int size) {
		this.SIZE = size;
		board = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			board.add(new ArrayList<>());
			for (int j = 0; j < size; j++) {
				board.get(i).add(new ArrayList<>());
			}
		}
	}
	
	protected <T extends Animal> void populate(Class<T> species, int num) {
		while (num > 0) {
			int row = gen.nextInt(SIZE);
			int col = gen.nextInt(SIZE);
			if (board.get(row).get(col).isEmpty()) {
				try { board.get(row).get(col).add(species.newInstance()); } 
				catch (InstantiationException | IllegalAccessException e) {}
				num--;
			}
		}
	}
	
	protected void iterate() {
		moveAll();
		flatten();
	}
	
	private void moveAll() {
		Game game = new Game(SIZE);
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (!board.get(i).get(j).isEmpty()) {
					Animal a = board.get(i).get(j).get(0);
					a.surroundings = getArea(i, j);
					Move aMove;
					try { aMove = a.move(); } 
					catch (Exception e) { aMove = Move.HOLD; }
					catch (Error e) { aMove = Move.HOLD; }
					switch(aMove) {
						case UP:
							game.board.get((i-1+SIZE)%SIZE).get(j).add(a);
							break;
						case RIGHT:
							game.board.get(i).get((j+1)%SIZE).add(a);
							break;
						case DOWN:
							game.board.get((i+1)%SIZE).get(j).add(a);
							break;
						case LEFT:
							game.board.get(i).get((j-1+SIZE)%SIZE).add(a);
							break;
						case HOLD:
							game.board.get(i).get(j).add(a);
							break;
					}
				}
			}
		}
		board = game.board;
	}
	
	private void flatten() {
		for (ArrayList<ArrayList<Animal>> row : board) {
			for (ArrayList<Animal> cell : row) {
				while (cell.size() > 1) {
					int rand1, rand2;
					rand1 = gen.nextInt(cell.size());
					do { rand2 = gen.nextInt(cell.size()); } while (rand1 == rand2);
					
					Animal a = cell.get(rand1);
					Animal b = cell.get(rand2);
					Attack aTack, bTack;
					try { aTack = a.fight(b.letter); } 
					catch (Exception e) { aTack = Attack.SUICIDE; }
					catch (Error e) { aTack = Attack.SUICIDE; }
					try {  bTack = b.fight(a.letter); }
					catch (Exception e) { bTack = Attack.SUICIDE; }
					catch (Error e) { bTack = Attack.SUICIDE; }
					
					if (aTack == bTack) {
						cell.remove((Animal)(Math.random() > 0.5 ? a : b));
					} else {
						switch (aTack) {
							case ROCK:
								cell.remove((Animal)(bTack == Attack.PAPER ? a : b));
								break;
							case PAPER:
								cell.remove((Animal)(bTack == Attack.SCISSORS ? a : b));
								break;
							case SCISSORS:
								cell.remove((Animal)(bTack == Attack.ROCK ? a : b));
								break;
						}
					} 
				}
			}
		}
	}
	
	protected int poll(Class c) {
		int count = 0;
		for (ArrayList<ArrayList<Animal>> row : board) {
			for (ArrayList<Animal> cell : row) {
				for (Animal a : cell) {
					if(c.isInstance(a))
						count++;
				}
			}
		}
		return count;
	}
		
	@Override
	public String toString() {
		String s = "<html>";
		for (ArrayList<ArrayList<Animal>> row : board) {
			for (ArrayList<Animal> cell : row) {
				if (cell.isEmpty())
					s += "&nbsp;&nbsp;";
				else
					s += cell.get(0).letter + "&nbsp;";
			}
			s+="<br>";
		}
		return s + "</html>";
	}

	private char[][] getArea(int i, int j) {
		char[][] area = new char[3][3];
		for(int k = -1; k < 2; k++) {
			for(int l = -1; l < 2; l++) {
				ArrayList<Animal> cell = board.get((i+k+SIZE)%SIZE).get((j+l+SIZE)%SIZE);
				area[k+1][l+1] = (char)(cell.isEmpty() ? ' ' : cell.get(0).letter);
			}
		}
		return area;
	}
}
