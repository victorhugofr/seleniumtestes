package formularios;

//import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
// import org.testng.Reporter;

import br.ufrn.imd.sigsaude.dominio.Colaborador;
import exceptions.FormularioException;
import utils.AbstractTestSigSaude;

public class FormularioCadastroColaborador extends AbstractTestSigSaude {

	public void inserirNome(String nome) {
		WebElement inputNome = driver.findElement(By.id("inputNome"));
		inputNome.click();
		inputNome.clear();
		inputNome.sendKeys(nome);
	}

	public void inserirCpf(String cpf) {
		WebElement inputCpf = driver.findElement(By.id("inputCPF"));
		inputCpf.click();
		inputCpf.clear();
		inputCpf.sendKeys(cpf);
	}

	public void inserirEmail(String email) {
		WebElement inputEmail = driver.findElement(By.id("inputEmail"));
		inputEmail.click();
		inputEmail.clear();
		inputEmail.sendKeys(email);
	}

	public void inserirTelefone(String telefone) {
		WebElement inputTelefone = driver.findElement(By.id("inputTelefone"));
		inputTelefone.click();
		inputTelefone.clear();
		inputTelefone.sendKeys(telefone);
	}

	public void inserirLogin(String login) {
		WebElement inputLogin = driver.findElement(By.id("inputLogin"));
		inputLogin.click();
		inputLogin.clear();
		inputLogin.sendKeys(login);
	}

