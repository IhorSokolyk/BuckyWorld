package slick.tutorial;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

;

public class Game extends StateBasedGame {

	public static final String GAME_NAME = "Ham Blaster!";
	public static final int MANU = 0;
	public static final int PLAY = 1;

	public Game(String gamename) {
		super(gamename);
		this.addState(new Manu(MANU));
		this.addState(new Play(PLAY));
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.getState(MANU).init(container, this);
		this.getState(PLAY).init(container, this);
		this.enterState(MANU);

	}

	public static void main(String[] args) {
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new Game(GAME_NAME));
			appgc.setDisplayMode(640, 360, false);
			appgc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
