package de.fhflensburg.graveyardmanager.level;

import de.fhflensburg.graveyardmanager.core.Player;
import de.fhflensburg.graveyardmanager.core.map.Map;
import de.fhflensburg.graveyardmanager.states.InGameView;
import de.fhflensburg.graveyardmanager.utils.ResourceManager;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 19.12.12
 * Time: 00:36
 */
public class Level
{
	private Map map;
//	private GameGoal goal;
	private ArrayList<Player> players;
//	private NPC npc;

	public Level(String map/*, GameGoal goal*/)
	{
		setMap(map);
//		this.goal = goal;
		this.players = new ArrayList<Player>();
//		this.npc = new NPC();
	}

	public void update(InGameView engine, int delta) throws SlickException
	{
//		for (int i = 0; i < players.size(); i++)
//		{
//			if (players.get(i).isNPC())
//			{
//				npc.update(engine, players.get(i), delta);
//			}
//		}
//
//		if (goal.isComplete(engine, delta))
//		{
//			engine.setGameWin();
//		}
	}

	public void updatePlayer(Player player)
	{
		for (int i = 0; i < players.size(); i++)
		{
			if (players.get(i).getId() == player.getId())
			{
				if (players.get(i).isNPC())
				{
					players.get(i).update(player);
				}
			}
		}
	}

	public void addPlayer(Player player)
	{
		players.add(player);
	}

	public void removePlayer(int id)
	{
		for (int i = 0;  i < players.size(); i++)
		{
			if (players.get(i).getId() == id)
			{
				players.remove(id);
			}
		}
	}

	public Player getPlayer(int id)
	{
		for (int i = 0;  i < players.size(); i++)
		{
			if (players.get(i).getId() == id)
			{
				return players.get(id);
			}
		}
		return null;
	}

	public Player getPlayer()
	{
		for (int i = 0;  i < players.size(); i++)
		{
			if (!players.get(i).isNPC())
			{
				return players.get(i);
			}
		}
		return null;
	}

	public Map getMap()
	{
		return map;
	}

	public void setMap(String map)
	{
		this.map = ResourceManager.getMap(map);
	}
}
