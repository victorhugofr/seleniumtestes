package formularios;



// import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
// import org.testng.Reporter;

import br.ufrn.imd.sigsaude.dominio.Paciente;
import br.ufrn.imd.sigsaude.dominio.Pessoa;
import dados.gerarNumeroDeDocumentos;
import enums.IDsPaginaPrincipalSistema;
import enums.IDsUsuarioDoServico;
import exceptions.FormularioException;
import utils.AbstractTestSigSaude;
import utils.ConverterData;


public class FormularioCadastroUsuarioServico extends AbstractTestSigSaude {

//	@Autowired
//	RecuperaMensagem recuperaMensagem;
	
	public void inserirDadosPessoaisPaciente(Paciente paciente,  boolean possuiCPF) {

		wait.until(ExpectedConditions.elementToBeClickable(By.id(IDsPaginaPrincipalSistema.MENU_USUARIO_SERVICO.getIdOrXpath())));
		driver.findElement(By.id(IDsPaginaPrincipalSistema.MENU_USUARIO_SERVICO.getIdOrXpath())).click();
		driver.findElement(By.linkText(IDsPaginaPrincipalSistema.MENU_NOVO_USUARIO_SERVICO.getIdOrXpath())).click();

		driver.findElement(By.id(IDsUsuarioDoServico.INPUT_NOME.getId())).sendKeys(paciente.getNome());
		

		driver.findElement(By.id(IDsUsuarioDoServico.INPUT_DATA_NASCIMENTO.getId())).click();
		// CAMPO DATA NASCIMENTO
		if(paciente.getPessoa().getDataNascimento()!=null) {
			driver.findElement(By.xpath(IDsUsuarioDoServico.INPUT_DATA_NASCIMENTO.getXpath()))
				.sendKeys(ConverterData.DataNascimentoConvert(paciente.getPessoa()));
		}

		// driver.findElement(By.id("inputDate")).sendKeys(ConverterDataNascimento.DataNascimentoConvert(pessoa));
		driver.findElement(By.id(IDsUsuarioDoServico.INPUT_EMAIL.getId())).sendKeys(paciente.getEmail());

		// LUPA CPF
		// driver.findElement(By.xpath("(.//*[normalize-space(text()) and
		// normalize-space(.)='CPF'])[1]/following::i[1]")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("selectSexo")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectSexo")));

		driver.findElement(By.id("selectSexo")).click();

		if (paciente.getSexo().equals("F")) {
			new Select(driver.findElement(By.id("selectSexo"))).selectByVisibleText("Feminino");
		} else {
			new Select(driver.findElement(By.id("selectSexo"))).selectByVisibleText("Masculino");
		}

		
		if (paciente.getPessoa().isEstrangeiro()) {
			driver.findElement(By.xpath(
					"(.//*[normalize-space(text()) and normalize-space(.)='Sou estrangeiro e não possuo CPF'])[1]/following::span[1]"))
					.click();
			if(paciente.getPessoa().getPassaporte()!=null) {
				
				driver.findElement(By.id("inputPassaporte")).click();
				driver.findElement(By.id("inputPassaporte")).sendKeys(paciente.getPessoa().getPassaporte());
			}
		}else {
			if(possuiCPF) {
				driver.findElement(By.id("inputCPF")).sendKeys(gerarNumeroDeDocumentos.geraCPF());
			}
			driver.findElement(By.id("inputIdentidade")).click();
			driver.findElement(By.id("inputIdentidade")).sendKeys(paciente.getPessoa().getRg());
			driver.findElement(By.id("inputOrgaoExpedidor")).click();
			driver.findElement(By.id("inputOrgaoExpedidor")).clear();
			driver.findElement(By.id("inputOrgaoExpedidor")).sendKeys(paciente.getPessoa().getOrgaoExpedidor());
			driver.findElement(By.id("inputSUS")).click();
			driver.findElement(By.id("inputSUS")).clear();
			driver.findElement(By.id("inputSUS")).sendKeys(paciente.getCarteiraSus());
		}
	
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectEstadoCivil")));
		driver.findElement(By.id("selectEstadoCivil")).click();

		new Select(driver.findElement(By.id("selectEstadoCivil"))).selectByValue(paciente.getEstadoCivil().toString());

		if (paciente.getPessoa().isEmancipado()) {
			// MENOR EMANCIPADO
			driver.findElement(By.xpath(
					"(.//*[normalize-space(text()) and normalize-space(.)='Menor Emancipado?'])[1]/following::span[1]"))
					.click();
		}



		new Select(driver.findElement(By.id("inputTipoSanguineo")))
				.selectByValue(paciente.getTipoSanguineo().toString());

		new Select(driver.findElement(By.id("pessoa.corRaca")))
				.selectByValue(paciente.getPessoa().getCorRaca().toString());

		if (paciente.isDeficiente()) {
			driver.findElement(By.id("checkboxDeficiente")).click();
			driver.findElement(By.id("inputDeficiencia")).click();
			driver.findElement(By.id("inputDeficiencia")).clear();
			driver.findElement(By.id("inputDeficiencia")).sendKeys(paciente.getTipoDeficiencia());

		}
		
		inputParentesco(paciente);
		
		driver.findElement(By.id("inputPaisNascimento")).click();
		driver.findElement(By.id("inputPaisNascimento")).clear();
		driver.findElement(By.id("inputPaisNascimento")).sendKeys(paciente.getPessoa().getPaisNascimento());

		// CIDADE NATURAL
//	    
//	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Naturalidade'])[1]/following::input[1]")).click();
//	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Naturalidade'])[1]/following::input[1]")).clear();
//	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Naturalidade'])[1]/following::input[1]")).sendKeys(pessoa.getNaturalidade());
//	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Naturalidade'])[1]/following::input[1]")).sendKeys(Keys.ENTER);
//	    
		driver.findElement(By.id("selectVinvulo")).click();
		new Select(driver.findElement(By.id("selectVinvulo"))).selectByValue(paciente.getTipoVinculo().toString());

		driver.findElement(By.id("inputUBS")).click();
		driver.findElement(By.id("inputUBS")).clear();
		driver.findElement(By.id("inputUBS")).sendKeys(paciente.getUnidadeBasica());

		inputNomeSocial(paciente);

		if (paciente.getPessoa().getInformaGenero()) {
			driver.findElement(By.id("checkboxGenero")).click();
			driver.findElement(By.id("inputGenero")).click();
			driver.findElement(By.id("inputGenero")).clear();
			driver.findElement(By.id("inputGenero")).sendKeys(paciente.getPessoa().getInformacaoGenero());

		}

		driver.findElement(By.name("salvarPaciente")).click();
		System.out.println("Clicou");
	}
	
