package util;

import java.io.File;

import jaco.mp3.player.MP3Player;

/**
 * Classe capable de lire des MP3, de les arreter, de les mettres en pause et de reprendre la lecture
 * @author Nicolas Bighetti & Flavian Jacquot
 *
 */

public class MyMP3Player {
	
	private MP3Player mp;
	
	private String title;

	public MyMP3Player(String mp3)  {
		title = mp3.substring(mp3.lastIndexOf("/")+1, mp3.indexOf(".mp3"));
		mp = new MP3Player(new File(mp3));
	}
	
	/**
	 * Démarre la lecture du fichier MP3 paramètre
	 */
	public void play()
	{
		mp.play();
	}
	
	/**
	 * Stop la lecture du MP3 paramètre
	 */
	public void stop()
	{
		mp.stop();
	}

	/**
	 * Mets en pause la lecture du MP3
	 */
	public void pause()
	{
		mp.pause();
	}
	
	/**
	 * Reprends la lecture du MP3 mise en pause
	 */
	public void unpause()
	{
		mp.play();
	}
	
	/**
	 * Libère la mémoire, ferme les flux, et stop le thread de lecture du MP3
	 */
	public void close()
	{
		mp.play();
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the mp
	 */
	public MP3Player getMp() {
		return mp;
	}

	/**
	 * @param mp the mp to set
	 */
	public void setMp(MP3Player mp) {
		this.mp = mp;
	}
	
	

}