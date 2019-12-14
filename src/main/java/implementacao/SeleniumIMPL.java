package implementacao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import interfaces.Selenium;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumIMPL implements Selenium {

	private WebDriver driver;

	@Override
	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		if (this.driver == null) {
			this.driver = initializeChromeDriver();
		}
		return driver;
	}

	@Override
	public void setDriver(WebDriver driver) {
		// TODO Auto-generated method stub
		this.driver = driver;
	}

	@Override
	public WebDriver initializeFirefoxDriver() {

		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();

		return driver;
	}

	@Override
	public WebDriver initializeChromeDriver() {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		return driver;
	}

	@Override
	public WebDriver initializePhantomJsDriver() {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void closeDriver() {
		// TODO Auto-generated method stub
		driver.close();
	}

}
