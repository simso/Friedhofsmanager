package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.fhflensburg.graveyardmanager.core.Player;
import de.fhflensburg.graveyardmanager.core.PlayerInput;
import de.fhflensburg.graveyardmanager.core.layers.Layer;
import de.fhflensburg.graveyardmanager.core.layers.entities.ActiveEntity;
import de.fhflensburg.graveyardmanager.core.layers.entities.EntityGenerator;
import de.fhflensburg.graveyardmanager.core.layers.entities.IEntity;
import de.fhflensburg.graveyardmanager.core.layers.entities.buildings.Building;
import de.fhflensburg.graveyardmanager.core.map.Map;
import de.fhflensburg.graveyardmanager.core.music.GameMusic;
import de.fhflensburg.graveyardmanager.core.music.GameSound;
import de.fhflensburg.graveyardmanager.level.Level;
<<<<<<< HEAD
import de.fhflensburg.graveyardmanager.core.layers.entities.EntityData;
=======
>>>>>>> 6cf063c3574ff0c51e0ed88bf2e0e2e11447a506
import de.fhflensburg.graveyardmanager.states.controller.InGameController;
import de.fhflensburg.graveyardmanager.utils.ResourceManager;
import de.fhflensburg.graveyardmanager.utils.Timer;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

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
	private Image gameOverImage;
	private Image gameWinImage;
	private boolean gameOver;
	private boolean gameWin;
	private ArrayList<Layer> layers;
	private Level level;

	private Nifty nifty;
	private Screen screen;
	View view;
	InGameController gameController;

	// Timers

	/** Timer for the blinking letter */
	private Timer newOrderFlash;

	/** Game Timer */
	private Timer gameTime;



	// To count the ents
	private int[] entsCount;

	/**
	 * The default constructor
	 */
	public InGameView()
	{
		super();
		input = new PlayerInput(this);
		entsCount = new int[9];
		mouseScrollSpeed = DEFAULT_MOUSE_SCROLL_SPEED;
		isMinimapEnabled = false;
		newOrderFlash = new Timer(1000);
		gameTime = new Timer(30000);
		layers = new ArrayList<Layer>();
		for (int i = 0; i < 5; i++)
		{
			layers.add(new Layer(this, i));
		}
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
	}

	public void initGame(Level level)
	{
		this.level = level;
		GameSound.init();
		GameMusic.playMusic();
		resetEntsCount();
		gameOver = false;
		gameWin = false;
		mouseLeftPressed = false;
		mouseRightPressed = false;
		container.getInput().clearMousePressedRecord();
		container.getInput().clearKeyPressedRecord();
		container.getInput().consumeEvent();
		for (int i = 0; i < layers.size(); i++) {
			layers.get(i).clear();
		}

		for (int i = 0; i < gameController.nonVisibleElementsAtStart.length; i++)
		{
			gameController.nonVisibleElementsAtStart[i].hide();
		}
		newOrderFlash.resetTime();
		gameController.newOrdersNoticed = false;
		getMap().init(this);
	}

	/**
	 * Here you have to initialize all resources (images, fonts, sounds, ...)
	 * which you want to use in this state. The method will be called by the ResourceView and
	 * pre load all the stuff.
	 */
	public void initResources()
	{
		gameWinImage = ResourceManager.getImage("Winner");
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
		// Map related stuff
		// Render the normal map and if enabled the mini map
		// Visibility of the minimap is controlled by Nifty in InGameController
		getMap().render(gameContainer, g, -xScrollDecal, -yScrollDecal);
		if (isMinimapEnabled) {
			getMap().renderMiniMap(g, gameContainer.getWidth() - 200 + 25, 25, 150, 150, -xScrollDecal, -yScrollDecal);
		}

		g.translate(xScrollDecal, yScrollDecal);

		for	(int i = 0; i < layers.size(); i++)
		{
			layers.get(i).render(gameContainer, g);
		}

		g.translate(-xScrollDecal, -yScrollDecal);

		// Draw the a rectangle with green and red rectangles if we allowed to build something here
		if (gameController.getBuilding() != null)
		{
			gameController.getBuilding().renderLocationOnMap(g);

		}

        if (gameOver)
		{
			g.drawImage(gameOverImage, (container.getWidth() / 2) - 240, (container.getHeight() / 2) - 27);
		}
		else if (gameWin)
		{
			g.drawImage(gameWinImage, (container.getWidth() / 2) - 240, (container.getHeight() / 2) - 27);
		}

		gameController.render();
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
		super.updateGame(gameContainer, game, delta);

		int mx = gameContainer.getInput().getMouseX();
		int my = gameContainer.getInput().getMouseY();

		mouseLeftPressed = gameContainer.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON);
		mouseRightPressed = gameContainer.getInput().isMousePressed(Input.MOUSE_RIGHT_BUTTON);

<<<<<<< HEAD
        //GameOverCheck if Money <=0
//        if(getPlayer().getBalance()<=0)
//        {
//            gameOver=true;
//        }

//		sum up the time
=======
		// sum up the time
