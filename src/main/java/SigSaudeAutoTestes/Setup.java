package SigSaudeAutoTestes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import enums.Navegadores;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Setup {
	
	public WebDriver setNavegador(Navegadores navegador) {
		WebDriver driver = null;
		if (navegador.getNavegador().equals(Navegadores.FIREFOX.getNavegador())){
//			 FirefoxBinary firefoxBinary = new FirefoxBinary();
//			    firefoxBinary.addCommandLineOptions("--headless");
			    FirefoxOptions firefoxOptions = new FirefoxOptions();
//			    firefoxOptions.addArguments("--headless");
			    firefoxOptions.addArguments("--window-size=1200x600");
			    firefoxOptions.addArguments("--disable-gpu");
			    firefoxOptions.addArguments("--no-sandbox");
//			    firefoxOptions.setBinary(firefoxBinary);
			    
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(firefoxOptions);
		} else if (navegador.getNavegador().equals(Navegadores.CHROME.getNavegador())) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
//		      options.addArguments("--headless");
		      options.addArguments("--window-size=1200x600");
		      options.addArguments("--disable-gpu");
		      options.addArguments("--no-sandbox");


			driver = new ChromeDriver(options);
		} else if(navegador.getNavegador().equals(Navegadores.IE.getNavegador())) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}else if (navegador.getNavegador().equals(Navegadores.EDGE.getNavegador())) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (navegador.getNavegador().equals(Navegadores.PHANTON_JS.getNavegador())) {
			WebDriverManager.phantomjs().setup();
			 driver = new PhantomJSDriver();
		} else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		return driver;
	}

}
