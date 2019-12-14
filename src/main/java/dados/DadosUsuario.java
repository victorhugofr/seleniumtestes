package dados;

import model.UsuarioAdmin;
import br.ufrn.imd.sigsaude.dominio.*;

public class DadosUsuario {

	public static UsuarioAdmin gerarDadosUsuarioAdmin() {
		
		UsuarioAdmin usuario = new UsuarioAdmin("admin", "12345678", gerarNumeroDeDocumentos.geraCPF());
		
		return usuario;
		
	}
	
	public static Usuario gerarDadosUsuario() {
		br.ufrn.imd.sigsaude.dominio.Usuario usuario = new br.ufrn.imd.sigsaude.dominio.Usuario();
		usuario.setLogin("userLogin" + GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 4000)));
		usuario.setPessoa(DadosPessoa.gerarDadosPessoa(false, true));
		usuario.setSenha("userSenha" + GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 4000)));
		return usuario;
	}

}
