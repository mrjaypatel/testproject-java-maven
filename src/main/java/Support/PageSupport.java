package Support;

import Utils.Common;
import Utils.Driver;
import Utils.Global;
import io.testproject.sdk.drivers.web.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;


public class PageSupport {
    public ChromeDriver lDriver;
    public static String elememetId = "";
    public static int searchId = 1;
    public static int opsPopup = 1;
    public Common cmn = new Common();
    public PageSupport() {
        this.lDriver = Driver.getInstance().openBrowser();
        PageFactory.initElements(lDriver,this);
    }

    //Deprecated method to set username at login page
    public void setUsrName(String username){
        cmn.setText("//input[@id='txtUserID']", username);
    }

    //Deprecated method to set password at login page
    public void setUsrPassword(String password){
        cmn.setText("//input[@id='txtPassword']", password);
    }

    //Deprecated method to click on login button
    public void clickLogin(){
        Common.think(1);
        cmn.click("//button[@id='sub']");
    }

    //By passing username and password user can login to application
    public void loginValidUser(String username, String password){
        setUsrName(username);
        setUsrPassword(password);
        clickLogin();
    }
    //For clicking on Dealership Link Navigation
    public void clickDealerships(){
        Common.think(2);
        cmn.click("//li[@title='Dealerships']/child::a");
    }
    public void clickDealershipsLink(){
        cmn.click("//li[@title='Dealerships']/child::a");
    }

    //For clicking on create dealership button with section of specific frame
    public void clickCreateDelShipGrp() {
        Common.think(1);
        lDriver.switchTo().frame(lDriver.findElement(By.xpath("//iframe[@title='Dealerships']")));
        cmn.clickButton("Create a new dealership group");
    }

    //################## Create Dealership Group Section Started ####################################
    //Setting up dealership group name
    public void setDealerShipGrpName(String data){
        if(data.equalsIgnoreCase("GrpName")){
            cmn.setTextByName("$PpyWorkPage$pDealershipGroup$pOrganizationName", Global.GrpName);
        }else{
            cmn.setTextByName("$PpyWorkPage$pDealershipGroup$pOrganizationName", data);
        }
    }

    //For selecting Account manager on Dealership create page
    public void selectAcMgr(String data) throws InterruptedException {
        cmn.select("//select[contains(@name,'pAccountManagerID')]", data);
    }
    //Set Dealership group address
    public void setAddress(String data){
        cmn.setTextByName("$PpyWorkPage$pDealershipGroup$pAddresses$l1$pAddress1", data);
    }
    //Setting up dealership city
    public void setCity(String data){
        cmn.setTextByName("$PpyWorkPage$pDealershipGroup$pAddresses$l1$pCity", data);
    }

    //Setting up dealership state
    public void selectState(String data) throws InterruptedException {
        cmn.select("$PpyWorkPage$pDealershipGroup$pAddresses$l1$pState", data);
    }

    //Setting up dealership pincode
    public void setPincode(String data){
        cmn.setText("//input[contains(@name,'$PpyWorkPage$pDealershipGroup$pAddresses$l1$pPostalCode')]",data);
    }

    //############### Add contact for dealership group started #################################
    //Setting up Contact First Name
    public void setFirstName(String data){
        cmn.setText("//label[text()='First name']/following::input[@id='c736acc6']",data);
    }
    //Setting up Contact Last Name
    public void setLastName(String data){
        cmn.setText("//*[text()='Last Name']/following::input[@id='3bd48104']", data);
    }
    //Setting up Contact Role
    public void setRole(String data){
        cmn.setText("//*[text()='Role']/following::input[@id='b1af4b3c']", data);
    }

    //Click on Add Contact Button After filling required Fields
    public void clickAddContact(){
        cmn.click("//*[text()='Contact Information']/following::button[contains(text(),'Add')]");
    }
    //############### Add contact for dealership group Ended #################################
    //################## Create Dealership Group Section Ended ###############################


    //################## Adding Product To Dealership Group Start ############################
    //Select Product Category For dealership Group
    public void selectProdCat(String data) throws InterruptedException {
        cmn.select("$PpyWorkPage$pProductAddPg$pProductCategoryID", data);
    }
    //Set Product name
    public void setProdName(String data){
        cmn.setTextByName("$PpyWorkPage$pProductAddPg$pProductName", data);
    }

    //Select Product Type
    public void selectProdType(String data) throws InterruptedException {
        cmn.select("$PpyWorkPage$pProductAddPg$pPrimaryServiceType", data);
    }

