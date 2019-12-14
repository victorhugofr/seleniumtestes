package formularios;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import br.ufrn.imd.sigsaude.dominio.Convenio;
import dados.gerarNumeroDeDocumentos;
import exceptions.FormularioException;
import utils.AbstractTestSigSaude;
import utils.ConverterData;

public class FormularioCadastroConvenio extends AbstractTestSigSaude {
	public void inserirDadosConvenio(Convenio convenio, Boolean termocompromisso) {
		try {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Convênio")));
		}catch(TimeoutException e) {
			if (driver.findElement(By.linkText("Voltar")).isDisplayed()) {
				driver.findElement(By.linkText("Voltar")).click();
			}
		}
		driver.findElement(By.linkText("Convênio")).click();
		// NOVO CONVENIO
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Novo")));
		driver.findElement(By.linkText("Novo")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("inputNome")));
		WebElement inputNome = driver.findElement(By.id("inputNome"));
		inputNome.click();
		inputNome.clear();
		inputNome.sendKeys(convenio.getNome());

		WebElement inputEmail = driver.findElement(By.id("inputEmail"));
		inputEmail.click();
		inputEmail.clear();
		inputEmail.sendKeys(convenio.getEmail());

		WebElement inputInicioVigencia = driver.findElement(By.id("inputInicioVigencia"));
		inputInicioVigencia.click();
		inputInicioVigencia.sendKeys("06");
		inputInicioVigencia.sendKeys("10");
		inputInicioVigencia.sendKeys("2019");

		WebElement inputFinalVigencia = driver.findElement(By.id("inputFinalVigencia"));
		inputFinalVigencia.click();
		inputFinalVigencia.sendKeys(ConverterData.DataFimVigencia(convenio));

		if (termocompromisso) {
			driver.findElement(By.id("inputTermoCompromisso")).sendKeys(gerarNumeroDeDocumentos.gerarPDF());
		}
	}

	public void inserirEnderecoConvenio(Convenio convenio) {
		WebElement inputCep = driver.findElement(By.id("endereco.cep"));
		inputCep.click();
		inputCep.clear();
		inputCep.sendKeys(convenio.getEndereco().getCep());

		WebElement inputLogradouro = driver.findElement(By.id("endereco.logradouro"));
		inputLogradouro.click();
		inputLogradouro.clear();
		inputLogradouro.sendKeys(convenio.getEndereco().getLogradouro());

		WebElement inputNumero = driver.findElement(By.id("endereco.numero"));
		inputNumero.click();
		inputNumero.clear();
		inputNumero.sendKeys(convenio.getEndereco().getNumero());

		driver.findElement(By.id("endereco.uf.id")).click();
		new Select(driver.findElement(By.id("endereco.uf.id"))).selectByVisibleText("RN");

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading-background")));
		try {
			driver.findElement(By.id("endereco.municipio.id")).click();
		}catch(ElementClickInterceptedException e) {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading-background")));
			driver.findElement(By.id("endereco.municipio.id")).click();
			e.printStackTrace();
		}
		new Select(driver.findElement(By.id("endereco.municipio.id"))).selectByVisibleText("Natal");

		WebElement inputBairro = driver.findElement(By.id("endereco.bairro"));
		inputBairro.click();
		inputBairro.clear();
		inputBairro.sendKeys(convenio.getEndereco().getBairro());
	}

	public void salvarConvenio() {
		driver.findElement(By.id("salvarConvenio")).click();
	}

	public void confirmarCadastro() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Cadastrado com sucesso!'])[1]/following::button[1]")));
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Cadastrado com sucesso!'])[1]/following::button[1]"))
				.click();
	}

	public void inserirEnderecoConvenioSemUF(Convenio convenio) {
		WebElement inputCep = driver.findElement(By.id("endereco.cep"));
		inputCep.click();
		inputCep.clear();
		inputCep.sendKeys(convenio.getEndereco().getCep());

		WebElement inputLogradouro = driver.findElement(By.id("endereco.logradouro"));
		inputLogradouro.click();
		inputLogradouro.clear();
		inputLogradouro.sendKeys(convenio.getEndereco().getLogradouro());

		WebElement inputNumero = driver.findElement(By.id("endereco.numero"));
		inputNumero.click();
		inputNumero.clear();
		inputNumero.sendKeys(convenio.getEndereco().getNumero());

		WebElement inputBairro = driver.findElement(By.id("endereco.bairro"));
		inputBairro.click();
		inputBairro.clear();
		inputBairro.sendKeys(convenio.getEndereco().getBairro());

	}

	public void inserirEnderecoConvenioSemMunicipio(Convenio convenio) {
		WebElement inputCep = driver.findElement(By.id("endereco.cep"));
		inputCep.click();
		inputCep.clear();
		inputCep.sendKeys(convenio.getEndereco().getCep());

		WebElement inputLogradouro = driver.findElement(By.id("endereco.logradouro"));
		inputLogradouro.click();
		inputLogradouro.clear();
		inputLogradouro.sendKeys(convenio.getEndereco().getLogradouro());

		WebElement inputNumero = driver.findElement(By.id("endereco.numero"));
		inputNumero.click();
		inputNumero.clear();
		inputNumero.sendKeys(convenio.getEndereco().getNumero());

		driver.findElement(By.id("endereco.uf.id")).click();
		new Select(driver.findElement(By.id("endereco.uf.id"))).selectByVisibleText("RN");

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loading-background']")));
		WebElement inputBairro = driver.findElement(By.id("endereco.bairro"));
		inputBairro.click();
		inputBairro.clear();
		inputBairro.sendKeys(convenio.getEndereco().getBairro());

	}

	public void inserirContatoConvenio(Convenio convenio) {
		WebElement inputTelefone = driver.findElement(By.name("contato.telefone"));
		inputTelefone.click();
		inputTelefone.clear();
		inputTelefone.sendKeys(convenio.getContato().getTelefone());

		WebElement inputTipoTelefone = driver.findElement(By.name("contato.tipoTelefone"));
		inputTipoTelefone.click();

		new Select(inputTipoTelefone).selectByValue(convenio.getContato().getTipoTelefone().toString());

		WebElement inputObservacao = driver.findElement(By.name("contato.observacao"));
		inputObservacao.click();
		inputObservacao.sendKeys(convenio.getContato().getObservacao());
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
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='invalid-feedback']")));

