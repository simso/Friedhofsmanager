package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.fhflensburg.graveyardmanager.utils.ResourceManager;
import de.fhflensburg.graveyardmanager.utils.Timer;
import de.lessvoid.nifty.Nifty;
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
 * Date: 29.11.12
 * Time: 17:57
 */
public class EndGameView extends View
{
	Timer timer;

	/** The game holding this state */
	private StateBasedGame game;

	/** Holds the window where the game lives in */
	private GameContainer container;

	private Image image;

	/** Defines the duration in ms how long the splash screen will be shown */
	private static final int DELAY = 1000;

	public EndGameView()
	{
		timer = new Timer(DELAY);
	}

	/**
	 * Returns the id of the state
	 *
	 * @return int id
	 */
	@Override
	public int getID()
	{
		return GraveyardManagerGame.GameStates.ENDGAME.ordinal();
	}

	public void initResources()
	{
		image = ResourceManager.getImage("Credits");
	}

	@Override
	public void initGameAndGUI(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		super.initGameAndGUI(gameContainer, stateBasedGame);
	}

	@Override
	public void renderGame(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException
	{
		image.draw((gameContainer.getWidth() / 2) - (image.getWidth() / 2), (gameContainer.getHeight() / 2) - (image.getHeight() / 2));
	}

	@Override
	public void updateGame(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException
	{
		timer.update(delta);

		if (timer.isTimeComplete())
		{
			timer.resetTime();
			gameContainer.exit();
		}
	}
}
