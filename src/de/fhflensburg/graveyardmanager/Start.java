package de.fhflensburg.graveyardmanager;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 02.12.12
 * Time: 19:42
 */

import de.fhflensburg.graveyardmanager.core.GraveyardManagerGame;
import org.newdawn.slick.SlickException;

import java.io.IOException;

/**
 * Entry point to start the game
 *
 * @author Stefano Kowalke <stefan.kowalke@stud.fh-flensburg.de>
 */
public class Start
{
	public static void main(String[] argv)
	{
		try
		{
			GraveyardManagerGame g = new GraveyardManagerGame("libs/resources.jar", "config/game.properties");
			g.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
}
