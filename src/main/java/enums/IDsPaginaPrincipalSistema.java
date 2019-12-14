package enums;

public enum IDsPaginaPrincipalSistema {
	
	MENU_USUARIO_SERVICO{
		@Override
		public String getIdOrXpath() {
			// TODO Auto-generated method stub
			return "navbarDropdown";
		}
	},
	MENU_NOVO_USUARIO_SERVICO{
		@Override
		public String getIdOrXpath() {
			// TODO Auto-generated method stub
			return "Novo";
		}
	},
	MENU_LISTAR_USUARIO_SERVICO{
		@Override
		public String getIdOrXpath() {
			// TODO Auto-generated method stub
			return "Listar";
		}
	}
	;
	
	public abstract String getIdOrXpath();
}
