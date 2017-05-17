package eu.leoregner.tunescast;
import javax.swing.*;

public class AboutDialog extends JDialog
{
	private static final long serialVersionUID = -5001138564668458023L;
	
	public AboutDialog()
	{
		super((JFrame) null, "About TunesCast", true);
		setAlwaysOnTop(true);
		
		final StringBuffer aboutText = new StringBuffer();
		aboutText.append("<h1>TunesCast</h1>");
		aboutText.append("<p>&copy; Leopold Mathias Regner<br/>published under GNU General Public License</p>");
		aboutText.append("<p>More information can be found on the website and the project's GitHub repository:");
		aboutText.append("<ul><li>https://leoregner.eu/product/2</li>");
		aboutText.append("<li>https://github.com/leoregner/tunescast</li></ul>");
		aboutText.append("<p>Icons provided by EmojiOne.com and icons8.com</p>");
		
		final JEditorPane textPane = new JEditorPane();
		textPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		textPane.setContentType("text/html");
		textPane.setEditable(false);
		textPane.setText("<html>" + aboutText.toString() + "</html>");
		textPane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
		textPane.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
		setContentPane(textPane);
		
		final int width = 550, height = 250;
		setSize(width, height);
		setLocation((getToolkit().getScreenSize().width - width) / 2, (getToolkit().getScreenSize().height - height) / 2);
	}
}