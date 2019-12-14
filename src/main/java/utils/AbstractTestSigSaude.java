package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
// import org.testng.Reporter;

import SigSaudeAutoTestes.Setup;
import enums.IDsLogin;
import enums.Navegadores;
import model.UsuarioAdmin;

public class AbstractTestSigSaude {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Actions actions;
	
	public void loginAmbienteTeste(UsuarioAdmin usuario,Navegadores navegador) {
		Setup login = new Setup();
		driver = login.setNavegador(navegador);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 5);
		driver.get("https://testessigsaude.imd.ufrn.br/sigsaude/");
		// driver.findElement(By.linkText("Entrar")).click();
		// BOTAO DE ENTRAR
//		driver.findElement(By.xpath(IDsLogin.ENTRAR.getIdOrXpath())).click();
		if(usuario!=null) {
			driver.findElement(By.id(IDsLogin.LOGIN.getIdOrXpath())).click();
			System.out.println(usuario.getLogin());
			driver.findElement(By.id(IDsLogin.LOGIN.getIdOrXpath())).sendKeys(usuario.getLogin());
			System.out.println(usuario.getCPF());
			driver.findElement(By.id(IDsLogin.PASSWORD.getIdOrXpath())).sendKeys(usuario.getCPF());
		}else {
			driver.findElement(By.id(IDsLogin.LOGIN.getIdOrXpath())).click();
			driver.findElement(By.id(IDsLogin.LOGIN.getIdOrXpath())).sendKeys("itamir.filho");
			driver.findElement(By.id(IDsLogin.PASSWORD.getIdOrXpath())).sendKeys("admin12345678");
		}
		// BOTAO LOGAR
		driver.findElement(By.xpath(IDsLogin.LOGAR.getIdOrXpath())).click();
		actions = new Actions(driver);
		
	}
	
	public void loginAmbienteHomologacao(UsuarioAdmin usuario,Navegadores navegador) {
		Setup login = new Setup();
		driver = login.setNavegador(navegador);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 5);
		driver.get("https://homologacaosigsaude.imd.ufrn.br/sigsaude/");
		// driver.findElement(By.linkText("Entrar")).click();
		// BOTAO DE ENTRAR
//		driver.findElement(By.xpath(IDsLogin.ENTRAR.getIdOrXpath())).click();
		if(usuario!=null) {
			driver.findElement(By.id(IDsLogin.LOGIN.getIdOrXpath())).click();
			System.out.println(usuario.getLogin());
			driver.findElement(By.id(IDsLogin.LOGIN.getIdOrXpath())).sendKeys(usuario.getLogin());
			System.out.println(usuario.getCPF());
			driver.findElement(By.id(IDsLogin.PASSWORD.getIdOrXpath())).sendKeys(usuario.getCPF());
		}else {
			driver.findElement(By.id(IDsLogin.LOGIN.getIdOrXpath())).click();
			driver.findElement(By.id(IDsLogin.LOGIN.getIdOrXpath())).sendKeys("itamir.filho");
			driver.findElement(By.id(IDsLogin.PASSWORD.getIdOrXpath())).sendKeys("htestes123");
		}
		// BOTAO LOGAR
		driver.findElement(By.xpath(IDsLogin.LOGAR.getIdOrXpath())).click();
		actions = new Actions(driver);
		
	}
	
	public void logout() {
		driver.quit();
	}
	
}
