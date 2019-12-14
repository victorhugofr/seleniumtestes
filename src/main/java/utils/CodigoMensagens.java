package utils;

public enum CodigoMensagens {

	MSG_SALVAR_SUCESSO {
		@Override
		public String getDenominacao() {
			return "Cadastrado com sucesso!";
		}
	},
	MSG_CPF_INVALIDO {
		@Override
		public String getDenominacao() {
			return "CPF invalido.";
		}
	},
	MSG_CPF_CADASTRADO {
		@Override
		public String getDenominacao() {
			return "Já existe uma pessoa com o CPF informado!";
		}
	},
	MSG_EMAIL_INVALIDO {
		@Override
		public String getDenominacao() {
			return "Já existe um usuário com o email informado!";
		}
	},
	MSG_EMAIL_MALFORMATADO {
		@Override
		public String getDenominacao() {
			return "Formato de e-mail inválido. Por exemplo, naorespondersigsaude@imd.ufrn.br";
		}
	},
	MSG_EMAIL_ENVIADO {
		@Override
		public String getDenominacao() {
			return "Email enviado com sucesso. Siga as instruções para redefinir a senha";
		}
	},
	MSG_EMAIL_SUCESSO {
		@Override
		public String getDenominacao() {
			return "Email enviado com sucesso";
		}
	},
	MSG_EMAIL_FALHA {
		@Override
		public String getDenominacao() {
			return "Usuário não encontrado";
		}
	},
	MSG_OPERACAO_SUCESSO {
		@Override
		public String getDenominacao() {
			return "Operação realizada  com sucesso!";
		}
	},
	MSG_EDITAR_SUCESSO {
		@Override
		public String getDenominacao() {
			return "Editado com sucesso!";
		}
	},
	MSG_REMOVER_SUCESSO {
		@Override
		public String getDenominacao() {
			return "Removido com sucesso!";
		}
	},
	MSG_SENHA_NAOCORRESPONDE {
		@Override
		public String getDenominacao() {
			return "As senhas não se correspondem";
		}
	},
	MSG_SENHA_INCORRETA {
		@Override
		public String getDenominacao() {
			return "A senha informada está incorreta";
		}
	},
	MSG_DATA_INCORRETA {
		@Override
		public String getDenominacao() {
			return "A data final não pode ser anterior a data de inicio!";
		}
	},
	MSG_ERRO_CATEGORIA {
		@Override
		public String getDenominacao() {
			return "Registro não pode ser removido";
		}
	},
	MSG_ERRO_TIPOEDITAL {
		@Override
		public String getDenominacao() {
			return "Registro não pode ser removido";
		}
	},
	MSG_ERRO_EXCLUSAOINVALIDA {
		@Override
		public String getDenominacao() {
			return "O edital deve possuir pelo manos 1 arquivo!";
		}
	},
	MSG_ERRO_REMOVER {
		@Override
		public String getDenominacao() {
			return "Registro não pode ser removido, pois esta vinculado a outro registro.";
		}
	},
	MSG_CAMPO_OBRIGATORIO {
		@Override
		public String getDenominacao() {
			return "Campo obrigatório";
		}
	},
	MSG_IMAGEM_FORMATO_AVISO {
		@Override
		public String getDenominacao() {
			return "Tipo de arquivo inválido. Por favor, use os formatos .PNG ou .JPG.";
		}
	},
	MSG_IMAGEM_OBRIGATORIO_AVISO {
		@Override
		public String getDenominacao() {
			return "Campo imagem precisa ser preenchido.";
		}
	},
	MSG_ARQUIVO_FORMATOINVALIDO {
		@Override
		public String getDenominacao() {
			return "O tipo do termo é PDF";
		}
	},
	MSG_REGISTRO_CAMPOS_EMBRANCO {
		@Override
		public String getDenominacao() {
			return "Não é possível deixar os campos apenas com espaços em branco.";
		}
	},
	MSG_CONTATO_CADASTRADO {
		@Override
		public String getDenominacao() {
			return "Já existe um telefone de contato com esse número cadastrado para esta pessoa";
		}
	},
	MSG_CONTATO_SMS_CADASTRADO {
		@Override
		public String getDenominacao() {
			return "Já existe um telefone de contato de SMS para esta pessoa";
		}
	},
	MSG_CONTATO_WHATSAPP_CADASTRADO {
		@Override
		public String getDenominacao() {
			return "Já existe um telefone de contato de WhatsApp para esta pessoa";
		}
	},
	MSG_CONTATO_URGENCIA_CADASTRADO {
		@Override
		public String getDenominacao() {
			return "Já existe um telefone de contato de urgência para esta pessoa";
		}
	},
	MSG_CONTATO_OBRIGATORIO {
		@Override
		public String getDenominacao() {
			return "Um telefone de contato precisa ser adicionado";
		}

	},
	MSG_CONTATO_OBSERVACAO_GRANDE{
		@Override
		public String getDenominacao() {
			return "Observação deve ter no máximo 250 caracteres";
		}
	},
	MSG_CONTATO_VALIDADO {
		@Override
		public String getDenominacao() {
			return "Telefone deve ter quantidade mínima de caracteres";
		}
	},
	