	public void inserirDadosPessoaisApenasObrigatorio(Paciente paciente) {
		
		driver.findElement(By.id(IDsPaginaPrincipalSistema.MENU_USUARIO_SERVICO.getIdOrXpath())).click();
		driver.findElement(By.linkText(IDsPaginaPrincipalSistema.MENU_NOVO_USUARIO_SERVICO.getIdOrXpath())).click();

		driver.findElement(By.id(IDsUsuarioDoServico.INPUT_NOME.getId())).sendKeys(paciente.getNome());
		

		driver.findElement(By.id(IDsUsuarioDoServico.INPUT_DATA_NASCIMENTO.getId())).click();
		// CAMPO DATA NASCIMENTO
		if(paciente.getPessoa().getDataNascimento()!=null) {
			driver.findElement(By.xpath(IDsUsuarioDoServico.INPUT_DATA_NASCIMENTO.getXpath()))
				.sendKeys(ConverterData.DataNascimentoConvert(paciente.getPessoa()));
		}
		
		driver.findElement(By.id(IDsUsuarioDoServico.INPUT_EMAIL.getId())).click();
		driver.findElement(By.name("salvarPaciente")).click();
	}
	
	public void inputParentesco(Paciente paciente) {
		if (!paciente.getPessoa().isDesconheceMae()) {
			driver.findElement(By.id("checkboxDesconheceNomeMae")).click();
			driver.findElement(By.id("inputNomeMae")).click();
			driver.findElement(By.id("inputNomeMae")).clear();
			driver.findElement(By.id("inputNomeMae")).sendKeys(paciente.getPessoa().getNomeMae());
		}

		if (!paciente.getPessoa().isDesconhecePai()) {
			driver.findElement(By.id("checkboxDesconheceNomePai")).click();
			driver.findElement(By.id("inputNomePai")).click();
			driver.findElement(By.id("inputNomePai")).clear();
			driver.findElement(By.id("inputNomePai")).sendKeys(paciente.getPessoa().getNomePai());
		}
	}
	
