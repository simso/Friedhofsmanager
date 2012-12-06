package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.fhflensburg.graveyardmanager.utils.Configuration;
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
	/** The game holding this state */
	private StateBasedGame game;

	/** Holds the window where the game lives in */
	private GameContainer container;

	/** Background image of the main menu */
	Image backgroundImage;

	/** Main menu options */
	private String[] options = new String[] {"Neues Spiel", "Spiel laden", "Optionen", "Spiel beerdigen"};

	/** The index of the selected option */
	private int selectedOption;

	/** Shortcut for fadeout transition */
	private FadeOutTransition fot;

	/** Shortcut for fadein transition */
	private FadeInTransition fit;

	/**
	 * Returns the id of this state
	 *
	 * @return int id
	 */
	@Override
	public int getID()
	{
		return GraveyardManagerGame.GameStates.MENU.ordinal();
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		game = stateBasedGame;
		container = gameContainer;
		fot = new FadeOutTransition(Color.black);
		fit = new FadeInTransition(Color.black);
		backgroundImage = new Image("de/fhflensburg/graveyardmanager/images/menu_plain.png");
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException
	{
		backgroundImage.draw(0, 0, (float) container.getWidth(), (float) container.getHeight());
		g.setColor(Color.black);

		if (Configuration.isDebugMode())
		{
			g.drawString("Window height: " + container.getHeight(), 0, 0);
			g.drawString("Window width: " + container.getWidth(), 0, 20);
		}

		g.drawString("Menu", 500f, 350f);

		for (int i = 0; i < options.length; i++)
		{
			g.drawString(i + 1 + ". " + options[i], 430f, 380 + (i * 30));
		}
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException
	{
	}

	/**
	 * Implements the method from BasicGameState to pass the input to the game
	 *
	 * @param key
	 * @param c
	 */
	public void keyReleased(int key, char c)
	{
		if (key == Input.KEY_1)
		{
			game.enterState(GraveyardManagerGame.GameStates.INGAME.ordinal(), fot, fit);
		}

		if (key == Input.KEY_2)
		{
			game.enterState(GraveyardManagerGame.GameStates.LOADING.ordinal(), fot, fit);
		}

		if (key == Input.KEY_3)
		{
			game.enterState(GraveyardManagerGame.GameStates.OPTIONS.ordinal(), fot, fit);
		}

		if (key == Input.KEY_4)
		{
			game.enterState(GraveyardManagerGame.GameStates.CREDITS.ordinal(), fot, fit);
		}
	}
}
