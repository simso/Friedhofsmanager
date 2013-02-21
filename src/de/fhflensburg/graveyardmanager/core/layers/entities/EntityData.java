package de.fhflensburg.graveyardmanager.core.layers.entities;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 18.12.12
 * Time: 17:08
 */
public final class EntityData
{
	// Buildings
	public static final int BUILDING_CHURCH = 0;
	public static final int BUILDING_CHAPEL = 1;
	public static final int BUILDING_HOUSE = 2;

	// Tombstones
	public static final int TOMBSTONE_WATER = 3;
	public static final int TOMBSTONE_CROSS_WOODEN = 4;
	public static final int TOMBSTONE_SANDSTONE = 5;
	public static final int TOMBSTONE_GRANITE = 6;

	// Ways

	// Fences

	public static boolean isBuilding(int type)
	{
		return type >= 0 && type < TOMBSTONE_WATER;
	}

	public static boolean isTombstone(int type)
	{
		return type >= TOMBSTONE_WATER && type <= TOMBSTONE_GRANITE;

	}

	public static final String[] NAMES = new String[]
	{
		"Kirche",
		"Kapelle",
		"Haus",
		"Seeurne",
		"Holzkreuz",
		"Grabstein (Sandstein)",
		"Grabstein (Granit)"
	};

	public static final int[] PRICE = new int[]
	{
		// Buildings
		10000, //Kirche
		5000,  //Kapelle
		2500,  //Haus
		// Tombstones
		500,  //Seeurne
		100,  //Holzkreuz
		250, //Grabstein (Sandstein)
		500,  //Grabstein (Granit)
		// Ways

		// Fences
	};

}