	public void inputNomeSocial(Paciente paciente) {
		if (paciente.getPessoa().getUsaNomeSocial()) {
			driver.findElement(By.id("checkboxNomeSocial")).click();
			driver.findElement(By.id("inputNomeSocial")).click();
			driver.findElement(By.id("inputNomeSocial")).clear();
			driver.findElement(By.id("inputNomeSocial")).sendKeys(paciente.getPessoa().getNomeSocial());

		}
	}
	
	public void inserirDadosPacienteCPFInvalido(Paciente paciente) {
		
		Pessoa pessoa=paciente.getPessoa();
		
		driver.findElement(By.id(IDsPaginaPrincipalSistema.MENU_USUARIO_SERVICO.getIdOrXpath())).click();
		driver.findElement(By.linkText(IDsPaginaPrincipalSistema.MENU_NOVO_USUARIO_SERVICO.getIdOrXpath())).click();

		driver.findElement(By.id(IDsUsuarioDoServico.INPUT_NOME.getId())).sendKeys(pessoa.getNome());

		driver.findElement(By.id(IDsUsuarioDoServico.INPUT_DATA_NASCIMENTO.getId())).click();
		// CAMPO DATA NASCIMENTO
		driver.findElement(By.xpath(IDsUsuarioDoServico.INPUT_DATA_NASCIMENTO.getXpath()))
				.sendKeys(ConverterData.DataNascimentoConvert(pessoa));

		// driver.findElement(By.id("inputDate")).sendKeys(ConverterDataNascimento.DataNascimentoConvert(pessoa));
		driver.findElement(By.id(IDsUsuarioDoServico.INPUT_EMAIL.getId())).sendKeys(pessoa.getEmail());

		driver.findElement(By.id("inputCPF")).sendKeys("12345678911");
		// LUPA CPF
		// driver.findElement(By.xpath("(.//*[normalize-space(text()) and
		// normalize-space(.)='CPF'])[1]/following::i[1]")).click();

		driver.findElement(By.name("salvarPaciente")).click();
	}

