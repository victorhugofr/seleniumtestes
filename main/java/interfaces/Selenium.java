package interfaces;

import org.openqa.selenium.WebDriver;

public interface Selenium {
	public WebDriver getDriver();
	public void setDriver(WebDriver driver);
	public WebDriver initializeFirefoxDriver();
	public WebDriver initializeChromeDriver();
	public WebDriver initializePhantomJsDriver();
	public void closeDriver();
}
