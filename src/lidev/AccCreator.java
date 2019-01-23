package lidev;

import java.util.Random;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

public abstract class AccCreator {
	private WebClient client;

	public AccCreator(BrowserVersion browser) {
		setClient(new WebClient(browser));
	}

	public abstract boolean createAccount(); // must be implemented by subclasses, should contain the main account creation progress
	
	// used to generate random usernames & passwords
	protected String generateRandString(boolean numbers, int minLength) {
		String consonants = "bcdfghjklmnpqrstvwxyz";
		String vowels = "aeiou";
		
		Random rand = new Random();
		int len = minLength + rand.nextInt(4);
		StringBuilder sb = new StringBuilder(len);
		for(int i = 0; i < len; i++) {
			if(numbers && len - i <= 1 + (len % 3)) { // if wanted, the last chars will be numbers
				sb.append(rand.nextInt(10));
				continue;
			}
				
			if(i % 2 == 0)
				sb.append(consonants.charAt(rand.nextInt(consonants.length())));
			else
				sb.append(vowels.charAt(rand.nextInt(vowels.length())));
		}
		
		return sb.toString();
	}
	
	protected String generateEMail(String username) {
		Random rand = new Random();
		String[] domains = {	"@gmx.de",
								"@yahoo.com",
								"@gmail.com",
								"@googlemail.com",
								"@web.de",
								"@aol.com",
								"@one.com",
								"@t-online.de" };
		
		return username + domains[rand.nextInt(domains.length)];
	}
	
	protected String generateRandDelCode(int len) {
		Random rand = new Random();
		StringBuilder sb = new StringBuilder(len);
		for(int i = 0; i < len; i++)
			sb.append(rand.nextInt(10));

		return sb.toString();
	}
	
	// getter / setter
	public WebClient getClient() { return client; }
	public void setClient(WebClient client) { this.client = client; }
}
