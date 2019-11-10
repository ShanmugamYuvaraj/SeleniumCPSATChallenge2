package commonutils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import pageobjects.HomePage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Helper {

    WebDriverUtility webDriverUtility=new WebDriverUtility();
    HomePage homePage=PageFactory.initElements(BaseClass.driver,HomePage.class);
    /**
     * Used to find the broken links if available
     *
     * @param links
     * @return
     */
    public List isUrlBroken(List<WebElement> links) {
        List brokenLinks = new ArrayList();
        String url = "";
        HttpURLConnection huc = null;
        int respCode = 200;
        Iterator<WebElement> it = links.iterator();

        while (it.hasNext()) {

            url = it.next().getAttribute("href");
            System.out.println(url);
            try {
                huc = (HttpURLConnection) (new URL(url).openConnection());
                huc.setRequestMethod("HEAD");
                huc.connect();
                respCode = huc.getResponseCode();
                if (respCode >= 400) {
                    System.out.println(url + " is a broken link");
                    brokenLinks.add(url);
                } else {
                    System.out.println(url + " is a valid link");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return brokenLinks;
    }

    public void getMinimumNumber(List<WebElement> advances, List<WebElement> declines, List<WebElement> unchanged) {
        int advancesValue = Integer.parseInt(advances.get(0).getText());
        int declinesValue = Integer.parseInt(declines.get(0).getText());
        int unchangedValue = Integer.parseInt(unchanged.get(0).getText());

        if (advancesValue < declinesValue && advancesValue < unchangedValue) {
            System.out.println("Minimum value is - Advances " + advancesValue);
        } else if (declinesValue < advancesValue && declinesValue < unchangedValue) {
            System.out.println("Minimum value is - Declines " + declinesValue);
        } else if (unchangedValue < advancesValue && unchangedValue < declinesValue) {
            System.out.println("Minimum value is - Unchanged " + unchangedValue);
        }
    }

    /**
     * Used to read the input data from .xls sheet
     * @param filePathName
     * @throws IOException
     * @return
     */
    public ArrayList<String> readInputDataFromExcel(String filePathName) throws IOException {
        ArrayList<String> inputData=new ArrayList<String>();

        FileInputStream file = new FileInputStream(new File(filePathName));
        HSSFWorkbook workbook=new HSSFWorkbook(file);
        HSSFSheet sheet=workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext())
            {
                inputData.add(cellIterator.next().getStringCellValue());
            }
        }
        file.close();
        return inputData;
    }

    /**
     * Click on the banner slider > for the number of times till the banner gets repeated
     * @param sizeOfBanners
     * @throws InterruptedException
     */
    public void clickBannerSlider(long sizeOfBanners,WebElement webElement) throws InterruptedException {
        int i=0;
        while(true){
            Thread.sleep(1000);
            webElement.click();
            if(i==sizeOfBanners-1){
                break;
            }
            i++;
        }
    }

    public void printfAccessories(List<WebElement> webElements){
        Iterator<WebElement> iterator = webElements.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().getText());
        }
    }

    public void printAllStoresInCity(List<WebElement> webElements){
        for(WebElement element : webElements){
            System.out.println(element.getText());
        }

    }

    public void validateValuePresentInDropDown(List<WebElement> webElements, String... values){
        Iterator<WebElement> dropDownValues = webElements.iterator();
        for (int i=0;i<values.length;i++){
            while (dropDownValues.hasNext()){
                if(dropDownValues.next().getText().equalsIgnoreCase(values[i])){
                    System.out.println("Value "+values[i]+" present in dropdown");
                    break;
                }
            }
        }

    }

    public void excelWriter(Workbook book,int sizeOfRow, int sizeOfColumn, String sheetName, String filePath, Map<String,List<String>> inputData
    ,String[] headers) throws IOException {
        Sheet sheet = book.createSheet(sheetName);
        for(int i=0;i<sizeOfRow;i++){
            Row row = sheet.createRow(i);
            for(int j=0;j<=sizeOfColumn-1;j++ ){
                if(i==0){
                    Cell name = row.createCell(j);
                    name.setCellValue(headers[j]);
                    book.write(new FileOutputStream(filePath));
                }else{
                    Cell name = row.createCell(j);
                    name.setCellValue(inputData.get(headers[j]).get(i));
                    book.write(new FileOutputStream(filePath));
                }

            }

        }
        book.close();
    }

    public boolean validatePercentageOfChanges(List<String> listOfPerChangeReal,List<String> listOfPerChange){
        boolean bFlag=true;
        int i=0;
        while (true){
            if(listOfPerChangeReal.get(i).equalsIgnoreCase(listOfPerChange.get(i))){

            }else{
                bFlag=false;
                break;
            }
            if(i==listOfPerChangeReal.size()-1){
                break;
            }
            i++;
        }
        return bFlag;
    }





}