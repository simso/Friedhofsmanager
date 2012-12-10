package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.fhflensburg.graveyardmanager.utils.Configuration;
import de.fhflensburg.graveyardmanager.utils.ResourceManager;
import de.lessvoid.nifty.Nifty;
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
public class MainMenuView extends View
{
	/** Background image of the main menu */
	Image backgroundImage;

	/** Main menu options */
	private String[] options = new String[] {"Neues Spiel", "Spiel laden", "Optionen", "Spiel beerdigen"};

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

	@Override
	public void initResources()
	{
		backgroundImage = ResourceManager.getImage("menu_plain");
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
			game.enterState(GraveyardManagerGame.GameStates.IN_GAME_STATE.ordinal(), fot, fit);
		}

		if (key == Input.KEY_2)
		{
			game.enterState(GraveyardManagerGame.GameStates.SAVE_LOAD_GAME_STATE.ordinal(), fot, fit);
		}

		if (key == Input.KEY_3)
		{
			game.enterState(GraveyardManagerGame.GameStates.OPTION_MENU_STATE.ordinal(), fot, fit);
		}

		if (key == Input.KEY_4)
		{
			game.enterState(GraveyardManagerGame.GameStates.ENDGAME.ordinal(), fot, fit);
		}
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
	public void prepareNifty(Nifty nifty, StateBasedGame stateBasedGame)
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}
}