	public void inserirEnderecoPaciente(Paciente paciente) {
		Pessoa pessoa = paciente.getPessoa();
		System.out.println(driver.getCurrentUrl());
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("inputCEP"))));
		
		if (pessoa.getEnderecos().get(pessoa.getEnderecos().size() - 1).isNomade()) {
			driver.findElement(
					By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sim'])[1]/following::label[1]"))
					.click();
			driver.findElement(By.id("inputReferencia")).click();
			driver.findElement(By.id("inputReferencia")).clear();
			driver.findElement(By.id("inputReferencia"))
					.sendKeys(pessoa.getEnderecos().get(pessoa.getEnderecos().size() - 1).getReferencia());
		} else {
			driver.findElement(By.id("inputCEP")).click();
			driver.findElement(By.id("inputCEP")).clear();
			driver.findElement(By.id("inputCEP"))
					.sendKeys(pessoa.getEnderecos().get(pessoa.getEnderecos().size() - 1).getCep());
			// LUPA CEP
			// driver.findElement(By.xpath("(.//*[normalize-space(text()) and
			// normalize-space(.)='CEP'])[1]/following::i[1]")).click();

			driver.findElement(By.id("inputLogradouro")).click();
			driver.findElement(By.id("inputLogradouro")).clear();
			driver.findElement(By.id("inputLogradouro"))
					.sendKeys(pessoa.getEnderecos().get(pessoa.getEnderecos().size() - 1).getLogradouro());

			driver.findElement(By.id("inputNumero")).click();
			driver.findElement(By.id("inputNumero")).clear();
			driver.findElement(By.id("inputNumero"))
					.sendKeys(pessoa.getEnderecos().get(pessoa.getEnderecos().size() - 1).getNumero());

			driver.findElement(By.id("inputPais")).click();

			driver.findElement(By.id("inputBairro")).click();
			driver.findElement(By.id("inputBairro")).clear();

			driver.findElement(By.id("inputBairro"))
					.sendKeys(pessoa.getEnderecos().get(pessoa.getEnderecos().size() - 1).getBairro());

			driver.findElement(By.id("selectMunicipio")).click();

			driver.findElement(By.id("inputPais")).click();
			driver.findElement(By.id("inputPais"))
					.sendKeys(pessoa.getEnderecos().get(pessoa.getEnderecos().size() - 1).getPais());

			driver.findElement(By.id("selectUF")).click();
			new Select(driver.findElement(By.id("selectUF"))).selectByVisibleText(
					pessoa.getEnderecos().get(pessoa.getEnderecos().size() - 1).getUf().getSigla().toUpperCase());

			// driver.findElement(By.xpath("(.//*[normalize-space(text()) and
			// normalize-space(.)='UF'])[1]/following::option[20]")).click();
			driver.findElement(By.id("selectMunicipio")).click();

			new Select(driver.findElement(By.id("selectMunicipio"))).selectByVisibleText(
					pessoa.getEnderecos().get(pessoa.getEnderecos().size() - 1).getMunicipio().getNome());

			driver.findElement(By.xpath(
					"(.//*[normalize-space(text()) and normalize-space(.)='Município'])[1]/following::option[13]"))
					.click();

			driver.findElement(By.id("inputComplemento")).click();
			driver.findElement(By.id("inputComplemento")).clear();
			driver.findElement(By.id("inputComplemento"))
					.sendKeys(pessoa.getEnderecos().get(pessoa.getEnderecos().size() - 1).getComplemento());

			driver.findElement(By.id("inputReferencia")).click();
			driver.findElement(By.id("inputReferencia")).clear();
			driver.findElement(By.id("inputReferencia"))
					.sendKeys(pessoa.getEnderecos().get(pessoa.getEnderecos().size() - 1).getReferencia());

			driver.findElement(By.name("salvarEndereco")).click();
		}
	}
	
	public void inserirEnderecoSemNada() throws FormularioException {
		
		driver.findElement(By.name("salvarEndereco")).click();
		
		if (!driver.findElement(By.className("invalid-feedback")).isDisplayed()) {
			throw new FormularioException("Não foi possivel encontrar a mensagem de obrigatoriedade do endereço");
		}
	}
	
	public void inserirEnderecoNomade(Paciente paciente) {
		
		Pessoa pessoa=paciente.getPessoa();
		driver.findElement(By.xpath("//label[contains(@for, 'radioNomadeSim')]")).click();
		driver.findElement(By.id("inputReferencia")).click();
		driver.findElement(By.id("inputReferencia")).clear();
		driver.findElement(By.id("inputReferencia"))
				.sendKeys(pessoa.getEnderecos().get(pessoa.getEnderecos().size() - 1).getReferencia());

		driver.findElement(By.name("salvarEndereco")).click();
	}
	public void inserirContatoPaciente(Paciente paciente) {
		
		Pessoa pessoa = paciente.getPessoa();
		
		driver.findElement(By.id("tipoTelefone")).click();

		if (pessoa.getContatos().get(pessoa.getContatos().size() - 1).getTipoTelefone().name().equals("FIXO")) {
			new Select(driver.findElement(By.id("tipoTelefone"))).selectByValue("FIXO");
			driver.findElement(By.id("inputTelefone"))
					.sendKeys(pessoa.getContatos().get(pessoa.getContatos().size() - 1).getTelefone());
		} else {
			new Select(driver.findElement(By.id("tipoTelefone"))).selectByValue("MOVEL");
			driver.findElement(By.id("inputTelefone")).click();
			driver.findElement(By.id("inputTelefone")).clear();
			driver.findElement(By.id("inputTelefone"))
					.sendKeys(pessoa.getContatos().get(pessoa.getContatos().size() - 1).getTelefone());
		}

		/*
		 * driver.findElement(By.
		 * xpath("(.//*[normalize-space(text()) and normalize-space(.)='Tipo'])[1]/following::option[1]"
		 * )).click();
		 * 
		 * 
		 * driver.findElement(By.
		 * xpath("(.//*[normalize-space(text()) and normalize-space(.)='Tipo'])[1]/following::option[2]"
		 * )).click();
		 */
		if (pessoa.getContatos().get(pessoa.getContatos().size() - 1).isSms()) {
			driver.findElement(By.id("checkSMS")).click();
		}
		if (pessoa.getContatos().get(pessoa.getContatos().size() - 1).isWhatsapp()) {
			driver.findElement(By.id("checkWhatsapp")).click();
		}
		if (pessoa.getContatos().get(pessoa.getContatos().size() - 1).isContatoUrgencia()) {
			driver.findElement(By.id("checkContatoUrgencia")).click();
		}

		driver.findElement(By.id("observacao")).click();
		driver.findElement(By.id("observacao")).clear();
		driver.findElement(By.id("observacao"))
				.sendKeys(pessoa.getContatos().get(pessoa.getContatos().size() - 1).getObservacao());

		driver.findElement(By.id("adicionarContatoPaciente")).click();

		driver.findElement(By.id("salvarContatoPaciente")).click();
	}
	
	public void inserirContatoSemNada() {
		driver.findElement(By.id("adicionarContatoPaciente")).click();
	}
	
	public void inserirResponsavelPaciente(Paciente paciente) {
		
		if (paciente.getResponsavel().isProprioResponsavel()) {
			// driver.findElement(By.xpath("(.//*[normalize-space(text()) and
			// normalize-space(.)='Próprio usuário do
			// serviço'])[1]/following::span[1]")).click();
			driver.findElement(By.name("salvarResponsavel")).click();
		} else {
			driver.findElement(By.xpath(
					"(.//*[normalize-space(text()) and normalize-space(.)='Próprio usuário do serviço'])[1]/following::span[1]"))
					.click();
			new Select(driver.findElement(By.id("inputRelacao")))
					.selectByValue(paciente.getResponsavel().getRelacao().toString());

			driver.findElement(By.id("inputResponsavel")).click();
			driver.findElement(By.id("inputResponsavel")).clear();
			driver.findElement(By.id("inputResponsavel")).sendKeys(paciente.getResponsavel().getNome());

			driver.findElement(By.id("inputIdResponsavel")).click();
			driver.findElement(By.id("inputIdResponsavel")).clear();
			driver.findElement(By.id("inputIdResponsavel")).sendKeys(paciente.getResponsavel().getRg().toString());

			driver.findElement(By.id("inputOrgaoExpResponsavel")).clear();
			driver.findElement(By.id("inputOrgaoExpResponsavel"))
					.sendKeys(paciente.getResponsavel().getOrgaoExpedidor());

			driver.findElement(By.id("inputEmailResponsavel")).click();
			driver.findElement(By.id("inputEmailResponsavel")).clear();
			driver.findElement(By.id("inputEmailResponsavel")).sendKeys(paciente.getResponsavel().getEmail());

			driver.findElement(By.name("salvarResponsavel")).click();

			driver.findElement(By.id("inputTelefone")).click();

			if (paciente.getResponsavel().getContatos().get(0).getTipoTelefone().toString().equals("FIXO")) {
				new Select(driver.findElement(By.id("tipoTelefone"))).selectByValue("FIXO");
				driver.findElement(By.id("inputTelefone"))
						.sendKeys(paciente.getResponsavel().getContatos().get(0).getTelefone());
			} else {
				new Select(driver.findElement(By.id("tipoTelefone"))).selectByValue("MOVEL");
				driver.findElement(By.id("inputTelefone")).click();
				driver.findElement(By.id("inputTelefone")).clear();
				driver.findElement(By.id("inputTelefone"))
						.sendKeys(paciente.getResponsavel().getContatos().get(0).getTelefone());
			}

			if (paciente.getResponsavel().getContatos().get(0).isSms()) {
				driver.findElement(By.id("checkSMS")).click();
			}
			if (paciente.getResponsavel().getContatos().get(0).isWhatsapp()) {
				driver.findElement(By.id("checkWhatsapp")).click();
			}
			if (paciente.getResponsavel().getContatos().get(0).isContatoUrgencia()) {
				driver.findElement(By.id("checkContatoUrgencia")).click();
			}

			driver.findElement(By.id("observacao")).click();
			driver.findElement(By.id("observacao")).clear();
			driver.findElement(By.id("observacao"))
					.sendKeys(paciente.getResponsavel().getContatos().get(0).getObservacao());

			driver.findElement(By.id("adicionarContatoResponsavel")).click();

			driver.findElement(By.id("salvarContatoResponsavel")).click();

		}
		
	}
	
	public void inserirResponsavelSemNada() {
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Próprio usuário do serviço'])[1]/following::span[1]"))
				.click();

		driver.findElement(By.name("salvarResponsavel")).click();

	}
	
	public void verificaUsuarioCadastrado(Paciente paciente) throws FormularioException {
		Pessoa pessoa = paciente.getPessoa();
		driver.findElement(By.id(IDsPaginaPrincipalSistema.MENU_USUARIO_SERVICO.getIdOrXpath())).click();
		driver.findElement(By.linkText(IDsPaginaPrincipalSistema.MENU_LISTAR_USUARIO_SERVICO.getIdOrXpath())).click();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Listagem de usúarios de serviço'])[3]/following::input[3]")).click();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Listagem de usúarios de serviço'])[3]/following::input[3]"))
		.sendKeys(pessoa.getNome());
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Listagem de usúarios de serviço'])[3]/following::div[52]")).click();
		
		driver.findElement(By.xpath("//*[@title=\"Visualizar\"]")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"+pessoa.getNome().toUpperCase()+"']")));

		if(!driver.getPageSource().contains(pessoa.getNome().toUpperCase())){
			throw new FormularioException("O usuário não foi cadastrado com sucesso, não apresenta na lista de usuários cadastrados");
		}
	}
	
	public void verificaUsuarioCadastradoApenasObrigatorio(Paciente paciente) throws FormularioException {
		Pessoa pessoa = paciente.getPessoa();
		driver.findElement(By.id(IDsPaginaPrincipalSistema.MENU_USUARIO_SERVICO.getIdOrXpath())).click();
		driver.findElement(By.linkText(IDsPaginaPrincipalSistema.MENU_LISTAR_USUARIO_SERVICO.getIdOrXpath())).click();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Listagem de usúarios de serviço'])[3]/following::input[3]")).click();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Listagem de usúarios de serviço'])[3]/following::input[3]"))
		.sendKeys(pessoa.getNome());
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Listagem de usúarios de serviço'])[3]/following::div[52]")).click();
		
		driver.findElement(By.xpath("//*[@title=\"Visualizar\"]")).click();
		
		if(!(driver.getPageSource().contains(pessoa.getNome().toUpperCase()))){
			throw new FormularioException("O usuário não foi cadastrado com sucesso, não apresenta na lista de usuários cadastrados");
		}
	}
	
	public void verificaErro(String erro) throws FormularioException {

		try {
			if (!driver.findElement(By.className("toast-error")).isDisplayed()) {
				throw new FormularioException(erro);
			} else {
				System.out.println("Toast sendo exibido");
				System.out.println(driver.findElement(By.className("toast-error")).getText());
			}
		}catch(NoSuchElementException e) {
			try {
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("invalid-feedback"))));
				
				if(!driver.findElement(By.className("invalid-feedback")).isDisplayed()){
					// Reporter.log((driver.findElement(By.className("invalid-feedback")).getText()));
					throw new FormularioException(erro);
				}else {
					System.out.println("Toast sendo exibido");
					System.out.println(driver.findElement(By.className("invalid-feedback")).getText());
				}
			}catch(RuntimeException e1) {
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("toast-message"))));
				
				if(!driver.findElement(By.className("toast-message")).isDisplayed()){
					// Reporter.log((driver.findElement(By.className("toast-message")).getText()));
					System.out.println((driver.findElement(By.className("toast-message")).getText()));
					// fail(erro);
				}else {
					System.out.println("Toast sendo exibido");
					System.out.println(driver.findElement(By.className("toast-message")).getText());
				}
			}
		}
	}
	
	public void verificaErroCPFInvalido(String erro) throws FormularioException {

		if (!(driver.getPageSource().contains("invalid Brazilian individual taxpayer registry number (CPF)") || driver.getPageSource().contains("CPF inválido"))) {
			throw new FormularioException("pagina nao contem exigencia de CPF");
		}
	}
	
	public void verificaErroObrigatorio(String erro) throws FormularioException {

		if (!driver.getPageSource().contains("Campo obrigatório.")) {
			throw new FormularioException(erro);
		} 
	}
	
	public void inserirResponsavelPropioUsuarioServico() {
		//#eee
		if(driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Próprio usuário do serviço'])[1]/following::span[1]")).
				getCssValue("background-color").equals("#eee")){
		driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Próprio usuário do serviço'])[1]/following::span[1]"))
				.click();
		}
		driver.findElement(By.name("salvarResponsavel")).click();
	}
	
	public void confirmarCadastro() {
		
		driver.findElement(By.id("btnFinalizar")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				".swal-button")));

		driver.findElement(By.cssSelector(
				".swal-button"))
				.click();
	}	
}
