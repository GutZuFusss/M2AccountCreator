package lidev;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class RubinumAccCreator extends AccCreator{
	private final int USER_LEN = 8;
	private final int PASS_LEN = 8;
	
	public RubinumAccCreator(BrowserVersion browser) {
		super(browser);
	}

	public boolean createAccount() {
		try {
			HtmlPage page = getClient().getPage("https://s1.rubinum.biz/register");
			HtmlForm form = page.getForms().get(0); // spaghetti but 1 line, apparently rubinum is not able to name their forms... w/e
			
			// gather information
			String username = generateRandString(false, USER_LEN);
			String delCode = generateRandDelCode(7);
			String email = username + generateEMail(username);
			String password = generateRandString(true, PASS_LEN);
			
			form.getInputByName("login").setValueAttribute(username);
			form.getInputByName("delete_code").setValueAttribute(delCode);
			form.getInputByName("email").setValueAttribute(email);
			form.getInputByName("email_confirmation").setValueAttribute(email);
			form.getInputByName("password").setValueAttribute(password);
			form.getInputByName("password_confirmation").setValueAttribute(password);
			form.getInputByName("security_answer").setValueAttribute(generateRandString(false, 5));
			
			HtmlButton button = page.getFirstByXPath("//button[.=\"\n                            Register now !                        \"]"); // yep, the button is apparently labeled this way
			page = (HtmlPage)button.click();
			getClient().waitForBackgroundJavaScript(1500);
			// debugging stuff...
			System.out.println(page.asText());
			System.out.println("U: " + username + "\nP: " + password);
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
