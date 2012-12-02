package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import org.newdawn.slick.*;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.io.IOException;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 29.11.12
 * Time: 21:21
 */
public class InitGameState extends BasicGameState
{
	/** The game holding this state */
	private StateBasedGame game;

	/** Holds the window where the game lives in */
	private GameContainer container;

	/** Loading image */
	public Image loading;

	/** Holds unique game id */
	private final int id;

	/** Shortcut for fadeout transition */
	private FadeOutTransition fot;

	/** Shortcut for fadein transition */
	private FadeInTransition fit;

	/** The music which could play during loading */
	private Music music;

	/** The sound which could play during loading */
	private Sound sound;

	/** The font we used for loading text */
	private Font font;

	/** The next resource to load */
	private DeferredResource nextResource;

	/** True if we have loaded all the resources and started the game */
	private boolean started;

	/**
	 * The constructor of this class
	 *
	 * @param id
	 */
	public InitGameState(int id)
	{
		this.id = id;
	}

	/**
	 * Returns the id of the state
	 *
	 * @return int id
	 */
	@Override
	public int getID()
	{
		return id;
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		LoadingList.setDeferredLoading(true);
		game = stateBasedGame;
		container = gameContainer;
		fot = new FadeOutTransition(Color.black);
		fit = new FadeInTransition(Color.black);
		loading = new Image("de/fhflensburg/graveyardmanager/images/splash.jpg");
		new Image("de/fhflensburg/graveyardmanager/images/menu_plain.png");
		new Image("de/fhflensburg/graveyardmanager/images/slick.png");
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException
	{
		loading.draw(0,0,container.getWidth(),container.getScreenHeight());

		if (nextResource != null)
		{
			g.drawString("Lade: " + nextResource.getDescription(), 100, 100);
		}

		int total = LoadingList.get().getTotalResources();
		int loaded = LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources();

		g.fillRect((float) container.getWidth() / 2, (float) container.getHeight() / 2, loaded * 40, 20);
		g.drawRect((float) container.getWidth() / 2, (float) container.getHeight() / 2, total * 40, 20);

		if (started)
		{

			g.drawString("Fertig", 500f, 500f);
		}

	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException
	{
		if (nextResource != null)
		{
			try
			{
				nextResource.load();
			} catch (IOException e)
			{
				throw new SlickException("Failed to load " + nextResource.getDescription(), e);
			}

			nextResource = null;
		}

		if (LoadingList.get().getRemainingResources() > 0)
		{
			nextResource = LoadingList.get().getNext();
		}
		else
		{
			if (!started)
			{
				started = true;
//				music.loop();
//				sound.play();
				game.enterState(GraveyardManagerGame.GameStates.MENU.ordinal(), fot, fit);
			}
		}
	}
}
