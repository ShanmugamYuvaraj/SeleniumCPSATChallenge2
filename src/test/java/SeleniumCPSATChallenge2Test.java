import commonutils.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.AllStoresPage;
import pageobjects.HomePage;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

public class SeleniumCPSATChallenge2Test {
    Helper helper=new Helper();
    SoftAssert softAssert=new SoftAssert();
    WebDriverUtility webDriverUtility=new WebDriverUtility();
    public static HomePage homePage;
    public static AllStoresPage allStoresPage;

    @AfterTest
    public void closeBrowser(){
        webDriverUtility.closeBrowser();
    }

    @org.junit.Test
    public void clkCertifications_mouseoverCPCCT_takeScreenshot()  {
        BaseClass.launchBrowser(Constants.chromeBrowser);
        webDriverUtility.implicitlyWait();
        webDriverUtility.maximizeWindow();
        webDriverUtility.navigateURL(Constants.url1);

        webDriverUtility.click(By.xpath(XpathConstants.ClkCertificationsLink));
        List<WebElement> numberOfCertificationsIcons =webDriverUtility.getListWebElement(By.xpath(XpathConstants.getImagePresentInPage));
        Assert.assertEquals(numberOfCertificationsIcons.size(),12l);

        List brokenLinks = helper.isUrlBroken(numberOfCertificationsIcons);
        if(brokenLinks.size()>0){
            softAssert.assertEquals(brokenLinks.size(),0,"The website have broken links");
            System.out.println("The Following links are broken in web page");
            for(int i=0;i<=brokenLinks.size()-1;i++){
                System.out.println(brokenLinks.get(i));
            }
        }
        softAssert.assertTrue(webDriverUtility.takeSnapShot("screenShot_"+new Date()));
        //TODO mouse over on CP-CCT is not working
        webDriverUtility.mouseOverOperation(By.xpath(XpathConstants.cpcctImage));
        softAssert.assertTrue(webDriverUtility.takeSnapShot("screenShot_CP-CCT_"+new Date()));
        softAssert.assertAll();
        webDriverUtility.closeBrowser();

    }

    @Test
    public void printMinimumNumber(){
        BaseClass.launchBrowser(Constants.chromeBrowser);
        webDriverUtility.implicitlyWait();
        webDriverUtility.maximizeWindow();
        webDriverUtility.navigateURL(Constants.url2);
        org.testng.Assert.assertEquals(webDriverUtility.getTitle(),Constants.nscHomePageTitle,"title of the home page is not matching");

        List<WebElement> advancesValues = webDriverUtility.getListWebElement(By.xpath(XpathConstants.advancesTab));
        List<WebElement> declinesValues = webDriverUtility.getListWebElement(By.xpath(XpathConstants.declinesTab));
        List<WebElement> unchangedValues = webDriverUtility.getListWebElement(By.xpath(XpathConstants.unchangedTab));

        helper.getMinimumNumber(advancesValues,declinesValues,unchangedValues);
    }

      @Test
      public void enterCompanyNameAndDisplayRequiredDetailsUsingForeFox(){
          BaseClass.launchBrowser(Constants.firefoxBrowser);
          webDriverUtility.implicitlyWait();
          webDriverUtility.maximizeWindow();
          webDriverUtility.navigateURL(Constants.url2);
          org.testng.Assert.assertEquals(webDriverUtility.getTitle(),Constants.nscHomePageTitle,"title of the home page is not matching");
          webDriverUtility.setFieldValue(By.xpath(XpathConstants.companyNameTextBox),Constants.companyName);
          webDriverUtility.click(By.xpath(XpathConstants.clkAllSymbolLink));
          String companyName = webDriverUtility.getText(By.id(XpathConstants.companyTextBoxId));
          Assert.assertTrue(companyName.contains(Constants.companyName),"company name is not matching ");
          softAssert.assertTrue(webDriverUtility.takeSnapShot("screenShot_TC_3_"+new Date()));
          String faceValue = webDriverUtility.getText(By.id(XpathConstants.faceValueId));
          System.out.println(MessageFormat.format("{0} company Face Value is  - {1}",Constants.companyName,faceValue));
          String weekHigh52Value = webDriverUtility.getText(By.id(XpathConstants.high52Id));
          System.out.println(MessageFormat.format("{0} company 52 week high is  - {1}",Constants.companyName,weekHigh52Value));
          String weekLow52Value = webDriverUtility.getText(By.id(XpathConstants.low52Id));
          System.out.println(MessageFormat.format("{0} company 52 week low is  - {1}",Constants.companyName,weekLow52Value));
      }

