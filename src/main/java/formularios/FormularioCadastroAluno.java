package formularios;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.ufrn.imd.sigsaude.dominio.Aluno;
import br.ufrn.imd.sigsaude.dominio.Vinculo;
import exceptions.FormularioException;
import utils.AbstractTestSigSaude;

public class FormularioCadastroAluno extends AbstractTestSigSaude {
	public void inserirCpf(String cpf) {
		WebElement campoCpf = driver.findElement(By.xpath("//input[@aria-label='CPF']"));
		wait.until(ExpectedConditions.elementToBeClickable(campoCpf));
		campoCpf.click();
		campoCpf.clear();
		campoCpf.sendKeys(cpf);
	}
	
	public void inserirNome(String nome) {
		WebElement campoNome = driver.findElement(By.xpath("//input[@aria-label='Nome*']"));
		campoNome.click();
		campoNome.clear();
		campoNome.sendKeys(nome);
	}
	
	public void inserirEmail(String email) {
		WebElement campoEmail = driver.findElement(By.xpath("//input[@aria-label='Email*']"));
		campoEmail.click();
		campoEmail.clear();
		campoEmail.sendKeys(email);
	}
	public void clicarAluno() {
		driver.findElement(By.linkText("Acadêmico")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Aluno")));
		driver.findElement(By.linkText("Aluno")).click();
	}
	
	public void inserirLogin(String login) {
		WebElement campoLogin = driver.findElement(By.xpath("//input[@aria-label='Login*']"));
		campoLogin.click();
		campoLogin.clear();
		campoLogin.sendKeys(login);
	}
	
	public void inserirDadosAluno(Aluno aluno, String cpf) {
		clicarAluno();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Novo")));
		driver.findElement(By.linkText("Novo")).click();
		inserirCpf(cpf);
		inserirNome(aluno.getUsuario().getNome());
		inserirDataNascimento();
		inserirSexo(aluno.getUsuario().getPessoa().getSexo());
		inserirEmail(aluno.getUsuario().getPessoa().getEmail());
		inserirLogin(aluno.getUsuario().getLogin());
	}
	
	public void continuarCadastro() {
		driver.findElement(By.xpath("//button[@class='v-btn theme--light primary']")).click();
	}
	
	public void inserirDataNascimento() {
		driver.findElement(By.xpath("//input[@aria-label='Data de Nascimento']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[3]/following::div[" + 1 + "]")));
		driver.findElement(By
				.xpath("(.//*[normalize-space(text()) and normalize-space(.)='S'])[3]/following::div[" + 1 + "]"))
				.click();
	}
	
	public void inserirDadosAlunoSemDataNascimento(Aluno aluno, String cpf) {
		clicarAluno();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Novo")));
		driver.findElement(By.linkText("Novo")).click();
		
		inserirCpf(cpf);
		
		inserirNome(aluno.getUsuario().getNome());

		inserirSexo("Masculino");
		
		inserirEnderecoNomadeAluno("referencia");
		inserirTelefoneAluno(aluno.getUsuario().getPessoa().getCelular());
		inserirEmail(aluno.getUsuario().getPessoa().getEmail());
		inserirLogin(aluno.getUsuario().getLogin());
		
		driver.findElement(By.xpath("//button[@class='v-btn theme--light primary']")).click();
	}
	
