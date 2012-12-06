package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 28.11.12
 * Time: 18:11
 */
public class SplashView extends View
{
	/** The game holding this state */
	private StateBasedGame game;

	/** Holds the window where the game lives in */
	private GameContainer container;

	/** Splash image */
	public Image splash;

	/** Shortcut for fadeout transition */
	private FadeOutTransition fot;

	/** Shortcut for fadein transition */
	private FadeInTransition fit;

	/** Defines the duration in ms how long the splash screen will be shown */
	private static final int delay = 2000;

	/** Store the time which is elapsed since update() called last time and sums it up */
	private int elapsedTime;

	/**
	 * Returns the id of the state
	 *
	 * @return int id
	 */
	@Override
	public int getID()
	{
		return GraveyardManagerGame.GameStates.SPLASH_SCREEN_STATE.ordinal();
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		game = stateBasedGame;
		container = gameContainer;
		fot = new FadeOutTransition(Color.black);
		fit = new FadeInTransition(Color.black);
		splash = new Image("de/fhflensburg/graveyardmanager/images/slick.png");


	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException
	{
		splash.draw((container.getWidth()/2) - (splash.getWidth()/2),(container.getHeight()/2) - (splash.getHeight()/2));
	}

	@Override
	public void initResources()
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException
	{
		elapsedTime += delta;

		if (elapsedTime > delay)
		{
			elapsedTime = 0;
			game.enterState(GraveyardManagerGame.GameStates.LOAD_RESOURCES_STATE.ordinal(), fot, fit);
		}
	}
}
