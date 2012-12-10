package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyInputConsumer;
import de.lessvoid.nifty.slick2d.NiftyBasicGameState;
import de.lessvoid.nifty.slick2d.NiftyOverlayBasicGameState;
import de.lessvoid.nifty.slick2d.input.PlainSlickInputSystem;
import de.lessvoid.nifty.slick2d.input.SlickInputSystem;
import de.lessvoid.nifty.slick2d.input.SlickSlickInputSystem;
import de.lessvoid.nifty.slick2d.render.SlickRenderDevice;
import de.lessvoid.nifty.slick2d.sound.SlickSoundDevice;
import de.lessvoid.nifty.slick2d.time.LWJGLTimeProvider;
import de.lessvoid.nifty.tools.resourceloader.NiftyResourceLoader;
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
 * Date: 06.12.12
 * Time: 17:39
 */
public abstract class View extends NiftyOverlayBasicGameState
{

	/** Holds the window where the game lives in */
	protected GameContainer container;

	/** The game context */
	protected GraveyardManagerGame game;

	/** Shortcut for fadeout transition */
	protected FadeOutTransition fot;

	/** Shortcut for fadein transition */
	protected FadeInTransition fit;



	@Override
	public void initGameAndGUI(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		SlickRenderDevice slickRenderDevice = new SlickRenderDevice(gameContainer);
		SlickSoundDevice slickSoundDevice = new SlickSoundDevice();
		LWJGLTimeProvider lwjglTimeProvider = new LWJGLTimeProvider();
		SlickInputSystem slickInputSystem = new SlickInputSystem()
		{

			@Override
			public void controllerLeftPressed(int i)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void controllerLeftReleased(int i)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void controllerRightPressed(int i)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void controllerRightReleased(int i)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void controllerUpPressed(int i)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void controllerUpReleased(int i)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void controllerDownPressed(int i)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void controllerDownReleased(int i)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void controllerButtonPressed(int i, int i1)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void controllerButtonReleased(int i, int i1)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void setResourceLoader(NiftyResourceLoader niftyResourceLoader)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void forwardEvents(NiftyInputConsumer niftyInputConsumer)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void setMousePosition(int i, int i1)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void keyPressed(int i, char c)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void keyReleased(int i, char c)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void mouseWheelMoved(int i)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void mouseClicked(int i, int i1, int i2, int i3)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void mousePressed(int i, int i1, int i2)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void mouseReleased(int i, int i1, int i2)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void mouseMoved(int i, int i1, int i2, int i3)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void mouseDragged(int i, int i1, int i2, int i3)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void setInput(Input input)
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public boolean isAcceptingInput()
			{
				return false;  //To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void inputEnded()
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}

			@Override
			public void inputStarted()
			{
				//To change body of implemented methods use File | Settings | File Templates.
			}
		};

		initNifty(gameContainer, stateBasedGame, slickRenderDevice, slickSoundDevice, slickInputSystem);
		container = gameContainer;
		game = (GraveyardManagerGame) stateBasedGame;
		fot = new FadeOutTransition(Color.black);
		fit = new FadeInTransition(Color.black);
	}

	@Override
	public void updateGame(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException
	{
		//game.updateTWL();
	}

	@Override
	public void renderGame(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException
	{
//		if (!initTWL)
//		{
//			game.setRootPane(getID());
//			game.updateTWL();
//			this.initTWL = true;
//		}
//		game.renderTWL();
	}

	@Override
	public void prepareNifty(Nifty nifty, StateBasedGame stateBasedGame)
	{
		if (nifty != null)
		{
			nifty.loadStyleFile("nifty-default-styles.xml");
			nifty.loadControlFile("nifty-default-controls.xml");
			//nifty.fromXml("", "main", controller);
		}
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
