package de.fhflensburg.graveyardmanager.utils;

import java.io.*;
import java.util.Properties;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 02.12.12
 * Time: 19:58
 */
public class Configuration
{
	/** The path to the configuration file */
	private static String configFileLocation;

	/** An instance of the configuration file */
	private static Properties configurationFile;

	public static void init(String fileLocation) throws IOException
	{
		Configuration.configFileLocation = fileLocation;
		updateConfigFile();
	}

	public static void updateConfigFile() throws IOException
	{
		InputStreamReader inputStream = new InputStreamReader(new FileInputStream(configFileLocation));
		configurationFile = new Properties();
		configurationFile.load(inputStream);
		inputStream.close();
	}

	public static void saveNewConfig() throws IOException
	{
		OutputStream outputStream = new FileOutputStream(configFileLocation);
		configurationFile.store(outputStream, "");
		outputStream.flush();
		outputStream.close();
		updateConfigFile();
	}

	public static int getWidth()
	{
		return Integer.parseInt(configurationFile.getProperty("width", "800"));
	}

	public static void setWidth(int width)
	{
		configurationFile.setProperty("width", width + "");
	}

	public static int getHeight()
	{
		return Integer.parseInt(configurationFile.getProperty("height", "600"));
	}

	public static void setHeight(int height)
	{
		configurationFile.setProperty("height", height + "");
	}

	public static boolean isFullScreen()
	{
		return configurationFile.getProperty("fullscreen").equals("true");
	}

	public static void toggleFullScreen(boolean fullscreen)
	{
		configurationFile.setProperty("fullscreen", (fullscreen) ? "true" : "false");
	}

	public static boolean isDebugMode()
	{
		return configurationFile.getProperty("debug").equals("true");
	}

	public static void setDebugMode(boolean debug)
	{
		configurationFile.setProperty("debug", (debug) ? "true" : "false");
	}

	public static boolean isVSync()
	{
		return configurationFile.getProperty("vsync").equals("true");
	}

	public static void setVSync(boolean vsync)
	{
		configurationFile.setProperty("vsync", (vsync) ? "true" : "false");
	}

	public static float getMusicVolume()
	{
		return Float.parseFloat(configurationFile.getProperty("musicVolume", "1"));
	}

	public static void setMusicVolume(float volume)
	{
		configurationFile.setProperty("musicVolume", volume + "");
	}

	public static float getSoundVolume()
	{
		return Float.parseFloat(configurationFile.getProperty("soundVolume", "1"));
	}

	public static void setSoundVolume(float volume)
	{
		configurationFile.setProperty("soundVolume", volume + "");
	}
}
