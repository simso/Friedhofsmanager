package de.fhflensburg.graveyardmanager;

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
 * <p/>
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 29.11.12
 * Time: 21:21
 */
public class InitGameState extends BasicGameState
{
	// The game holding this state
	private StateBasedGame game;

	private Music music;

	private Sound sound;

	// The splash screen
	private Image loading;

	private Font font;

	// The next resource to load
	private DeferredResource nextResource;

	// True if we have loaded all the resources and started the game
	private boolean started;

	// The game id
	private int ID;




	public InitGameState(int id)
	{
		this.ID = id;
	}

	@Override
	public int getID()
	{
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbgame) throws SlickException
	{
		LoadingList.setDeferredLoading(true);

		this.loading = new Image("de/fhflensburg/graveyardmanager/Graphics/splash.jpg");
		new Image("de/fhflensburg/graveyardmanager/Graphics/menu_plain.png");
		new Image("de/fhflensburg/graveyardmanager/Graphics/slick.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException
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
	public void update(GameContainer container, StateBasedGame game, int i) throws SlickException
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
				game.enterState(Game.GameStates.MENU.ordinal(), new FadeOutTransition(), new FadeInTransition());
			}
		}
	}
}
