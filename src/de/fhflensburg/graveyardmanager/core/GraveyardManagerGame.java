package de.fhflensburg.graveyardmanager.core;

import de.fhflensburg.graveyardmanager.states.*;
import de.fhflensburg.graveyardmanager.utils.Configuration;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;

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

	/**
	 * Generate game state ids
	 */
	public enum GameStates {
		SPLASH,
		INITGAME,
		LOADING,
		MENU,
		INGAME,
		OPTIONS,
		PAUSE,
		CREDITS,
		ENDGAME
	}

	/**
	 * The constructor
	 *
	 * @param configurationFile The path to the configuration file
	 * @throws IOException
	 * @throws SlickException
	 */
	public GraveyardManagerGame(String configurationFile) throws IOException, SlickException
	{
		super(GAMETITLE);
		Configuration.init(configurationFile);
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
		addState(new SplashState());
		addState(new InitGameState());
		addState(new LoadingState());
		addState(new MenuState());
		addState(new InGameState());
		addState(new PauseState());
		addState(new EndGameState());
		//		addState(new OptionState());
		if (!Configuration.isDebugMode())
		{
			enterState(GameStates.SPLASH.ordinal());
		}
		else
		{
			enterState(GameStates.INITGAME.ordinal());
		}
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
	}

	public void setCurrentConfiguration() throws IOException, SlickException
	{
		setCurrentConfiguration((AppGameContainer) this.getContainer());
	}
}
