package interfaces;

public interface Element {
	public void click();
	public void clear();
	public void loadElementSelenium();
	public void loadElementSeleniumById();
	public void loadElementSeleniumByXPath();
	public void selectTextoVisivel(String texto);
	public void selectTextoByValue(String value);
	public Page getPage();
	public String getId();
	public String getText();
	public String getTag();
	public void setPage(Page page);
	public void setId(String id);
	public void setValue(String value);
	public void setValueByJs(String value);
}