//				if (!driver.findElement(By.xpath("//div[@class='invalid-feedback']")).isDisplayed()) {
//					throw new FormularioException(erro);
//				} else {
//					System.out.println("Toast sendo exibido");
//					System.out.println(driver.findElement(By.className("invalid-feedback")).getText());
//				}
			} catch (RuntimeException e1) {
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("toast-message"))));

				if (!driver.findElement(By.className("toast-message")).isDisplayed()) {
					throw new FormularioException(erro);
				} else {
					System.out.println("Toast sendo exibido");
					System.out.println(driver.findElement(By.className("toast-message")).getText());
				}
			}
		}
	}

	public void verificaErroConvenio(String erro) throws FormularioException {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By
					.cssSelector("div.col-12:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(3)")));
			if (driver
					.findElement(By.cssSelector(
							"div.col-12:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(3)"))
					.getText().equals("Campo Obrigatório"))
				System.out.println("mensagem sendo mostrada");
			else
				throw new FormularioException(erro);

		} catch (TimeoutException e) {
			if (!driver.getPageSource().contains("Campo obrigatório")) {
				throw new FormularioException(erro);
			}
		}
	}

	public void alterarDadosConvenio(Convenio convenio, Boolean termocompromisso) {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Convênio")));
		driver.findElement(By.linkText("Convênio")).click();
		// NOVO CONVENIO
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Novo")));
		driver.findElement(By.linkText("Listar")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']")));
		driver.findElement(By.xpath("//input[@type='search']")).click();
		driver.findElement(By.xpath("//input[@type='search']")).clear();
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(convenio.getNome());
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-original-title='Alterar']")));
		driver.findElement(By.xpath("//a[@data-original-title='Alterar']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("inputNome")));
		WebElement inputNome = driver.findElement(By.id("inputNome"));
		inputNome.click();
		inputNome.clear();
		inputNome.sendKeys(convenio.getNome());

		WebElement inputEmail = driver.findElement(By.id("inputEmail"));
		inputEmail.click();
		inputEmail.clear();
		inputEmail.sendKeys(convenio.getEmail());

		WebElement inputInicioVigencia = driver.findElement(By.id("inputInicioVigencia"));
		inputInicioVigencia.click();
		inputInicioVigencia.sendKeys("06");
		inputInicioVigencia.sendKeys("10");
		inputInicioVigencia.sendKeys("2019");

		WebElement inputFinalVigencia = driver.findElement(By.id("inputFinalVigencia"));
		inputFinalVigencia.click();
		inputFinalVigencia.sendKeys(ConverterData.DataFimVigencia(convenio));

		if (termocompromisso) {
			driver.findElement(By.id("inputTermoCompromisso")).sendKeys(gerarNumeroDeDocumentos.gerarPDF());
		}
		
	}

	public void inativarConvenio(Convenio convenio, String justificativa) {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Convênio")));
		driver.findElement(By.linkText("Convênio")).click();
		// NOVO CONVENIO
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Novo")));
		driver.findElement(By.linkText("Listar")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']")));
		driver.findElement(By.xpath("//input[@type='search']")).click();
		driver.findElement(By.xpath("//input[@type='search']")).clear();
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(convenio.getNome());
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-original-title='Inativar']")));
		driver.findElement(By.xpath("//a[@data-original-title='Inativar']")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='inputJustificativa']")));
		driver.findElement(By.xpath("//textarea[@id='inputJustificativa']")).click();
		driver.findElement(By.xpath("//textarea[@id='inputJustificativa']")).clear();
		driver.findElement(By.xpath("//textarea[@id='inputJustificativa']")).sendKeys(justificativa);
		
		driver.findElement(By.xpath("//button[@class='btn btn-success pull-right']")).click();
		
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[@class='swal-button swal-button--confirm']")));
		driver.findElement(By.xpath("//button[@class='swal-button swal-button--confirm']")).click();
	}
}
