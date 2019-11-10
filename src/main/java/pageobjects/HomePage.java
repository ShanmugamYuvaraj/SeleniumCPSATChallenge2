package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage {

    @FindBy(xpath = "//div[@class='dy-custom-slider']/div/div[@class='dy-slick-arrow dy-next-arrow slick-arrow']")
    private WebElement clickBannerSlider;

    public WebElement getClickBannerSlider() {
        return clickBannerSlider;
    }

    @FindBy(xpath = "//*[@role='presentation']")
    private List<WebElement> bannerSize;

    public List<WebElement> getBannerSize() {
        return bannerSize;
    }

    @FindBy(xpath = "//a[@title='MEN']")
    private WebElement menHeader;

    public WebElement getMenHeader() {
        return menHeader;
    }

    @FindBy(xpath = "//a[@title='MEN']/following-sibling::div/div[1]/ul[1]/li[6]/a")
    private WebElement MensFragrance;

    public WebElement getMensFragrance() {
        return MensFragrance;
    }

    @FindBy(xpath = "//a[@title='MEN']/following-sibling::div/div[1]/ul[1]/li[6]/div[1]/ul[1]/li[1]/div[1]/ul[1]/li/div/span/a")
    private List<WebElement> listOfAccessories;

    public List<WebElement> getListOfAccessories() {
        return listOfAccessories;
    }

    @FindBy(xpath = "//a[@title='MEN']/following-sibling::div/div[1]/ul[1]/li[6]/div[1]/ul[1]/li[1]/div[1]/ul[1]/li[4]/div[1]/ul[1]/li/a")
    private List<WebElement> listOfAccessoriesForMen;

    public List<WebElement> getListOfAccessoriesForMen() {
        return listOfAccessoriesForMen;
    }

    @FindBy(xpath = "//ul[@class='text-right']/li[1]/a")
    private WebElement clkAllStoresLink;

    public WebElement getClkAllStoresLink() {
        return clkAllStoresLink;
    }
}
