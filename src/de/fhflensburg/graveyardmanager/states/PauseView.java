package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
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
public class PauseView extends View
{
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
			game.enterState(GraveyardManagerGame.GameStates.IN_GAME_STATE.ordinal());
		}
	}
}
