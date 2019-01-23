package lidev;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class Main {

	public static void main(String[] args) {
		RubinumAccCreator ac = new RubinumAccCreator(BrowserVersion.FIREFOX_60);
		ac.createAccount();
	}
}
