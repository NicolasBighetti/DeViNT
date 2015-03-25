package util;
import java.io.File;

import java.util.ArrayList;
import java.util.List;


/**
 * Récupère de manière récursive les fichiers mp3 dans un dossier source passé en paramètre
 * @author Nicolas Bighetti
 *
 */

public class MP3Gatherer {

	private List<String> mp3Path = new ArrayList<String>();
	
	private List<String> mp3Name = new ArrayList<String>();
	
	private String originalPath;
	
	public MP3Gatherer(String path)
	{
		this.originalPath = path;
		
	}

	
	/**
	 * @return<code> true </code> si le path de construction est un dossier valide, <code> faux </code> aussi non
	 */
	public boolean isAValidFile()
	{
		File f = new File(originalPath);
		return f.isDirectory();
	}
	/**
	 * Méthode charge de récupérer les fichiers MP3 dans un dossier
	 * La récolte se fait de manière récursive
	 */
	public void gatherMP3() {
		// TODO Auto-generated method stub
		File f = new File(originalPath);
		for (String s : f.list())
		{
			if(s.endsWith(".mp3"))
			{
				mp3Path.add(originalPath+s);
				System.out.println(originalPath+s);
			}
			File tmp = new File(originalPath + s);
			if(tmp.isDirectory())
			{
				gatherMP3(originalPath+ s);
			}
		}
		
		gatherTitle();
	}

	/**
	 * Retire le nom des musiques à partir des chemins
	 */
	private void gatherTitle() {
		// TODO Auto-generated method stub
		for (String string : mp3Path) {
			mp3Name.add( string.substring(string.lastIndexOf('/')+1, string.indexOf(".mp3")));
		}
	}


	/**
	 * Récupère les mp3 depuis le path passé en paramètre
	 * @param string le path de départ
	 */
	private void gatherMP3(String string) {
		// TODO Auto-generated method stub
		File f = new File(string);
		for (String s : f.list())
		{
			if(s.endsWith(".mp3"))
			{
				mp3Path.add(string+"/"+s);
				System.out.println(string+"/"+s);
			}
			
			File tmp = new File(string +"/"+ s);
			if(tmp.isDirectory())
			{
				gatherMP3(string +"/"+ s);
			}
		}
	}


	/**
	 * @return the mp3Path
	 */
	public List<String> getMp3Path() {
		return mp3Path;
	}

	/**
	 * @param mp3Path the mp3Path to set
	 */
	public void setMp3Path(List<String> mp3Path) {
		this.mp3Path = mp3Path;
	}

	/**
	 * @return the originalPath
	 */
	public String getOriginalPath() {
		return originalPath;
	}

	/**
	 * @param originalPath the originalPath to set
	 */
	public void setOriginalPath(String originalPath) {
		this.originalPath = originalPath;
	}


	/**
	 * @return the mp3Name
	 */
	public List<String> getMp3Name() {
		return mp3Name;
	}


	/**
	 * @param mp3Name the mp3Name to set
	 */
	public void setMp3Name(List<String> mp3Name) {
		this.mp3Name = mp3Name;
	}
	
}