    @Test
    public void enterCompanyNameAndDisplayRequiredDetailsUsingChrome() throws IOException {
        BaseClass.launchBrowser(Constants.chromeBrowser);
        webDriverUtility.implicitlyWait();
        webDriverUtility.maximizeWindow();
        webDriverUtility.navigateURL(Constants.url2);
        org.testng.Assert.assertEquals(webDriverUtility.getTitle(),Constants.nscHomePageTitle,"title of the home page is not matching");
        ArrayList<String> inputData =helper.readInputDataFromExcel(Constants.filePathToRead);
        for (int i=0;i<inputData.size();i++){
            webDriverUtility.setFieldValue(By.xpath(XpathConstants.companyNameTextBox),inputData.get(i));
            webDriverUtility.click(By.xpath(XpathConstants.clkAllSymbolLink));
            String companyName = webDriverUtility.getText(By.id(XpathConstants.companyTextBoxId));
            Assert.assertTrue(companyName.contains(inputData.get(i)),"company name is not matching ");
            String faceValue = webDriverUtility.getText(By.id(XpathConstants.faceValueId));
            System.out.println(MessageFormat.format("{0} company Face Value is  - {1}",inputData.get(i),faceValue));
            String weekHigh52Value = webDriverUtility.getText(By.id(XpathConstants.high52Id));
            System.out.println(MessageFormat.format("{0} company 52 week high is  - {1}",inputData.get(i),weekHigh52Value));
            String weekLow52Value = webDriverUtility.getText(By.id(XpathConstants.low52Id));
            System.out.println(MessageFormat.format("{0} company 52 week low is  - {1}",inputData.get(i),weekLow52Value));
            softAssert.assertTrue(webDriverUtility.takeSnapShot("screenShot_TC_4_"+i+"_"+new Date()));
        }
    }

    @Test
    public void shoppersStopDetails() throws InterruptedException {

        BaseClass.launchBrowser(Constants.chromeBrowser);
        webDriverUtility.implicitlyWait();
        webDriverUtility.maximizeWindow();
        webDriverUtility.navigateURL(Constants.url3);
        org.testng.Assert.assertEquals(webDriverUtility.getTitle(),Constants.title_ShoppersStop_homePage,"title of the home page is not matching");

        //Click on the banner slider > for the number of times till the banner gets repeated
        homePage=PageFactory.initElements(BaseClass.driver,HomePage.class);
        long noOfBanners= homePage.getBannerSize().size();
        helper.clickBannerSlider(noOfBanners,homePage.getClickBannerSlider());

        //Print all the accessories name under MEN section > Men’s Fragrance
        webDriverUtility.moveToWebElement(homePage.getMenHeader(),homePage.getMensFragrance());
        helper.printfAccessories(homePage.getListOfAccessories());
        helper.printfAccessories(homePage.getListOfAccessoriesForMen());

        //Click on All Stores link
        homePage.getClkAllStoresLink().click();
        Assert.assertEquals(webDriverUtility.getTitle(),Constants.title_ShoppersStop_homePage,"All stores page title is not matching");

        //Print the Cities name that available in Find Stores in your city
        allStoresPage=PageFactory.initElements(BaseClass.driver,AllStoresPage.class);
        List<WebElement> allStoresValues = webDriverUtility.getDropDownValues(allStoresPage.getFindStoresDropDown());
        helper.printAllStoresInCity(allStoresValues);

        //Assert Agra, Bhopal and Mysore are available in Find Stores in your city.
        helper.validateValuePresentInDropDown(allStoresValues,Constants.valuesPresent1,Constants.valuesPresent2,Constants.valuesPresent3);

        //Print the page title in console.
        System.out.println("title of the All Stores page is - "+webDriverUtility.getTitle());
    }

