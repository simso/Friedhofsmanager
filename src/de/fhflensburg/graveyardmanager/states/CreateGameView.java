package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.ImageSelectSelectionChangedEvent;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 14.12.12
 * Time: 15:57
 */
public class CreateGameView extends View implements ScreenController
{
	/** An instance of the nifty object */
	private Nifty nifty;

	/** An instance of the screen */
	private Screen screen;

	/** An instance of the game */
	GraveyardManagerGame game;

	/** Contains the name of the selected map */
	private String currentMapSelection;

	/**
	 * The default constructor
	 */
	public CreateGameView()
	{
		currentMapSelection = "Sahara";
	}

	/**
	 * Set a pointer to the game instance.
	 * We have to call this constructor inside the fromXml() method.
	 *
	 * @param stateBasedGame A representation of the game
	 */
	public CreateGameView(StateBasedGame stateBasedGame)
	{
		this();
		this.game = (GraveyardManagerGame) stateBasedGame;
	}

	/**
	 * Called by Slick2D
	 *
	 * @return The unique id of this state
	 */
	@Override
	public int getID()
	{
		return GraveyardManagerGame.GameStates.CREATE_GAME_STATE.ordinal();
	}

	/**
	 * Here you have to initialize all resources (images, fonts, sounds, ...)
	 * which you want to use in this state. The method will be called by the ResourceView and
	 * pre load all the stuff.
	 *
	 */
	@Override
	public void initResources()
	{}

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
		nifty.fromXml(GUI_PATH + "creategame.xml", "start", new CreateGameView(stateBasedGame));
	}

	/**
	 *
	 * @param gameContainer
	 * @param stateBasedGame A representation of the game
	 * @throws SlickException
	 */
	@Override
	protected void initGameAndGUI(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
	{
		initNifty(gameContainer, stateBasedGame);
	}

	/**
	 *
	 */
	@Override
	public void onStartScreen()
	{
	}

	/**
	 *
	 */
	@Override
	public void onEndScreen()
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}


	/**
	 * This method will be handled from Nifty through the annotation.
	 * It will be called when the user select a map from the imageSelect.
	 *
	 * @param id The elementId that has published the event
	 * @param event The fired event object
	 */
	@NiftyEventSubscriber(id = "mapSelect")
	public void onSwitchMapSelection(final String id, final ImageSelectSelectionChangedEvent event)
	{
		currentMapSelection = getMapName(event.getSelectedIndex());
		Element textElement = screen.findElementByName("mapSelectLabel");
		textElement.getRenderer(TextRenderer.class).setText(currentMapSelection);
	}

	/**
	 * This method will be handled from Nifty through the annotation.
	 * It will be called when the user select a character from the imageSelect.
	 *   Note: Something strange happens here. If this method was written in between the methods getAllMaps()
	 *   and getCharacterName() the functions ${CALL.getMapName()} and ${CALL.getCharacterName()} within the
	 *   creategame.xml won't be executed.
	 *
	 * @param id The elementId that has published the event
	 * @param event The fired event object
	 */
	@NiftyEventSubscriber(id = "charSelect")
	public void onSwitchCharacterSelection(final String id, final ImageSelectSelectionChangedEvent event)
	{
		Element textElement = screen.findElementByName("charSelectLabel");
		textElement.getRenderer(TextRenderer.class).setText(getCharacterName(event.getSelectedIndex()));
	}


	/**
	 * This method will be called from Nifty XML file
	 * to set the name of the map at runtime
	 *
	 * @return The current name of the map
	 */
	public String getMapName()
	{
		return getMapName(0);
	}

	/**
	 * This method will be called if the user select a map and will update the name of the map
	 * to display it and to load the selected map.
	 *
	 * @param id The index of the map
	 * @return The name of the selected map
	 */
	public String getMapName(int id)
	{
		String result;

		switch (id)
		{
			case 0:
				result = "Sahara";
				break;

			case 1:
				result = "Wiese";
				break;

			default:
				result = "Sahara";
				break;
		}

		return result;
	}

	/**
	 * This method will be called from Nifty XML file
	 * to set the images of all available maps at runtime
	 *
	 * @return Comma separated string with map image locations
	 */
	public String getAllMaps()
	{
		return "res/de/fhflensburg/graveyardmanager/images/Sahara.png,res/de/fhflensburg/graveyardmanager/images/Wiese.png";
	}

	/**
	 * This method will be called from Nifty XML file
	 * to set the names of all characters at runtime
	 *
	 * @return The name of the selected character
	 */
	public String getCharacterName()
	{
		return getCharacterName(0);
	}

	/**
	 * This method will be called if the user select a character and will update the name of the Character
	 * to display it.
	 *
	 * TODO: Connect the selected character with the player
	 *
	 * @param id The index of the character
	 * @return The name of the selected character
	 */
	public String getCharacterName(int id)
	{
		String result;

		switch (id)
		{
			case 0:
				result = "Gevatter";
				break;

			case 1:
				result = "Engeli";
				break;

			case 2:
				result = "Dracula";
				break;

			case 3:
				result = "Hugo";
				break;

			default:
				result = "Gevatter";
				break;
		}

		return result;
	}

	/**
	 * This method will be called from Nifty XML file
	 * to set the images of all available characters at runtime
	 *
	 * @return Comma separated string with character image locations
	 */
	public String getAllCharacterImages()
	{
		return "res/de/fhflensburg/graveyardmanager/images/Gevatter.png,res/de/fhflensburg/graveyardmanager/images/Engeli.png,res/de/fhflensburg/graveyardmanager/images/Dracula.png,res/de/fhflensburg/graveyardmanager/images/Hugo.png";
	}

	/**
	 * This method will be handled from Nifty through the annotation.
	 * It will be called when the user hit the "Start" button
	 *
	 * @param id The elementId that has published the event
	 * @param event The fired event object
	 */
	@NiftyEventSubscriber(id = "startGameButton")
	public void onStartGameButton(final String id, final ButtonClickedEvent event)
	{
		game.setMap(currentMapSelection);
		game.enterState(GraveyardManagerGame.GameStates.IN_GAME_STATE.ordinal());
	}

	/**
	 * This method will be handled from Nifty through the annotation.
	 * It will be called when the user hit the "Zurück" button
	 *
	 * @param id The elementId that has published the event
	 * @param event The fired event object
	 */
	@NiftyEventSubscriber(id = "backToMainMenuButton")
	public void onBackButtonClick(final String id, final ButtonClickedEvent event)
	{
		game.enterState(GraveyardManagerGame.GameStates.MAIN_MENU_STATE.ordinal());
	}
}
