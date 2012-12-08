package de.fhflensburg.graveyardmanager.core.map;

import de.fhflensburg.graveyardmanager.states.InGameView;
import de.fhflensburg.graveyardmanager.utils.Configuration;
import de.fhflensburg.graveyardmanager.utils.ResourceManager;
import org.newdawn.slick.*;
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
public class Map extends TiledMap implements Comparable<Map>
{
	private boolean[][] blocked;
	private boolean[][] water;
	private boolean needScroll;
	private Image backgroundImage;
	private String name;




	public Map(String name, InputStream ref, String tileSetsLocation) throws SlickException
	{
		super(ref, tileSetsLocation);
		this.name = name;
		backgroundImage = ResourceManager.getImage(name);
		blocked = new boolean[width][height];
		water = new	boolean[width][height];

		for (int x = 0; x < width; x++)
		{
			for (int y= 0; y < height; y++)
			{
				// Collisions
				int tileID = this.getTileId(x, y, 2);
				String value = this.getTileProperty(tileID, "blocked", "false");
				blocked[x][y] = value.equals("true");

				tileID = this.getTileId(x, y, 1);
				value = this.getTileProperty(tileID, "water", "false");
				water[x][y] = value.equals("true");
			}
		}
		int i = 0;
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
					backgroundImage.getSubImage(
							decalX, decalY,
							(decalX > (getWidthInPixel() - gameContainer.getWidth())) ? gameContainer.getWidth() - (decalX - (getWidthInPixel() - gameContainer.getWidth())) : decalX + gameContainer.getWidth(), decalY + gameContainer.getHeight()),
							0, 0
					);
		}
		else
		{
			g.drawImage(backgroundImage, 0, 0);
		}
	}

	/**
	 * Renders a mini map of the game world to give the user an overview and
	 * indicate where he/she is
	 *
	 * @param g The graphic context
	 * @param x The x origin where the mini map should be go
	 * @param y The y origin where the mini map should be go
	 * @param width The width of the mini map
	 * @param height The height of the mini map
	 * @param decalX The offset when the user scroll left / left
	 * @param decalY The offset when the user scrolls up / down
	 */
	public void renderMiniMap(Graphics g, int x, int y, int width, int height, int decalX, int decalY)
	{
		g.drawImage(backgroundImage.getScaledCopy(width, height), x, y);
		// A border around the minimap
		g.drawRect((float) x, (float) y, (float) width, (float) height);

		// The scaled rectangle which indicates the viewport
		g.setColor(Color.red);
		float scaleFactor = (float) width / backgroundImage.getWidth();
		if (needScroll)
		{
			g.drawRect((float) x + (decalX*scaleFactor), (float) y + (decalY * scaleFactor), (float) Configuration.getWidth() * scaleFactor, (float) Configuration.getHeight() * scaleFactor);
		}
		else
		{
			g.drawRect((float) x, (float)y, (float) Configuration.getWidth() * scaleFactor, (float) Configuration.getHeight() * scaleFactor);
		}
		g.setColor(Color.black);
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

	public String getName()
	{
		return name;
	}

	@Override
	public int compareTo(Map map)
	{
		return this.name.compareTo(map.name);
	}
}
