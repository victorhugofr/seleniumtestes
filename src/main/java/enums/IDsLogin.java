package enums;

public enum IDsLogin {
	ENTRAR{
		@Override
		public String getIdOrXpath() {
			// TODO Auto-generated method stub
			return "/html/body/div/div/div/div/div[2]/div/div/form/input[2]";
		}
	},
	
	LOGIN{
		@Override
		public String getIdOrXpath() {
			// TODO Auto-generated method stub
			return "login-username";
		}
	},
	
	PASSWORD{
		@Override
		public String getIdOrXpath() {
			// TODO Auto-generated method stub
			return "login-password";
		}
	},
	LOGAR{
		@Override
		public String getIdOrXpath() {
			// TODO Auto-generated method stub
			return "(.//*[normalize-space(text()) and normalize-space(.)='Senha'])[1]/following::input[1]";
		}
	};
	public abstract String getIdOrXpath();
}
