package lidev;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHtml;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

public class AeldraAccCreator extends AccCreator {
	private final int USER_LEN = 8;
	private final int PASS_LEN = 8;

	public AeldraAccCreator(BrowserVersion browser) {
		super(browser);
	}
	
	public boolean createAccount() {
		try {
			HtmlPage page = getClient().getPage("https://aeldra.net/register");
			HtmlForm form = page.getForms().get(1);
			String captchaJS = page.<HtmlHtml>getFirstByXPath("/html").getChildElements().toString(); // note: these XPath's were found using firefox -> inspect element -> right click code line -> copy -> XPath
			
			System.out.println(form.toString());
			System.out.println(captchaJS);
		    // gather information
			/*String username = generateRandString(false, USER_LEN);
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
				return false;*/
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}

		return false;
	}
}
