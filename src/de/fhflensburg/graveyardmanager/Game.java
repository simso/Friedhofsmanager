package de.fhflensburg.graveyardmanager;

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
public class Game extends StateBasedGame
{
	/**
	 * The name of the game
	 */
	public static final String gameName = "Hodie mihi, Cras tibi - Der Friedhofsmanager";

	public static int resolutionHeight = 1024;
	public static int resolutionWidth = 768;
	public static final boolean fullscreen = false;
	public static final boolean debugMode = false;


	/**
	 * The constructor
	 * @param title The title/name of the game
	 */
	public Game(String title)
	{
		super(title);
	}

	/**
	 * Generate gamestates ids on the fly
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

	@Override
	public void initStatesList(GameContainer container) throws SlickException
	{
		addState(new SplashState(GameStates.SPLASH.ordinal()));
		addState(new InitGameState(GameStates.INITGAME.ordinal()));
		addState(new LoadingState(GameStates.LOADING.ordinal()));
		addState(new MenuState(GameStates.MENU.ordinal()));
		addState(new InGameState(GameStates.INGAME.ordinal()));
		addState(new PauseState(GameStates.PAUSE.ordinal()));
		addState(new CreditState(GameStates.CREDITS.ordinal()));
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

	public static void main(String[] argv)
	{
		try {
			AppGameContainer container = new AppGameContainer(new Game(gameName));
			container.setDisplayMode(resolutionHeight, resolutionWidth, fullscreen);
			container.start();
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
}
