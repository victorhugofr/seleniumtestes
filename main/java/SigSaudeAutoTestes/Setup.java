package SigSaudeAutoTestes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import enums.Navegadores;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Setup {
	
	public WebDriver setNavegador(Navegadores navegador) {
		WebDriver driver = null;
		if (navegador.getNavegador().equals(Navegadores.FIREFOX.getNavegador())){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (navegador.getNavegador().equals(Navegadores.CHROME.getNavegador())) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if(navegador.getNavegador().equals(Navegadores.IE.getNavegador())) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}else if (navegador.getNavegador().equals(Navegadores.EDGE.getNavegador())) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (navegador.getNavegador().equals(Navegadores.PHANTON_JS.getNavegador())) {
			WebDriverManager.phantomjs().setup();
			// driver = new PhantomJSDriver();
			driver = new PhantomJSDriver();
		} else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		return driver;
	}

}
