package eu.leoregner.tunescast;
import javax.swing.*;

public class StartUp
{
	public static void main(String[] args)
	{
		try
		{
			// set system look and feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			// start up the user interface
			new Application();
		}
		catch(Exception x)
		{
			final String msg = "An unexpected error occured.";
			JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}