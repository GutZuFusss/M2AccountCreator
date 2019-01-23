package lidev;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class AdaraAccCreator extends AccCreator {
	private final int USER_LEN = 8;
	private final int PASS_LEN = 8;
	
	public AdaraAccCreator(BrowserVersion browser) {
		super(browser);
	}

	public boolean createAccount() {
		try {
			HtmlPage page = getClient().getPage("https://adara2.com/register");
			HtmlForm form = page.getForms().get(1);
			HtmlImage image = page.<HtmlImage>getFirstByXPath("//*[@id=\"captcha\"]"); // note: these XPath's were found using firefox -> inspect element -> right click code line -> copy -> XPath
			File imageFile = new File("temp/captcha.jpg");
			image.saveAs(imageFile);
		    final ImageIcon captchaimg = new ImageIcon("temp/captcha.jpg");
			
			// gather information
			String username = generateRandString(false, USER_LEN);
			String delCode = generateRandDelCode(7);
			String email = username + generateEMail(username);
			String password = generateRandString(true, PASS_LEN);
			String captchaAnswer = (String)JOptionPane.showInputDialog(null, "Please solve the captcha", "Captcha", JOptionPane.QUESTION_MESSAGE, captchaimg, null, "");
			
			form.getInputByName("username").setValueAttribute(username);
			form.getInputByName("email").setValueAttribute(email);
			form.getInputByName("pw").setValueAttribute(password);
			form.getInputByName("pw2").setValueAttribute(password);
			form.getInputByName("deleteCode").setValueAttribute(delCode);
			
			form.getInputByName("captcha").setValueAttribute(captchaAnswer);
			
			HtmlButton button = page.getFirstByXPath("/html/body/div[4]/section/div/div/div[2]/div/div/form/button");
			page = (HtmlPage)button.click();
			getClient().waitForBackgroundJavaScript(1500);
			
			if(page.asText().contains("Du hast dich erfolgreich angemeldet.")) {
				System.out.println("U: " + username + "\nP: " + password);
				return true;
			}
			else
				return false;
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
