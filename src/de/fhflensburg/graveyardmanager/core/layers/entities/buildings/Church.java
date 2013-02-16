package de.fhflensburg.graveyardmanager.core.layers.entities.buildings;

import de.fhflensburg.graveyardmanager.core.layers.entities.EntityData;
import de.fhflensburg.graveyardmanager.states.InGameView;
import de.fhflensburg.graveyardmanager.utils.ResourceManager;
import org.newdawn.slick.*;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 18.12.12
 * Time: 18:25
 */
public class Church extends Building
{

	private SpriteSheet image;

	public Church(InGameView engine, int playerId)
	{
		super(engine, EntityData.BUILDING_CHURCH, true, playerId);
		image = ResourceManager.getSpriteSheet("Buildings");
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
		g.drawImage(image.getSprite(0, 0), x, y);
	}

	public void setPlayerId(int playerId)
	{
		super.setPlayerId(playerId);
	}
}
