package de.fhflensburg.graveyardmanager.states.controller;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.fhflensburg.graveyardmanager.core.layers.entities.EntityData;
import de.fhflensburg.graveyardmanager.core.layers.entities.EntityGenerator;
import de.fhflensburg.graveyardmanager.core.layers.entities.buildings.Building;
import de.fhflensburg.graveyardmanager.states.InGameView;
import de.fhflensburg.graveyardmanager.states.View;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 16.12.12
 * Time: 14:22
 */
public class InGameController implements ScreenController
{

	/** An instance of the nifty object */
	private Nifty nifty;

	/** An instance of the screen */
	private Screen screen;

	/** An instance of the game */
	GraveyardManagerGame game;

	InGameView view;

	private Building building;

	NiftyImage[] orderImages = new NiftyImage[2];

	private int newOrderImage;

	public boolean newOrdersNoticed;

	public Element[] nonVisibleElementsAtStart = new Element[3];

	private NiftyImage buildItems;

	public InGameController(StateBasedGame stateBasedGame, InGameView view)
	{
		game = (GraveyardManagerGame) stateBasedGame;
		this.view = view;
		newOrdersNoticed = false;

	}

	@Override
	public void bind(Nifty nifty, Screen screen)
	{
		this.nifty = nifty;
		this.screen = screen;
		orderImages[0] = nifty.getRenderEngine().createImage(View.IMAGE_PATH + "Auftrag.png", false);
		orderImages[1] = nifty.getRenderEngine().createImage(View.IMAGE_PATH + "Auftrag_neu.png", false);
		nonVisibleElementsAtStart[0] = screen.findElementByName("window_BuildItems");
		nonVisibleElementsAtStart[1] = screen.findElementByName("window_OrderList");
        nonVisibleElementsAtStart[2] = screen.findElementByName("window_OrderList2");
//		buildItems = nifty.getRenderEngine().createImage(View.IMAGE_PATH + "Tombstones.png", false);

	}

	@Override
	public void onStartScreen()
	{
		Element orderList = screen.findElementByName("orderList");
		orderList.getRenderer(ImageRenderer.class).setImage(orderImages[1]);
		newOrderImage = 1;

		//Element buildItem_1 = screen.findElementByName("buildItem_1");
		//buildItem_1.getRenderer(ImageRenderer.class).setImage(buildItems.setImageMode("sprite:w,h,index"));
	}

	public void render()
	{
		screen.findElementByName("money").getRenderer(TextRenderer.class).setText("Guthaben: " + view.getPlayer().getBalance());
		screen.findElementByName("bodycount").getRenderer(TextRenderer.class).setText("Anzahl der Toten: " + view.getPlayer().getScore());
		screen.findElementByName("time").getRenderer(TextRenderer.class).setText("Zeit: " + view.getGameTime());
	}

	public void update()
	{
		screen.findElementByName("money").getRenderer(TextRenderer.class).setText("Guthaben: " + view.getPlayer().getBalance());
		screen.findElementByName("bodycount").getRenderer(TextRenderer.class).setText("Anzahl der Toten: " + view.getPlayer().getScore());
		screen.findElementByName("time").getRenderer(TextRenderer.class).setText("Zeit: " + view.getGameTime());
	}

	@Override
	public void onEndScreen()
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	public void toggleMiniMap()
	{
		view.isMinimapEnabled = !view.isMinimapEnabled;
	}


    public void goSettings()
    {
        game.enterState(GraveyardManagerGame.GameStates.MAIN_MENU_STATE.ordinal());
    }

	public void buildItems()
	{
		if (nonVisibleElementsAtStart[0].isVisible())
		{
			nonVisibleElementsAtStart[0].hide();
		}
		else
		{
			nonVisibleElementsAtStart[0].show();
		}
	}

	public String getCurrentMoney()
	{
		return view.getPlayer().getBalance() + "";

	}

	public String getCurrentBodies()
	{
		return "0";
	}

	public String getTime()
	{
	 	return "";
	}

	/**
	 * Will be called from ingame.xml by Nifty
	 * @param id
	 */
	public void buildItem(String id)
	{
		building = (Building) EntityGenerator.createEntity(view, Integer.parseInt(id), view.getPlayer().getId());
	}

	/**
	 * Will be called from ingame.xml by Nifty
	 * @param id
	 */
	public String getItemName(String id)
	{
		return EntityData.NAMES[Integer.parseInt(id)];
	}

	public void changeOrderImage()
	{
		Element element = screen.findElementByName("orderList");

		if (newOrdersNoticed)
		{
			element.getRenderer(ImageRenderer.class).setImage(orderImages[0]);
			newOrderImage = 0;
            
		}
		else
		{
			switch (newOrderImage)
			{
				case 0:
					element.getRenderer(ImageRenderer.class).setImage(orderImages[1]);
					newOrderImage = 1;
					break;

				case 1:
					element.getRenderer(ImageRenderer.class).setImage(orderImages[0]);
					newOrderImage = 0;
					break;

				default:
					element.getRenderer(ImageRenderer.class).setImage(orderImages[0]);
					newOrderImage = 0;
					break;
			}
		}
	}

    public void newOrder() {

        if (nonVisibleElementsAtStart[1].isVisible() || nonVisibleElementsAtStart[2].isVisible()) {
            nonVisibleElementsAtStart[1].hide();
            nonVisibleElementsAtStart[2].hide();
        } else {
            if (newOrdersNoticed) {
                nonVisibleElementsAtStart[1].hide();
                nonVisibleElementsAtStart[2].show();
            } else nonVisibleElementsAtStart[1].show();
        }
        newOrdersNoticed = true;
    }

    public void currentOrder()
    {

        if (nonVisibleElementsAtStart[1].isVisible())
        {
            nonVisibleElementsAtStart[1].hide();
        }
        else if (newOrdersNoticed == true)
        {
            nonVisibleElementsAtStart[1].show();
        }
    }



	public Building getBuilding()
	{
		return building;
	}

	public void resetBuilding()
	{
		building = null;
	}


}
