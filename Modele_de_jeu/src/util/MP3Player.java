package util;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.media.Time;


import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.bean.playerbean.MediaPlayer;
import javax.media.format.AudioFormat;

/**
 * Classe capable de lire des MP3, de les arreter, de les mettres en pause et de reprendre la lecture
 * @author Nicolas Bighetti & Flavian Jacquot
 *
 */

public class MP3Player {
	
	private MediaPlayer mp;
	
	private Time pauseTime;
	
	private String title;

	public MP3Player(String mp3) throws NoPlayerException,
			MalformedURLException, IOException {
		if (mp3 == null)
			return;
		Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
		Format input2 = new AudioFormat(AudioFormat.MPEG);
		Format output = new AudioFormat(AudioFormat.LINEAR);
		
		PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder",
				new Format[] { input1, input2 }, new Format[] { output },
				PlugInManager.CODEC);
		Player player = Manager.createPlayer(new MediaLocator(new File(mp3)
				.toURI().toURL()));
		mp = new MediaPlayer();
		mp.setPlayer(player);
		mp.setPreferredSize(new Dimension(400, 50));
		
		title = mp3.substring(mp3.lastIndexOf('/')+1, mp3.indexOf(".mp3"));
		System.out.println(title);
	}
	
	/**
	 * Démarre la lecture du fichier MP3 paramètre
	 */
	public void play()
	{
		mp.start();
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
		setPauseTime(mp.getMediaTime());
		mp.stop();
	}
	
	/**
	 * Reprends la lecture du MP3 mise en pause
	 */
	public void unpause()
	{
		mp.setMediaTime(pauseTime);
		mp.start();
	}
	
	/**
	 * Libère la mémoire, ferme les flux, et stop le thread de lecture du MP3
	 */
	public void close()
	{
		mp.deallocate();
		mp.close();
	}

	/**
	 * @return the pauseTime
	 */
	public Time getPauseTime() {
		return pauseTime;
	}

	/**
	 * @param pauseTime the pauseTime to set
	 */
	public void setPauseTime(Time pauseTime) {
		this.pauseTime = pauseTime;
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
	
	

}