package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 06.12.12
 * Time: 17:39
 */
public abstract class View extends BasicGameState
{

	/** Holds the window where the game lives in */
	protected GameContainer container;

	/** The game context */
	protected GraveyardManagerGame game;

	/** Flag if TWL is initialized */
//	protected boolean initTWL;


	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		container = gameContainer;
		game = (GraveyardManagerGame) stateBasedGame;
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException
	{
		//game.updateTWL();
	}

	@Override
	public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		super.leave(gameContainer, stateBasedGame);
		//initTWL = false;
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException
	{
//		if (!initTWL)
//		{
//			game.setRootPane(getID());
//			game.updateTWL();
//			this.initTWL = true;
//		}
//		game.renderTWL();
	}

	/**
	 * Initialize TWL
	 */
	public void initTwl()
	{
//		rootWidget = new Widget();
//		rootWidget.setTheme("");
//		initTwlComponent();
	}

	/**
	 * You must init the state resources here
	 *
	 * @param container The game container
	 * @param game The game
	 */
	public abstract void initResources();
}
