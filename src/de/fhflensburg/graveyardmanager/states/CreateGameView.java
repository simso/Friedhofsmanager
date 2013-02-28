package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;

import de.fhflensburg.graveyardmanager.core.music.GameMusic;
import de.fhflensburg.graveyardmanager.utils.ResourceManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ButtonClickedEvent;

import de.lessvoid.nifty.screen.Screen;
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
    public Image[] backgroundImages;
	/** An instance of the nifty object */
	private Nifty nifty;
    private int toggle;
    private boolean goprevchargotklicked;
    private boolean gonextchargotklicked;


	/** An instance of the screen */
	private Screen screen;

	/** An instance of the game */
	GraveyardManagerGame game;

	/**
	 * The default constructor
	 */

    public CreateGameView()
    {
       backgroundImages = new Image[2];
       initResources();
       toggle=0;
        gonextchargotklicked=false;
        goprevchargotklicked=false;
    }

	public CreateGameView(StateBasedGame stateBasedGame)
	{
        this();
        this.game = (GraveyardManagerGame) stateBasedGame;
        toggle=0;
        gonextchargotklicked=false;
        goprevchargotklicked=false;

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
        try
        {
            toggle=0;
            gonextchargotklicked=false;
            goprevchargotklicked=false;
            backgroundImages[0] = new Image("res/de/fhflensburg/graveyardmanager/images/Character_Gevatter.png");
            backgroundImages[1] = new Image("res/de/fhflensburg/graveyardmanager/images/Character_Hugo.png");

        } catch (SlickException e)
        {
            e.printStackTrace();
        }
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




            switch(toggle)
            {
                case 0:
                    backgroundImages[0].draw(0, 0, (float) gameContainer.getWidth(), (float) gameContainer.getHeight());
                    break;

                case 1:
                    backgroundImages[1].draw(0, 0, (float) gameContainer.getWidth(), (float) gameContainer.getHeight());
                    break;

                default:
                    backgroundImages[0].draw(0, 0, (float) gameContainer.getWidth(), (float) gameContainer.getHeight());
                    break;
            }





    }

    @Override
    public void updateGame(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException
    {
        if(gonextchargotklicked || goprevchargotklicked)
        {
            if(toggle==0) {  toggle=1;  }
            if(toggle==1) {  toggle=0;  }
            gonextchargotklicked=false;
            goprevchargotklicked=false;
        }


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
       goprevchargotklicked=true;
    }

    public void goNextChar()
    {
        gonextchargotklicked=true;
    }
}