    //Set Number of service you want to add for Product
    public void setNumberOfService(String data){
        cmn.setTextByName("$PpyWorkPage$pProductAddPg$pPrimaryServiceNoOfSvcs", data);
    }

    //Set Term for the product
    public void setTerm(String data){
        cmn.setTextByName("$PpyWorkPage$pProductAddPg$pTerm", data);
    }

    //Set Grace days for given Product
    public void setGraceDays(String data){
        cmn.setTextByName("$PpyWorkPage$pProductAddPg$pGraceDays", data);
    }




    public void removeProduct(String data){
        cmn.click("//span[text()='"+data+"']/following::img[1]");
    }

    public void clickAddProduct(){
        cmn.clickButton("Add Product");
    }

    public void clickContinueProd(){
        cmn.clickButton("Continue");
    }

    public void searchForGrpDealership(String data) {
//        String searchTxt = "";
//        if (!data.equalsIgnoreCase("GrpName")){
//            searchTxt = data;
//        }else{
//            searchTxt = Global.GrpName;
//        }
//        Common.think(2);
//        lDriver.switchTo().frame(lDriver.findElementByXPath("//iframe[@title='Dealerships']"));
//        cmn.setText("//input[contains(@id,'aa620880')]", data);
//        Common.think(2);
//        cmn.click("//*[contains(@id,'poc0')]");
//        Common.think(2);
//        cmn.click("//a[contains(text(),'"+searchTxt+"') and contains(@name,'pxResults("+searchId+")')]");
//        Common.think(2);
        searchForGrpDealershipNew(data);
    }

    //Product Settings
    public void checkDisplayedOn(String data){
        String XPATH= "";
        if(data.equalsIgnoreCase("Service")){
            XPATH = "//input[@name='$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pDisplayOnServiceLane']/following::label[text()='Service']";
        }else{
            XPATH = "//input[@name='$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pDisplayOnFnI']/following::label[text()='F&I']";
        }
        try{
            cmn.click(XPATH);
        }catch (Exception e){
            cmn.click(XPATH);
        }
    }

    public void setProductDesc(String data){
        Common.think(2);
        try{
            cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l1$pProductDescription", data);
            elememetId = "1";
        }catch (Exception e){
            try{
                cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l2$pProductDescription", data);
                elememetId = "2";
            }catch (Exception e1){
                cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l3$pProductDescription", data);
                elememetId = "3";
            }
        }

    }

    //Product Add
    public void setInsurer(String data){
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pInsurer", data);
    }

    public void setProdEffectiveDate(String data){
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pEffectiveDate", data);
    }

    public void setProdTerminationDate(String data){
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pTerminationDate", data);
    }

    public void selectFuleType(String data)  {
        cmn.select("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pFuelType", data);
    }

    public void SaveAndExit()  {
        cmn.click("//a[contains(@name,'SaveAndExitLink_pyWorkPage_1')]");
    }

    public void logOff(){
        Common.think(2);
        cmn.click("//button[@title='KAS Admin User']");
        Common.think(2);
        cmn.click("//button[@title='KAS Admin User']/following::*[text()='Log off']/parent::span/parent::a");
        Common.think(2);
    }
    public void logOffUser(String username){
        Common.think(2);
        cmn.click("//button[@title='"+username+"']");
        Common.think(2);
        cmn.click("//button[@title='"+username+"']/following::*[text()='Log off']/parent::span/parent::a");
        Common.think(2);
    }

    public void setAdjustmentType(String mData) {
        try{
            cmn.setText("//input[contains(@name,'pAdjustmentAdd$pAdjustmentType')][1]", mData);
        }catch(Exception e){
            cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pAdjustmentAdd$pAdjustmentType", mData);
        }


    }

    public void setAdjustmentAmount(String mData) {
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pAdjustmentAdd$pAdjustmentAmount", mData);
    }

    public void setAdjustMentEffectiveDate(String mData) {
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pAdjustmentAdd$pEffectiveDate", mData);
    }

    public void clickAddAdjustment(){
        cmn.clickButton("Add Adjustment");
    }


    public void setServiceType(String data){

        String xpath = "//input[contains(@name,'pServiceType')]";
        try{
            cmn.setText(xpath, data);
            Common.think(1);
            cmn.pressTAB(xpath);
        }catch(Exception e){
            cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pService$pServiceType", data);
            Common.think(1);
            cmn.pressTAB("//input[@name='$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pService$pServiceType']");
        }

    }

