package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.fhflensburg.graveyardmanager.utils.ResourceManager;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 29.11.12
 * Time: 23:22
 */
public class InGameView extends BasicGameState
{
	/** The game holding this state */
	private StateBasedGame game;

	/** Holds the window where the game lives in */
	private GameContainer container;

	private TiledMap map;

	/** Shortcut for fadeout transition */
	private FadeOutTransition fot;

	/** Shortcut for fadein transition */
	private FadeInTransition fit;

	/**
	 * Returns the id of the state
	 *
	 * @return int id
	 */
	@Override
	public int getID()
	{
		return GraveyardManagerGame.GameStates.IN_GAME_STATE.ordinal();
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		game = stateBasedGame;
		container = gameContainer;
		fot = new FadeOutTransition(Color.black);
		fit = new FadeInTransition(Color.black);
		map = ResourceManager.getMap("testmap");
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException
	{
//		g.setColor(Color.white);
//		g.drawString("Spielen", (float) container.getWidth() / 2, (float) container.getHeight() / 2);
		map.render(0, 0);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException
	{
		if (container.getInput().isKeyPressed(Input.KEY_SPACE))
		{
			game.enterState(GraveyardManagerGame.GameStates.PAUSE_STATE.ordinal());
		}
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE))
		{
			game.enterState(GraveyardManagerGame.GameStates.MAIN_MENU_STATE.ordinal());
		}
	}
}
