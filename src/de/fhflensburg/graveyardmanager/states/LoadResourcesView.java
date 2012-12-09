package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GameMusic;
import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.fhflensburg.graveyardmanager.util.Timer;
import de.fhflensburg.graveyardmanager.utils.ResourceManager;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 29.11.12
 * Time: 21:21
 */
public class LoadResourcesView extends View
{
	/** Loading images */
	public Image[] backgroundImages;

	/** The animation of the candle */
	public Animation candleAnimation;

	/** Shortcut for fadeout transition */
	private FadeOutTransition fot;

	/** Shortcut for fadein transition */
	private FadeInTransition fit;

	private static final int WAIT_TIME_BEFORE_NEXTRESOURCE = 100;

	private boolean finished;

	private Timer timer;



	/** True if we have loaded all the resources and started the game */
	private boolean started;

	public LoadResourcesView(GameContainer container)
	{
		timer = new Timer(WAIT_TIME_BEFORE_NEXTRESOURCE);
		this.container = container;
		backgroundImages = new Image[7];
		initResources();
	}

	/**
	 * Returns the id of the state
	 *
	 * @return int id
	 */
	@Override
	public int getID()
	{
		return GraveyardManagerGame.GameStates.LOAD_RESOURCES_STATE.ordinal();
	}

	@Override
	public void initResources()
	{
		try {
			GameMusic.initMainTheme();
			GameMusic.loopMainTheme();
			for (int i = 0; i < backgroundImages.length; i++)
			{
				backgroundImages[i] = new Image("res/de/fhflensburg/graveyardmanager/images/kerze" + (i + 1) + ".png");
			}

			candleAnimation = new Animation(backgroundImages, 110);
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException
	{
		super.render(gameContainer, stateBasedGame, g);
		g.setColor(Color.red);
		if (!finished)
		{
			g.drawString("Lade ... " + ResourceManager.getAdvancement() + "%", (container.getWidth() / 2) - 80, (container.getHeight() / 2) + 330);
			backgroundImages[6].draw(0, 0, container.getWidth(), container.getHeight());
		}
		else
		{
			candleAnimation.draw(0, 0, container.getWidth(), container.getHeight());
			g.drawString("Drücke eine Taste oder klick mit der Maus", (container.getWidth() / 2) - 200, (container.getHeight() / 2) + 330);
		}
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException
	{
		super.update(gameContainer, stateBasedGame, delta);
		timer.update(delta);

		if (timer.isTimeComplete())
		{
			ResourceManager.loadNextResource();

			if (ResourceManager.isLoadComplete() && !finished)
			{
				for (int i = 2; i < stateBasedGame.getStateCount(); i++)
				{
					View view = ((GraveyardManagerGame) stateBasedGame).getStateById(i);
					view.initResources();
				}

				// TODO: Init the music here
				finished = true;
			}

			timer.resetTime();
		}
	}

	@Override
	public void keyPressed(int key, char c)
	{
		super.keyPressed(key, c);
		goToMenu();
	}

	@Override
	public void mousePressed(int button, int x, int y)
	{
		super.mousePressed(button, x, y);
		goToMenu();
	}

	private void goToMenu()
	{
		if (finished)
		{
			container.setMouseGrabbed(false);
			game.enterState(GraveyardManagerGame.GameStates.MAIN_MENU_STATE.ordinal(), fot, fit);
		}
	}
}
