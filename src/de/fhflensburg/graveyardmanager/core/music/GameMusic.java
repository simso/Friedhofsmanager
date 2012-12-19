package de.fhflensburg.graveyardmanager.core.music;

import de.fhflensburg.graveyardmanager.utils.ResourceManager;
import org.newdawn.slick.Music;
import org.newdawn.slick.MusicListener;
import org.newdawn.slick.SlickException;

/**
 * Hodie mihi, Cras tibi - Der Friedhofsmanager
 * Casual Game im Kurs Game Design an der FH Flensburg
 *
 * Created with IntelliJ IDEA.
 * Author: Stefano Kowalke
 * Date: 09.12.12
 * Time: 14:03
 */
public class GameMusic
{
	private static Music theme;

	public static void initMainTheme()
	{
		try {
			theme = new Music("res/de/fhflensburg/graveyardmanager/musics/graveyard.ogg");
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}

	public static void initMusic()
	{
		ResourceManager.getMusic("ingame_1").addListener(new GameMusicListener(1));
	}

	public static void loopMainTheme()
	{
		theme.loop();
	}

	public static void playMusic()
	{
		theme.stop();
		ResourceManager.getMusic("ingame_1").play();
	}

	public static void stopMusic()
	{
		ResourceManager.getMusic("ingame_1").stop();
	}

	private static class GameMusicListener implements MusicListener
	{
		private int nextSong;

		public GameMusicListener(int nextSong)
		{
			this.nextSong = nextSong;
		}

		@Override
		public void musicEnded(Music music)
		{
			ResourceManager.getMusic("ingame_" + nextSong).play();
		}

		@Override
		public void musicSwapped(Music music, Music music1)
		{

		}
	}
}
