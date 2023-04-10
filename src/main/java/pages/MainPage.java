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

public class MainPage {
    private WebDriver driver;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[text() = 'Личный Кабинет']")
    private WebElement personalAccountLink;

    @FindBy(xpath = "//button[contains(@class, 'button_button')]")
    private WebElement enterInAccountButton;

    @FindBy(xpath = "//h1[contains(@class, 'type_main-large')]")
    private WebElement h1SoberiteBurgerText;

    @FindBy(xpath = "//span[text() = 'Соусы']//parent::div")
    private WebElement sousyButtonParentWebElement;

    @FindBy(xpath = "//span[text() = 'Булки']//parent::div")
    private WebElement bulkiButtonParentWebElement;

    @FindBy(xpath = "//span[text() = 'Начинки']//parent::div")
    private WebElement nachinkiButtonParentWebElement;

    @FindBy(xpath = "//span[text() = 'Соусы']")
    private WebElement sousyButton;

    @FindBy(xpath = "//span[text() = 'Булки']")
    private WebElement bulkiButton;

    @FindBy(xpath = "//span[text() = 'Начинки']")
    private WebElement nachinkiButton;

    @FindBy(xpath = "//h2[text() = 'Начинки']")
    private WebElement nachinkiText;

    @FindBy(xpath = "//h2[text() = 'Булки']")
    private WebElement bulkiText;
    @FindBy(xpath = "//h2[text() = 'Соусы']")
    private WebElement sousyText;

    @Step
    @DisplayName("click on button 'lichniy kabinet'")
    public void clickPersonalAccountLink() {
        waitBeforeClick(personalAccountLink);
        personalAccountLink.click();
    }

    @Step
    @DisplayName("click on button 'voiti v akkaunt'")
    public void clickEnterInAccountButton() {
        waitBeforeClick(enterInAccountButton);
        enterInAccountButton.click();
    }

    public void waitH1Text() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(h1SoberiteBurgerText));
    }

    public void waitBeforeClick(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(element));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public void waitElementIsPresent(WebElement ... element){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public WebElement getH1Text() {
        return h1SoberiteBurgerText;
    }


    public void clickBulkiButton() {
        waitBeforeClick(bulkiButton);
        bulkiButton.click();
    }

    public void clickSousyButton() {
        waitBeforeClick(sousyButton);
        sousyButton.click();
    }

    public void clickNachinkiButton() {
        waitBeforeClick(nachinkiButton);
        nachinkiButton.click();

    }

    public WebElement getBulkiButtonParentWebElement() {

        return bulkiButtonParentWebElement;
    }

    public WebElement getSousyButtonParentWebElement() {

        return sousyButtonParentWebElement;
    }

    public WebElement getNachinkiButtonParentWebElement() {

        return nachinkiButtonParentWebElement;
    }

}
