package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.fhflensburg.graveyardmanager.utils.Configuration;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.CheckBoxStateChangedEvent;
import de.lessvoid.nifty.controls.DropDown;
import de.lessvoid.nifty.controls.SliderChangedEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.util.*;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 11.12.12
 * Time: 22:17
 */
public class GameOptionsView extends View implements ScreenController
{
	/** An instance of the nifty object */
	private Nifty nifty;

	/** An instance of the screen */
	private Screen screen;

	/** An instance of the game */
	GraveyardManagerGame game;

	/**
	 * The default constructor
	 */
	public GameOptionsView()
	{

	}

	/**
	 * Set a pointer to the game instance.
	 * We have to call this constructor inside the fromXml() method.
	 *
	 * @param stateBasedGame A representation of the game
	 */
	public GameOptionsView(StateBasedGame stateBasedGame)
	{
		game = (GraveyardManagerGame) stateBasedGame;
	}

	/**
	 * Called by Slick2D
	 *
	 * @return The unique id of this state
	 */
	@Override
	public int getID()
	{
		return GraveyardManagerGame.GameStates.OPTION_MENU_STATE.ordinal();
	}

	/**
	 * Here you have to initialize all resources (images, fonts, sounds, ...)
	 * which you want to use in this state. The method will be called by the ResourceView and
	 * pre load all the stuff.
	 */
	@Override
	public void initResources()
	{
	}

	/**
	 * This method will called by Nifty to initialize itself. Usually you have to call
	 * initNifty(gameContainer, stateBasedGame) here.
	 *
	 * Call super.initGUI(gameContainer, stateBasedGame) if you need an instance of the gameContainer and/or
	 * the statedBasedGame in other methods then this.
	 *
	 * @param gameContainer The game container
	 * @param stateBasedGame A representation of the game
	 * @throws SlickException
	 */
	@Override
	public void initGameAndGUI(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		initNifty(gameContainer, stateBasedGame);
	}

	/**
	 * Load here all the gui stuff
	 *
	 * @param nifty An instance of the nifty object
	 * @param stateBasedGame A representation of the game
	 */
	@Override
	public void prepareNifty(Nifty nifty, StateBasedGame stateBasedGame)
	{
		super.prepareNifty(nifty, stateBasedGame);
		nifty.fromXml(GUI_PATH + "options.xml", "start", new GameOptionsView(stateBasedGame));
	}

	/**
	 * Bind this ScreenController class to the screen defined in XML file
	 *
	 * @param nifty The main nifty instance
	 * @param screen The representation of the active screen
	 */
	@Override
	public void bind(Nifty nifty, Screen screen)
	{
		this.nifty = nifty;
		this.screen = screen;

		// get all resolutions available into the resolutions drop down
		fillResolutionDropDown(screen);
	}

	/**
	 * Called after the onStartScreenEvent ended
	 */
	@Override
	public void onStartScreen()
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Called after the onEndScreen event ended
	 */
	@Override
	public void onEndScreen()
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	public void quit()
	{
		game.enterState(GraveyardManagerGame.GameStates.MAIN_MENU_STATE.ordinal());
	}

	public String isFullscreen()
	{
		return Configuration.isFullScreen() ? "true" : "false";
	}

	public String isVSync()
	{
		return Configuration.isVSync() ? "true" : "false";
	}

	public float getMusicVolume()
	{
		return Configuration.getMusicVolume();
	}

	public String getSoundVolume()
	{
		return Configuration.getSoundVolume() + "";
	}

	@NiftyEventSubscriber(id = "fullscreen")
	public void onToggleFullscreenCheckbox(final String id, final CheckBoxStateChangedEvent event)
	{
		Configuration.toggleFullScreen(event.isChecked());
	}

	@NiftyEventSubscriber(id = "vsync")
	public void onToggleVSyncCheckbox(final String id, final CheckBoxStateChangedEvent event)
	{
		Configuration.setVSync(event.isChecked());
	}

	@NiftyEventSubscriber(id = "music")
	public void onMusicVolumeSlider(final String id, final SliderChangedEvent event)
	{
		Configuration.setMusicVolume(event.getValue());
		try {
			Configuration.saveNewConfig();
			game.setCurrentConfiguration();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}

	@NiftyEventSubscriber(id = "sound")
	public void onSoundVolumeSlider(final String id, final SliderChangedEvent event)
	{
		Configuration.setSoundVolume(event.getValue());
	}

	@NiftyEventSubscriber(id = "applyButton")
	public void onApplyButton(final String id, final ButtonClickedEvent event)
	{
		try {
			Configuration.saveNewConfig();
			game.setCurrentConfiguration();
			game.enterState(GraveyardManagerGame.GameStates.MAIN_MENU_STATE.ordinal());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}

	@NiftyEventSubscriber(id = "exitButton")
	public void onExitButton(final String id, final ButtonClickedEvent event)
	{
		game.enterState(GraveyardManagerGame.GameStates.MAIN_MENU_STATE.ordinal());
	}

	/**
	 * Get all LWJGL DisplayModes into the DropDown
	 * @param screen
	 */
	private void fillResolutionDropDown(final Screen screen)
	{
		try
		{
			DisplayMode currentMode = Display.getDisplayMode();
			List<DisplayMode> sorted = new ArrayList<DisplayMode>();

			DisplayMode[] modes = Display.getAvailableDisplayModes();
			for (int i=0; i<modes.length; i++) {
				DisplayMode mode = modes[i];
				if (mode.getBitsPerPixel() == 32 && mode.getFrequency() == currentMode.getFrequency())
				{
					sorted.add(mode);
				}
			}

			Collections.sort(sorted, new Comparator<DisplayMode>()
			{
				@Override
				public int compare(DisplayMode o1, DisplayMode o2)
				{
					int widthCompare = Integer.valueOf(o1.getWidth()).compareTo(Integer.valueOf(o2.getWidth()));
					if (widthCompare != 0)
					{
						return widthCompare;
					}

					int heightCompare = Integer.valueOf(o1.getHeight()).compareTo(Integer.valueOf(o2.getHeight()));
					if (heightCompare != 0)
					{
						return heightCompare;
					}

					return o1.toString().compareTo(o2.toString());
				}
			});

			DropDown dropDown = screen.findNiftyControl("resolutions", DropDown.class);
			for (DisplayMode mode : sorted)
			{
				dropDown.addItem(mode);
			}
		}
		catch (Exception e)
		{}
	}
}
