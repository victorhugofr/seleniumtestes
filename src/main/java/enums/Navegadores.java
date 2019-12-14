package enums;

public enum Navegadores {
	
	PADRAO{
		@Override
		public String getNavegador() {
			// TODO Auto-generated method stub
			return "firefox";
		}
	},
	
	FIREFOX{
		@Override
		public String getNavegador() {
			// TODO Auto-generated method stub
			return "firefox";
		}
	},
	CHROME{
		@Override
		public String getNavegador() {
			// TODO Auto-generated method stub
			return "chrome";
		}
	}, IE{
		@Override
		public String getNavegador() {
			// TODO Auto-generated method stub
			return "internet-explorer";
		}
	}, EDGE{
		@Override
		public String getNavegador() {
			// TODO Auto-generated method stub
			return "edge";
		}
	},
	PHANTON_JS{
		@Override
		public String getNavegador() {
			// TODO Auto-generated method stub
			return "phantonjs";
		}
	};
	
	public abstract String getNavegador();
	

}
