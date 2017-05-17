package eu.leoregner.tunescast.comm;
import java.io.*;

public class ITunesReader
{
	private static final ProcessBuilder statusConnector, artworkConnector;
	
	static
	{
		if(System.getProperty("os.name").toLowerCase().contains("mac"))
		{
			statusConnector = new ProcessBuilder("osascript", "iTunes_status.scpt");
			artworkConnector = new ProcessBuilder("osascript", "iTunes_artwork.scpt");
			
		}
		else // Microsoft Windows
		{
			statusConnector = new ProcessBuilder("CScript", "//NoLogo", "iTunes_status.js");
			artworkConnector = new ProcessBuilder("CScript", "//NoLogo", "iTunes_artwork.scpt");
		}
		
		statusConnector.directory(new File("res"));
		artworkConnector.directory(new File("res"));
	}
	
	/** @return the current iTunes status as JSON string */
	public static synchronized String getStatus()
	{
		try
		{
			final BufferedReader responseReader = new BufferedReader(new InputStreamReader(statusConnector.start().getInputStream()));
			String response = responseReader.readLine();
			responseReader.close();
			return response;
		}
		catch(IOException x)
		{
			x.printStackTrace();
			return null;
		}
	}
	
	/** @return the album artwork of the currently playing iTunes track */
	public static synchronized File getCurrentArtwork()
	{
		try
		{
			final File artworkImageFile = new File("res/albumArt.jpg");
			
			// delete old album art and store new one
			artworkImageFile.delete();
			artworkConnector.start().waitFor();
			return artworkImageFile;
		}
		catch(Exception x)
		{
			x.printStackTrace();
			return null;
		}
	}
}