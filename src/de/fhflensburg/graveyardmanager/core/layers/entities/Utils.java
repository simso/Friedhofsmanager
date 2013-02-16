package de.fhflensburg.graveyardmanager.core.layers.entities;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 18.12.12
 * Time: 17:22
 */
public final class Utils
{
	public static float getDistanceBetween(float startX, float startY, float endX, float endY) {
		return (float) Math.sqrt((Math.pow((endX - startX), 2)) + (Math.pow((endY - startY), 2)));
	}
}