	MSG_CONVENIO_TERMOCOMPROMISSO_OBRIGATORIO {
		@Override
		public String getDenominacao() {
			return "O termo de compromisso é obrigatório";
		}
	},
	MSG_CONVEIO_TERMOCOMPROMISSO_TIPOVALIDO {
		@Override
		public String getDenominacao() {
			return "O tipo do termo é PDF";
		}
	},
	MSG_CONVENIO_TERMOCOMPROMISSO_TAMANHOMAXIMO {
		@Override
		public String getDenominacao() {
			return "O tamanho máximo é 20MB";
		}
	},
	MSG_CONVENIO_PRAZOVIGENCIA_INVALIDO {
		@Override
		public String getDenominacao() {
			return "A data fim do prazo de vigência deve ser posterior a data inicial.";
		}
	},
	MSG_CONVENIO_PRAZOVIGENCIA_VAZIO {
		@Override
		public String getDenominacao() {
			return "As datas de início e fim do prazo de vigência devem ser preenchidos.";
		}
	},
	MSG_CONVENIO_ENDERECO_OBRIGATORIO {
		@Override
		public String getDenominacao() {
			return "O logradouro é obrigatório";
		}
	},

	MSG_PACIENTE_RESPONSAVEL_NECESSITACONTATO {
		@Override
		public String getDenominacao() {
			return "É necessário informar pelo menos um contato para o responsável";
		}
	},

	MSG_USUARIO_USERNAME_CADASTRADO {
		@Override
		public String getDenominacao() {
			return "O login informado já está sendo utilizado";
		}

	},
	MSG_PESSOA_EMAIL_CADASTRADO {
		@Override
		public String getDenominacao() {
			return "O email informado já está sendo utilizado";
		}
	},

	MSG_PROCEDIMENTO_DATAVIGENCIA_OBRIGATORIO{
		@Override
		public String getDenominacao() {
			return "Data de Vigência é obrigatória para a origem SIGTAP.";
		}
	},
	
