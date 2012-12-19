package de.fhflensburg.graveyardmanager.core.layers.entities;

import de.fhflensburg.graveyardmanager.states.InGameView;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 18.12.12
 * Time: 00:49
 */
public abstract class BasicEntity implements IEntity
{
	protected InGameView engine;
	protected int layer;
	protected int width;
	protected int height;
	protected float x;
	protected float y;

	public BasicEntity(InGameView engine, int layer)
	{
		this.engine = engine;
		this.layer = layer;
	}

	public void setLocation(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public boolean onEntity(int mx, int my)
	{
		return ((mx >= x) && (mx <= x + width) && (my >= y) && (my <= y + height));
	}

	public int getHeight()
	{
		return height;
	}

	public int getWidth()
	{
		return width;
	}

	public int getLayer()
	{
		return layer;
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

}
