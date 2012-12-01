package de.fhflensburg.graveyardmanager;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 * <p/>
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 29.11.12
 * Time: 22:30
 */

/**
 * This is a singleton because the settings
 * of a game must be there only once
 */
public final class GameSettings
{
	private static GameSettings settings;

	private GameSettings()
	{}

	public static synchronized GameSettings getInstance()
	{
		if (settings == null)
		{
			settings = new GameSettings();
		}
		return settings;
	}
}
