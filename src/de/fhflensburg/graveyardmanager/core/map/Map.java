package de.fhflensburg.graveyardmanager.core.map;

import de.fhflensburg.graveyardmanager.states.InGameView;
import de.fhflensburg.graveyardmanager.utils.ResourceManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.io.InputStream;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 06.12.12
 * Time: 19:02
 */
public class Map extends TiledMap
{
	private boolean needScroll;
	private Image backgroundImage;

	public Map(String name, InputStream ref, String tileSetsLocation) throws SlickException
	{
		super(ref, tileSetsLocation);

		backgroundImage = ResourceManager.getImage(name);
	}

	public void init(InGameView engine)
	{
		needScroll = (getWidthInPixel() > engine.getContainer().getWidth() || getHeightInPixel() > engine.getContainer().getHeight());
	}

	public void render(GameContainer gameContainer, Graphics g, int decalX, int decalY)
	{
		if (isNeedScroll())
		{
			g.drawImage(
					backgroundImage.getSubImage(decalX, decalY, (decalX > (getWidthInPixel() - gameContainer.getWidth())) ? gameContainer.getWidth() - (decalX - (getWidthInPixel() - gameContainer.getWidth())) : decalX + gameContainer.getWidth(), decalY + gameContainer.getHeight()),
							0,
							0
					);
		}
		else
		{
			g.drawImage(backgroundImage, 0, 0);
		}
	}

	public int getWidthInPixel()
	{
		return width * tileWidth;
	}

	public int getHeightInPixel()
	{
		return height * tileHeight;
	}

	public boolean isNeedScroll()
	{
		return needScroll;
	}
}
