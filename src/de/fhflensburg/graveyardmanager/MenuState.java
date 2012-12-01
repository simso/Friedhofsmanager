package de.fhflensburg.graveyardmanager;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 28.11.12
 * Time: 00:48
 */
public class MenuState extends BasicGameState
{
	// The game holding this state
	private StateBasedGame game;

	// Background image of the main menu
	Image backgroundImage;

	// The game id
	private int ID;

	public MenuState(int id)
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
		this.game = sbgame;
		backgroundImage = new Image("de/fhflensburg/graveyardmanager/Graphics/menu_plain.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException
	{
		backgroundImage.draw(0, 0, (float) container.getWidth(), (float) container.getHeight());
		g.setColor(Color.black);
		g.drawString("Window height: " + container.getHeight(), 0, 0);

		g.drawString("Window width: " + container.getWidth(), 0, 20);



		g.drawString("Menu", 500f, 350f);
	 	g.drawString("1. Neues Spiel", 430f, 380f);
		g.drawString("2. Spiel laden", 430f, 410f);
		g.drawString("3. Optionen", 430f, 440f);
		g.drawString("4. Spiel beerdigen", 430f, 470f);


	}

	@Override
	public void update(GameContainer container, StateBasedGame sbgame, int i) throws SlickException
	{
		if (container.getInput().isKeyPressed(Input.KEY_1))
		{
			game.enterState(Game.GameStates.INGAME.ordinal(), new FadeOutTransition(), new FadeInTransition());
		}
		if (container.getInput().isKeyPressed(Input.KEY_2))
		{
			game.enterState(Game.GameStates.LOADING.ordinal(), new FadeOutTransition(), new FadeInTransition());
		}
		if (container.getInput().isKeyPressed(Input.KEY_3))
		{
			game.enterState(Game.GameStates.OPTIONS.ordinal(), new FadeOutTransition(), new FadeInTransition());
		}
		if (container.getInput().isKeyPressed(Input.KEY_4))
		{
			game.enterState(Game.GameStates.CREDITS.ordinal(), new FadeOutTransition(), new FadeInTransition());
		}

	}
}
