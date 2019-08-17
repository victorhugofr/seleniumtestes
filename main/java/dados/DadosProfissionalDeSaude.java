package dados;

import br.ufrn.imd.sigsaude.dominio.ProfissionalSaude;

/**
 * Classe criada para gerar os dados de um profissional de saude
 * durante a etapa do cadastro
 * @author Pedro H B Cavalcante <pedrohbcavalcante@outlook.com>
 */
public class DadosProfissionalDeSaude {

    public static ProfissionalSaude gerarDadosProfissionalDeSaude(){
        ProfissionalSaude profissionalSaude = new ProfissionalSaude();
        
        profissionalSaude.setEspecialidade(DadosEspecialidade.gerarDadosEspecialidade());
        profissionalSaude.setIdentidadeProfissional(GerarNumeroRomano.toRoman((int) Math.round(Math.random() * 1000)) + Math.round(Math.random() * 1000));
        profissionalSaude.setColaborador(DadosColaborador.gerarDadosColaborador());
        
        return profissionalSaude;
    }

    public static ProfissionalSaude gerarDadosProfissionalDeSaudeComCPFVazio() {
        ProfissionalSaude profissionalSaude = gerarDadosProfissionalDeSaude();
        
        profissionalSaude.getColaborador().getUsuario().getPessoa().setCpf("");

        return profissionalSaude;
    }

    public static ProfissionalSaude gerarDadosProfissionalDeSaudeComCPFInvalido() {
        ProfissionalSaude profissionalSaude = gerarDadosProfissionalDeSaude();
        
        profissionalSaude.getColaborador().getUsuario().getPessoa().setCpf("00000000000");

        return profissionalSaude;
    }

    public static ProfissionalSaude gerarDadosProfissionalDeSaudeComNomeVazio() {
        ProfissionalSaude profissionalSaude = gerarDadosProfissionalDeSaude();
        
        profissionalSaude.getColaborador().getUsuario().getPessoa().setNome("");

        return profissionalSaude;
    }
    
    public static ProfissionalSaude gerarDadosProfissionalDeSaudeComNomeInvalido() {
        ProfissionalSaude profissionalSaude = gerarDadosProfissionalDeSaude();
        
        profissionalSaude.getColaborador().getUsuario().getPessoa().setNome(GerarStringInvalida.gerarStringAleatoria());

        return profissionalSaude;
    }

    public static ProfissionalSaude gerarDadosProfissionalDeSaudeComIdentidadeProfissionalVazia() {
        ProfissionalSaude profissionalSaude = gerarDadosProfissionalDeSaude();
        
        profissionalSaude.setIdentidadeProfissional("");
        
        return profissionalSaude;
    }
    
    public static ProfissionalSaude gerarDadosProfissionalDeSaudeComIdentidadeProfissionalInvalida() {
        ProfissionalSaude profissionalSaude = gerarDadosProfissionalDeSaude();
        
        profissionalSaude.setIdentidadeProfissional(GerarStringInvalida.gerarStringAleatoria());
        
        return profissionalSaude;
    }

    public static ProfissionalSaude gerarDadosProfissionalDeSaudeComEmailVazio() {
        ProfissionalSaude profissionalSaude = gerarDadosProfissionalDeSaude();
        
        profissionalSaude.getColaborador().getUsuario().getPessoa().setEmail("");
        
        return profissionalSaude;
    }

    
    public static ProfissionalSaude gerarDadosProfissionalDeSaudeComEmailInvalido() {
        ProfissionalSaude profissionalSaude = gerarDadosProfissionalDeSaude();
        
        profissionalSaude.getColaborador().getUsuario().getPessoa().setEmail(GerarStringInvalida.gerarStringAleatoria());
        
        return profissionalSaude;
    }

    public static ProfissionalSaude gerarDadosProfissionalDeSaudeComLoginVazio() {
        ProfissionalSaude profissionalSaude = gerarDadosProfissionalDeSaude();
        
        profissionalSaude.getColaborador().getUsuario().setLogin("");

        return profissionalSaude;
    }
}