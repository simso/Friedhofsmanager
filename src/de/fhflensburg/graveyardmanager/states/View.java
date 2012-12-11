package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.slick2d.NiftyOverlayBasicGameState;
import de.lessvoid.nifty.slick2d.input.PlainSlickInputSystem;
import de.lessvoid.nifty.slick2d.input.SlickSlickInputSystem;
import de.lessvoid.nifty.slick2d.render.SlickRenderDevice;
import de.lessvoid.nifty.slick2d.sound.SlickSoundDevice;
import de.lessvoid.nifty.slick2d.time.LWJGLTimeProvider;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 06.12.12
 * Time: 17:39
 */
public abstract class View extends NiftyOverlayBasicGameState
{

	/** Holds the window where the game lives in */
	protected GameContainer container;

	/** The game context */
	protected GraveyardManagerGame game;

	/** Shortcut for fade out transition */
	protected FadeOutTransition fot;

	/** Shortcut for fade in transition */
	protected FadeInTransition fit;

	SlickRenderDevice renderDevice;
	SlickSoundDevice soundDevice;
	SlickSlickInputSystem inputSystem;
	LWJGLTimeProvider accurateTimer;

	public void initGUI(GameContainer gameContainer, StateBasedGame stateBasedGame)
	{
		container = gameContainer;
		game = (GraveyardManagerGame) stateBasedGame;
		fot = new FadeOutTransition(Color.black);
		fit = new FadeInTransition(Color.black);
		renderDevice =  new SlickRenderDevice(gameContainer);
		soundDevice = new SlickSoundDevice();
		inputSystem = new SlickSlickInputSystem(new PlainSlickInputSystem());
		accurateTimer = new LWJGLTimeProvider();
	}

	@Override
	public void initGameAndGUI(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		container = gameContainer;
		game = (GraveyardManagerGame) stateBasedGame;
		fot = new FadeOutTransition(Color.black);
		fit = new FadeInTransition(Color.black);
		//initNifty(gameContainer, game, renderDevice, soundDevice, inputSystem, accurateTimer);
	}

	@Override
	public void updateGame(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException
	{}

	@Override
	public void renderGame(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException
	{}

	@Override
	public void prepareNifty(Nifty nifty, StateBasedGame stateBasedGame)
	{}

	/**
	 * You must init the state resources here
	 *
	 * @param container The game container
	 * @param game The game
	 */
	public abstract void initResources();
}