	public void clicarColaborador() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("v-overlay v-overlay--active")));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Pessoal")));
		} catch (TimeoutException e) {

			if (driver.findElement(By.linkText("Voltar")).isDisplayed()) {
				driver.findElement(By.linkText("Voltar")).click();
			}
		}
		actions.click(driver.findElement(By.linkText("Pessoal"))).perform();
		actions.click(driver.findElement(By.linkText("Pessoal"))).perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Colaborador")));
		driver.findElement(By.linkText("Colaborador")).click();
	}

	public void inserirDadosColaborador(Colaborador colaborador, String cpf) {
		clicarColaborador();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Listar'])[2]/following::a[3]")).click();

		inserirNome(colaborador.getNome());
		if (cpf != null) {
			inserirCpf(cpf);
		}

		if (colaborador.getTipoColaborador() != null) {
			driver.findElement(By.id("selectTipo")).click();
			new Select(driver.findElement(By.id("selectTipo")))
					.selectByValue(colaborador.getTipoColaborador().getId().toString());
		}

		inserirEmail(colaborador.getEmail());

		if (colaborador.getPessoa().getTelefone() != null) {
			inserirTelefone(colaborador.getPessoa().getTelefone());
		}

		inserirLogin(colaborador.getLogin());

		driver.findElement(By.id("cadastrarColaborador")).click();
	}

	public void inserirDadosColaboradorCPFExistente(Colaborador colaborador, String cpf) {
		clicarColaborador();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Listar'])[2]/following::a[3]")).click();

		inserirCpf(cpf);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'OK')]")));
		driver.findElement(By.xpath("//button[contains(text(), 'OK')]")).click();

		inserirNome(colaborador.getNome());
		driver.findElement(By.id("cadastrarColaborador")).click();

	}

	public void inserirDadosColaboradorCPFInvalido(Colaborador colaborador) {

		clicarColaborador();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Listar'])[2]/following::a[3]")).click();

		inserirNome(colaborador.getNome());

		inserirCpf("12345678910");

		if (colaborador.getTipoColaborador() != null) {
			driver.findElement(By.id("selectTipo")).click();

			new Select(driver.findElement(By.id("selectTipo")))
					.selectByValue(colaborador.getTipoColaborador().getId().toString());
		}

		inserirEmail(colaborador.getEmail());

		if (colaborador.getPessoa().getTelefone() != null) {
			inserirTelefone(colaborador.getPessoa().getTelefone());
		}

		inserirLogin(colaborador.getLogin());

		driver.findElement(By.id("cadastrarColaborador")).click();
	}

	public void verificaErro(String erro) throws FormularioException {

		try {
			if (!driver.findElement(By.className("toast-error")).isDisplayed()) {
				throw new FormularioException(erro);

			} else {
				System.out.println("Toast sendo exibido");
				System.out.println(driver.findElement(By.className("toast-error")).getText());
			}
		} catch (NoSuchElementException e) {

			wait.until(ExpectedConditions.presenceOfElementLocated((By.className("invalid-feedback"))));

			if (!driver.findElement(By.className("invalid-feedback")).isDisplayed()) {
				// Reporter.log((driver.findElement(By.className("invalid-feedback")).getText()));
				// fail(erro);
				throw new FormularioException(erro);
			} else {
				System.out.println("Toast sendo exibido");
				System.out.println(driver.findElement(By.className("invalid-feedback")).getText());
			}
		}
	}

	public void verificaErroEmailObrigatorio(String erro) throws Exception {

		if (!driver.getPageSource().contains("Por favor digite um endereço de email válido.")) {
			// fail(erro);
			throw new FormularioException(erro);
		} else {
			System.out.println("Toast sendo exibido");
		}
	}

	public void inativarColaborador(Colaborador colaborador,String justificativa) {
		clicarColaborador();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Listar'])[2]/following::a[4]")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']")));
		WebElement searchNome = driver.findElement(By.xpath("//input[@type='search']"));
		searchNome.click();
		searchNome.clear();
		searchNome.sendKeys(colaborador.getNome());

		driver.findElement(By.cssSelector("tr.odd:nth-child(1) > td:nth-child(4) > a:nth-child(2)")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='inputJustificativa']")));
		driver.findElement(By.xpath("//textarea[@id='inputJustificativa']")).click();
		driver.findElement(By.xpath("//textarea[@id='inputJustificativa']")).clear();
		driver.findElement(By.xpath("//textarea[@id='inputJustificativa']")).sendKeys(justificativa);
		
		driver.findElement(By.xpath("//button[@class='btn btn-success pull-right']")).click();
		
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[@class='swal-button swal-button--confirm']")));
		driver.findElement(By.xpath("//button[@class='swal-button swal-button--confirm']")).click();

	}

	public void mudarNomeColaborador(Colaborador colaborador, Colaborador colaborador2) {
		listarColaboradores();
		pesquisarColaborador(colaborador);
		clicarAlterarColaborador();
		inserirNome(colaborador2.getNome());
		driver.findElement(By.id("cadastrarColaborador")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'OK')]")));
		driver.findElement(By.xpath("//button[contains(text(), 'OK')]")).click();
	}

	public void listarColaboradores() {
		clicarColaborador();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Listar'])[2]/following::a[4]")).click();
	}

	public void pesquisarColaborador(Colaborador colaborador) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']")));
		driver.findElement(By.xpath("//input[@type='search']")).click();
		driver.findElement(By.xpath("//input[@type='search']")).clear();
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(colaborador.getNome());
	}

	public void confirmarCadastro() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'OK')]")));
		driver.findElement(By.xpath("//button[contains(text(), 'OK')]")).click();
	}

	public void verificaNomeLogado(Colaborador colaborador) throws FormularioException {
		System.out.println(driver.findElement(By.cssSelector("#dropdownMenuLink > div:nth-child(2)")).getText());
		if (driver.findElement(By.cssSelector("#dropdownMenuLink > div:nth-child(2)")).getText()
				.equals(colaborador.getNome())) {
			System.out.println("alterou nome em cima");
		} else {
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#dropdownMenuLink")));
			driver.findElement(By.cssSelector("#dropdownMenuLink")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Altere seu perfil']")));
			driver.findElement(By.xpath("//a[@title='Altere seu perfil']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#inputNome")));
			if (driver.findElement(By.cssSelector("#inputNome")).getAttribute("value").equals(colaborador.getNome().toUpperCase())) {
				System.out.println("alterou no campo");
			} else {
				throw new FormularioException("não está na verificação");
			}
		}
	}

	public void clicarAlterarPerfilColaborador() {
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#dropdownMenuLink")));
		driver.findElement(By.cssSelector("#dropdownMenuLink")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Altere seu perfil']")));
		driver.findElement(By.xpath("//a[@title='Altere seu perfil']")).click();
	}
	
	public void alterarPerfilColaborador(Colaborador colaborador) {
		clicarAlterarPerfilColaborador();
		if(colaborador.getEmail()!=null) {
			inserirEmail(colaborador.getEmail());
		}
		if(colaborador.getNome()!=null) {
			inserirNome(colaborador.getNome());
		}
		driver.findElement(By.xpath("//button[contains(text(), 'Alterar')]")).click();
	}
	public void deslogar() {
		driver.findElement(By.cssSelector("#dropdownMenuLink")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#logout")));
		driver.findElement(By.cssSelector("#logout")).click();
	}

	public void clicarAlterarColaborador() {
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.btn-primary:nth-child(1)")));
		driver.findElement(By.cssSelector("a.btn-primary:nth-child(1)")).click();
	}

	public void verificaColaboradorCadastrado(Colaborador colaborador) throws FormularioException {
		clicarColaborador();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Listar'])[2]/following::a[4]")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']")));
		WebElement searchNome = driver.findElement(By.xpath("//input[@type='search']"));
		searchNome.click();
		searchNome.clear();
		searchNome.sendKeys(colaborador.getNome());	
		
		if(driver.getPageSource().contains(colaborador.getNome().toUpperCase())) {
			System.out.println("Colaborador presente");
		}else {
			throw new FormularioException("colaborador não esta presente");
		}
	}
}
