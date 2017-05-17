package eu.leoregner.tunescast.web;
import com.sun.net.httpserver.*;
import java.io.*;

public class ITunesAlbumArt implements HttpHandler
{
	public void handle(final HttpExchange http)
	{
		new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					File artImage = eu.leoregner.tunescast.comm.ITunesReader.getCurrentArtwork();
					if(!artImage.exists()) artImage = new File("res/albumArtNone.png");
					WebServer.stream(http, "image/png", artImage);
				}
				catch(IOException x) { http.close(); }
			}
		}).start();
	}
}