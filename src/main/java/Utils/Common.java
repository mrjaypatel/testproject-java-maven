package Utils;

import io.testproject.sdk.drivers.web.ChromeDriver;
import io.testproject.sdk.internal.reporting.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.Console;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Common {
    public ChromeDriver driver = Driver.getInstance().openBrowser();
    public static int waitedSec = 0;

    //Wait by thread for given seconds
    public static void think(int second){
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitedSec += second;
        System.out.println("## Total Waited Seconds:["+waitedSec+"]");
    }

    //Simple String log
    public static void log(String data){System.out.println(data);}

    //Change Iframe Context To Last Active frame - "In case Element not found in current active frame"
    public void changeIframeContext(){
        //Common.think(1);
        driver.switchTo().defaultContent();
        int size = driver.findElements(By.tagName("iframe")).size();
        driver.switchTo().frame(size - 1);
    }

    //Click event by passing xpath of element, It will find element and click in current context frame if not found will try in different frames
    public void click(String xpath){
        try{
            Common.think(1);
            driver.findElementByXPath(xpath).click();
        }catch (Exception e){
            try{
                changeIframeContext();
                driver.findElementByXPath(xpath).click();
            }catch (Exception e2){
                driver.switchTo().defaultContent();
                think(2);
                driver.findElementByXPath(xpath).click();
            }
        }
    }

    //Simplified version of click element "Button" where just by giving button name it will find and click that button
    public void clickButton(String Label){
        String xpath = "//button[contains(text(),'"+Label+"')]";
        click(xpath);
    }

    //For setting up data in input text element, pass xpath of element and value you want to set
    public void setText(String xpath, String value) {
        System.out.println("## SetText: " + value);
        if(!value.equalsIgnoreCase("null") ){
            try {
                driver.findElementByXPath(xpath).clear();
                driver.findElementByXPath(xpath).sendKeys(value);
            } catch (Exception e) {
                changeIframeContext();
                driver.findElementByXPath(xpath).clear();
                driver.findElementByXPath(xpath).sendKeys(value);
            }
        }
    }

    //For setting up data in input textbox with element name and value you want to set
    public void setTextByName(String name, String value){
        String xpath = "//input[@name='"+name+"']";
        setText(xpath, value);
    }

    //For setting data in input text box for trying multiple index for element name as presting and poststring are static and inbetween
    // values will be change, where at place of TRY you can pass max TRY for index change you want
    public int tryMultipleIndexSetText(String preString, String postString, int TRY, String data){
        int index = 0;
        for(int i = 1; i <= TRY; i++){
            try{
                String val = driver.findElementByXPath("//input[@name='"+preString+i+postString+"']").getAttribute("value");
                if(val.length() < 2){
                    setTextByName(preString+i+postString, data);
                    index = i;
                    break;
                }else{
                    log("### Input already Contains Data!");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return index;
    }


    //For selecting option in select element by giving xpath of element and option to select
    public void selectByXpath(String xpath, String value){
        System.out.println("####Select: "+value);
        try{
            Select select = new Select(driver.findElementByXPath(xpath));
            select.selectByVisibleText(value);
        }catch (Exception e){
            changeIframeContext();
            Common.think(1);
            driver.findElementByXPath(xpath+"/child::*[text()='"+value+"']").click();
        }
    }

    //For selecting option in select element by giving just name of element and value
    public void select(String name, String value) {
        String xpath =  "//select[@name='"+name+"']";
        try {
            selectByXpath(xpath, value);
        }catch (Exception e){
            Common.think(2);
            try{
                selectByXpath(xpath, value);
            }catch (Exception e2){
                selectByXpath(xpath, value);
            }

        }
    }

    //For making "TAB_KEY" press event for given element
    public void pressTAB(String xpath){
        Common.think(1);
        driver.findElementByXPath(xpath).sendKeys(Keys.TAB);
    }
    //For making "ENTER_KEY" press event for given element
    public void pressENTER(String xpath){
        Common.think(1);
        driver.findElementByXPath(xpath).sendKeys(Keys.ENTER);
    }

    public void ckeckBox(String xpath,boolean checked){
        //If the checkbox is unchecked then isSelected() will return false
        //and NOT of false is true, hence we can click on checkbox
        if(checked){
            if(!driver.findElementByXPath(xpath).isSelected()){
                driver.findElementByXPath(xpath).click();
                Common.log("##### Checkbox Checked Now!!");
            }else {
                Common.log("##### Checkbox Already Checked! Skipping Step");
            }
        }else{
            if(driver.findElementByXPath(xpath).isSelected()){
                driver.findElementByXPath(xpath).click();
                Common.log("##### Checkbox UnChecked Now!!");
            }else {
                Common.log("##### Checkbox Already unChecked! Skipping Step");
            }
        }

    }

    public String getDate(String format, int day){
        //dd/MM/yyyy HH:mm:ss
        DateFormat dateFormat = new SimpleDateFormat(format);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, day);
        return  dateFormat.format(c.getTime());
    }

    public void uploadFile(String filePath, String fileBoxName) {
        Common.think(1);
        File resourcesDirectory = new File("src/main/resources/data/"+filePath);
        System.out.println("### File PAth "+resourcesDirectory.getAbsolutePath());
        setTextByName(fileBoxName, resourcesDirectory.getAbsolutePath());

    }
    public void uploadFileWithRobot(String filePath, String fileBoxName) throws AWTException {
        Common.think(1);
        File resourcesDirectory = new File("src/main/resources/data/"+filePath);
        String FP = resourcesDirectory.getAbsolutePath();
        System.out.println("### File PAth "+FP);

        Robot robot = new Robot();
        robot.setAutoDelay(1000);
        StringSelection stringSelection = new StringSelection(FP);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public void closeWindow(){

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_W);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_W);
        robot.setAutoDelay(1000);

    }

    public void reloadPage() {
        driver.navigate().refresh();
    }

    public String getContractNumberByVin(String vin, String status, String reference){
        Common.think(1);
        click("//span[text()='Home']//parent::span//parent::a");
        Common.think(1);
        click("//label[contains(text(),'VIN number')]");
        Common.think(1);
        String vinNumberName =  "$PpyDisplayHarness$ppxUserDashboard$ppySlots$l1$ppyWidgets$l1$ppyWidget$pSearchContract";
        setTextByName(vinNumberName, vin);
        clickButton("Search");
        click("//*[text()='Status']/parent::div/parent::div/parent::th");
        click("//*[text()='Status']/parent::div/parent::div/parent::th");
        //click("//*[contains(@name,'RadioButtonToggle_ContractResults.pxResults')]");
        if(!reference.equalsIgnoreCase("NA")){
            click("(//*[text()='"+reference+"']/following::*[text()='"+status+"']/parent::div/parent::td/following-sibling::td[2]/child::div/child::span)[1]");
        }else{
            click("//*[text()='"+status+"']");
        }

        Common.think(2);
        String  xpath = "(//*[text()='Contract Details']/following::*[text()='"+status+"']/parent::div/following-sibling::div[2]/child::span)[1]";
        return getReadOnlyText(xpath);
    }

    public String getReadOnlyText(String xpath){
        return driver.findElementByXPath(xpath).getText();
    }
    public boolean findTextInPage(String text, boolean shouldPresent){
        if ( driver.getPageSource().contains(text) && shouldPresent){
            System.out.println("Text: " + text + " is present. ");
            driver.report().step("Text found "+text,true);
           // Reporter.reportStep("Page is successfully loaded :" + title, "PASS");

            return true;
        } else {
            System.out.println("Text: " + text + " is not present. ");
            driver.report().step("Text not found "+text,false);
            return false;
        }
    }
}