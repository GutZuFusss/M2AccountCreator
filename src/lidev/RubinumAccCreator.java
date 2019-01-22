package lidev;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

public class RubinumAccCreator extends AccCreator{
	private final int USER_LEN = 7;
	private final int PASS_LEN = 6;
	
	public RubinumAccCreator(BrowserVersion browser) {
		super(browser);
	}

	public boolean createAccount(String user, String pw) {
		try {
			HtmlPage page = getClient().getPage("https://s1.rubinum.biz/register");
			HtmlForm form = page.getForms().get(0); // spaghetti but 1 line, apparently rubinum is not able to name their forms... w/e
			
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
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
