package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.fhflensburg.graveyardmanager.utils.Timer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

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
	/** Splash image */
	public Image splash;

	private Timer timer;

	/** Defines the duration in ms how long the splash screen will be shown */
	private static final int DELAY = 2000;

	public SplashView()
	{
		timer = new Timer(DELAY);
	}

	/**
	 * Called by Slick2D
	 *
	 * @return The unique id of this state
	 */
	@Override
	public int getID()
	{
		return GraveyardManagerGame.GameStates.SPLASH_SCREEN_STATE.ordinal();
	}

	@Override
	public void initGameAndGUI(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		super.initGameAndGUI(gameContainer, stateBasedGame);

		splash = new Image("de/fhflensburg/graveyardmanager/images/slick.png");
	}

	@Override
	public void renderGame(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException
	{
		//splash.draw((container.getWidth() / 2) - (splash.getWidth() / 2),(container.getHeight() / 2) - (splash.getHeight() / 2));
	}

	public void initResources()
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void updateGame(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException
	{
		timer.update(delta);

		if (timer.isTimeComplete())
		{
			timer.resetTime();
			game.enterState(GraveyardManagerGame.GameStates.LOAD_RESOURCES_STATE.ordinal(), fot, fit);
		}
	}
}
