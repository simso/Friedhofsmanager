package de.fhflensburg.graveyardmanager.core.music;

import de.fhflensburg.graveyardmanager.utils.ResourceManager;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 09.12.12
 * Time: 14:03
 */
public class GameSound
{
	public static void init()
	{

	}

	public static void inGameMouseClick()
	{
		ResourceManager.getSound("mouseclick").play();
	}
}
