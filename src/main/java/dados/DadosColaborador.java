package dados;

import br.ufrn.imd.sigsaude.dominio.Colaborador;
import br.ufrn.imd.sigsaude.dominio.Pessoa;
import br.ufrn.imd.sigsaude.dominio.TipoColaborador;
import br.ufrn.imd.sigsaude.dominio.Usuario;

public class DadosColaborador {

	public static Colaborador gerarDadosColaborador() {
		Colaborador colaborador = new Colaborador();
		Usuario usuario = new Usuario();
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(false, true);
	
		usuario.setPessoa(pessoa);
		colaborador.setPessoa(pessoa);
		
		int tipoColaborador = (int) Math.round(Math.random() * 5);
		
		if (tipoColaborador == 1) {
			TipoColaborador tipoDoColaborador = new TipoColaborador();
			tipoDoColaborador.setDenominacao("ALUNO COLABORADOR");
			tipoDoColaborador.setId(7);
			colaborador.setTipoColaborador(tipoDoColaborador);
		} else if (tipoColaborador == 2) {
			TipoColaborador tipoDoColaborador = new TipoColaborador();
			tipoDoColaborador.setDenominacao("BOLSISTA DE APOIO TÉCNICO");
			tipoDoColaborador.setId(1);
			colaborador.setTipoColaborador(tipoDoColaborador);
		} else if (tipoColaborador == 3) {
			TipoColaborador tipoDoColaborador = new TipoColaborador();
			tipoDoColaborador.setDenominacao("TÉCNICO-ADMINISTRATIVO");
			tipoDoColaborador.setId(4);
			colaborador.setTipoColaborador(tipoDoColaborador);
		} else {
			TipoColaborador tipoDoColaborador = new TipoColaborador();
			tipoDoColaborador.setDenominacao("TÉCNICO-ADMINISTRATIVO EXTERNO");
			tipoDoColaborador.setId(6);
			colaborador.setTipoColaborador(tipoDoColaborador);
		}
		
		usuario.setLogin(GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 3000)) + GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 1000)));
		colaborador.setUsuario(usuario);
		
		return colaborador;
	}
	
	public static Colaborador gerarDadosColaboradorComNomeVazio() {
		Colaborador colaborador = gerarDadosColaborador();
		Pessoa pessoa = DadosPessoa.gerarDadosPessoaComNomeVazio(false, true);
		
		colaborador.getUsuario().setPessoa(pessoa);
		colaborador.setPessoa(pessoa);
		
		return colaborador;
	}
	
	public static Colaborador gerarDadosColaboradorComNomeInvalido() {
		Colaborador colaborador = gerarDadosColaborador();
		Pessoa pessoa = DadosPessoa.gerarDadosPessoaComNomeInvalido(false, true);
		
		colaborador.getUsuario().setPessoa(pessoa);
		colaborador.setPessoa(pessoa);

		return colaborador;
	}

	public static Colaborador gerarDadosColaboradorInformandoCPF(String cpf) {
		Colaborador colaborador = gerarDadosColaborador();
		Pessoa pessoa = DadosPessoa.gerarDadosPessoa(false, true);
		
		pessoa.setCpf(cpf);
		colaborador.getUsuario().setPessoa(pessoa);
		colaborador.setPessoa(pessoa);
		
		return colaborador;
	}
	
	public static Colaborador gerarDadosColaboradorComEmailVazio() {
		Colaborador colaborador = gerarDadosColaborador();
		Pessoa pessoa = DadosPessoa.gerarDadosPessoaComEmailVazio(false, true);
		
		colaborador.getUsuario().setPessoa(pessoa);
		colaborador.setPessoa(pessoa);
		
		return colaborador;
	}
	
	public static Colaborador gerarDadosColaboradorComEmailInvalido() {
		Colaborador colaborador = gerarDadosColaborador();
		Pessoa pessoa = DadosPessoa.gerarDadosPessoaComEmailInvalido(false, true);
		
		colaborador.getUsuario().setPessoa(pessoa);
		colaborador.setPessoa(pessoa);
		
		return colaborador;
	}

	public static Colaborador gerarDadosColaboradorComTelefoneVazio() {
		Colaborador colaborador = gerarDadosColaborador();
		Pessoa pessoa = DadosPessoa.gerarDadosPessoaComContatoVazio(false, true);
		
		colaborador.getUsuario().setPessoa(pessoa);
		colaborador.setPessoa(pessoa);
		
		return colaborador;
	}
	
	public static Colaborador gerarDadosColaboradorComTelefoneInvalido() {
		Colaborador colaborador = gerarDadosColaborador();
		Pessoa pessoa = DadosPessoa.gerarDadosPessoaComContatoInvalido(false, true);
		
		colaborador.getUsuario().setPessoa(pessoa);
		colaborador.setPessoa(pessoa);
		
		return colaborador;
	}
	
	public static Colaborador gerarDadosColaboradorSemTipo() {
		Colaborador colaborador = gerarDadosColaborador();
		colaborador.setTipoColaborador(null);
		
		return colaborador;
	}
	
}