	MSG_DENOMINACAO_EXISTENTE {
		@Override
		public String getDenominacao() {
			return "A denominação informada já está sendo utilizada";
		}
	},
	MSG_DENOMINACAO_VALIDA{
		@Override
		public String getDenominacao() {
			return "O campo denominação não pode números ou caractéres especiais.";
		}
	},
	MSG_CODIGO_EXISTENTE {
		@Override
		public String getDenominacao() {
			return "O código informado já está sendo utilizada";
		}
	},
	MSG_CODIGO_VALIDO{
		@Override
		public String getDenominacao() {
			return "O código informado deve conter apenas números.";
		}
	},
	MSG_CODIGO_INCOMPLETO {
		@Override
		public String getDenominacao() {
			return null;
		}
	},
	MSG_CAPACIDADE_ZERO {
		@Override
		public String getDenominacao() {
			return "Capacidade deve ser maior do que zero.";
		}
	},
	MSG_PACIENTE_NECESSITARESPONSAVEL {
		@Override
		public String getDenominacao() {
			return "É necessário informar pelo menos um responsável, pois a idade do paciente é menor que 18 anos.";
		}
	},
	MSG_PACIENTE_SUGESTAORESPONSAVEL {
		@Override
		public String getDenominacao() {
			return "O paciente possui idade superior a 65 anos. É recomendado informar um responsável.";
		}
	},
	MSG_PACIENTE_EMANCIPADOMAIOR {
		@Override
		public String getDenominacao() {
			return "Apenas menores de idade podem ser emancipados.";
		}
	},
	MSG_PACIENTE_EMANCIPADOMENORLIMITE {
		@Override
		public String getDenominacao() {
			return null;
		}
	},
	MSG_PACIENTE_NOMESOCIALNECESSARIO {
		@Override
		public String getDenominacao() {
			return "É necessário informar o nome social do paciente, caso ele deseje que este seja usado";
		}
	},
	MSG_PACIENTE_GENERONECESSARIO {
		@Override
		public String getDenominacao() {
			return "O paciente optou por informar o gênero, portanto essa informação não pode ser vazio ou nulo.";
		}
	},
	MSG_PACIENTE_PASSAPORTENECESSARIO {
		@Override
		public String getDenominacao() {
			return "Necessario informar o passaporte do paciente.";
		}
	},
	MSG_PACIENTE_NASCIMENTOVALIDO {
		@Override
		public String getDenominacao() {
			return "Data de nascimento deve ser inferior a hoje.";
		}
	},
	MSG_PACIENTE_NOMEVALIDO {
		@Override
		public String getDenominacao() {
			return "Nome não pode ter números ou caracteres especiais.";
		}
	},
	MSG_PACIENTE_HISTORICO_INCOMPLETO {
		@Override
		public String getDenominacao() {
			return "Usuário com cadastro incompleto no sistema, deseja continuar de onde parou?";
		}
	},
	MSG_PACIENTE_HOMONIMO {
		@Override
		public String getDenominacao() {
			return "Já existe um usuário de serviço cadastrado com este nome e esta data de nascimento.";
		}
	},
	MSG_PACIENTE_NOMEMAE {
		@Override
		public String getDenominacao() {
			return "Nome da mãe deve ser preenchido caso seja conhecido.";
		}
	},
	MSG_PACIENTE_NOMEPAI {
		@Override
		public String getDenominacao() {
			return null;
		}
	},
	MSG_PACIENTE_SEXO {
		@Override
		public String getDenominacao() {
			return "O sexo do paciente deve ser informado.";
		}
	},
	MSG_PACIENTE_DEFICIENCIAVALIDA {
		@Override
		public String getDenominacao() {
			return "O campo deficiência não pode números ou caractéres especiais.";
		}
	},
	MSG_PACIENTE_RGVALIDO {
		@Override
		public String getDenominacao() {
			return "O RG deve conter apenas números.";
		}
	},

	MSG_API_PESSOA_NAOENCONTRADO {
		@Override
		public String getDenominacao() {
			return "Não foi encontrado usuário referente ao CPF informado";
		}
	},
	MSG_API_ENDERECO_NAOENCONTRADO {
		@Override
		public String getDenominacao() {
			return "Não foi encontrado endereço referente ao CEP informado";
		}
	},
	MSG_API_VINCULO_NAOENCONTRADO {
		@Override
		public String getDenominacao() {
			return "Núo foi encontrado vínculo referente ao CPF informado";
		}
	},
	MSG_API_VINCULO_EXCEDENTES {
		@Override
		public String getDenominacao() {
			return "O usuário possui mais de um vínculo";
		}
	},

	MSG_FORM_CONSULTA_CAMPOSVAZIOS {
		@Override
		public String getDenominacao() {
			return "É necessário preencher pelo menos um campo do formulário para consulta";
		}
	},
	MSG_OFERTA_SERVICO_DATA_TERMINO {
		@Override
		public String getDenominacao() {
			return "A data de Termino não pode ser menor que a data de Início";
		}
	},
	MSG_OFERTA_SERVICO_DATAS {
		@Override
		public String getDenominacao() {
			return "As datas informadas tem que ser superior a data de hoje";
		}
	},
	MSG_OFERTA_SERVICO_INATIVAR {
		@Override
		public String getDenominacao() {
			return "Oferta de Serviço inativada com sucesso";
		}
	},
	MSG_OFERTA_SERVICO_SERVICO {
		@Override
		public String getDenominacao() {
			return "Selecione um Serviço para a Oferta de Serviço";
		}
	},
	MSG_OFERTA_SERVICO_PROCEDIMENTOS_VAZIO {
		@Override
		public String getDenominacao() {
			return "É necessário escolher pelo menos um procedimento.";
		}
	},
	MSG_OFERTA_SERVICO_ESPECIALIDADE_SEM_PROFISSIONAL {
		@Override
		public String getDenominacao() {
			return "Especialidade da Oferta de Serviço ofertada não tem Profissional de Saúde vinculado";
		}
	},
	
	MSG_OFERTA_SERVICO_ESPECIALIDADE {
		@Override
		public String getDenominacao() {
			return "Selecione uma Especialidade para a Oferta de Serviço";
		}
	},
	
