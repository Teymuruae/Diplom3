package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserAccountPage {
    private WebDriver driver;

    public UserAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = "button.Account_button__14Yp3")
    private WebElement exitButton;

    @FindBy(css = "p.Account_text__fZAIn")
    private WebElement infoText;

    @Step
    public void clickExitButton() {
        exitButton.click();
    }


    public WebElement getInfoTextWebElement() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(infoText));
        return infoText;
    }

    public void waitInfoText() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(infoText));
    }
}
