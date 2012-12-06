package de.fhflensburg.graveyardmanager.core;

import de.fhflensburg.graveyardmanager.states.*;
import de.fhflensburg.graveyardmanager.utils.Configuration;
import de.fhflensburg.graveyardmanager.utils.ResourceManager;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 28.11.12
 * Time: 00:02
 */
public class GraveyardManagerGame extends StateBasedGame
{
	/** The name of the game */
	public static final String GAMETITLE = "Hodie mihi, Cras tibi - Der Friedhofsmanager";

	/** The current version of the game */
	public static final String VERSION = "1.0.0-Alpha";

	private ArrayList<View> states;

	/**
	 * Generate game state ids
	 */
	public enum GameStates {
		SPLASH_SCREEN_STATE,
		LOAD_RESOURCES_STATE,
		SAVE_LOAD_GAME_STATE,
		MAIN_MENU_STATE,
		IN_GAME_STATE,
		OPTION_MENU_STATE,
		PAUSE_STATE,
		ENDGAME
	}

	/**
	 * The constructor
	 *
	 * @param pathToConfigurationFile The path to the configuration file
	 * @throws IOException
	 * @throws SlickException
	 */
	public GraveyardManagerGame(String pathToResourcesJar, String pathToConfigurationFile) throws IOException, SlickException
	{
		super(GAMETITLE);
		Configuration.init(pathToConfigurationFile);
		ResourceManager.init(pathToResourcesJar);
		states = new ArrayList<View>();
	}

	/**
	 * Register the different game states to the game
	 *
	 * @param container
	 * @throws SlickException
	 */
	@Override
	public void initStatesList(GameContainer container) throws SlickException
	{
		addState(new SplashView());
		addState(new LoadResourcesView(container));
//		addState(new SaveLoadGameView());
		addState(new MainMenuView());
//		addState(new InGameView());
//		addState(new PauseView());
		addState(new EndGameView());
		//		addState(new OptionState());
		if (!Configuration.isDebugMode())
		{
			enterState(GameStates.SPLASH_SCREEN_STATE.ordinal());
		}
		else
		{
			enterState(GameStates.LOAD_RESOURCES_STATE.ordinal());
		}
	}

	@Override
	public void addState(GameState state)
	{
		super.addState(state);
		states.add((View) state);
	}

	public View getStateById(int id)
	{
		return (View) states.get(id);
	}

	/**
	 * Our entry point to start the game
	 *
	 * @throws SlickException
	 * @throws IOException
	 */
	public void start() throws SlickException, IOException
	{
		AppGameContainer container = new AppGameContainer(this);

		container.setMinimumLogicUpdateInterval(20);
		container.setMaximumLogicUpdateInterval(20);
		container.setUpdateOnlyWhenVisible(false);
		container.setAlwaysRender(true);

		// Set the configuration
		setCurrentConfiguration(container);

		// Start the game
		container.start();
	}

	private void setCurrentConfiguration(AppGameContainer container) throws IOException, SlickException
	{
		Configuration.updateConfigFile();
		container.setDisplayMode(Configuration.getWidth(), Configuration.getHeight(), Configuration.isFullScreen());
		container.setVerbose(Configuration.isDebugMode() ? true : false);
		container.setShowFPS(Configuration.isDebugMode() ? true : false);
		container.setVSync(Configuration.isVSync() ? true : false);
	}

	public void setCurrentConfiguration() throws IOException, SlickException
	{
		setCurrentConfiguration((AppGameContainer) this.getContainer());
	}
}
