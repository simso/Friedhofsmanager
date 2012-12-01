package de.fhflensburg.graveyardmanager;

/**
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 21.11.12
 * Time: 22:20
 * To change this template use File | Settings | File Templates.
 */

import org.newdawn.slick.*;

public class Moin extends BasicGame
{
	public Moin()
	{
		super("Moin Flensburg");
	}

	@Override
	public void init(GameContainer gc) throws SlickException
	{

	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{

	}

	@Override
	public void	render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawString("Moin Flensburg", 100, 100);
	}

	public static void main(String[] args) throws SlickException
	{
		AppGameContainer app = new AppGameContainer(new Moin());

		app.setDisplayMode(800, 600, false);
		app.start();
	}
}