    public void setNoOfService(String data){
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pService$pNumberOfServices", data);
    }

    public void setReimbursement(String data){
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pService$pReimbursementRate", data);
    }

    public void setEffData(String data){
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pService$pEffectiveDate", data);
    }
    public void setCancellationService(String data){
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pService$pRefundPrice", data);
    }

    public void setServiceLaborCode(String data){
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pService$pLaborOPSCode", data);
    }

    public void clickAddService(){
        cmn.clickButton("Add Service");
    }

    public void setProdPrice(String mData) {
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pProductPriceAdd$pProductPrice", mData);
    }

    public void setProdEffDate(String mData) {
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pProductPriceAdd$pEffectiveDate", mData);
    }

    public void setProdTermDate(String mData) {
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pProductPriceAdd$pTerminationDate", mData);
    }

    public void clickAddProdPrice() {
        cmn.clickButton("Add Product Price");
    }

    public void setOverridePrice(String mData) {
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pProductPriceAdd$pProductPrice",mData);
    }

    public void setOverrideEffDate(String mData) {
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pProductPriceAdd$pEffectiveDate", mData);
    }
    public void setOverrideTermDate(String mData) {
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pPriceTerminationDate",mData);
    }

    public void setOverrideLaborCode(String mData) {
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pSalesLaborOPSCode", mData);
    }

    public String getProductName(){
        Common.log("#### In GetProductName");
        String response ="";
        cmn.changeIframeContext();
        Common.log("#### In GetProductName Frame Switched");
        try {
            Common.log("#### In GetProductName In Try");
            WebElement elem = lDriver.findElementByXPath("//input[@name='$PpyWorkPage$pUncommittedProducts$l1$pProductName']");
            response = elem.getAttribute("value");
            Common.log("######Try 1");

        }catch (Exception e){
            e.printStackTrace();
            try{
                Common.log("##### IN found 2");
                WebElement elem1 = lDriver.findElementByXPath("//input[@name='$PpyWorkPage$pUncommittedProducts$l2$pProductName']");
                response = elem1.getAttribute("value");
                Common.log("######Try 2");
            }catch (Exception e1){
                e1.printStackTrace();
                try{
                    Common.log("##### IN found 3");
                    WebElement elem2 = lDriver.findElementByXPath("//input[@name='$PpyWorkPage$pUncommittedProducts$l3$pProductName']");
                    response = elem2.getAttribute("value");
                }catch (Exception e2){
                    e2.printStackTrace();
                    Common.log("######Element Not Found");
                }
            }
        }
        return response;
    }

    public boolean verifyAndProceed(String ProductName) throws InterruptedException {
        boolean response = false;
        cmn.changeIframeContext();
        try {
            WebElement elem = lDriver.findElementByXPath("//input[@name='$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pProductName']");
            String value = elem.getAttribute("value");
            System.out.println("######### Element Value: " + value );
            if(ProductName.equalsIgnoreCase(value))
                response = true;

        }catch (Exception e){
            System.out.println("######### Element Not Found In UI");
        }
        SaveAndExit();
        Common.think(1);
        clickDealerships();
        return response;
    }

    public void changeIframeContext() {
        cmn.changeIframeContext();
    }

    public void enableExpanseCatchall() {
        String xpath = "//input[@name='$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pExpense$pIsCatchAll' and @type='checkbox']";
        if(!lDriver.findElementByXPath(xpath).isSelected()){
            cmn.click("//label[text()='Is Catch All']");
        }
    }

    public void clickOnAddExpense() {
        cmn.clickButton("Add Expense");
    }

    public void clickOnContinueOnExpense() {
        try{
            cmn.clickButton("Continue");
        }catch (Exception e){
            cmn.clickButton("Next Step");
        }
    }

    public void changeProdTab(String data) {
        cmn.click("//div[@aria-label='"+data+"']");
    }

    public void clickOnDealershipFlow() {
        cmn.click("//a[@title='Dealerships']/parent::span/parent::div/parent::div/parent::div/parent::div");
    }

    public void removeDealership(String mData) {
        Common.think(2);
        cmn.click("//*[contains(text(),'"+mData+"')]/following::img[1]");
        Common.think(2);
        cmn.click("//*[text()='Please confirm you wish to delete this record.']/following::*[contains(text(),'Delete')]");
    }

