package de.fhflensburg.graveyardmanager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 * <p/>
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 29.11.12
 * Time: 17:57
 */
public class CreditState extends BasicGameState
{
	// The game holding this state
	private StateBasedGame game;

	// Background image of the main menu
	Image menu;

	// The game id
	private int ID;

	public CreditState(int id)
	{
		this.ID = id;
	}

	@Override
	public int getID()
	{
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbgame) throws SlickException
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbgame, int i) throws SlickException
	{
		container.exit();
	}
}
