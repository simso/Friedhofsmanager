package de.fhflensburg.graveyardmanager.core.layers;

import de.fhflensburg.graveyardmanager.core.layers.entities.ActiveEntity;
import de.fhflensburg.graveyardmanager.core.layers.entities.IEntity;
import de.fhflensburg.graveyardmanager.core.layers.entities.buildings.Building;
import de.fhflensburg.graveyardmanager.states.InGameView;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 18.12.12
 * Time: 19:39
 */
public class Layer
{
	public static final int FIRST_EFFECT = 0;
	public static final int EARTH_MARINE_ENT = 1;
	public static final int SECOND_EFFECT = 2;
	public static final int FLIGHT_ENT = 3;
	public static final int THIRD_EFFECT = 4;

	private int id;
	private boolean visible;
	private ArrayList<IEntity> array;
	private InGameView engine;

	public Layer(InGameView engine, int id) {
		this.engine = engine;
		this.id = id;
		this.array = new ArrayList<IEntity>();
		this.visible = true;
	}

	public void render(GameContainer container, Graphics g) throws SlickException
	{
		if (visible) {
			for (int i = 0; i < array.size(); i++) {
				array.get(i).render(container, g);
			}
		}
	}

	public void renderInfos(Graphics g) {
		if (visible) {
			for (int i = 0; i < array.size(); i++) {
				if (array.get(i) instanceof ActiveEntity) {
					((ActiveEntity) array.get(i)).renderInfos(g);
				}
			}
		}
	}

	public void updateAll(GameContainer container, int delta) throws SlickException {
		for (int i = 0; i < array.size(); i++) {
			array.get(i).update(container, delta);

			// Must check the size because an update can remove an entity
			if (i < array.size() && array.get(i) instanceof ActiveEntity) {
				engine.addEntToCount(((ActiveEntity) array.get(i)).getPlayerId());
			}
		}
	}

	public void addEntity(IEntity e) {
		array.add(e);
	}

	public void removeEntity(IEntity e) {
		array.remove(e);
	}

	public void removeAllEntity(int playerId) {
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) instanceof ActiveEntity && ((ActiveEntity) array.get(i)).getPlayerId() == playerId) {
				((ActiveEntity) array.get(i)).remove();
				i--;
			}
		}

	}

	public void deselectAllEntities() {
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) instanceof ActiveEntity) {
				((ActiveEntity) array.get(i)).deselected();
			}
		}
	}

	public ArrayList<ActiveEntity> getPlayerSelectedEntities(int playerId) {
		ArrayList<ActiveEntity> a = new ArrayList<ActiveEntity>();
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) instanceof ActiveEntity && ((ActiveEntity) array.get(i)).isSelected() && ((ActiveEntity) array.get(i)).getPlayerId() == playerId) {
				a.add((ActiveEntity) array.get(i));
			}
		}
		return a;
	}

	public void selectEntitiesBetween(int sx, int sy, int mx, int my) {
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) instanceof ActiveEntity && !(array.get(i) instanceof Building)) {
				ActiveEntity e = (ActiveEntity) array.get(i);
				if (e.getPlayerId() == engine.getPlayer().getId() && (e.getX() + e.getWidth() > sx && e.getX() + e.getWidth() < mx)
						&& (e.getY() + e.getHeight() > sy && e.getY() + e.getHeight() < my)) {
					e.selected();
				} else
					e.deselected();
			}
		}
	}

	public ArrayList<Building> getPlayerBuilding() {
		ArrayList<Building> a = new ArrayList<Building>();
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) instanceof Building) {
				Building b = (Building) array.get(i);
				if (engine.isPlayerEntity(b.getPlayerId())) {
					a.add(b);
				}
			}
		}
		return a;
	}

	public void clear() {
		array.clear();
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getId() {
		return id;
	}

}
