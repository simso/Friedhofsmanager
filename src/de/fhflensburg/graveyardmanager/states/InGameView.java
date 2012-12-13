package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GameSound;
import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.fhflensburg.graveyardmanager.core.PlayerInput;
import de.fhflensburg.graveyardmanager.core.map.Map;
import de.fhflensburg.graveyardmanager.utils.ResourceManager;
import de.lessvoid.nifty.Nifty;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 29.11.12
 * Time: 23:22
 */
public class InGameView extends View
{
	// Configs and others
	private static float DEFAULT_MOUSE_SCROLL_SPEED = 0.2f;
	private static final int LIMIT_BEFORE_SCROLL = 20;
	private static final int PATHFINDING_MAX_SEARCH_DISTANCE = 100;
	private static final int TIME_BEFORE_SEE_GAME = 5000;

	private Map map;
	private PlayerInput input;
	private int xScrollDecal;
	private int yScrollDecal;
	//private boolean mouseRightPressed;
	private boolean mouseLeftPressed;
	private float mouseScrollSpeed;

	public InGameView()
	{
		input = new PlayerInput(this);
		mouseScrollSpeed = DEFAULT_MOUSE_SCROLL_SPEED;
	}

	@Override
	protected void enterState(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	protected void leaveState(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Returns the id of the state
	 *
	 * @return int id
	 */
	@Override
	public int getID()
	{
		return GraveyardManagerGame.GameStates.IN_GAME_STATE.ordinal();
	}

	@Override
	public void initResources()
	{
		map = ResourceManager.getMap("Desert");
		map.init(this);
	}

	@Override
	public void initGameAndGUI(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		super.initGUI(gameContainer, stateBasedGame);
		initNifty(gameContainer, stateBasedGame);

	}


	@Override
	public void renderGame(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException
	{
		map.render(gameContainer, g, -xScrollDecal, -yScrollDecal);
		map.renderMiniMap(g, gameContainer.getWidth() - 200 + 25, 25, 150, 150, -xScrollDecal, -yScrollDecal);
	}

	@Override
	public void updateGame(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException
	{
		int mx = gameContainer.getInput().getMouseX();
		int my = gameContainer.getInput().getMouseY();

		mouseLeftPressed = gameContainer.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
		//mouseRightPressed = gameContainer.getInput().isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON);

		if (mouseLeftPressed)
		{
			GameSound.inGameMouseClick();
		}

		// UPDATE SCROLL
		if (/*!input.isPressedLeft() && */!container.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && map.isNeedScroll()) {
			float s = (container.getInput().isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) ? mouseScrollSpeed * delta * 2 : mouseScrollSpeed * delta;

			xScrollDecal += (mx < LIMIT_BEFORE_SCROLL && xScrollDecal + s < 0) ? s : 0;
			xScrollDecal -= (mx > container.getWidth() - LIMIT_BEFORE_SCROLL && xScrollDecal + s > container.getWidth() - map.getWidthInPixel()) ? s : 0;

			yScrollDecal += (my < LIMIT_BEFORE_SCROLL && yScrollDecal + s < 0) ? s : 0;
			yScrollDecal -= (my > container.getHeight() - LIMIT_BEFORE_SCROLL && yScrollDecal + s > container.getHeight() - map.getHeightInPixel()) ? s : 0;

			if (yScrollDecal + s < container.getHeight() - map.getHeightInPixel()) {
				yScrollDecal = container.getHeight() - map.getHeightInPixel();
			}

			if (xScrollDecal + s < container.getWidth() - map.getWidthInPixel()) {
				xScrollDecal = container.getWidth() - map.getWidthInPixel();
			}
		}

		//gui.updateMouseEvent(container, delta);

		// UPDATE MOUSE MOVE AND CLICK
		//input.update(container, gui.isMouseOnGui(container, mx, my), mx, my, -xScrollDecal, -yScrollDecal);


		if (container.getInput().isKeyPressed(Input.KEY_SPACE))
		{
			game.enterState(GraveyardManagerGame.GameStates.PAUSE_STATE.ordinal());
		}
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE))
		{
			game.enterState(GraveyardManagerGame.GameStates.MAIN_MENU_STATE.ordinal());
		}
	}

	@Override
	public void prepareNifty(Nifty nifty, StateBasedGame stateBasedGame)
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	public GameContainer getContainer()
	{
		return container;
	}
}
