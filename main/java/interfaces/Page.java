package interfaces;

public interface Page {
	public void open();
	public void close();
	public Selenium getSelenium();
	public JsExecutor getJsExecutor();
}
