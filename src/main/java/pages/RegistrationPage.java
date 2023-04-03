package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private WebDriver driver;

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[text() = 'Регистрация']")
    private WebElement h2RegistrationText;

    @FindBy(xpath = "//label[text() = 'Имя']//parent::div//input")
    private WebElement nameField;


    @FindBy(xpath = "//label[text() = 'Email']//parent::div//input")
    private WebElement emailField;

    @FindBy(xpath = "//input[@type = 'password']")
    private WebElement passwordField;

    @FindBy(xpath = "//form[@class = 'Auth_form__3qKeq mb-20']//button")
    private WebElement registrationButton;

    @FindBy(xpath = "//p[@class = 'input__error text_type_main-default']")
    private WebElement incorrectPasswordMessage;

    public void h2TextWait(){
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOf(h2RegistrationText));
    }

    public WebElement getIncorrectPasswordMessage(){
        return incorrectPasswordMessage;
    }
    public void fillNameField(String name){
        nameField.sendKeys(name);

    }

    public void fillEmailField(String email){
        emailField.sendKeys(email);
    }

    public void fillPasswordField(String password){
        passwordField.sendKeys(password);
    }

    public void pressRegistrationButton(){
        registrationButton.click();
    }

    public void userRegistration(String name, String email, String password){
        h2TextWait();
        fillNameField(name);
        fillEmailField(email);
        fillPasswordField(password);
        pressRegistrationButton();
    }


}
