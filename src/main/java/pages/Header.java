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

public class Header {
    private WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//div[contains(@class, 'AppHeader')]")
    private WebElement stellarBurgerImg;
    @FindBy(xpath = "//p[text() = 'Конструктор' ]")
    private WebElement konstruktorButton;

    @Step
    @DisplayName("click on img StellarBurger")
    public void clickStellarBurgerImg() {
        waitBeforeClick(stellarBurgerImg);
        stellarBurgerImg.click();
    }

    @Step
    @DisplayName("click on button Konstruktor")
    public void clickKonstruktorButton() {
        waitBeforeClick(konstruktorButton);
        konstruktorButton.click();
    }

    public void waitBeforeClick(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.elementToBeClickable(element));
    }
}