package formularios;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
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

	public void inserirDadosPessoaisPaciente(Paciente paciente, boolean possuiCPF) {
		try {
		wait.until(ExpectedConditions
				.elementToBeClickable(By.id(IDsPaginaPrincipalSistema.MENU_USUARIO_SERVICO.getIdOrXpath())));
		}catch(TimeoutException e) {
			
			if(driver.findElement(By.linkText("Voltar")).isDisplayed()) {
				driver.findElement(By.linkText("Voltar")).click();
			}
		}
		driver.findElement(By.id(IDsPaginaPrincipalSistema.MENU_USUARIO_SERVICO.getIdOrXpath())).click();
		driver.findElement(By.linkText(IDsPaginaPrincipalSistema.MENU_NOVO_USUARIO_SERVICO.getIdOrXpath())).click();

		driver.findElement(By.id(IDsUsuarioDoServico.INPUT_NOME.getId())).sendKeys(paciente.getNome());

		// driver.findElement(By.id(IDsUsuarioDoServico.INPUT_DATA_NASCIMENTO.getId())).click();
		// CAMPO DATA NASCIMENTO
		if (paciente.getPessoa().getDataNascimento() != null) {
			driver.findElement(By.id(IDsUsuarioDoServico.INPUT_DATA_NASCIMENTO.getId()))
					.sendKeys(ConverterData.DataNascimentoConvert(paciente.getPessoa()));
		}

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
			actions.click(driver.findElement(By.id("checkboxEstrangeiro"))).perform();
			
			if (paciente.getPessoa().getPassaporte() != null) {
				driver.findElement(By.id("inputPassaporte")).click();
				driver.findElement(By.id("inputPassaporte")).sendKeys(paciente.getPessoa().getPassaporte());
			}
		} else {
			if (possuiCPF) {
				driver.findElement(By.id("inputCPF")).sendKeys(gerarNumeroDeDocumentos.geraCPF());
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loading-background']")));
			}
			driver.findElement(By.id("inputIdentidade")).click();
			driver.findElement(By.id("inputIdentidade")).sendKeys(paciente.getPessoa().getRg());
			WebElement orgaoExpedidor = driver.findElement(By.id("inputOrgaoExpedidor"));
			orgaoExpedidor.click();
			orgaoExpedidor.clear();
			orgaoExpedidor.sendKeys(paciente.getPessoa().getOrgaoExpedidor());
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
		WebElement paisNascimento = driver.findElement(By.id("inputPaisNascimento"));
		paisNascimento.click();
		paisNascimento.clear();
		paisNascimento.sendKeys(paciente.getPessoa().getPaisNascimento());
		

		if (paciente.getPessoa().getInformaGenero()) {
			driver.findElement(By.id("checkboxGenero")).click();
			driver.findElement(By.id("inputGenero")).click();
			driver.findElement(By.id("inputGenero")).clear();
			driver.findElement(By.id("inputGenero")).sendKeys(paciente.getPessoa().getInformacaoGenero());

		}
		//
		driver.findElement(By.id("selectVinvulo")).click();
		new Select(driver.findElement(By.id("selectVinvulo"))).selectByValue(paciente.getTipoVinculo().toString());

		driver.findElement(By.id("inputUBS")).click();
		driver.findElement(By.id("inputUBS")).clear();
		driver.findElement(By.id("inputUBS")).sendKeys(paciente.getUnidadeBasica());

		inputNomeSocial(paciente);
		

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	public void inserirDadosPessoaisApenasObrigatorio(Paciente paciente) {

		driver.findElement(By.id(IDsPaginaPrincipalSistema.MENU_USUARIO_SERVICO.getIdOrXpath())).click();
		driver.findElement(By.linkText(IDsPaginaPrincipalSistema.MENU_NOVO_USUARIO_SERVICO.getIdOrXpath())).click();

		driver.findElement(By.id(IDsUsuarioDoServico.INPUT_NOME.getId())).sendKeys(paciente.getNome());

		driver.findElement(By.id(IDsUsuarioDoServico.INPUT_DATA_NASCIMENTO.getId())).click();
		// CAMPO DATA NASCIMENTO
		if (paciente.getPessoa().getDataNascimento() != null) {
			driver.findElement(By.id(IDsUsuarioDoServico.INPUT_DATA_NASCIMENTO.getId()))
					.sendKeys(ConverterData.DataNascimentoConvert(paciente.getPessoa()));
		}

		driver.findElement(By.id(IDsUsuarioDoServico.INPUT_EMAIL.getId())).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("selectSexo")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectSexo")));

		driver.findElement(By.id("selectSexo")).click();

		if (paciente.getSexo().equals("F")) {
			new Select(driver.findElement(By.id("selectSexo"))).selectByVisibleText("Feminino");
		} else {
			new Select(driver.findElement(By.id("selectSexo"))).selectByVisibleText("Masculino");
		}

		new Select(driver.findElement(By.id("pessoa.corRaca")))
				.selectByValue(paciente.getPessoa().getCorRaca().toString());

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectEstadoCivil")));
		driver.findElement(By.id("selectEstadoCivil")).click();
		new Select(driver.findElement(By.id("selectEstadoCivil"))).selectByValue(paciente.getEstadoCivil().toString());

		driver.findElement(By.name("salvarPaciente")).click();
	}

	public void inputParentesco(Paciente paciente) {
		if (!paciente.getPessoa().isDesconheceMae()) {
			driver.findElement(By.id("checkboxDesconheceNomeMae")).click();
			WebElement nomeMae = driver.findElement(By.id("inputNomeMae"));
			nomeMae.click();
			nomeMae.clear();
			nomeMae.sendKeys(paciente.getPessoa().getNomeMae());
			WebElement generoFiliacao1 = driver.findElement(By.id("inputGeneroFiliacao1"));
			generoFiliacao1.click();
			generoFiliacao1.clear();
			generoFiliacao1.sendKeys("Feminino");
		}

		if (!paciente.getPessoa().isDesconhecePai()) {
			driver.findElement(By.id("checkboxDesconheceNomePai")).click();
			driver.findElement(By.id("inputNomePai")).click();
			driver.findElement(By.id("inputNomePai")).clear();
			driver.findElement(By.id("inputNomePai")).sendKeys(paciente.getPessoa().getNomePai());
			
			WebElement generoFiliacao1 = driver.findElement(By.id("inputGeneroFiliacao2"));
			generoFiliacao1.click();
			generoFiliacao1.clear();
			generoFiliacao1.sendKeys("Masculino");
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

		Pessoa pessoa = paciente.getPessoa();

		driver.findElement(By.id(IDsPaginaPrincipalSistema.MENU_USUARIO_SERVICO.getIdOrXpath())).click();
		driver.findElement(By.linkText(IDsPaginaPrincipalSistema.MENU_NOVO_USUARIO_SERVICO.getIdOrXpath())).click();

		driver.findElement(By.id(IDsUsuarioDoServico.INPUT_NOME.getId())).sendKeys(pessoa.getNome());

		driver.findElement(By.id(IDsUsuarioDoServico.INPUT_DATA_NASCIMENTO.getId())).click();
		// CAMPO DATA NASCIMENTO
		driver.findElement(By.xpath(IDsUsuarioDoServico.INPUT_DATA_NASCIMENTO.getXpath()))
				.sendKeys(ConverterData.DataNascimentoConvert(pessoa));

		driver.findElement(By.id(IDsUsuarioDoServico.INPUT_EMAIL.getId())).sendKeys(pessoa.getEmail());
		wait.until(ExpectedConditions.elementToBeClickable(By.id("selectSexo")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectSexo")));

		driver.findElement(By.id("selectSexo")).click();

		if (paciente.getSexo().equals("F")) {
			new Select(driver.findElement(By.id("selectSexo"))).selectByVisibleText("Feminino");
		} else {
			new Select(driver.findElement(By.id("selectSexo"))).selectByVisibleText("Masculino");
		}

		new Select(driver.findElement(By.id("pessoa.corRaca")))
				.selectByValue(paciente.getPessoa().getCorRaca().toString());

		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("selectEstadoCivil")));
		driver.findElement(By.id("selectEstadoCivil")).click();
		new Select(driver.findElement(By.id("selectEstadoCivil"))).selectByValue(paciente.getEstadoCivil().toString());

		driver.findElement(By.id("inputCPF")).sendKeys("12345678911");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loading-background']")));
		// LUPA CPF
		// driver.findElement(By.xpath("(.//*[normalize-space(text()) and
		// normalize-space(.)='CPF'])[1]/following::i[1]")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.name("salvarPaciente")));
		driver.findElement(By.name("salvarPaciente")).click();
	}

	public void inserirEnderecoPaciente(Paciente paciente) {
		System.out.println(driver.getPageSource());
		Pessoa pessoa = paciente.getPessoa();
		System.out.println(driver.getCurrentUrl());

		// wait.until(ExpectedConditions.elementToBeClickable(By.name("cep")));

		if (pessoa.getEnderecos().get(pessoa.getEnderecos().size() - 1).isNomade()) {
			driver.findElement(
					By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Sim'])[1]/following::label[1]"))
					.click();
			WebElement inputReferencia = driver.findElement(By.id("inputReferencia"));
			inputReferencia.click();
			inputReferencia.clear();
			inputReferencia.sendKeys(pessoa.getEnderecos().get(pessoa.getEnderecos().size() - 1).getReferencia());
		} else {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.name("cep")));
			}catch(TimeoutException e) {
				try {
				wait.until(ExpectedConditions.elementToBeClickable(By.name("cep")));
				}catch(TimeoutException e1) {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
					driver.findElement(By.xpath("//button[@type='submit']")).click();
				}
			}
			WebElement inputCep = driver.findElement(By.name("cep"));
			inputCep.click();
			inputCep.clear();
			inputCep.sendKeys(pessoa.getEnderecos().get(pessoa.getEnderecos().size() - 1).getCep());
			// LUPA CEP
			driver.findElement(
					By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='CEP'])[1]/following::i[1]"))
					.click();
			// WebElement inputLogradouro = driver.findElement(By.id("inputLogradouro"));
			// inputLogradouro.click();
			// inputLogradouro.clear();
			// inputLogradouro.sendKeys(pessoa.getEnderecos().get(pessoa.getEnderecos().size()
			// - 1).getLogradouro());
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loading-background']")));
			wait.until(ExpectedConditions.elementToBeClickable(By.id("inputNumero")));
			WebElement inputNumero = driver.findElement(By.id("inputNumero"));
			actions.click(inputNumero).build().perform();
			inputNumero.clear();
			actions.sendKeys(inputNumero, pessoa.getEnderecos().get(pessoa.getEnderecos().size() - 1).getNumero()).build().perform();
//			inputNumero.sendKeys(pessoa.getEnderecos().get(pessoa.getEnderecos().size() - 1).getNumero());
			//
			// driver.findElement(By.id("inputPais")).click();
			//
			// driver.findElement(By.id("inputBairro")).click();
			// driver.findElement(By.id("inputBairro")).clear();
			//
			// driver.findElement(By.id("inputBairro"))
			// .sendKeys(pessoa.getEnderecos().get(pessoa.getEnderecos().size() -
			// 1).getBairro());
			//
			// driver.findElement(By.id("selectMunicipio")).click();
			//
			// driver.findElement(By.id("inputPais")).click();
			// driver.findElement(By.id("inputPais"))
			// .sendKeys(pessoa.getEnderecos().get(pessoa.getEnderecos().size() -
			// 1).getPais());
			//
			// driver.findElement(By.id("selectUF")).click();
			// new Select(driver.findElement(By.id("selectUF"))).selectByVisibleText(
			// pessoa.getEnderecos().get(pessoa.getEnderecos().size() -
			// 1).getUf().getSigla().toUpperCase());
			//
			// driver.findElement(By.id("selectMunicipio")).click();
			//
			// new Select(driver.findElement(By.id("selectMunicipio"))).selectByVisibleText(
			// pessoa.getEnderecos().get(pessoa.getEnderecos().size() -
			// 1).getMunicipio().getNome());
			//
			// driver.findElement(By.xpath(
			// "(.//*[normalize-space(text()) and
			// normalize-space(.)='Município'])[1]/following::option[13]"))
			// .click();
			// WebElement inputComplemento = driver.findElement(By.id("inputComplemento"));
			// inputComplemento.click();
			// inputComplemento.clear();
			// inputComplemento.sendKeys(pessoa.getEnderecos().get(pessoa.getEnderecos().size()
			// - 1).getComplemento());
			//
			// WebElement inputReferencia = driver.findElement(By.id("inputReferencia"));
			// inputReferencia.click();
			// inputReferencia.clear();
			// inputReferencia.sendKeys(pessoa.getEnderecos().get(pessoa.getEnderecos().size()
			// - 1).getReferencia());

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
		System.out.println(driver.getPageSource());
		System.out.println(driver.getCurrentUrl());
		Pessoa pessoa = paciente.getPessoa();
		driver.findElement(By.xpath("//label[contains(@for, 'radioNomadeSim')]")).click();
		WebElement inputReferencia = driver.findElement(By.id("inputReferencia"));
		inputReferencia.click();
		inputReferencia.clear();
		inputReferencia.sendKeys(pessoa.getEnderecos().get(pessoa.getEnderecos().size() - 1).getReferencia());

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
			WebElement inputTelefone = driver.findElement(By.id("inputTelefone"));
			inputTelefone.click();
			inputTelefone.clear();
			inputTelefone.sendKeys(pessoa.getContatos().get(pessoa.getContatos().size() - 1).getTelefone());
		}

		if (pessoa.getContatos().get(pessoa.getContatos().size() - 1).isSms()) {
			driver.findElement(By.id("checkSMS")).click();
		}
		if (pessoa.getContatos().get(pessoa.getContatos().size() - 1).isWhatsapp()) {
			driver.findElement(By.id("checkWhatsapp")).click();
		}
		if (pessoa.getContatos().get(pessoa.getContatos().size() - 1).isContatoUrgencia()) {
			driver.findElement(By.id("checkContatoUrgencia")).click();
		}
		WebElement inputObservacao = driver.findElement(By.id("observacao"));
		inputObservacao.click();
		inputObservacao.clear();
		inputObservacao.sendKeys(pessoa.getContatos().get(pessoa.getContatos().size() - 1).getObservacao());

		driver.findElement(By.id("adicionarContatoPaciente")).click();

	}

	public void salvarContatoPaciente() {
		driver.findElement(By.id("salvarContatoPaciente")).click();
	}

	public void inserirContatoSemNada() {
		driver.findElement(By.id("adicionarContatoPaciente")).click();
	}

	public void inserirResponsavelPaciente(Paciente paciente) {

		if (paciente.getResponsavel().isProprioResponsavel()) {
			driver.findElement(By.name("salvarResponsavel")).click();
		} else {
			driver.findElement(By.xpath(
					"(.//*[normalize-space(text()) and normalize-space(.)='Próprio usuário do serviço'])[1]/following::span[1]"))
					.click();
			new Select(driver.findElement(By.id("inputRelacao")))
					.selectByValue(paciente.getResponsavel().getRelacao().toString());

			WebElement inputResponsavel = driver.findElement(By.id("inputResponsavel"));
			inputResponsavel.click();
			inputResponsavel.clear();
			inputResponsavel.sendKeys(paciente.getResponsavel().getNome());

			WebElement inputIdResponsavel = driver.findElement(By.id("inputIdResponsavel"));
			inputIdResponsavel.click();
			inputIdResponsavel.clear();
			inputIdResponsavel.sendKeys(paciente.getResponsavel().getRg().toString());

			WebElement inputOrgaoExpResponsavel = driver.findElement(By.id("inputOrgaoExpResponsavel"));
			inputOrgaoExpResponsavel.clear();
			inputOrgaoExpResponsavel.sendKeys(paciente.getResponsavel().getOrgaoExpedidor());

			WebElement inputEmailResponsavel = driver.findElement(By.id("inputEmailResponsavel"));
			inputEmailResponsavel.click();
			inputEmailResponsavel.clear();
			inputEmailResponsavel.sendKeys(paciente.getResponsavel().getEmail());

			driver.findElement(By.name("salvarResponsavel")).click();

			driver.findElement(By.id("inputTelefone")).click();

			if (paciente.getResponsavel().getContatos().get(0).getTipoTelefone().toString().equals("FIXO")) {
				new Select(driver.findElement(By.id("tipoTelefone"))).selectByValue("FIXO");
				driver.findElement(By.id("inputTelefone"))
						.sendKeys(paciente.getResponsavel().getContatos().get(0).getTelefone());
			} else {
				new Select(driver.findElement(By.id("tipoTelefone"))).selectByValue("MOVEL");
				WebElement inputTelefone = driver.findElement(By.id("inputTelefone"));
				inputTelefone.click();
				inputTelefone.clear();
				inputTelefone.sendKeys(paciente.getResponsavel().getContatos().get(0).getTelefone());
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
			WebElement inputObservacao = driver.findElement(By.id("observacao"));
			inputObservacao.click();
			inputObservacao.clear();
			inputObservacao.sendKeys(paciente.getResponsavel().getContatos().get(0).getObservacao());

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
		driver.findElement(By.xpath("//input[@aria-label='Nome do paciente']")).click();
		driver.findElement(By.xpath("//input[@aria-label='Nome do paciente']")).sendKeys(pessoa.getNome());
		driver.findElement(By.cssSelector(".v-btn--small")).click();

		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@title='Visualizar']")));
			driver.findElement(By.xpath("//i[@title='Visualizar']")).click();
			wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//span[text()='" + pessoa.getNome().toUpperCase() + "']")));
		} catch (TimeoutException e) {
			if (!driver.getPageSource().contains(pessoa.getNome().toUpperCase())) {
				throw new FormularioException(
						"O usuário não foi cadastrado com sucesso, não apresenta na lista de usuários cadastrados");
			}
		}
	}

	public void inativarUsuario(Paciente paciente) {
		Pessoa pessoa = paciente.getPessoa();
		driver.findElement(By.id(IDsPaginaPrincipalSistema.MENU_USUARIO_SERVICO.getIdOrXpath())).click();
		driver.findElement(By.linkText(IDsPaginaPrincipalSistema.MENU_LISTAR_USUARIO_SERVICO.getIdOrXpath())).click();
		driver.findElement(By.xpath("//input[@aria-label='Nome do paciente']")).click();
		driver.findElement(By.xpath("//input[@aria-label='Nome do paciente']")).sendKeys(pessoa.getNome());
		driver.findElement(By.cssSelector(".v-btn--small")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@title='Inativar']")));
		driver.findElement(By.xpath("//i[@title='Inativar']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("inputJustificativa")));
		driver.findElement(By.id("inputJustificativa")).click();
		driver.findElement(By.id("inputJustificativa")).clear();
		driver.findElement(By.id("inputJustificativa")).sendKeys("justificativa");

		driver.findElement(By.xpath("//button[@class='btn btn-success pull-right']")).click();

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[@class='swal-button swal-button--confirm']")));
		driver.findElement(By.xpath("//button[@class='swal-button swal-button--confirm']")).click();
	}

	public void inativarUsuarioNomeSocial(Paciente paciente) {
		Pessoa pessoa = paciente.getPessoa();
		driver.findElement(By.id(IDsPaginaPrincipalSistema.MENU_USUARIO_SERVICO.getIdOrXpath())).click();
		driver.findElement(By.linkText(IDsPaginaPrincipalSistema.MENU_LISTAR_USUARIO_SERVICO.getIdOrXpath())).click();
		driver.findElement(By.xpath("//input[@aria-label='Nome do paciente']")).click();
		driver.findElement(By.xpath("//input[@aria-label='Nome do paciente']")).sendKeys(pessoa.getNomeSocial());
		driver.findElement(By.cssSelector(".v-btn--small")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@title='Inativar']")));
		driver.findElement(By.xpath("//i[@title='Inativar']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("inputJustificativa")));
		driver.findElement(By.id("inputJustificativa")).click();
		driver.findElement(By.id("inputJustificativa")).clear();
		driver.findElement(By.id("inputJustificativa")).sendKeys("justificativa");

		driver.findElement(By.xpath("//button[@class='btn btn-success pull-right']")).click();

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[@class='swal-button swal-button--confirm']")));
		driver.findElement(By.xpath("//button[@class='swal-button swal-button--confirm']")).click();
	}

	public void verificaUsuarioNomeSocial(Paciente paciente) throws FormularioException {
		Pessoa pessoa = paciente.getPessoa();
		driver.findElement(By.id(IDsPaginaPrincipalSistema.MENU_USUARIO_SERVICO.getIdOrXpath())).click();
		driver.findElement(By.linkText(IDsPaginaPrincipalSistema.MENU_LISTAR_USUARIO_SERVICO.getIdOrXpath())).click();
		driver.findElement(By.xpath("//input[@aria-label='Nome do paciente']")).click();
		driver.findElement(By.xpath("//input[@aria-label='Nome do paciente']")).sendKeys(pessoa.getNomeSocial());
		driver.findElement(By.cssSelector(".v-btn--small")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@title='Visualizar']")));
		driver.findElement(By.xpath("//i[@title='Visualizar']")).click();
		if (!(driver.getPageSource().contains(pessoa.getNome().toUpperCase()))) {
			throw new FormularioException(
					"O usuário não foi cadastrado com sucesso, não apresenta na lista de usuários cadastrados");
		}
	}

	public void verificaUsuarioCadastradoApenasObrigatorio(Paciente paciente) throws FormularioException {
		Pessoa pessoa = paciente.getPessoa();
		driver.findElement(By.id(IDsPaginaPrincipalSistema.MENU_USUARIO_SERVICO.getIdOrXpath())).click();
		driver.findElement(By.linkText(IDsPaginaPrincipalSistema.MENU_LISTAR_USUARIO_SERVICO.getIdOrXpath())).click();
		driver.findElement(By.xpath("//input[@aria-label='Nome do paciente']")).click();
		driver.findElement(By.xpath("//input[@aria-label='Nome do paciente']")).sendKeys(pessoa.getNome());
		driver.findElement(By.cssSelector(".v-btn--small")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@title='Visualizar']")));
		driver.findElement(By.xpath("//i[@title='Visualizar']")).click();

		if (!(driver.getPageSource().contains(pessoa.getNome().toUpperCase()))) {
			throw new FormularioException(
					"O usuário não foi cadastrado com sucesso, não apresenta na lista de usuários cadastrados");
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
		} catch (NoSuchElementException e) {
			try {
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("invalid-feedback"))));

				if (!driver.findElement(By.className("invalid-feedback")).isDisplayed()) {
					throw new FormularioException(erro);
				} else {
					System.out.println("Toast sendo exibido");
					System.out.println(driver.findElement(By.className("invalid-feedback")).getText());
				}
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

	public void verificaErroCPFInvalido(String erro) throws FormularioException {
		if (!(driver.getPageSource().contains("invalid Brazilian individual taxpayer registry number (CPF)")
				|| driver.getPageSource().contains("O CPF informado é inválido."))) {
			
			throw new FormularioException("pagina nao contem exigencia de CPF");
		}
	}

	public void verificaErroObrigatorio(String erro) throws FormularioException {
		if (!driver.getPageSource().contains("Campo obrigatório.")) {
			throw new FormularioException(erro);
		}
	}

	public void inserirResponsavelPropioUsuarioServico() {
		if (driver.findElement(By.xpath(
				"(.//*[normalize-space(text()) and normalize-space(.)='Próprio usuário do serviço'])[1]/following::span[1]"))
				.getCssValue("background-color").equals("#eee")) {
			driver.findElement(By.xpath(
					"(.//*[normalize-space(text()) and normalize-space(.)='Próprio usuário do serviço'])[1]/following::span[1]"))
					.click();
		}
		driver.findElement(By.name("salvarResponsavel")).click();
	}

	public void confirmarCadastro() {

		driver.findElement(By.id("btnFinalizar")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".swal-button")));

		driver.findElement(By.cssSelector(".swal-button")).click();
	}

	public void clicarVoltar() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='voltar']")));
		driver.findElement(By.xpath("//button[@name='voltar']")).click();
	}

	public void excluirContato() {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Remover")));
		driver.findElement(By.linkText("Remover")).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[@class='swal-button swal-button--confirm']")));
		driver.findElement(By.xpath("//button[@class='swal-button swal-button--confirm']")).click();
	}

	public void inserirContatoDuplicadoPaciente(Paciente paciente) {
		adicionarNumero(paciente);
		adicionarNumero(paciente);
		driver.findElement(By.id("salvarContatoPaciente")).click();
	}

	public void adicionarNumero(Paciente paciente) {
		Pessoa pessoa = paciente.getPessoa();

		driver.findElement(By.id("tipoTelefone")).click();

		if (pessoa.getContatos().get(pessoa.getContatos().size() - 1).getTipoTelefone().name().equals("FIXO")) {
			new Select(driver.findElement(By.id("tipoTelefone"))).selectByValue("FIXO");
			driver.findElement(By.id("inputTelefone"))
					.sendKeys(pessoa.getContatos().get(pessoa.getContatos().size() - 1).getTelefone());
		} else {
			new Select(driver.findElement(By.id("tipoTelefone"))).selectByValue("MOVEL");
			WebElement inputTelefone = driver.findElement(By.id("inputTelefone"));
			inputTelefone.click();
			inputTelefone.clear();
			inputTelefone.sendKeys(pessoa.getContatos().get(pessoa.getContatos().size() - 1).getTelefone());
		}
		WebElement inputObservacao = driver.findElement(By.id("observacao"));
		inputObservacao.click();
		inputObservacao.clear();
		inputObservacao.sendKeys(pessoa.getContatos().get(pessoa.getContatos().size() - 1).getObservacao());

		driver.findElement(By.id("adicionarContatoPaciente")).click();
	}
}