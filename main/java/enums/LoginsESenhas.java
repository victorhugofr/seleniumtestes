package enums;

public enum LoginsESenhas {
	
	SENHA_LOGIN_TESTES{
		@Override
		public String getSenha() {
			// TODO Auto-generated method stub
			return "admin12345678";
		}
	},
	SENHA_LOGIN_SUSTENTACAO(){
		@Override
		public String getSenha() {
			// TODO Auto-generated method stub
			return null;
		}
	};

	public abstract String getSenha();
}
