package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.fhflensburg.graveyardmanager.core.Player;
import de.fhflensburg.graveyardmanager.core.music.GameMusic;
import de.fhflensburg.graveyardmanager.level.Level;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.ImageSelectSelectionChangedEvent;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.render.NiftyImage;
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
public class CreateMapView extends View implements ScreenController
{
    /** An instance of the nifty object */
    private Nifty nifty;

    /** An instance of the screen */
    private Screen screen;

    /** An instance of the game */
    GraveyardManagerGame game;

    /** Contains the name of the selected map */
    private String currentMapSelection;

    private boolean toggle;

    /**
     * The default constructor
     */
    public CreateMapView()
    {
        currentMapSelection = "Wiese";
        toggle=false;
    }

    /**
     * Set a pointer to the game instance.
     * We have to call this constructor inside the fromXml() method.
     *
     * @param stateBasedGame A representation of the game
     */
    public CreateMapView(StateBasedGame stateBasedGame)
    {
        this();
        this.game = (GraveyardManagerGame) stateBasedGame;
        toggle=false;
    }

    /**
     * Called by Slick2D
     *
     * @return The unique id of this state
     */
    @Override
    public int getID()
    {
        return GraveyardManagerGame.GameStates.CREATE_MAP_STATE.ordinal();
    }

    /**
     * Here you have to initialize all resources (images, fonts, sounds, ...)
     * which you want to use in this state. The method will be called by the ResourceView and
     * pre load all the stuff.
     *
     */
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
    nifty.fromXml(GUI_PATH + "creategamemap.xml", "start", new CreateMapView(stateBasedGame));
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


    public void goPrevMap()
    {

        // first load the new image
        NiftyImage newImage = nifty.getRenderEngine().createImage("res/de/fhflensburg/graveyardmanager/images/Karte_Wiese.png", false);
        NiftyImage newImage1 = nifty.getRenderEngine().createImage("res/de/fhflensburg/graveyardmanager/images/Karte_Wueste.png", false);

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

    public void goNextMap()
    {
        // first load the new image
        NiftyImage newImage = nifty.getRenderEngine().createImage("res/de/fhflensburg/graveyardmanager/images/Karte_Wiese.png", false);
        NiftyImage newImage1 = nifty.getRenderEngine().createImage("res/de/fhflensburg/graveyardmanager/images/Karte_Wueste.png", false);

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

    public void gotoGame()
    {
        //Übergangslösung, da Sahara in dieser Version nicht auswählbar sein soll
        currentMapSelection = "Wiese";

		Level level = new Level(currentMapSelection);
		Player player = new Player(1, "Stefano", 0);
		level.addPlayer(player);
		game.getEngine().initGame(level);
        game.enterState(GraveyardManagerGame.GameStates.IN_GAME_STATE.ordinal());
        game.getContainer().setShowFPS(false);
    }

    public void goToCharMenue()
    {
        game.enterState(GraveyardManagerGame.GameStates.CREATE_GAME_STATE.ordinal());
    }
}
