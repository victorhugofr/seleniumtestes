package enums;

public enum IDsUsuarioDoServico {
	
	INPUT_NOME{
		@Override
		public String getId() {
			// TODO Auto-generated method stub
			return "inputNome";
		}
		@Override
		public String getXpath() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	INPUT_DATA_NASCIMENTO{
		@Override
		public String getId() {
			// TODO Auto-generated method stub
			return "inputDate";
		}
		@Override
		public String getXpath() {
			// TODO Auto-generated method stub
			return "(.//*[normalize-space(text()) and normalize-space(.)='*'])[2]/following::input[2]";
		}
	},
	INPUT_EMAIL{
		@Override
		public String getId() {
			// TODO Auto-generated method stub
			return "inputEmail";	
		}
		@Override
		public String getXpath() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	;
	public abstract String getId();
	public abstract String getXpath();
	

}
