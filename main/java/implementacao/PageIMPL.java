package implementacao;

import interfaces.JsExecutor;
import interfaces.Page;
import interfaces.Selenium;

public class PageIMPL implements Page {
	
	protected String url;
	private Selenium selenium;
	private JsExecutor js;
	
	public PageIMPL() {
		// TODO Auto-generated constructor stub
	}
	
	public PageIMPL(Selenium selenium) {
		this.selenium = selenium;
		this.js = new JsExecutorIMPL(this.selenium.getDriver());
	}
	
	public PageIMPL(String url, Selenium selenium) {
		this.url = url;
		this.selenium = selenium;
		this.js = new JsExecutorIMPL(this.selenium.getDriver());
	}
	

	@Override
	public void open() {
		this.selenium.getDriver().get(url);
		selenium.getDriver().manage().window().maximize();

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		this.selenium.closeDriver();
	}

	@Override
	public Selenium getSelenium() {
		// TODO Auto-generated method stub
		return this.selenium;
	}

	@Override
	public JsExecutor getJsExecutor() {
		// TODO Auto-generated method stub
		return js;
	}

}
