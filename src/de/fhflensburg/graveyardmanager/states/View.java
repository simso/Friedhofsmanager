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

	/** The render device for the Nifty GUI */
	protected SlickRenderDevice renderDevice;

	/** The sound device for the Nifty GUI */
	protected SlickSoundDevice soundDevice;

	/** The input system for the Nifty GUI */
	protected SlickSlickInputSystem inputSystem;

	/** The time for the Nifty GUI */
	protected LWJGLTimeProvider accurateTimer;

	/** Path to xml files for Nifty GUI */
	protected static final String GUI_PATH = "res/de/fhflensburg/graveyardmanager/gui/";

	/**
	 * This function must be called in initGameAndGUI methods of every state which
	 * make use of one of the members in here.
	 *
	 * @param gameContainer The game container
	 * @param stateBasedGame The game
	 */
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
	protected void updateGame(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException
	{
		//
	}

	@Override
	protected void renderGame(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException
	{
		//
	}

	/**
	 * Load here all the gui stuff
	 *
	 * @param nifty An instance of the nifty object
	 * @param stateBasedGame A representation of the game
	 */
	@Override
	protected void prepareNifty(Nifty nifty, StateBasedGame stateBasedGame)
	{
		//
	}

	/**
	 * Will be called every time we enter this state
	 *
	 * @param gameContainer The game container
	 * @param stateBasedGame A representation of the game
	 * @throws SlickException
	 */
	@Override
	protected void enterState(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Will be called every time we leave this state
	 *
	 * @param gameContainer The game container
	 * @param stateBasedGame A representation of the game
	 * @throws SlickException
	 */
	@Override
	protected void leaveState(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * We must implement this method in every state where we need to
	 * load resources like sounds, images, music, ...
	 *
	 * It will be called by the LoadResourcesView for every registered state.
	 */
	public abstract void initResources();

}
