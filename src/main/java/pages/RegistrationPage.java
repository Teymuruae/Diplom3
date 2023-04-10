package pages;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
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

    @FindBy(xpath = "//a[@class = 'Auth_link__1fOlj']")
    private WebElement loginButton;

    public void h2TextWait() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(h2RegistrationText));
    }

    public WebElement getIncorrectPasswordMessage() {
        return incorrectPasswordMessage;
    }

    @Step
    public void fillNameField(String name) {
        nameField.sendKeys(name);

    }

    @Step
    public void fillEmailField(String email) {
        emailField.sendKeys(email);
    }

    @Step
    public void fillPasswordField(String password) {
        passwordField.sendKeys(password);
    }

    @Step
    public void pressRegistrationButton() {
        registrationButton.click();
    }

    @Step
    public void pressLoginButton() {
        waitBeforeClick(loginButton);
        loginButton.click();
    }

    @Step
    @DisplayName("registration in account")
    public void userRegistration(String name, String email, String password) {
        h2TextWait();
        fillNameField(name);
        fillEmailField(email);
        fillPasswordField(password);
        pressRegistrationButton();
    }

    public void waitBeforeClick(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.elementToBeClickable(element));


    }
}