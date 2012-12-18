package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.fhflensburg.graveyardmanager.core.PlayerInput;
import de.fhflensburg.graveyardmanager.core.map.Map;
import de.fhflensburg.graveyardmanager.states.controller.InGameController;
import de.fhflensburg.graveyardmanager.utils.Timer;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
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

	private Map map;
	private PlayerInput input;
	private int xScrollDecal;
	private int yScrollDecal;
	private boolean mouseRightPressed;
	private boolean mouseLeftPressed;
	private float mouseScrollSpeed;
	public boolean isMinimapEnabled;

	private Nifty nifty;
	private Screen screen;
	View view;
	InGameController gameController;

	private Timer newOrderFlash;

	/**
	 * The default constructor
	 */
	public InGameView()
	{
		super();
		input = new PlayerInput(this);
		mouseScrollSpeed = DEFAULT_MOUSE_SCROLL_SPEED;
		isMinimapEnabled = false;
		newOrderFlash = new Timer(1000);
	}

	/**
	 * Called by Slick2D
	 *
	 * @return The unique id of this state
	 */
	@Override
	public int getID()
	{
		return GraveyardManagerGame.GameStates.IN_GAME_STATE.ordinal();
	}

	/**
	 * Will be called every time we enter this state
	 * Every time we enter this state, we have to reinitialize the map because
	 * the player could have abort the last game and starts a new one with a different map.
	 *
	 * @param gameContainer The game container
	 * @param stateBasedGame A representation of the game
	 * @throws SlickException
	 */
	@Override
	protected void enterState(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		super.enterState(gameContainer, stateBasedGame);
		map = game.getMap();
		map.init(this);
		for (int i = 0; i < gameController.nonVisibleElementsAtStart.length; i++)
		{
			gameController.nonVisibleElementsAtStart[i].hide();
		}
		newOrderFlash.resetTime();
		gameController.newOrdersNoticed = false;
	}

	/**
	 * Here you have to initialize all resources (images, fonts, sounds, ...)
	 * which you want to use in this state. The method will be called by the ResourceView and
	 * pre load all the stuff.
	 */
	public void initResources()
	{
	}

	/**
	 *
	 * @param gameContainer The game container
	 * @param stateBasedGame A representation of the game
	 * @throws SlickException
	 */
	@Override
	public void initGameAndGUI(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		super.initGameAndGUI(gameContainer, stateBasedGame);
	}

	/**
	 *
	 * @param gameContainer The game container
	 * @param stateBasedGame
	 * @param g
	 * @throws SlickException
	 */
	@Override
	public void renderGame(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException
	{
		map.render(gameContainer, g, -xScrollDecal, -yScrollDecal);
		if (isMinimapEnabled) {
			map.renderMiniMap(g, gameContainer.getWidth() - 200 + 25, 25, 150, 150, -xScrollDecal, -yScrollDecal);
		}
	}

	/**
	 *
	 * @param gameContainer The game container
	 * @param stateBasedGame A representation of the game
	 * @param delta
	 * @throws SlickException
	 */
	@Override
	public void updateGame(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException
	{
		int mx = gameContainer.getInput().getMouseX();
		int my = gameContainer.getInput().getMouseY();

		// sum up the time
		newOrderFlash.update(delta);

		if ((newOrderFlash.isTimeComplete()) && (!gameController.newOrdersNoticed))
		{
			newOrderFlash.resetTime();
			gameController.changeOrderImage();
		}
		else if (gameController.newOrdersNoticed)
		{
			gameController.changeOrderImage();
		}



		mouseLeftPressed = gameContainer.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
		//mouseRightPressed = gameContainer.getInput().isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON);

//		if (mouseLeftPressed)
//		{
//			//GameSound.inGameMouseClick();
//		}

		// UPDATE SCROLL
		if (!container.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && map.isNeedScroll()) {
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

		if (container.getInput().isKeyPressed(Input.KEY_SPACE))
		{
			game.enterState(GraveyardManagerGame.GameStates.PAUSE_STATE.ordinal());
		}
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE))
		{
			game.enterState(GraveyardManagerGame.GameStates.MAIN_MENU_STATE.ordinal());
		}
	}

	/**
	 * Load here all the gui stuff
	 *
	 * @param nifty An instance of the nifty object
	 * @param stateBasedGame A representation of the game
	 */
	@Override
	public void prepareNifty(Nifty nifty, StateBasedGame stateBasedGame)
	{
//		nifty.loadStyleFile(GUI_PATH + "nifty-default-styles.xml");
//		nifty.loadControlFile(GUI_PATH + "nifty-default-controls.xml");

		gameController = new InGameController(stateBasedGame, this);
		nifty.fromXml(GUI_PATH + "ingame.xml", "start", gameController);
	}

	/**
	 * Returns the current game container
	 *
	 * @return The current game container
	 */
	public GameContainer getContainer()
	{
		return container;
	}

	public int getMouseX()
	{
		return container.getInput().getMouseX() + (-xScrollDecal);
	}

	public int getMouseY()
	{
		return container.getInput().getMouseY() + (-yScrollDecal);
	}

	public int getXScrollDecal()
	{
		return xScrollDecal;
	}

	public int getYScrollDecal()
	{
		return yScrollDecal;
	}

	public boolean isMouseRightPressed()
	{
		return mouseRightPressed;
	}

	public boolean isMouseLeftPressed()
	{
		return mouseLeftPressed;
	}
}
