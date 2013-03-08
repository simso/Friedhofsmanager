package de.fhflensburg.graveyardmanager.states;
import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.ScreenController;
import org.newdawn.slick.*;
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
    private boolean toggle;



	/** An instance of the screen */
	private Screen screen;

	/** An instance of the game */
	GraveyardManagerGame game;

	/**
	 * The default constructor
	 */

    public CreateGameView()
    {

       initResources();
       toggle=false;

    }

	public CreateGameView(StateBasedGame stateBasedGame)
	{
        this();
        this.game = (GraveyardManagerGame) stateBasedGame;
        toggle=false;


	}

	/**
	 * Set a pointer to the game instance.
	 * We have to call this constructor inside the fromXml() method.
	 *
	 * @param stateBasedGame A representation of the game
	 */




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
    public void initResources()
    {

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
	}


    @Override
    public void renderGame(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException
    {

    }

    @Override
    public void updateGame(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException
    {

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
		nifty.fromXml(GUI_PATH + "creategame.xml", "startchar", new CreateGameView(stateBasedGame));
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





	public void gotoMap()
	{
        game.enterState(GraveyardManagerGame.GameStates.CREATE_MAP_STATE.ordinal());
	}


	public void goToMainMenu()
	{
		game.enterState(GraveyardManagerGame.GameStates.MAIN_MENU_STATE.ordinal());
	}


    public void goPrevChar()
    {

        // first load the new image
        NiftyImage newImage = nifty.getRenderEngine().createImage("res/de/fhflensburg/graveyardmanager/images/Character_Hugo.png", false);
        NiftyImage newImage1 = nifty.getRenderEngine().createImage("res/de/fhflensburg/graveyardmanager/images/Character_Gevatter.png", false);

        // find the element with it's id
        Element element = screen.findElementByName("backgroundimage");

        if(!toggle)
        {
            // change the image with the ImageRenderer
            element.getRenderer(ImageRenderer.class).setImage(newImage);
            toggle=!toggle;


        }else
        {

            element.getRenderer(ImageRenderer.class).setImage(newImage1);
            toggle=!toggle;

        }
    }

    public void goNextChar()
    {
        // first load the new image
        NiftyImage newImage = nifty.getRenderEngine().createImage("res/de/fhflensburg/graveyardmanager/images/Character_Hugo.png", false);
        NiftyImage newImage1 = nifty.getRenderEngine().createImage("res/de/fhflensburg/graveyardmanager/images/Character_Gevatter.png", false);

        // find the element with it's id
        Element element = screen.findElementByName("backgroundimage");

        if(!toggle)
        {
            // change the image with the ImageRenderer
            element.getRenderer(ImageRenderer.class).setImage(newImage);
            toggle=!toggle;


        }else
        {

            element.getRenderer(ImageRenderer.class).setImage(newImage1);
            toggle=!toggle;

        }
    }
}
