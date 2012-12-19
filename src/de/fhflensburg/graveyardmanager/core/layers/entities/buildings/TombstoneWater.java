package de.fhflensburg.graveyardmanager.core.layers.entities.buildings;

import de.fhflensburg.graveyardmanager.core.layers.entities.EntityData;
import de.fhflensburg.graveyardmanager.states.InGameView;
import de.fhflensburg.graveyardmanager.utils.ResourceManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 18.12.12
 * Time: 19:09
 */
public class TombstoneWater extends Building
{

	private SpriteSheet image;

	public TombstoneWater(InGameView engine, int playerId)
	{
		super(engine, EntityData.TOMBSTONE_WATER, true, playerId);
		image = ResourceManager.getSpriteSheet("Tombstones");
		width = image.getSprite(0, 0).getWidth();
		height = image.getSprite(0, 0).getHeight();
	}

	@Override
	public void setLocation(float x, float y)
	{
		super.setLocation(x, y);
	}

	@Override
	protected void renderBuilding(GameContainer gameContainer, Graphics g) throws SlickException
	{
		g.drawImage(image.getSprite(0, 1), x, y);
	}

	public void setPlayerId(int playerId)
	{
		super.setPlayerId(playerId);
	}
}
