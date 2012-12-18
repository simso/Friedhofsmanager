package de.fhflensburg.graveyardmanager.states.controller;

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import de.fhflensburg.graveyardmanager.states.InGameView;
import de.fhflensburg.graveyardmanager.states.View;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
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

	NiftyImage[] orderImages = new NiftyImage[2];

	private int currentOrderImage;

	public boolean newOrdersNoticed;

	public Element[] nonVisibleElementsAtStart = new Element[2];

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
//		buildItems = nifty.getRenderEngine().createImage(View.IMAGE_PATH + "Tombstones.png", false);

	}

	@Override
	public void onStartScreen()
	{
		Element orderList = screen.findElementByName("orderList");
		orderList.getRenderer(ImageRenderer.class).setImage(orderImages[1]);
		currentOrderImage = 1;

		//Element buildItem_1 = screen.findElementByName("buildItem_1");
		//buildItem_1.getRenderer(ImageRenderer.class).setImage(buildItems.setImageMode("sprite:w,h,index"));
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

	public void changeOrderImage()
	{
		Element element = screen.findElementByName("orderList");

		if (newOrdersNoticed)
		{
			element.getRenderer(ImageRenderer.class).setImage(orderImages[0]);
			currentOrderImage = 0;
		}
		else
		{
			switch (currentOrderImage)
			{
				case 0:
					element.getRenderer(ImageRenderer.class).setImage(orderImages[1]);
					currentOrderImage = 1;
					break;

				case 1:
					element.getRenderer(ImageRenderer.class).setImage(orderImages[0]);
					currentOrderImage = 0;
					break;

				default:
					element.getRenderer(ImageRenderer.class).setImage(orderImages[0]);
					currentOrderImage = 0;
					break;
			}
		}


	}

	public void newOrder()
	{
		newOrdersNoticed = true;
		if (nonVisibleElementsAtStart[1].isVisible())
		{
			nonVisibleElementsAtStart[1].hide();
		}
		else
		{
			nonVisibleElementsAtStart[1].show();
		}
	}
}
