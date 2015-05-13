package se.recan.framework.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import se.recan.framework.selenium.BasePO;

/**
 *
 * 2015-jan-23
 * @author Anders Recksén (recan)
 */
public class PersonPO extends BasePO {

    public static final int FEMALE  = 0;
    public static final int MALE    = 1;
    
    public static final int TINKER  = 0;
    public static final int TAYLOR  = 1;
    public static final int SOLDIER = 2;
    public static final int SPY     = 3;
    
    private static final By body        = By.tagName("body");
    private static final By span        = By.tagName("span");
    private static final By headline    = By.tagName("h1");
    private static final By form        = By.id("person");
    private static final By form2       = By.id("upprepning");
    private static final By firstName   = By.cssSelector("input[name=firstName]");
    private static final By lastName    = By.cssSelector("input[name=lastName]");
    private static final By socialNumb  = By.cssSelector("input[name=socialNumb]");
    private static final By userName    = By.cssSelector("input[name=userName]");
    private static final By password1   = By.cssSelector("input[name=password1]");
    private static final By password2   = By.cssSelector("input[name=password2]");
    private static final By radio       = By.cssSelector("input[type=radio]");
    private static final By checkbox    = By.cssSelector("input[type=checkbox]");
    private static final By profession  = By.cssSelector("select[name=profession]");
    private static final By description = By.cssSelector("textarea[name=description]");
    private static final By save        = By.cssSelector("input[name=save]");
    private static final By clean       = By.cssSelector("input[name=clean]");
    private static final By delete      = By.cssSelector("input[name=delete]");
    private static final By resultList  = By.id("resultList");

    public PersonPO() {
        super();
    }
    
    public void setNameOldStyle(String value) {
        WebElement formElement = driver.findElement(By.id("form"));
        WebElement nameElement = formElement.findElement(By.cssSelector("input[name=firstName]"));
        nameElement.clear();
        nameElement.sendKeys(value);
    }
    
    public String getHeadline() {
        return super.getText(body, headline);
    }
    
    public String getResult() {
        return super.getText(resultList);
    }
    
    public void setFirstName(String value) {
       super.type(value, form, firstName);
    }
    
    public void setFirstNameAgain(String value) {
        super.type(value, firstName);
//        super.type(value, form2, firstName);
    }
    
    public void setLastName(String value) {
        super.type(value, form, lastName);
    }
    
    public void setLastNameAgain(String value) {
        super.type(value, form2, lastName);
    }
    
    public void setUserName(String value) {
        super.type(value, form, userName);
    }
    
    public void setUserName2(String value) {
        super.type(value, form, span, userName);
    }
    
    public void setSocialNumb(String value) {
        super.type(value, form, socialNumb);
    }
    
    public void setPassword1(String value) {
        super.type(value, form, password1);
    }
    
    public void setPassword2(String value) {
        super.type(value, form, password2);
    }
    
    public void setGender(int position) {
        super.click(position, form, radio);
    }
    
    public void setSkill(int position) {
        super.click(position, form, checkbox);
    }
    
    public void select(int position) {
        super.select(position, form, profession);
    }
    
    public void select(String value) {
        super.select(value, form, profession);
    }
    
    public void setDescription(String value) {
        super.type(value, form, description);
    }
    
    public void save() {
        super.click(form, save);
    }
    
    public void clean() {
        super.click(form, clean);
    }
    
    public void delete() {
        super.click(form, delete);
    }
    
    public boolean validate() {
        if(super.getAttribute(form, firstName).isEmpty()) {
            return false;
        }
        if(super.getAttribute(form, lastName).isEmpty()) {
            LOGGER.debug("");
            return false;
        }
        if(super.getAttribute(form, socialNumb).isEmpty()) {
            LOGGER.debug("");
            return false;
        }
        if(super.getAttribute(form, userName).isEmpty()) {
            LOGGER.debug("");
            return false;
        }
        if(super.getAttribute(form, password1).isEmpty()) {
            LOGGER.debug("");
            return false;
        }
        if(super.getAttribute(form, password2).isEmpty()) {
            LOGGER.debug("");
            return false;
        }
        if(!super.getAttribute(form, password1).equals(super.getAttribute(form, password2))) {
            LOGGER.debug("");
            return false;
        }
    
        return true;
    }
}
    