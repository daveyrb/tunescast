package eu.leoregner.tunescast.web;
import com.sun.net.httpserver.*;
import java.io.*;

public class WebServer
{
	private static final int buffer_size = 32 * 1024;
	private final int port;
	
	/** starts the web server */
	public WebServer() throws IOException
	{
		final HttpServer server = HttpServer.create(new java.net.InetSocketAddress("localhost", 0), 1000);
		server.createContext("/status/itunes", new ITunesStatusNotifier());
		server.createContext("/proxy", new MediaProxy());
		server.createContext("/image", new ITunesAlbumArt());
		server.createContext("/", new ITunesView());
		server.start();
		
		this.port = server.getAddress().getPort();
	}
	
	/** @return the port on which this web server is running */
	public final int getLocalTcpPort()
	{
		return port;
	}
	
	/** streams the given file using the provided http exchange */
	public static final void stream(HttpExchange http, String mimeType, File file) throws IOException
	{
		// check if only a part of the file shall be streamed
		int start = 0;
		if(http.getRequestHeaders().containsKey("Range"))
		{
			final String rangeHeader = http.getRequestHeaders().getFirst("Range");
			start = Integer.parseInt(rangeHeader.substring(6, rangeHeader.indexOf("-")));
		}
		
		// send file as HTTP response
		http.getResponseHeaders().add("Content-Type", mimeType);
		http.getResponseHeaders().add("Accept-Ranges", "bytes");
		if(start > 0)
			http.getResponseHeaders().add("Content-Range", "bytes " + start + "-" + (file.length() - 1) + "/" + file.length());
		http.sendResponseHeaders(start == 0 ? 200 : 206, file.length() - start);
		
		int read;
		byte[] buffer = new byte[buffer_size];
		final InputStream inputStream = new FileInputStream(file);
		if(start > 0) inputStream.skip(start);
		while((read = inputStream.read(buffer)) > 0)
			http.getResponseBody().write(buffer, 0, read);
		inputStream.close();
		http.close();
	}
}