	MSG_ESPECIALIDADE_SIGLA {
		@Override
		public String getDenominacao() {
			return "";
		}
	},
	MSG_UF_INVALIDO {
		@Override
		public String getDenominacao() {
			return "A Unidade Federativa selecionada é inválida.";
		}
	},
	MSG_MUNICIPIO_INVALIDO {
		@Override
		public String getDenominacao() {
			return "O Município selecionado é inválido.";
		}
	},
	MSG_BAIRRO_INVALIDO {
		@Override
		public String getDenominacao() {
			return null;
		}
	},
	MSG_LOGRADOURO_INVALIDO {
		@Override
		public String getDenominacao() {
			return null;
		}
	},
	
	MSG_PROFISSIONAL_SAUDE_NOME_OBRIGATORIO{
		@Override
		public String getDenominacao() {
			return "Nome é obrigatório";
		}
	},
	MSG_PROFISSIONAL_SAUDE_CPF_OBRIGATORIO{
		@Override
		public String getDenominacao() {
			return "CPF é obrigatório";
		}
	},
	MSG_PROFISSIONAL_SAUDE_ESPECIALIDADE_OBRIGATORIO{
		@Override
		public String getDenominacao() {
			return "Especialidade é obrigatório";
		}
	},
	MSG_PROFISSIONAL_SAUDE_UNIDADE_OBRIGATORIO{
		@Override
		public String getDenominacao() {
			return "Unidade é obrigatório";
		}
	},
	MSG_PROFISSIONAL_SAUDE_EMAIL_OBRIGATORIO{
		@Override
		public String getDenominacao() {
			return "E-mail é obrigatório";
		}
	},
	MSG_PROFISSIONAL_SAUDE_LOGIN_OBRIGATORIO{
		@Override
		public String getDenominacao() {
			return "Login é obrigatório";
		}
	},
	MSG_PROFISSIONAL_SAUDE_IDENTIDADE_PROFISSIONAL_OBRIGATORIO{
		@Override
		public String getDenominacao() {
			return "Identidade profissional é obrigatório";
		}
	},
	MSG_PROFISSIONAL_SAUDE_LOGIN_CADASTRADO{
		@Override
		public String getDenominacao() {
			return "Login já cadastrado";
		}
	},
	MSG_PROFISSIONAL_SAUDE_IDENTIDADE_PROFISSIONAL_CADASTRADO {
		@Override
		public String getDenominacao() {
			return "Identidade profissional já cadastrada";
		}
	},
	MSG_ESCALA_CONFLITANTE {
		@Override
		public String getDenominacao() {
			return "Existe escala conflitante para esse Profissional de Saúde";
		}
	},
	MSG_ESCALA_FORA_DA_OFERTA_DE_SERVICO {
		@Override
		public String getDenominacao() {
			return "Escala fora do intervalo da Oferta de Servico";
		}
	},
	MSG_ESCALA_JUSTIFICATIVA_OBRIGATORIA {
		@Override
		public String getDenominacao() {
			return "Por favor, preencha o campo de justificativa";
		}
	},
	MSG_ESPECIALIDADE_SEM_PROFISSIONAL {
		@Override
		public String getDenominacao() {
			return null;
		}
	},
	MSG_JUSTIFICATIVAINATIVACAO_VAZIA {
		@Override
		public String getDenominacao() {
			return "A justificativa de inativação não pode estar vazia";
		}
	},
	MSG_USUARIO_SENHA_ALTERAR_OBRIGATORIA {
		@Override
		public String getDenominacao() {
			return "Para alterar a senha é preciso preencher o campo senha e de confirmar senha.";
		}
	},
	MSG_USUARIO_SENHA_NAO_IGUAIS {
		@Override
		public String getDenominacao() {
			return "Senhas informadas não correspondem";
		}
	},
	
	MSG_USUARIO_SENHA_TAMANHO {
		@Override
		public String getDenominacao() {
			return "Tamanho minimo da senha é de 8 caracteres";
		}
	},
	MSG_ACESSO_API_UFRN_NEGADO {
		@Override
		public String getDenominacao() {
			return "Não foi possível estabelecer comunicação com o serviço da UFRN";
		}
	},
	
	 MSG_SIGLA_INVALIDA{
        @Override
        public String getDenominacao() {
            return "Sigla não pode ter números ou caracteres especiais.";
        }
    },

	MSG_WHITELABEL_ERROR_PAGE{
		@Override
		public String getDenominacao() {
			return "Whitelabel Error Page";
		}
	};
	
	public abstract String getDenominacao();

}
