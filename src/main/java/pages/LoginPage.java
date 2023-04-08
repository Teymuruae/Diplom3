package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[text() = 'Вход']")
    private WebElement h2VhodText;

    @FindBy(xpath = "//input[@name = 'name']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@type = 'password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(@class, 'button')]")
    private WebElement enterButton;

    @FindBy(xpath = "//a[text() = 'Восстановить пароль']")
    private WebElement forgotPasswordLink;

    public WebElement getH2VhodText() {
        return h2VhodText;
    }

    public void clickForgotPasswordLink() {
        forgotPasswordLink.click();
    }

    public void waitTextVhod() {
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.visibilityOf(h2VhodText));
    }

    @Step
    public void fillEmailField(String name) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(emailField));
        emailField.sendKeys(name);
    }


    @Step
    public void fillPasswordField(String password) {
        passwordField.sendKeys(password);
    }

    @Step
    public void clickEnterButton() {
        enterButton.click();
    }

    @Step
    public void login(String email, String password) {
        waitTextVhod();
        fillEmailField(email);
        fillPasswordField(password);
        clickEnterButton();
    }
}
