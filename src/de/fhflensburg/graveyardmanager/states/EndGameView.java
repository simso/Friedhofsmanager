package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.lessvoid.nifty.Nifty;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
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
	/** The game holding this state */
	private StateBasedGame game;

	/** Holds the window where the game lives in */
	private GameContainer container;

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

	@Override
	public void initResources()
	{
	}

	@Override
	protected void enterState(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	protected void leaveState(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void renderGame(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void updateGame(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException
	{
		gameContainer.exit();
	}

	@Override
	public void prepareNifty(Nifty nifty, StateBasedGame stateBasedGame)
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}
}
