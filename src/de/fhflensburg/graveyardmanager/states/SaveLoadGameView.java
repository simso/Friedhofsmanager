package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
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
 * Time: 00:11
 */
public class SaveLoadGameView extends BasicGameState
{
	// The game holding this state
	private StateBasedGame game;

	private static final int delay = 2000;
	private int elapsedTime;

	// The splash screen
	Image loading;

	@Override
	public int getID()
	{
		return GraveyardManagerGame.GameStates.SAVE_LOAD_GAME_STATE.ordinal();
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbgame) throws SlickException
	{
		this.game = sbgame;
		this.loading = new Image("de/fhflensburg/graveyardmanager/images/splash.jpg");

	}

	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException
	{

		//this.loading.draw((container.getWidth()/2) - (loading.getWidth()/2), (container.getHeight()/2) - (loading.getHeight()/2));
		this.loading.draw(0,0,1024,768);

	}

	@Override
	public void update(GameContainer container, StateBasedGame sbgame, int i) throws SlickException
	{
		elapsedTime += i;

		if (elapsedTime > delay)
		{
			elapsedTime = 0;
			game.enterState(GraveyardManagerGame.GameStates.MAIN_MENU_STATE.ordinal(), new FadeOutTransition(), new FadeInTransition());
		}
	}
}
