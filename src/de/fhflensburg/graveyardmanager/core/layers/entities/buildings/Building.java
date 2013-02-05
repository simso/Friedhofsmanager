package de.fhflensburg.graveyardmanager.core.layers.entities.buildings;

import de.fhflensburg.graveyardmanager.core.layers.Layer;
import de.fhflensburg.graveyardmanager.core.layers.entities.ActiveEntity;
import de.fhflensburg.graveyardmanager.core.layers.entities.IBigEntity;
import de.fhflensburg.graveyardmanager.states.InGameView;
import org.newdawn.slick.Color;
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
public abstract class Building extends ActiveEntity implements IBigEntity
{
	private static final int DEFAULT_DISTANCE_MAX_BETWEEN_BUILDINGS = 100;
	protected static final Color FADE_RED = new Color(255, 0, 0, 100);
	protected static final Color FADE_GREEN = new Color(0, 255, 0, 100);
    public int falszaehler;

	private boolean block;

	// For building with a different behaviour
	protected boolean validLocation;
	protected int distanceMaxBetweenBuilding;

	public Building(InGameView engine, int type, boolean block, int playerId)
	{
		super(engine, Layer.FIRST_EFFECT, type, playerId);
		distanceMaxBetweenBuilding = DEFAULT_DISTANCE_MAX_BETWEEN_BUILDINGS;
		this.block = block;

	}

	protected abstract void renderBuilding(GameContainer gameContainer, Graphics g) throws SlickException;

	@Override
	public void renderEntity(GameContainer gameContainer, Graphics g) throws SlickException
	{
	 	renderBuilding(gameContainer, g);

	}

	@Override
	public void renderOnMiniMap(Graphics g, float x, float y, float tw, float th)
	{
		g.setColor(Color.blue);
		g.fillRect(x, y, (width / 20) * tw, (height / 20) * th);
	}

	public void renderLocationOnMap(Graphics g) {
		x = engine.getMouseX();
		y = engine.getMouseY();

       falszaehler = 0;

		int lx = (int) (x / engine.getTileW());
		int ly = (int) (y / engine.getTileH());

		g.translate(engine.getXScrollDecal(), engine.getYScrollDecal());

		for (int i = 0; i < width / 64; i++) {
			for (int j = 0; j < height / 64; j++) {
				int x = (lx + i);
				int y = (ly + j);
				checkValidLocation(g, x, y);
				g.fillRect(x * 64, y * 64, 64, 64);
			}
		}
		g.translate(-engine.getXScrollDecal(), -engine.getYScrollDecal());
        if(falszaehler>0)
            validLocation=false;
        else
            validLocation=true;
	}

	public boolean isValidLocation() {
		return validLocation;
	}

	protected void checkValidLocation(Graphics g, int x, int y) {
		if (/*engine.getMap().isEntityOccupy(x, y) ||*/ engine.getMap().isBlocked(x, y) || engine.getMap().isWater(x, y)) {
			g.setColor(FADE_RED);
		//	validLocation = false;

            falszaehler++;

		} else {
			//validLocation = true;
			g.setColor(FADE_GREEN);
		}
	}

	@Override
	public void setLocation(float x, float y) {
		super.setLocation(x, y);

		int lx = (int) (x / engine.getTileW());
		int ly = (int) (y / engine.getTileH());

		for (int i = 0; i < width / 20; i++) {
			for (int j = 0; j < height / 20; j++) {
				engine.getMap().addEntityLocation(this, block, lx + i, ly + j);
			}
		}
	}

	@Override
	public float getRealX() {
		return x + (width / 2);
	}

	@Override
	public float getRealY() {
		return y + (height / 2);
	}



	@Override
	public void updateEntity(GameContainer container, int delta) throws SlickException
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void remove()
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void target(ActiveEntity target, int mx, int my)
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public int getTargetCursor(ActiveEntity target, int mx, int my)
	{
		return 0;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public boolean fogOnUnit()
	{
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}
}
