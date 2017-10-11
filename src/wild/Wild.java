package wild;

import animals.*;
import gui.*;

public class Wild {

	private Game game;
	private Statistics stats;
	private TextGui gui;

	private static final Class[] classes = { 
			Bear.class, 
			Lion.class, 
			Stone.class, 
			Wolf.class
	};
	
	public static final int MAP_SIZE = Math.round((float)Math.sqrt(classes.length+3)*20);
	public static final boolean USE_GUI = true;

	private void run(){
		this.init();
		this.gameLoop();
		this.gameOver();
	}

	private void init(){
		this.initGame();
		this.gui = new TextGui();
		
		if(USE_GUI){
			this.gui.init(game.toString(), stats.toString());
		}
	}

	private void initGame(){
		int size = Math.round((float)Math.sqrt(classes.length+3)*20);
		this.game = new Game(size);
		this.stats = new Statistics(this.game, classes);

		for(Class c : classes)
			this.game.populate(c, 100);

		this.stats.update();
	}

	private void gameLoop(){
		for (int j = 0; j < 1000; j++) {
			this.game.iterate();
			this.stats.update();
			if(USE_GUI){
				this.gui.update(game.toString(), stats.toHtmlString());
				try { Thread.sleep(50); } 
				catch (InterruptedException e) {

				}
			}
			
		}
	}

	private void gameOver(){
		this.gui.showResults(stats.toString());
	}

	public static void main(String[] args) {
		Wild wild = new Wild();
		wild.run();
	}
	
}
