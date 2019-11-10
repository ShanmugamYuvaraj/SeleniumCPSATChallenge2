package commonutils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pageobjects.HomePage;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverUtility  {


    public void implicitlyWait(){
        BaseClass.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * Used to navigate URL in the browser window
     * @param url
     * @return
     */
    public boolean navigateURL(String url){
        Boolean bFlag=false;
        try{
            BaseClass.driver.get(url);
            bFlag=true;
        }catch(Exception e){
            System.out.println("Exception accured,details - "+e.getStackTrace());
        }
        return bFlag;
    }

    /**
     * Used to maximize he current window
     */
    public void maximizeWindow(){
        BaseClass.driver.manage().window().maximize();
    }

    /**
     * USed to get the title of the current page
     * @return
     */
    public String getTitle(){
        String title = null;
        try{
            title = BaseClass.driver.getTitle();
        }catch(NullPointerException n){
            System.out.println("null pointer exception accured, details -"+n.getStackTrace());
        }
        return title;
    }

    /**
     * used to move the mouse on desired element
     * @param overElement
     */
    public void mouseOverOperation(By overElement){
        Actions action = new Actions(BaseClass.driver);
        WebElement element = BaseClass.driver.findElement(overElement);
        action.moveToElement(element).perform();
    }

    /**
     * click the expected element
     * @param clickableElement
     */
    public boolean click(By clickableElement){
        boolean bFlag=false;
        try{
            BaseClass.driver.findElement(clickableElement).click();
            bFlag=true;
        }catch(Exception e){
            System.out.println("Exception accured, details -"+e.getStackTrace());
        }
        return bFlag;
    }

    /**
     * Used to get the visible text present in webpage
     * @param webElement
     * @return
     */
    public String getText(By webElement){
        return BaseClass.driver.findElement(webElement).getText();
    }

    /**
     * Used to take a screen shots and save the file to mentioned location
     * @param fileWithPath
     */
    public boolean takeSnapShot(String fileWithPath){
        boolean bFlag=false;
        try{
            TakesScreenshot takesScreenshot = ((TakesScreenshot) BaseClass.driver);
            File SrcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File(MessageFormat.format(Constants.screenShotLocation,fileWithPath));
            FileUtils.copyFile(SrcFile, DestFile);
            bFlag=true;
        }catch(Exception e){
            System.out.println("Exception accured, details -"+e.getStackTrace());
        }
        return bFlag;
    }

    public List<WebElement> getListWebElement(By element){
        return BaseClass.driver.findElements(element);
    }

    /**
     * used to close the browser
     */
    public void closeBrowser(){
        BaseClass.driver.close();
    }

    public void setFieldValue(By element,String value){
        try{
            BaseClass.driver.findElement(element).sendKeys(value);
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }

    }

    public void moveToWebElement(WebElement webElement,WebElement webElement1) throws InterruptedException {
        Actions actions=new Actions(BaseClass.driver);
        actions.moveToElement(webElement).perform();
        Thread.sleep(1000);
        actions.moveToElement(webElement1).perform();
    }
    public void moveToWebElement(WebElement webElement) {
        Actions actions=new Actions(BaseClass.driver);
        actions.moveToElement(webElement).perform();
    }
    public List<WebElement> getDropDownValues(WebElement webElement){
        Select select=new Select(webElement);
        return select.getOptions();
    }

    public List<String> getListOfText(By xpath){
        List<String> data=new ArrayList<String>();
        long size = BaseClass.driver.findElements(xpath).size();
        for (int i=0;i<=size-1;i++){
            data.add(BaseClass.driver.findElements(xpath).get(i).getText());
        }
        return data;
    }

    public List<String> getListOfTextUsingGetAttribute(By xpath){
        List<String> data=new ArrayList<String>();
        long size = BaseClass.driver.findElements(xpath).size();
        for (int i=0;i<=size-1;i++){
            data.add(BaseClass.driver.findElements(xpath).get(i).getAttribute("title"));
        }
        return data;
    }



}
