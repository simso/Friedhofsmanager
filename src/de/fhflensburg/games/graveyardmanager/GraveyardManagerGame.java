package de.fhflensburg.games.graveyardmanager;

import de.fhflensburg.games.graveyardmanager.states.*;
import de.fhflensburg.games.graveyardmanager.states.LoadingState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

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
	public static final String gameName = "Hodie mihi, Cras tibi - Der Friedhofsmanager";

	/** The height of the game window */
	public static int resolutionHeight = 1024;

	/** The width of the game window */
	public static int resolutionWidth = 768;

	/** Flag for fullscreen */
	public static final boolean fullscreen = false;

	/** Flag for debug mode */
	public static final boolean debugMode = false;


	/**
	 * The constructor
	 * @param title The title/name of the game
	 */
	public GraveyardManagerGame(String title)
	{
		super(title);
	}

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
		CREDITS
	}

	public static void main(String[] argv)
	{
		try {
			AppGameContainer container = new AppGameContainer(new GraveyardManagerGame(gameName));
			container.setDisplayMode(resolutionHeight, resolutionWidth, fullscreen);
			container.start();
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
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
		addState(new SplashState(GameStates.SPLASH.ordinal()));
		addState(new InitGameState(GameStates.INITGAME.ordinal()));
		addState(new LoadingState(GameStates.LOADING.ordinal()));
		addState(new MenuState(GameStates.MENU.ordinal()));
		addState(new InGameState(GameStates.INGAME.ordinal()));
		addState(new PauseState(GameStates.PAUSE.ordinal()));
		addState(new EndGameState(GameStates.CREDITS.ordinal()));
		//		addState(new OptionState());
		if (!debugMode)
		{
			enterState(GameStates.SPLASH.ordinal());
		}
		else
		{
			enterState(GameStates.INITGAME.ordinal());
		}
	}
}
