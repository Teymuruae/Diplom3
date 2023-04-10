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

public class PasswordRecoveryPage {

    private WebDriver driver;

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class = 'Auth_link__1fOlj']")
    private WebElement enterAccountLink;

    @FindBy(xpath = "//h2[text() = 'Восстановление пароля']")
    private WebElement h2Text;


    @DisplayName("click on button 'voiti'")
    @Step
    public void clickEnterAccountLink() {
        waitBeforeClick(enterAccountLink);
        enterAccountLink.click();
    }

    public void waitForH2Text() {
        new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(ExpectedConditions.visibilityOf(h2Text));
    }

    public void waitBeforeClick(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions. elementToBeClickable(element));

    }
}