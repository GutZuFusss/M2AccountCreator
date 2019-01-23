package lidev;

import java.io.File;
import java.io.IOException;

import javax.swing.*;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.*;

public class InternationalAccCreator extends AccCreator {
	private final int USER_LEN = 8;
	private final int PASS_LEN = 8;

	public InternationalAccCreator(BrowserVersion browser) {
		super(browser);
	}
	
	public boolean createAccount() {
		try {
			HtmlPage page = getClient().getPage("https://metin2.international/users/register");
			HtmlForm form = page.getForms().get(0);
			HtmlImage image = page.<HtmlImage>getFirstByXPath("/html/body/div[1]/div[2]/div[2]/div[1]/div/div[3]/div/div/div/form/center/table/tbody/tr[5]/td[1]/img"); // note: these XPath's were found using firefox -> inspect element -> right click code line -> copy -> XPath
			File imageFile = new File("temp/captcha.jpg");
			image.saveAs(imageFile);
		    final ImageIcon captchaimg = new ImageIcon("temp/captcha.jpg");
			
		    // gather information
			String username = generateRandString(false, USER_LEN);
			String email = username + generateEMail(username);
			String password = generateRandString(true, PASS_LEN);
			String captchaAnswer = (String)JOptionPane.showInputDialog(null, "Please solve the captcha", "Captcha", JOptionPane.QUESTION_MESSAGE, captchaimg, null, "");
			
			form.getInputByName("username").setValueAttribute(username);
			form.getInputByName("email").setValueAttribute(email);
			form.getInputByName("password").setValueAttribute(password);
			form.getInputByName("rpassword").setValueAttribute(password);
			((HtmlCheckBoxInput)page.getHtmlElementById("agree")).setChecked(true);
		    form.getInputByName("captcha").setValueAttribute(captchaAnswer);
			
			HtmlSubmitInput button = page.getFirstByXPath("/html/body/div[1]/div[2]/div[2]/div[1]/div/div[3]/div/div/div/form/center/input");
			page = (HtmlPage)button.click();
			getClient().waitForBackgroundJavaScript(1500);

			if(page.asText().contains("The user account has been successfully created.")) {
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
