package implementacao;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import interfaces.JsExecutor;

public class JsExecutorIMPL implements JsExecutor {
	private JavascriptExecutor jse;
	
	public JsExecutorIMPL(WebDriver webdriver) {
		this.jse = (JavascriptExecutor) webdriver;
	}

	@Override
	public void setValueById(String id, String value) {
		// TODO Auto-generated method stub
		String script = String.format("document.getElementById('%s').value='%s';" , id, value);
		jse.executeScript(script);
	}

	@Override
	public void clickById(String id) {
		// TODO Auto-generated method stub
		String script = String.format("document.getElementById('%s').click;" , id);
		jse.executeScript(script);
	}

}
