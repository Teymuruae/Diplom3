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
    @DisplayName("fill Email Field")
    public void fillEmailField(String name) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(emailField));
        emailField.sendKeys(name);
    }


    @Step
    @DisplayName("fill Password Field")
    public void fillPasswordField(String password) {
        passwordField.sendKeys(password);
    }

    @Step
    @DisplayName("click Enter Button in login page")

    public void clickEnterButton() {
        waitBeforeClick(enterButton);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        enterButton.click();
    }

    @Step
    @DisplayName("user full login")
    public void login(String email, String password) {
        waitTextVhod();
        fillEmailField(email);
        fillPasswordField(password);
        clickEnterButton();
    }

    public void waitBeforeClick(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(element));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }
}
