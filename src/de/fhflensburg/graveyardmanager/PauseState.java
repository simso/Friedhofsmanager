package de.fhflensburg.graveyardmanager;

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

	// The game holding this state
	private StateBasedGame game;

	// Background image of the main menu
	Image backgroundImage;

	// The game id
	private int ID;

	public PauseState(int id)
	{
		ID = id;
	}

	@Override
	public int getID()
	{
		return ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbgame) throws SlickException
	{
		this.game = sbgame;
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException
	{
		g.setColor(Color.white);
		g.drawString("Pause", (float) container.getWidth() / 2, (float) container.getHeight() / 2);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbgame, int i) throws SlickException
	{
		if (container.getInput().isKeyPressed(Input.KEY_SPACE))
		{
			game.enterState(Game.GameStates.INGAME.ordinal());
		}
	}
}
