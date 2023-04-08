package stellarburgertests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import pages.UserAccountPage;
import utils.Randomizer;
import utils.Url;

import java.time.Duration;

public class UserAccountTest {

    private static WebDriver driver;
    private static RegistrationPage registrationPage;
    private static LoginPage loginPage;
    private UserAccountPage userAccountPage;
    private MainPage mainPage;
    private static String email;
    private static String password;

    @BeforeClass
    public static void beforeAll() {
        driver = new ChromeDriver();
        driver.get(Url.getRegistrationPageUrl());
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        email = Randomizer.getRandomEmail();
        password = Randomizer.getText();
        registrationPage.userRegistration(Randomizer.getText(), email, password);
        loginPage.waitTextVhod();
        driver.quit();
    }


    public void setUp() {
        driver = new ChromeDriver();
        driver.get(Url.getLoginPageUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        mainPage = new MainPage(driver);
        mainPage.waitH1Text();
        mainPage.clickPersonalAccountLink();

        userAccountPage = new UserAccountPage(driver);
        Assert.assertTrue(userAccountPage.getInfoTextWebElement().isDisplayed());
    }

    @Test
    public void enterUserAccountInChromeTest() {
        WebDriverManager.chromedriver().setup();
        setUp();
    }

    @Test
    public void enterUserAccountInYandexBrowserTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\barat\\Documents\\YandexDriver\\yandexdriver.exe");
        setUp();
    }

    @Test
    public void exitFromUserAccountInChromeTest() {
        WebDriverManager.chromedriver().setup();
        setUp();
        userAccountPage.clickExitButton();
        Assert.assertTrue(loginPage.getH2VhodText().isDisplayed());

    }

    @Test
    public void exitUserAccountInYandexBrowserTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\barat\\Documents\\YandexDriver\\yandexdriver.exe");
        setUp();
        userAccountPage.clickExitButton();
        Assert.assertTrue(loginPage.getH2VhodText().isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }


}
