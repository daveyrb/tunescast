package eu.leoregner.tunescast.web;
import com.sun.net.httpserver.*;
import java.io.*;

public class MediaProxy implements HttpHandler
{
	public void handle(final HttpExchange http)
	{
		new Thread(new Runnable()
		{
			public void run()
			{
				try
				{
					final File file = new File(java.net.URLDecoder.decode(http.getRequestURI().getQuery(), "UTF-8"));
					WebServer.stream(http, "audio/mp3", file);
				}
				catch(IOException x) { http.close(); }
			}
		}).start();
	}
}