    public void uploadSpreedsheet() {
        Common.think(2);
        try {
            cmn.clickButton("Upload From Spreadsheet");
        }catch (Exception e){
            e.printStackTrace();
        }
        Common.think(2);
        File resourcesDirectory = new File("src/main/resources/data/"+Global.DEALERSHIPS_SPREADSHEET);
        System.out.println("##### My Path"+resourcesDirectory.getAbsolutePath());
        lDriver.findElement(By.xpath("//input[@name='$PpyWorkPage$ppyFilePath']")).sendKeys(resourcesDirectory.getAbsolutePath());
        Common.think(2);
        cmn.clickButton("Upload file");

    }



    public void checkWarningMessage(String message) {
        String xpath = "//div[@class='custom_text']";
        Common.think(2);
        try {
            if(lDriver.getPageSource().contains(message)){
                System.out.println("######Catch Element is Visible");
            } else {
                System.out.println("######Catch Element is InVisible");
            }
        }catch (Exception e){
            cmn.changeIframeContext();
            if(lDriver.getPageSource().contains(message)){
                System.out.println("######Catch Element is Not Visible");
            } else {
                System.out.println("######Catch Element is Visible");
            }
        }
    }

    public void clickSetupOnDealership(String data) {
        cmn.click("//*[contains(text(),'"+data+"')]/following::button[contains(text(),'Set')]");
        try{
            cmn.clickButton("Edit");
        }catch (Exception e){}
    }

