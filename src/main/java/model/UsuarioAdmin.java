package model;

public class UsuarioAdmin {
	
	//TODO Vai se chamar paciente e vai copiar a classe pessoa do projeto do agendamento;]
	//TODO também trazer a classe pessoa
	
	String login;
	String senha;
	String CPF;
	
	int sexo;
	int isEstrangeiro;
	int estadoCivil;
	int isSocial;
	int isVinculoUFRN;
	int raca;
	
	public UsuarioAdmin(String login, String senha, String cpf) {
		this.login = login;
		this.senha = senha;
		this.CPF = cpf;
	}
	
	public int getSexo() {
		return sexo;
	}

	public void setSexo(int sexo) {
		this.sexo = sexo;
	}

	public int getIsEstrangeiro() {
		return isEstrangeiro;
	}

	public void setIsEstrangeiro(int isEstrangeiro) {
		this.isEstrangeiro = isEstrangeiro;
	}

	public int getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(int estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public int getIsSocial() {
		return isSocial;
	}

	public void setIsSocial(int isSocial) {
		this.isSocial = isSocial;
	}

	public int getIsVinculoUFRN() {
		return isVinculoUFRN;
	}

	public void setIsVinculoUFRN(int isVinculoUFRN) {
		this.isVinculoUFRN = isVinculoUFRN;
	}

	public int getRaca() {
		return raca;
	}

	public void setRaca(int raca) {
		this.raca = raca;
	}

	
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CPF == null) ? 0 : CPF.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioAdmin other = (UsuarioAdmin) obj;
		if (CPF == null) {
			if (other.CPF != null)
				return false;
		} else if (!CPF.equals(other.CPF))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

}
