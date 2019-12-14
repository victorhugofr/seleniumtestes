package formularios;

// import static org.junit.Assert.fail;

// import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import br.ufrn.imd.sigsaude.dominio.ProfissionalSaude;
import dados.gerarNumeroDeDocumentos;
import exceptions.FormularioException;
import utils.AbstractTestSigSaude;

public class FormularioCadastroProfissionalDeSaude extends AbstractTestSigSaude {
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

	public void inserirIdProfissional(String id) {
		WebElement inputIdProfissional = driver.findElement(By.id("inputIdentidadeProfissional"));
		inputIdProfissional.click();
		inputIdProfissional.clear();
		inputIdProfissional.sendKeys(id);
	}

	public void inserirDadosProfissionalDeSaude(ProfissionalSaude profissionalSaude, boolean possuiCpf) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Pessoal")));
		} catch (TimeoutException e) {

			if (driver.findElement(By.linkText("Voltar")).isDisplayed()) {
				driver.findElement(By.linkText("Voltar")).click();
			}
		}
		driver.findElement(By.linkText("Pessoal")).click();
		driver.findElement(By.linkText("Profissional de Saúde")).click();
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Listar'])[3]/following::a[2]")).click();

		inserirNome(profissionalSaude.getColaborador().getNome());

		if (possuiCpf) {
			inserirCpf(gerarNumeroDeDocumentos.geraCPF());
		}

		if (profissionalSaude.getEspecialidade() != null) {
			driver.findElement(By.id("selectEspecialidade")).click();
//			new Select(driver.findElement(By.id("selectEspecialidade")))
//					.selectByValue(profissionalSaude.getEspecialidade().getCodigo());
			new Select(driver.findElement(By.id("selectEspecialidade")))
			.selectByValue("255");
		}
		inserirIdProfissional(profissionalSaude.getIdentidadeProfissional());

		inserirEmail(profissionalSaude.getColaborador().getEmail());

		inserirLogin(profissionalSaude.getColaborador().getLogin());

		driver.findElement(By.id("cadastrarColaborador")).click();
	}

	public void confirmarCadastro() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Cadastrado com sucesso!'])[1]/following::button[1]")));
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Cadastrado com sucesso!'])[1]/following::button[1]"))
				.click();
	}

	public void verificaErro(String erro) throws FormularioException {

		try {
			if (!driver.findElement(By.className("toast-error")).isDisplayed()) {
				throw new NoSuchElementException(erro);
			} else {
				System.out.println("Toast sendo exibido");
				System.out.println(driver.findElement(By.className("toast-error")).getText());
			}
		} catch (NoSuchElementException e) {
			wait.until(ExpectedConditions.presenceOfElementLocated((By.className("invalid-feedback"))));
			if (!driver.findElement(By.className("invalid-feedback")).isEnabled()) {
				// Reporter.log((driver.findElement(By.className("invalid-feedback")).getText()));
				throw new FormularioException(erro);
			} else {
				System.out.println("Toast sendo exibido");
				System.out.println(driver.findElement(By.className("invalid-feedback")).getText());
			}
		}
	}

	public void verificaErroObrigatorio(String erro) throws FormularioException {

		if (!driver.getPageSource().contains("Campo obrigatório.")) {
			throw new FormularioException(erro);
		} else {
			System.out.println("Toast sendo exibido");
		}
	}

	public void inativarProfissionalSaude(ProfissionalSaude profissionalSaude, String justificativa) {
		driver.findElement(By.linkText("Pessoal")).click();
		driver.findElement(By.linkText("Profissional de Saúde")).click();
			driver.findElement(
					By.linkText("Listar")).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']")));
			WebElement searchNome = driver.findElement(By.xpath("//input[@type='search']"));
			searchNome.click();
			searchNome.clear();
			searchNome.sendKeys(profissionalSaude.getColaborador().getNome());

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
	
	public void verificaProfissionalCadastrado(ProfissionalSaude profissionalSaude) throws FormularioException {
		driver.findElement(By.linkText("Pessoal")).click();
		driver.findElement(By.linkText("Profissional de Saúde")).click();
			driver.findElement(
					By.linkText("Listar")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']")));
		WebElement searchNome = driver.findElement(By.xpath("//input[@type='search']"));
		searchNome.click();
		searchNome.clear();
		searchNome.sendKeys(profissionalSaude.getColaborador().getNome());	
		
		if(driver.getPageSource().contains(profissionalSaude.getColaborador().getNome().toUpperCase())) {
			System.out.println("Profissional presente");
		}else {
			throw new FormularioException("Profissional não esta presente");
		}
	}
}
