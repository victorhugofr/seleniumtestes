package implementacao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import interfaces.Element;
import interfaces.Page;

public class ElementIMPL implements Element {

	private Page page;
	private WebElement eSelenium;
	private String id;
	private String xPath;
	
	public ElementIMPL(Page page, String id) {
		this.page = page;
		this.id = id;
	}
	
	public ElementIMPL(Page page, String id, String xPath) {
		this.page = page;
		this.id = id;
		this.xPath = xPath;
	}
	
	@Override
	public void click() {

		getElementSelenium().click();

	}
	
	@Override
	public void clear() {

		getElementSelenium().clear();
	}

	@Override
	public void loadElementSelenium() {

		if (id != null && !id.isEmpty()) {
			loadElementSeleniumById();
		} else if (xPath != null && !xPath.isEmpty()) {
			loadElementSeleniumByXPath();
		}
	}

	@Override
	public void loadElementSeleniumById() {

		this.eSelenium = page.getSelenium().getDriver().findElement(By.id(id));
	}

	@Override
	public void loadElementSeleniumByXPath() {
		this.eSelenium = this.page.getSelenium().getDriver().findElement(By.xpath(xPath));
		
	}

	@Override
	public void selectTextoVisivel(String texto) {

		new Select(getElementSelenium()).selectByVisibleText(texto);
	}
	
	@Override
	public void selectTextoByValue(String value) {

		new Select(getElementSelenium()).selectByValue(value);
	}

	@Override
	public Page getPage() {

		return page;
	}

	@Override
	public String getId() {

		return id;
	}

	@Override
	public String getText() {

		return getElementSelenium().getText();
	}

	@Override
	public String getTag() {

		return getElementSelenium().getTagName();
	}

	@Override
	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void setValue(String value) {
		getElementSelenium().sendKeys(value);
	}

	@Override
	public void setValueByJs(String value) {
		page.getJsExecutor().setValueById(id, value);
	}
	
	private WebElement getElementSelenium() {
		if(this.eSelenium != null) {
			return this.eSelenium;
		} else {
			loadElementSelenium();
			return this.eSelenium;
		}
	}

}
