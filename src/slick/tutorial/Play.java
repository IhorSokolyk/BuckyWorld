package slick.tutorial;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState {

	Animation bucky, movingUp, movingDown, movingLeft, movingRight;
	Image worldMap;
	boolean quit = false;
	int[] duration = { 200, 200 };
	float buckyPositionX = 0;
	float buckyPositionY = 0;
	float shiftX = buckyPositionX + 320;
	float shiftY = buckyPositionY + 160;

	public Play(int state) {

	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		worldMap = new Image("res/world.png");
		Image[] walkUp = { new Image("res/buckysBack.png"),
				new Image("res/buckysBack.png") };
		Image[] walkDown = { new Image("res/buckysFront.png"),
				new Image("res/buckysFront.png") };
		Image[] walkLeft = { new Image("res/buckysLeft.png"),
				new Image("res/buckysLeft.png") };
		Image[] walkRight = { new Image("res/buckysRight.png"),
				new Image("res/buckysRight.png") };

		movingUp = new Animation(walkUp, duration, false);
		movingDown = new Animation(walkDown, duration, false);
		movingLeft = new Animation(walkLeft, duration, false);
		movingRight = new Animation(walkRight, duration, false);
		bucky = movingDown;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		worldMap.draw(buckyPositionX, buckyPositionY);
		bucky.draw(shiftX, shiftY);
		g.drawString("Buckys X: " + buckyPositionX + "\nBuckys Y: "
				+ buckyPositionY, 400, 20);

		if (quit) {
			g.drawString("Resume(R)", 250, 100);
			g.drawString("Main Manu(M)", 250, 150);
			g.drawString("Quit Game(Q)", 250, 200);
			if (!quit) {
				g.clear();
			}
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();

		if (input.isKeyDown(Input.KEY_UP)) {
			bucky = movingUp;
			buckyPositionY += delta * .1f;
			if (buckyPositionY > 162) {
				buckyPositionY -= delta * .1f;
			}
		}

		if (input.isKeyDown(Input.KEY_DOWN)) {
			bucky = movingDown;
			buckyPositionY -= delta * .1f;
			if (buckyPositionY < -600) {
				buckyPositionY += delta * .1f;
			}
		}

		if (input.isKeyDown(Input.KEY_RIGHT)) {
			bucky = movingRight;
			buckyPositionX -= delta * .1f;
			if (buckyPositionX < -840) {
				buckyPositionX += delta * .1f;
			}
		}

		if (input.isKeyDown(Input.KEY_LEFT)) {
			bucky = movingLeft;
			buckyPositionX += delta * .1f;
			if (buckyPositionX > 324) {
				buckyPositionX -= delta * .1f;
			}
		}

		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			quit = true;
		}

		if (quit) {
			if (input.isKeyDown(Input.KEY_R)) {
				quit = false;
			}
			if (input.isKeyDown(Input.KEY_M)) {
				game.enterState(0);
				quit = false;
			}
			if (input.isKeyDown(Input.KEY_Q)) {
				System.exit(0);
			}
		}
	}

	@Override
	public int getID() {

		return 1;
	}

}
