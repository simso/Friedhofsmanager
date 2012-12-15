package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.fhflensburg.graveyardmanager.utils.ResourceManager;
import de.lessvoid.nifty.Nifty;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 29.11.12
 * Time: 23:08
 */
public class PauseView extends View
{
	/** Loading images */
	public Image[] backgroundImages;

	/** The animation of the candle */
	public Animation candleAnimation;

	public PauseView()
	{
		backgroundImages = new Image[22];
	}

	/**
	 * Returns the id of the state
	 *
	 * @return int id
	 */
	@Override
	public int getID()
	{
		return GraveyardManagerGame.GameStates.PAUSE_STATE.ordinal();
	}

	@Override
	public void initResources()
	{
		for (int i = 0; i < backgroundImages.length; i++)
		{
			backgroundImages[i] = ResourceManager.getImage("Kerze_" + i);
		}

		candleAnimation = new Animation(backgroundImages, 110);
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
		g.setColor(Color.white);
		candleAnimation.draw(0, 0, container.getWidth(), container.getHeight());
		g.drawString("Pause", (float) container.getWidth() / 2, (float) container.getHeight() / 2 + 250);
	}

	@Override
	public void updateGame(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException
	{
		if (container.getInput().isKeyPressed(Input.KEY_SPACE))
		{
			game.enterState(GraveyardManagerGame.GameStates.IN_GAME_STATE.ordinal());
		}
	}
}
