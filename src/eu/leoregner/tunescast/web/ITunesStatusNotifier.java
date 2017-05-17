package eu.leoregner.tunescast.web;
import com.sun.net.httpserver.*;
import java.io.*;

public class ITunesStatusNotifier implements HttpHandler
{
	public void handle(HttpExchange http) throws IOException
	{
		final String jsonp = "callback";
		
		// get iTunes status as JSON array
		final byte[] data = (jsonp + "(" + eu.leoregner.tunescast.comm.ITunesReader.getStatus() + ")").getBytes("UTF-8");
		
		// send status as HTTP response
		http.getResponseHeaders().add("Cache-Control", "no-store, must-revalidate");
		http.getResponseHeaders().add("Content-Type", "text/javascript; charset=utf8");
		http.sendResponseHeaders(200, data.length);
		http.getResponseBody().write(data);
		http.getResponseBody().flush();
		http.close();
	}
}