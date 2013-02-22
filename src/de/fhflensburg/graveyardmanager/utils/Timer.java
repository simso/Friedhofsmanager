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
    public long spielstunden = 0;
    public long spieltage = 1;


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


        if((((TimeUnit.MILLISECONDS.toSeconds((long)deltaStock)))) > 0)
        /**
         *  der deltaStock liefert die fps in Millisekunden zurück, daher müssen sie mit
         *  24[fps]*25[Zeitfaktor]*60[Minuten] = 36000 berechnet werden         *
          */
            spielstunden++;

            if (spielstunden == 36000*24)
            {
                spieltage++;
                spielstunden=0;
            }



		return String.format("%d. Tag %d Uhr",spieltage, spielstunden/36000);
	}

}
