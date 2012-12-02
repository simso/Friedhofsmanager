package de.fhflensburg.games.graveyardmanager.states;

import de.fhflensburg.games.graveyardmanager.GraveyardManagerGame;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
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
public class PauseState extends BasicGameState
{
	/** The game holding this state */
	private StateBasedGame game;

	/** Holds the window where the game lives in */
	private GameContainer container;

	/** Holds unique game id */
	private final int id;

	/**
	 * The constructor of this class
	 *
	 * @param id
	 */
	public PauseState(int id)
	{
		this.id = id;
	}

	/**
	 * Returns the id of the state
	 *
	 * @return int id
	 */
	@Override
	public int getID()
	{
		return id;
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		game = stateBasedGame;
		container = gameContainer;
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException
	{
		g.setColor(Color.white);
		g.drawString("Pause", (float) container.getWidth() / 2, (float) container.getHeight() / 2);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException
	{
		if (container.getInput().isKeyPressed(Input.KEY_SPACE))
		{
			game.enterState(GraveyardManagerGame.GameStates.INGAME.ordinal());
		}
	}
}
