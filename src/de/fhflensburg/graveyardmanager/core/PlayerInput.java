package de.fhflensburg.graveyardmanager.core;

import de.fhflensburg.graveyardmanager.states.InGameView;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 07.12.12
 * Time: 01:04
 */
public class PlayerInput
{
	private int pressedX;
	private int pressedY;
	private boolean pressedLeft;
	private InGameView engine;

	public PlayerInput(InGameView engine)
	{
		this.engine = engine;
	}

	public boolean isPressedLeft() {
		return pressedLeft;
	}
}
