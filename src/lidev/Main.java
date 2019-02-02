package lidev;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import java.awt.event.ActionEvent;

public class Main extends JFrame {
	private final Action action = new SwingAction();
	public Main() {
		super("Account Creator");
		setResizable(false);
	    getContentPane().setLayout(null);
	    
	    JLabel lblInfoText = new JLabel("<html><center>Please select the server you want to<br>create accounts on below.</center></html>");
	    lblInfoText.setBounds(10, 5, 246, 32);
	    getContentPane().add(lblInfoText);
	    
	    JButton btnAdara = new JButton("Adara2");
	    btnAdara.setBounds(10, 50, 246, 40);
	    getContentPane().add(btnAdara);
	    
	    JButton btnInternational = new JButton("Metin2 International");
	    btnInternational.setBounds(10, 90, 246, 40);
	    getContentPane().add(btnInternational);
	    
	    JButton btnRubinumS1 = new JButton("Rubinum S1 (coming soon)");
	    btnRubinumS1.setBounds(10, 170, 246, 40);
	    getContentPane().add(btnRubinumS1);
	    
	    JButton btnRubinumS2 = new JButton("Rubinum S2 (coming soon");
	    btnRubinumS2.setBounds(10, 210, 246, 40);
	    getContentPane().add(btnRubinumS2);
	    
	    JButton btnAeldra = new JButton("Aeldra (almost done I swear)");
	    btnAeldra.setBounds(10, 130, 246, 40);
	    getContentPane().add(btnAeldra);
	    
	    JLabel lblCredits = new JLabel("<html><small>Programmed & released by CallSaul<br>at elitepvpers.com</html>");
	    lblCredits.setBounds(10, 262, 246, 32);
	    getContentPane().add(lblCredits);

	    this.setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR));
	    this.setSize(new Dimension(275, 325));
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel");
				} catch (Exception e) {
					System.out.println("Substance Graphite failed to initialize");
				}
				Main mainGUI = new Main();
				mainGUI.setVisible(true);
			}
		});
		
		
		/*RubinumAccCreator ac = new RubinumAccCreator(BrowserVersion.FIREFOX_60);
		ac.createAccount();*/
		
		/*InternationalAccCreator ac = new InternationalAccCreator(BrowserVersion.FIREFOX_60);
		ac.createAccount();*/
		
		/*AdaraAccCreator ac = new AdaraAccCreator(BrowserVersion.FIREFOX_60);
		ac.createAccount();
		
		AeldraAccCreator ac = new AeldraAccCreator(BrowserVersion.FIREFOX_60);
		ac.createAccount();*/
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
