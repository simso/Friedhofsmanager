package de.fhflensburg.graveyardmanager;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 * <p/>
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 28.11.12
 * Time: 18:11
 */
public class SplashState extends BasicGameState
{
	public final int id;

	public Image splash;

	public StateBasedGame game;

	private static final int delay = 2000;
	private int elapsedTime;

	public SplashState(int id)
	{
		this.id = id;
	}

	@Override
	public int getID()
	{
		return this.id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbgame) throws SlickException
	{
		this.game = sbgame;
		this.splash = new Image("de/fhflensburg/graveyardmanager/Graphics/slick.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException
	{
		splash.draw((container.getWidth()/2) - (splash.getWidth()/2),(container.getHeight()/2) - (splash.getHeight()/2));
		g.drawString(String.valueOf(container.getHeight()), 0, 0);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbgame, int i) throws SlickException
	{
		elapsedTime += i;

		if (elapsedTime > delay)
		{
			elapsedTime = 0;
			game.enterState(Game.GameStates.INITGAME.ordinal(), new FadeOutTransition(), new FadeInTransition());
		}
	}
}
