package de.fhflensburg.graveyardmanager.core.layers.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 18.12.12
 * Time: 00:37
 */
public interface IEntity
{
	public void render(GameContainer gameContainer, Graphics g) throws SlickException;
	public void update(GameContainer gameContainer, int delta) throws SlickException;
	public float getX();
	public float getY();
	public int getWidth();
	public int getHeight();
	public int getLayer();
}
