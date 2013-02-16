package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.fhflensburg.graveyardmanager.utils.Configuration;
import de.fhflensburg.graveyardmanager.utils.ResourceManager;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 28.11.12
 * Time: 00:48
 */
public class MainMenuView extends View
{
	/** Background image of the main menu */
	Image backgroundImage;

	/** Main menu options */
	private String[] options = new String[] {"Neues Spiel", "Optionen", "Spiel beerdigen"};

	/**
	 * Returns the id of this state
	 *
	 * @return int id
	 */
	@Override
	public int getID()
	{
		return GraveyardManagerGame.GameStates.MAIN_MENU_STATE.ordinal();
	}

	public void initResources()
	{
		backgroundImage = ResourceManager.getImage("Load_203");
	}

	@Override
	public void initGameAndGUI(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		super.initGameAndGUI(gameContainer, stateBasedGame);
	}

	@Override
	public void renderGame(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException
	{
		backgroundImage.draw(0, 0, (float) container.getWidth(), (float) container.getHeight());
		g.setColor(Color.lightGray);

		if (Configuration.isDebugMode())
		{
			g.drawString(GraveyardManagerGame.VERSION, 5, container.getHeight() - 20);
		}

		g.drawString("Menu", 500f, 430f);

		for (int i = 0; i < options.length; i++)
		{
			g.drawString(i + 1 + ". " + options[i], 430f, 460 + (i * 30));
		}
	}

	/**
	 * Implements the method fromF BasicGameState to pass the input to the game
	 *
	 * @param key
	 * @param c
	 */
	public void keyReleased(int key, char c)
	{
		if (key == Input.KEY_1)
		{
			game.enterState(GraveyardManagerGame.GameStates.CREATE_GAME_STATE.ordinal(), fot, fit);
		}

//		if (key == Input.KEY_2)
//		{
//			game.enterState(GraveyardManagerGame.GameStates.CREATE_GAME_STATE.ordinal(), fot, fit);
//		}

		if (key == Input.KEY_2)
		{
			game.enterState(GraveyardManagerGame.GameStates.OPTION_MENU_STATE.ordinal(), fot, fit);
		}

		if (key == Input.KEY_3)
		{
			game.enterState(GraveyardManagerGame.GameStates.ENDGAME.ordinal(), fot, fit);
		}
	}
}
