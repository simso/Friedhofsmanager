package de.fhflensburg.graveyardmanager.core.map;

import de.fhflensburg.graveyardmanager.core.layers.entities.ActiveEntity;
import de.fhflensburg.graveyardmanager.states.InGameView;
import de.fhflensburg.graveyardmanager.utils.Configuration;
import de.fhflensburg.graveyardmanager.utils.ResourceManager;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import java.io.InputStream;
import java.util.ArrayList;

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
	private EntityLocation[][] entitiesLocations;
	private ArrayList<Ent> entities;
	private ArrayList<ActiveEntity> rendererEntities;

	public Map(String name, InputStream ref, String tileSetsLocation) throws SlickException
	{
		super(ref, tileSetsLocation);
		this.name = name;
		backgroundImage = ResourceManager.getImage(name);
		entitiesLocations = new EntityLocation[width][height];
		rendererEntities = new ArrayList<ActiveEntity>();
		blocked = new boolean[width][height];
		water = new	boolean[width][height];

		for (int x = 0; x < width; x++)
		{
			for (int y= 0; y < height; y++)
			{
				entitiesLocations[x][y] = new EntityLocation();

				// Collisions
				int tileID = this.getTileId(x, y, 2);
				String value = this.getTileProperty(tileID, "blocked", "false");
				blocked[x][y] = value.equals("true");

				// Water
				tileID = this.getTileId(x, y, 1);
				value = this.getTileProperty(tileID, "water", "false");
				water[x][y] = value.equals("true");
			}
		}
	}

	public void init(InGameView engine)
	{
		needScroll = (getWidthInPixel() > engine.getContainer().getWidth() || getHeightInPixel() > engine.getContainer().getHeight());

		for (int i = 0; i < entitiesLocations.length; i++) {
			for (int j = 0; j < entitiesLocations[i].length; j++) {
				entitiesLocations[i][j].clear();
			}
		}
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

	public boolean isBlocked(int x, int y)
	{
		if (x > 0 && x < width && y > 0 && y < height )
		{
			return blocked[x][y];
		}
		else
		{
			return true;
		}
	}

	public boolean isWater(int x, int y)
	{
		if ((x > 0) && (x < width) && (y > 0) && (y < height))
		{
			return water[x][y];
		}
		else
		{
			return true;
		}
	}

	public void addEntityLocation(ActiveEntity entity, boolean block, int x, int y)
	{
		entitiesLocations[x][y].addEntity(entity, block);
	}

	public int compareTo(Map map)
	{
		return this.name.compareTo(map.name);
	}

	private static class Ent
	{
	 	public int type;
		public int x;
		public int y;
	}

	private static class EntityLocation
	{
	 	private ArrayList<Entity> entities;

		public EntityLocation()
		{
			entities = new ArrayList<Entity>();
		}

		private boolean contain(ActiveEntity e)
		{
			for (int i = 0; i < entities.size(); i++)
			{
				if (entities.get(i).entity == e)
				{
					return true;
				}
			}
			return false;
		}

		public void addEntity(ActiveEntity e, boolean block)
		{
			if (!contain(e))
			{
				entities.add(new Entity(e, block));
			}
		}

		public void removeEntity(ActiveEntity e)
		{
			for (int i = 0; i < entities.size(); i++)
			{
				if (entities.get(i).entity == e)
				{
					entities.remove(i);
					break;
				}
			}
		}

		public ActiveEntity getLastEntity()
		{
			if (entities.isEmpty())
			{
				return null;
			}
			else
			{
				return entities.get(entities.size() - 1).entity;
			}
		}

		public boolean isBlocked()
		{
			for(int i = 0; i < entities.size(); i++)
			{
				if(entities.get(i).blocked)
				{
					return true;
				}
			}
			return false;
		}

		public boolean isOccupy()
		{
			return !entities.isEmpty();
		}

		public void clear()
		{
			entities.clear();
		}

		private static class Entity
		{
			private ActiveEntity entity;
			protected boolean blocked;

			public Entity(ActiveEntity entity, boolean blocked)
			{
				this.entity = entity;
				this.blocked = blocked;
			}
		}
	}


}
