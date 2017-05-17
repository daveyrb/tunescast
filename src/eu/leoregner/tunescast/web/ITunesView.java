package eu.leoregner.tunescast.web;
import com.sun.net.httpserver.*;
import java.io.*;

public class ITunesView implements HttpHandler
{
	public void handle(HttpExchange http) throws IOException
	{
		final File indexHtml = new File("res/iTunes.html");
		WebServer.stream(http, "text/html; charset=utf8", indexHtml);
		http.sendResponseHeaders(200, indexHtml.length());
	}
}