	public void inserirSexo(String tipo) {
		if(tipo.equals("M")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-label='Sexo']")));
			actions.click(driver.findElement(By.xpath("//input[@aria-label='Sexo']"))).build().perform();;
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Masculino")));
			driver.findElement(By.linkText("Masculino")).click();
		}else if(tipo.equals("F")){
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-label='Sexo']")));
			actions.click(driver.findElement(By.xpath("//input[@aria-label='Sexo']"))).build().perform();;
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Feminino")));
			driver.findElement(By.linkText("Feminino")).click();
		}
	}
	
	public void inserirEnderecoAluno(String cep,String logradouro,String numero,String bairro,String pais,String uf, String municipio) {
		WebElement campoCEP = driver.findElement(By.xpath("//input[@aria-label='CEP']"));
		campoCEP.click();
		campoCEP.clear();
		campoCEP.sendKeys(cep);
		
		WebElement campoLogradouro = driver.findElement(By.xpath("//input[@aria-label='Logradouro*']"));
		campoLogradouro.click();
		campoLogradouro.clear();
		campoLogradouro.sendKeys(logradouro);
		
		WebElement campoNumero = driver.findElement(By.xpath("//input[@aria-label='Número']"));
		campoNumero.click();
		campoNumero.clear();
		campoNumero.sendKeys(numero);
		
		WebElement campoBairro = driver.findElement(By.xpath("//input[@aria-label='Bairro']"));
		campoBairro.click();
		campoBairro.clear();
		campoBairro.sendKeys(bairro);
		
		WebElement campoPais = driver.findElement(By.xpath("//input[@aria-label='País']"));
		campoPais.click();
		campoPais.clear();
		campoPais.sendKeys(pais);
		if(uf!=null) {
		actions.click(driver.findElement(By.xpath("//input[@aria-label='UF']"))).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(uf)));
		driver.findElement(By.linkText(uf)).click();
			if(municipio!=null) {
				actions.click(driver.findElement(By.xpath("//input[@aria-label='Município']"))).build().perform();
				wait.until(ExpectedConditions.elementToBeClickable(By.linkText(municipio)));
				driver.findElement(By.linkText(municipio)).click();
			}
		}
	}
	
	public void inserirEnderecoNomadeAluno(String referencia) {
		actions.click(driver.findElement(By.xpath("//input[@aria-label='Sim']"))).build().perform();
		
		WebElement campoReferencia = driver.findElement(By.xpath("//input[@aria-label='Ponto de referência']"));
		wait.until(ExpectedConditions.elementToBeClickable(campoReferencia));
		campoReferencia.click();
		campoReferencia.clear();
		campoReferencia.sendKeys(referencia);
		
		WebElement campoPais = driver.findElement(By.xpath("//input[@aria-label='País']"));
		campoPais.click();
		campoPais.clear();
		campoPais.sendKeys("Brasil");
		
		actions.click(driver.findElement(By.xpath("//input[@aria-label='UF']"))).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("AC")));
		driver.findElement(By.linkText("AC")).click();
		
		actions.click(driver.findElement(By.xpath("//input[@aria-label='Município']"))).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Acrelândia")));
		driver.findElement(By.linkText("Acrelândia")).click();
	}
	
	public void inserirTelefoneAluno(String telefone) {
		WebElement campoTelefone = driver.findElement(By.xpath("//input[@aria-label='Telefone*']"));
		campoTelefone.click();
		campoTelefone.clear();
		campoTelefone.sendKeys(telefone);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-label='Tipo']")));
		actions.click(driver.findElement(By.xpath("//input[@aria-label='Tipo']"))).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("MÓVEL")));
		driver.findElement(By.linkText("MÓVEL")).click();
	}
	
	public void inserirTelefoneAlunoSemTipo(String telefone) {
		WebElement campoTelefone = driver.findElement(By.xpath("//input[@aria-label='Telefone*']"));
		campoTelefone.click();
		campoTelefone.clear();
		campoTelefone.sendKeys(telefone);
	}
	
	public void inserirVinculo(Vinculo vinculo, String nivel, String curso, String profissional) {
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Informações de Vínculo'])[2]/following::div[3]")));
		driver.findElement(By
				.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Informações de Vínculo'])[2]/following::div[3]")).click();
	
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-label='Profissional Responsável']")));
		driver.findElement(By.xpath("//input[@aria-label='Profissional Responsável']")).click();
		driver.findElement(By.xpath("//input[@aria-label='Profissional Responsável']")).clear();
		driver.findElement(By.xpath("//input[@aria-label='Profissional Responsável']")).sendKeys(profissional);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(profissional)));
		driver.findElement(By.linkText(profissional)).click();
		
		WebElement matricula = driver.findElement(By.xpath("//input[@aria-label='Matrícula']"));
		wait.until(ExpectedConditions.elementToBeClickable(matricula));
		matricula.click();
		matricula.clear();
		matricula.sendKeys(vinculo.getMatricula());
		
		actions.click(driver.findElement(By.xpath("//input[@aria-label='Nível']"))).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(nivel)));
		driver.findElement(By.linkText(nivel)).click();
		
		if(curso!=null) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-label='Curso']")));
		actions.click(driver.findElement(By.xpath("//input[@aria-label='Curso']"))).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(curso)));
		driver.findElement(By.linkText(curso)).click();
		}
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Fechar'])[1]/following::div[1]")).click();
	}
	
	public void inserirVinculoComErros(Vinculo vinculo, String nivel, String curso, String profissional) {
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Informações de Vínculo'])[2]/following::div[3]")));
		driver.findElement(By
				.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Informações de Vínculo'])[2]/following::div[3]")).click();
	
		if(profissional!=null) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-label='Profissional Responsável']")));
			driver.findElement(By.xpath("//input[@aria-label='Profissional Responsável']")).click();
			driver.findElement(By.xpath("//input[@aria-label='Profissional Responsável']")).clear();
			driver.findElement(By.xpath("//input[@aria-label='Profissional Responsável']")).sendKeys(profissional);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(profissional)));
			driver.findElement(By.linkText(profissional)).click();
		}
		WebElement matricula = driver.findElement(By.xpath("//input[@aria-label='Matrícula']"));
		wait.until(ExpectedConditions.elementToBeClickable(matricula));
		matricula.click();
		matricula.clear();
		matricula.sendKeys(vinculo.getMatricula());
		if(nivel!=null) {
			actions.click(driver.findElement(By.xpath("//input[@aria-label='Nível']"))).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(nivel)));
			driver.findElement(By.linkText(nivel)).click();
		}
		
		if(curso!=null) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-label='Curso']")));
			actions.click(driver.findElement(By.xpath("//input[@aria-label='Curso']"))).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(curso)));
			driver.findElement(By.linkText(curso)).click();
		}
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Fechar'])[1]/following::div[1]")).click();
		driver.findElement(By.xpath("//div[@class='v-btn__content'][contains(.,'Fechar')]")).click();
	}
	
	public void confirmarCadastro() {
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancelar'])[1]/following::div[1]")));
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancelar'])[1]/following::div[1]"))
				.click();

		try{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
		
				"(.//*[normalize-space(text()) and normalize-space(.)='Cadastro de aluno finalizado com sucesso!'])[1]/following::div[3]")));
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Cadastro de aluno finalizado com sucesso!'])[1]/following::div[3]"))
				.click();
		}catch(TimeoutException e) {
			e.printStackTrace();
		}
	}

	public void verificaAlunoCadastrado(Aluno aluno) throws FormularioException, InterruptedException {
		clicarAluno();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Listar")));
		driver.findElement(By.linkText("Listar")).click();
		
		WebElement inputNome = driver.findElement(By.xpath("//input[@aria-label='Nome']"));
		wait.until(ExpectedConditions.elementToBeClickable(inputNome));
		inputNome.click();
		inputNome.clear();
		inputNome.sendKeys(aluno.getUsuario().getNome());
		
		// BOTAO CONSULTAR
		driver.findElement(
				By.xpath("//button[@class='v-btn v-btn--small theme--light'][.//div[contains(text(), 'Consultar')]]"))
				.click();
		
		Thread.sleep(1500);
		if(driver.getPageSource().contains(aluno.getUsuario().getNome().toUpperCase())) {
			System.out.println("Aluno cadastrado");
		}else {
			throw new FormularioException("Aluno nao foi carregado corretamente");
		}
	}
	
	public void verificaErroSnack(String erro) throws FormularioException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-snack__content")));
		if (!driver.getPageSource().contains(erro)) {
			throw new FormularioException("erro não aparece ou mensagem está indevida");
		} else {
//			driver.findElement(By.xpath("//button[@class='v-btn v-btn--icon theme--dark']")).click();
		}
	}
}
