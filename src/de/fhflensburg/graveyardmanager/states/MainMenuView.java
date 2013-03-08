package de.fhflensburg.graveyardmanager.states;

import de.fhflensburg.graveyardmanager.states.InGameView;
import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.lessvoid.nifty.Nifty;
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
public class MainMenuView extends View implements ScreenController
{
    /** An instance of the nifty object */
    private Nifty nifty;

    /** An instance of the screen */
    private Screen screen;

    /** An instance of the game */
    GraveyardManagerGame game;

    InGameView view;

    /**
     * The default constructor
     */
    public MainMenuView()
    {

    }

    /**
     * Set a pointer to the game instance.
     * We have to call this constructor inside the fromXml() method.
     *
     * @param stateBasedGame A representation of the game
     */
    public MainMenuView(StateBasedGame stateBasedGame)
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
        return GraveyardManagerGame.GameStates.MAIN_MENU_STATE.ordinal();
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
        nifty.fromXml(GUI_PATH + "mainmenu.xml", "gomain", new MainMenuView(stateBasedGame));
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

    public void goNeuesSpiel()
    {
        game.enterState(GraveyardManagerGame.GameStates.CREATE_GAME_STATE.ordinal());
    }

    public void goOptionen()
    {
        game.enterState(GraveyardManagerGame.GameStates.OPTION_MENU_STATE.ordinal());
    }

    public void goSpielFortsetzen()
    {
        if(view.gamehasstartedonce)
            game.enterState(GraveyardManagerGame.GameStates.IN_GAME_STATE.ordinal());
        else
        {}
    }

    public void goSpielBeerdigen()
    {
        game.enterState(GraveyardManagerGame.GameStates.ENDGAME.ordinal());
    }

}