>>>>>>> 6cf063c3574ff0c51e0ed88bf2e0e2e11447a506
		newOrderFlash.update(delta);

		gameTime.update(delta);

		if ((newOrderFlash.isTimeComplete()) && (!gameController.newOrdersNoticed))
		{
			newOrderFlash.resetTime();
			gameController.changeOrderImage();
		}
		else if (gameController.newOrdersNoticed)
		{
			gameController.changeOrderImage();
		}

		// update scrolling
		if (!gameContainer.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && getMap().isNeedScroll()) {
			float s = (gameContainer.getInput().isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) ? mouseScrollSpeed * delta * 2 : mouseScrollSpeed * delta;

			xScrollDecal += (mx < LIMIT_BEFORE_SCROLL && xScrollDecal + s < 0) ? s : 0;
			xScrollDecal -= (mx > container.getWidth() - LIMIT_BEFORE_SCROLL && xScrollDecal + s > container.getWidth() - getMap().getWidthInPixel()) ? s : 0;

			yScrollDecal += (my < LIMIT_BEFORE_SCROLL && yScrollDecal + s < 0) ? s : 0;
			yScrollDecal -= (my > container.getHeight() - LIMIT_BEFORE_SCROLL && yScrollDecal + s > container.getHeight() - getMap().getHeightInPixel()) ? s : 0;

			if (yScrollDecal + s < container.getHeight() - getMap().getHeightInPixel()) {
				yScrollDecal = container.getHeight() - getMap().getHeightInPixel();
			}

			if (xScrollDecal + s < container.getWidth() - getMap().getWidthInPixel()) {
				xScrollDecal = container.getWidth() - getMap().getWidthInPixel();
			}
		}

		if (isMouseLeftPressed())
		{
			int x = getMouseX() / getTileW();
			int y = getMouseY() / getTileH();
			if (gameController.getBuilding() != null && gameController.getBuilding().isValidLocation())
			{
                ActiveEntity activeEntity = EntityGenerator.createEntity(this, gameController.getBuilding().getType(), getPlayer().getId());
                activeEntity.setLocation(x*getTileW(),y*getTileH());
                addEntity(activeEntity);
<<<<<<< HEAD
                getPlayer().decreaseBalance(EntityData.PRICE[gameController.getBuilding().getType()]);
=======
>>>>>>> 6cf063c3574ff0c51e0ed88bf2e0e2e11447a506


			}
			else if(gameController.getBuilding() != null && !gameController.getBuilding().isValidLocation())
            {
                gameController.resetBuilding();
            }

			gameController.resetBuilding();
		}
		else if (isMouseRightPressed())
		{
			gameController.resetBuilding();
		}

		resetEntsCount();

		for (int i = 0; i < layers.size(); i++)
		{
			layers.get(i).updateAll(gameContainer, delta);
		}

		if (gameContainer.getInput().isKeyPressed(Input.KEY_SPACE))
		{
			game.enterState(GraveyardManagerGame.GameStates.PAUSE_STATE.ordinal());
		}
		if (gameContainer.getInput().isKeyPressed(Input.KEY_ESCAPE))
		{
			game.enterState(GraveyardManagerGame.GameStates.MAIN_MENU_STATE.ordinal());
		}

		gameController.update();

		if (gameOver || gameWin)
		{
			exit();
<<<<<<< HEAD

=======
>>>>>>> 6cf063c3574ff0c51e0ed88bf2e0e2e11447a506
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

	// Entities methods

	public ArrayList<Building> getPlayerBuilding() {
		return layers.get(Layer.FIRST_EFFECT).getPlayerBuilding();
	}

	public boolean isPlayerEntity(int playerId) {
		return getPlayer().getId() == playerId;
	}

	public void addEntity(IEntity e) {
		if (e instanceof Building && isPlayerEntity(((Building) e).getPlayerId())) {
			//GameSound.constructionEffect();
		}
		layers.get(e.getLayer()).addEntity(e);
	}

	public void removeEntity(IEntity e) {
		layers.get(e.getLayer()).removeEntity(e);
	}

//	public ActiveEntity getEntityAt(ActiveEntity entity, float x, float y) {
//		return getMap().getEntityAt(entity, (int) x / getTileW(), (int) y / getTileH());
//	}

	public void exit()
	{
		GameMusic.stopMusic();
		GameMusic.loopMainTheme();
		game.enterState(GraveyardManagerGame.GameStates.MAIN_MENU_STATE.ordinal(), fot, fit);
	}

	// count the entities

	public void resetEntsCount() {
		for (int i = 0; i < entsCount.length; i++)
			entsCount[i] = 0;
	}

	public int[] getEntsCount() {
		return entsCount;
	}

	public void addEntToCount(int playerId) {
		if (playerId == -1) {
			entsCount[8]++;
		} else {
			entsCount[playerId]++;
		}
	}


	// Inputs
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


	// Map stuff
	public int getTileW()
	{
		return getMap().getTileWidth();
	}

	public int getTileH()
	{
		return getMap().getTileHeight();
	}

	public Map getMap()
	{
		return level.getMap();
	}

	public Player getPlayer()
	{
		return level.getPlayer();
	}

	public Player getPlayer(int id)
	{
		return level.getPlayer(id);
	}

	public void setGameWin()
	{
		gameWin = true;
	}

	public String getGameTime()
	{
		return gameTime.getTime();
	}

}