    @Test
    public void writeTopGainersAndLosersValueInExcel() throws InterruptedException, IOException {

        Workbook book = new HSSFWorkbook();
        BaseClass.launchBrowser(Constants.chromeBrowser);
        webDriverUtility.implicitlyWait();
        webDriverUtility.maximizeWindow();
        webDriverUtility.navigateURL(Constants.url4);
        org.testng.Assert.assertEquals(webDriverUtility.getTitle(), Constants.nscHomePageTitle, "title of the home page is not matching");

        //Click on Top Ten Gainers / Losers
        webDriverUtility.moveToWebElement(BaseClass.driver.findElement(By.xpath(XpathConstants.liveMarketTab)));
        webDriverUtility.click(By.xpath(XpathConstants.clkTopTenGainersOrLosers));
        Assert.assertEquals(webDriverUtility.getText(By.xpath(XpathConstants.getTitleOfClickedLink)),"Top Ten Gainers & Losers","after clicking Top Ten Gainers link, expected page is not found");

        //Store the Top gainers table values in an excel sheet
        Thread.sleep(2000);
        List listOfSymbol =  webDriverUtility.getListOfText(By.xpath(XpathConstants.topGainersTableSymbolCellValue));
        List listOfLTP = webDriverUtility.getListOfText(By.xpath(XpathConstants.topGainersTableLTPCellValue));
        List listOfPerChange = webDriverUtility.getListOfText(By.xpath(XpathConstants.topGainersTablePerChangeCellValue));
        List listOfTradedQty = webDriverUtility.getListOfText(By.xpath(XpathConstants.topGainersTableTradedQtyCellValue));
        List listOfValue = webDriverUtility.getListOfText(By.xpath(XpathConstants.topGainersTableValueCellValue));
        List listOfOpen = webDriverUtility.getListOfText(By.xpath(XpathConstants.topGainersTableOpenCellValue));
        List listOfHigh = webDriverUtility.getListOfText(By.xpath(XpathConstants.topGainersTableHighCellValue));
        List listOfLow = webDriverUtility.getListOfText(By.xpath(XpathConstants.topGainersTableLowCellValue));
        List listOfPrevClose = webDriverUtility.getListOfText(By.xpath(XpathConstants.topGainersTablePrevCloseCellValue));
        List listOfLatestExDate = webDriverUtility.getListOfText(By.xpath(XpathConstants.topGainersTableLatestExDateCellValue));
        List listOfPerChangeReal=new ArrayList();
        listOfPerChangeReal.addAll(listOfPerChange);

        Map<String,List<String>> inputData=new HashMap<String, List<String>>();
        inputData.put("Symbol",listOfSymbol);
        inputData.put("LTP",listOfLTP);
        inputData.put("PerChange",listOfPerChange);
        inputData.put("TradedQty",listOfTradedQty);
        inputData.put("Value",listOfValue);
        inputData.put("Open",listOfOpen);
        inputData.put("High",listOfHigh);
        inputData.put("Low",listOfLow);
        inputData.put("PrevClose",listOfPrevClose);
        inputData.put("LatestExDate",listOfLatestExDate);
        //write Gainers data into Excel sheet
        helper.excelWriter(book,listOfSymbol.size(),listOfSymbol.size(),"Gainers",Constants.filePathToWrite,inputData,Constants.headers);

        //Store all the top losers table values in an excel sheet
        webDriverUtility.click(By.xpath(XpathConstants.clkLosersLink));
        Thread.sleep(2000);
        List listOfSymbol1 =  webDriverUtility.getListOfText(By.xpath(XpathConstants.topLosersTableSymbolCellValue));
        List listOfLTP1 = webDriverUtility.getListOfText(By.xpath(XpathConstants.topLosersTableLTPCellValue));
        List listOfPerChange1 = webDriverUtility.getListOfText(By.xpath(XpathConstants.topLosersTablePerChangeCellValue));
        List listOfTradedQty1 = webDriverUtility.getListOfText(By.xpath(XpathConstants.topLosersTableTradedQtyCellValue));
        List listOfValue1 = webDriverUtility.getListOfText(By.xpath(XpathConstants.topLosersTableValueCellValue));
        List listOfOpen1 = webDriverUtility.getListOfText(By.xpath(XpathConstants.topLosersTableOpenCellValue));
        List listOfHigh1 = webDriverUtility.getListOfText(By.xpath(XpathConstants.topLosersTableHighCellValue));
        List listOfLow1 = webDriverUtility.getListOfText(By.xpath(XpathConstants.topLosersTableLowCellValue));
        List listOfPrevClose1 = webDriverUtility.getListOfText(By.xpath(XpathConstants.topLosersTablePrevCloseCellValue));
        List listOfLatestExDate1 = webDriverUtility.getListOfText(By.xpath(XpathConstants.topLosersTableLatestExDateCellValue));
        List listOfPerChangeRealForLosers=new ArrayList();
        listOfPerChangeRealForLosers.addAll(listOfPerChange1);

        Map<String,List<String>> inputData1=new HashMap<String, List<String>>();
        inputData1.put("Symbol",listOfSymbol1);
        inputData1.put("LTP",listOfLTP1);
        inputData1.put("PerChange",listOfPerChange1);
        inputData1.put("TradedQty",listOfTradedQty1);
        inputData1.put("Value",listOfValue1);
        inputData1.put("Open",listOfOpen1);
        inputData1.put("High",listOfHigh1);
        inputData1.put("Low",listOfLow1);
        inputData1.put("PrevClose",listOfPrevClose1);
        inputData1.put("LatestExDate",listOfLatestExDate1);

        //write losers data into Excel sheet
        helper.excelWriter(book,listOfSymbol1.size(),listOfSymbol1.size(),"Losers",Constants.filePathToWrite,inputData1,Constants.headers);

        //Assert if the percentage change is high to low for each of Top gainers and losers
        Collections.sort(listOfPerChange);
        Collections.reverse(listOfPerChange);
        Assert.assertTrue(helper.validatePercentageOfChanges(listOfPerChangeReal,listOfPerChange),"Percentage change is not high to Low for Gainers");
        Collections.sort(listOfPerChange1);
        Collections.reverse(listOfPerChange1);
        Assert.assertTrue(helper.validatePercentageOfChanges(listOfPerChangeRealForLosers,listOfPerChange1),"Percentage change is not high to Low for Losers");
    }



}
