package de.fhflensburg.graveyardmanager.core.layers.entities;

import de.fhflensburg.graveyardmanager.core.layers.entities.buildings.*;


import de.fhflensburg.graveyardmanager.states.InGameView;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 18.12.12
 * Time: 18:17
 */
public class EntityGenerator
{

	public static ActiveEntity createActiveEntityFromMap(InGameView engine, int type, float x, float y)
	{
		ActiveEntity activeEntity = createEntity(engine, type, -1);
		activeEntity.setLocation(x, y);
		return activeEntity;
	}

	public static ActiveEntity createEntity(InGameView engine, int type, int playerId)
	{
		if (EntityData.isBuilding(type))
		{
			switch (type)
			{
				case EntityData.BUILDING_CHAPEL:
						return new Chapel(engine, playerId);
				case EntityData.BUILDING_CHURCH:
						return new Church(engine, playerId);
				case EntityData.BUILDING_HOUSE:
						return new House(engine, playerId);
				default:
					return null;
			}
		}
		else if (EntityData.isTombstone(type))
		{
			switch (type)
			{
				case EntityData.TOMBSTONE_WATER:
					return new TombstoneWater(engine, playerId);
				case EntityData.TOMBSTONE_CROSS_WOODEN:
					return new TombstoneWoodenCross(engine, playerId);
				case EntityData.TOMBSTONE_GRANITE:
					return new TombstoneGranite(engine, playerId);
				case EntityData.TOMBSTONE_SANDSTONE:
					return new TombstoneSandstone(engine, playerId);
				default:
					return null;
			}
		}
		else
		{
			return null;
		}

	}
}
