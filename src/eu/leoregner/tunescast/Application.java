package eu.leoregner.tunescast;
import eu.leoregner.tunescast.web.WebServer;
import java.awt.event.*;
import java.awt.*;

public class Application implements ActionListener
{
	private final WebServer server;
	private final TrayIcon trayIcon;
	
	public Application() throws Exception
	{
		// start the webserver
		server = new WebServer();
		System.out.println(server.getLocalTcpPort());
		
		// add icon into the system tray
		SystemTray.getSystemTray().add(trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("res/icon.png"), "TunesCast"));
		
		// add menu to the system tray icon
		final PopupMenu trayIconMenu = new PopupMenu();
		trayIconMenu.add(createMenuItem("Open", "chrome"));
		trayIconMenu.addSeparator();
		trayIconMenu.add(createMenuItem("About", "about"));
		trayIconMenu.add(createMenuItem("Quit", "quit"));
		trayIcon.setPopupMenu(trayIconMenu);
	}
	
	/** @return a {@code javax.swing.MenuItem} with the given label and action command */
	private MenuItem createMenuItem(String label, String actionCommand)
	{
		final MenuItem menuItem = new MenuItem(label);
		menuItem.addActionListener(this);
		menuItem.setActionCommand(actionCommand);
		return menuItem;
	}
	
	/** handle menu action */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("chrome"))
			try { Desktop.getDesktop().browse(new java.net.URL("http://localhost:" + server.getLocalTcpPort() + "/").toURI()); }
			catch(Throwable x) { x.printStackTrace(); }
		
		if(e.getActionCommand().equals("about"))
			new AboutDialog().setVisible(true);
		
		if(e.getActionCommand().equals("quit"))
			System.exit(0);
	}
}