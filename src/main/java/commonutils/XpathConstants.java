package commonutils;

public interface XpathConstants {

    String ClkCertificationsLink="//a[contains(.,'Certifications')]";
    String getImagePresentInPage="//map[@name='image-map']/area";
    String cpcctImage="//area[@title='CP-CCT' and @alt='CP-CCT']";

    String advancesTab="//li[@id='advances']/span";
    String declinesTab ="//li[@id='declines']/span";
    String unchangedTab="//li[@id='unchanged']/span";

    String companyNameTextBox="//input[@id='keyword']";
    String clkAllSymbolLink="//span[@class='symbol']";
    String companyTextBoxId="companyName";
    String faceValueId="faceValue";
    String high52Id="high52";
    String low52Id="low52";

    String liveMarketTab="//*[.='Live Market']";
    String clkTopTenGainersOrLosers="//a[contains(text(),'Top Ten Gainers')]";
    String getTitleOfClickedLink ="//*[@class='live_market_content']/h2";
    String topGainersTableSymbolCellValue="//*[@id='topGainers']/tbody/tr/td[1]";
    String topGainersTableLTPCellValue="//*[@id='topGainers']/tbody/tr/td[2]";
    String topGainersTablePerChangeCellValue="//*[@id='topGainers']/tbody/tr/td[3]";
    String topGainersTableTradedQtyCellValue="//*[@id='topGainers']/tbody/tr/td[4]";
    String topGainersTableValueCellValue="//*[@id='topGainers']/tbody/tr/td[5]";
    String topGainersTableOpenCellValue="//*[@id='topGainers']/tbody/tr/td[6]";
    String topGainersTableHighCellValue="//*[@id='topGainers']/tbody/tr/td[7]";
    String topGainersTableLowCellValue="//*[@id='topGainers']/tbody/tr/td[8]";
    String topGainersTablePrevCloseCellValue="//*[@id='topGainers']/tbody/tr/td[9]";
    String topGainersTableLatestExDateCellValue="//*[@id='topGainers']/tbody/tr/td[10]";
    //======================
    String clkLosersLink="//a[.='Losers']";
    String topLosersTableSymbolCellValue="//*[@id='topLosers']/tbody/tr/td[1]";
    String topLosersTableLTPCellValue="//*[@id='topLosers']/tbody/tr/td[2]";
    String topLosersTablePerChangeCellValue="//*[@id='topLosers']/tbody/tr/td[3]";
    String topLosersTableTradedQtyCellValue="//*[@id='topLosers']/tbody/tr/td[4]";
    String topLosersTableValueCellValue="//*[@id='topLosers']/tbody/tr/td[5]";
    String topLosersTableOpenCellValue="//*[@id='topLosers']/tbody/tr/td[6]";
    String topLosersTableHighCellValue="//*[@id='topLosers']/tbody/tr/td[7]";
    String topLosersTableLowCellValue="//*[@id='topLosers']/tbody/tr/td[8]";
    String topLosersTablePrevCloseCellValue="//*[@id='topLosers']/tbody/tr/td[9]";
    String topLosersTableLatestExDateCellValue="//*[@id='topLosers']/tbody/tr/td[10]";



}
