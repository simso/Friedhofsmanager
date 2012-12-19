package de.fhflensburg.graveyardmanager.utils;

import java.util.concurrent.TimeUnit;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 04.12.12
 * Time: 15:01
 */
public class Timer
{
	private float eventTime;
	private float deltaStock;
	private int limit;
	private int limitCounter;

	public Timer(int eventTime)
	{
		this.eventTime = eventTime;
		resetTime();
	}

	public Timer(int eventTime, int limit)
	{
		this(eventTime);
		this.limit = limit;
	}

	public void update(int delta)
	{
		deltaStock += delta;
		if ((deltaStock >= eventTime) && !(limit != 0 && limitCounter == limit))
		{
			deltaStock = eventTime;
			limitCounter++;
		}
	}

	public boolean isComplete()
	{
		return (limitCounter == limit && limit != 0);
	}

	public boolean isTimeComplete()
	{
		return deltaStock >= eventTime;
	}

	public void resetTime()
	{
		deltaStock = 0;
	}

	public boolean isTimeReset()
	{
		return deltaStock == 0;
	}

	public void reset()
	{
		limitCounter = 0;
		resetTime();
	}

	public void setTimeComplete()
	{
		deltaStock = eventTime;
	}

	public float getPercentage()
	{
		return deltaStock / eventTime;
	}

	public float getEventTime()
	{
		return eventTime;
	}

	public String getTime()
	{
		return String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) eventTime), TimeUnit.MILLISECONDS.toSeconds((long) eventTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) eventTime)));
	}

}
