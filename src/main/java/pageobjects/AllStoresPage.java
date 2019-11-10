package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AllStoresPage {

    @FindBy(xpath = "//select[@id='city-name']")
    private WebElement findStoresDropDown;

    public WebElement getFindStoresDropDown() {
        return findStoresDropDown;
    }
}