    public void setSetupFname(String mData) {
        cmn.setTextByName("$PpyWorkPage$pDealership$pContact$pFirstName", mData);
    }
    public void setSetupLname(String mData) {
        cmn.setTextByName("$PpyWorkPage$pDealership$pContact$pLastName",mData);
    }
    public void setSetupRole(String mData) {
        cmn.setTextByName("$PpyWorkPage$pDealership$pContact$pRole",mData);
    }
    public void setSetupEmail(String mData) {
        cmn.setTextByName("$PpyWorkPage$pDealership$pContact$pPrimaryEmail",mData);
    }
    public void selectContactType(String mData){
        cmn.select("$PpyWorkPage$pDealership$pContact$pContactType", mData);
    }
    public void setSetupPhNumber(String mData) {
        cmn.setTextByName("$PpyWorkPage$pDealership$pContact$pPrimaryPhoneNumber", mData);
    }
    public void editContact(String data) {
        cmn.click("//*[text()='"+data+"']/following::img[1]");
    }
    public void setPhoneInEditContactMode() {
        cmn.setText("//input[contains(@name,'$pPrimaryPhoneNumber')]", "202-777-3432");
    }
    public void clickSaveInContactEditBox() {
        cmn.clickButton("Save");
    }
    public void removeContact(String data) {
        cmn.click("//*[text()='"+data+"']/following::img[2]");
    }
    public void clickOnContinueOnSetup() {
        cmn.clickButton("Continue");
    }
    public void setSetupOEMMultiple(String mData) {
        cmn.setText("//input[@data-target='$PpyWorkPage$pDealership$pOEM']", mData);
    }
    public void setSaleOPSCode(String mData) {
        Common.think(1);
        opsPopup = cmn.tryMultipleIndexSetText("$PpyWorkPage$pUncommittedProducts$l","$pSalesLaborOPSCode",5, mData);
    }
    public void setLaborOpsCode(String mData) {
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+opsPopup+"$pServices$l1$pLaborOPSCode", mData);

    }
    public void clickSaveDialougBox() {
        cmn.click("//*[contains(text(),'OPS Code')]/following::button[text()='Save']");
    }
    public void setProdNameOnModify(String mData) {
        cmn.setTextByName("$PpyWorkPage$pTempEditProduct$pProductName", mData);
    }
    public void selectProdTypeOnModify(String mData) {
        cmn.select("$PpyWorkPage$pTempEditProduct$pPrimaryServiceType", mData);
    }

    public void selectOilTypeOnModify(String mData) {
        cmn.select("$PpyWorkPage$pTempEditProduct$pPrimaryServiceOilType",mData);
    }

    public void setNumberOfServiceOnModify(String mData) {
        cmn.setTextByName("$PpyWorkPage$pTempEditProduct$pPrimaryServiceNoOfSvcs",mData);
    }

    public void setTermOnModify(String mData) {
        cmn.setTextByName("$PpyWorkPage$pTempEditProduct$pTerm",mData);
    }

    public void clickOnModify(String arg0) {
        cmn.click("//*[text()='"+arg0+"']/following::button[text()='Modify']");
    }

    public void clickSaveProductOnModify() {
        cmn.click("//button[text()='Save Product']");
    }

    public void searchForGrpDealershipNew(String grpName) {
        cmn.setTextByName("$PpyDisplayHarness$pSearchKey", grpName+" Auto Group");
        cmn.clickButton("Search");
        Common.think(1);
        cmn.click("//a[text()='"+grpName+" Auto Group']");
        try{  cmn.clickButton("Edit");    }catch (Exception e){
          try{
              cmn.clickButton("Begin");

          }catch (Exception e1){}
        }
    }

    public void setMaxMarupPrice(String mData) {
        try {Common.think(1);
            cmn.setText("//input[contains(@name,'pContractPriceThreshold')]", mData);
            Common.think(1);
        }catch (Exception e){
            cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l" + elememetId + "$pContractPriceThreshold", mData);
        }
    }

    public static class ProductNotFound extends Exception {
        public ProductNotFound(String errorMessage) {
            super(errorMessage);
        }
    }

    public void setExpanseType(String mData) {
        cmn.select("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pExpense$pExpenseTypeID", mData);
    }

    public void setExpanseVendorType(String mData) {
        cmn.select("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pExpense$pVendorTypeID", mData);

    }

    public void setExpanseVendorName(String mData) {
        cmn.select("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pExpense$pVendorNameID", mData);
    }

    public void setExpanseAmount(String mData) {
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pExpense$pAmountExpenses", mData);
    }

    public void setExpanseEffDate(String mData) {
        cmn.setTextByName("$PpyWorkPage$pUncommittedProducts$l"+elememetId+"$pExpense$pEffectiveDate",mData);
    }
    public void setDealershipName(String mData) {
        cmn.setTextByName("$PpyWorkPage$pEnterDealerships",mData);
    }
    public void clickAddDealership() {
        cmn.clickButton("Add Dealership");
    }
    public void setSetupAddress(String data){
        cmn.setTextByName("$PpyWorkPage$pDealership$pAddresses$l1$pAddress1", data);
    }
    public void setSetupZip(String mData) {
        cmn.setTextByName("$PpyWorkPage$pDealership$pAddresses$l1$pPostalCode", mData);
    }
    public void selectSetupState(String mData) {
        cmn.select("$PpyWorkPage$pDealership$pAddresses$l1$pState", mData);
    }
    public void setSetupCity(String mData) {
        cmn.setTextByName("$PpyWorkPage$pDealership$pAddresses$l1$pCity", mData);
    }


    public void setSetupOEM(String mData) {
        String xpath =  "//input[@data-target='$PpyWorkPage$pDealership$pOEM']";
        try{
            cmn.setText(xpath, mData);
            cmn.pressTAB(xpath);
        }catch (Exception e){
            changeIframeContext();
            cmn.setText(xpath, mData);
            cmn.pressTAB(xpath);
        }
    }
    public void selectSetupType(String mData) {
        cmn.select("$PpyWorkPage$pDealership$pDealershipType", mData);
    }
    public void clickOnActiveProduct(String prodName) {
        cmn.click("//*[text()='"+prodName+"']/following::*[text()='Activate'][1]");
    }



    @FindBy(xpath="//label[text()='Term']/following::input[@id='5382eb38']")
    @CacheLookup
    WebElement checkBox;
    public void checkGiveaway(String data){
//        String xpath= "//label[text()='Grace days']/following::input[@name='$PpyWorkPage$pProductAddPg$pGiveaway']";
//        try{
//            if (!checkBox.isSelected() && data.equalsIgnoreCase("on")) {
//                System.out.println("Checkbox is Toggled On");
//                cmn.click(xpath);
//            } else if(checkBox.isSelected() && data.equalsIgnoreCase("off")){
//                System.out.println("Checkbox is Toggled Off");
//                cmn.click(xpath);
//            }
//        }catch (Exception e){
//            if (!lDriver.findElementByXPath(xpath).isSelected() && data.equalsIgnoreCase("on")) {
//                System.out.println("Checkbox is Toggled On");
//                cmn.click(xpath);
//            } else if(lDriver.findElementByXPath(xpath).isSelected() && data.equalsIgnoreCase("off")){
//                System.out.println("Checkbox is Toggled Off");
//                cmn.click(xpath);
//            }
//        }

    }
}

