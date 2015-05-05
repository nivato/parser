package parser.core.web;

import org.openqa.selenium.WebDriver;

public abstract class Component<T extends Component<T>> {

	protected WebDriver driver;

	public Component(WebDriver driver) {
		this.driver = driver;
	}

	public abstract boolean isAvailable();

	public T waitUntilAvailable() {
		return new Wait<T>().forComponent((T) this).toBeAvailable();
	}

}
