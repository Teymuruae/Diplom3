package stellarburgertests;

import browsers.Browsers;
import browsers.ChooseBrowser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.RegistrationPage;
import utils.Randomizer;
import utils.Url;

import java.time.Duration;

public class UserRegistrationNegativeTest {

    private static WebDriver driver;
    private static RegistrationPage registrationPage;
    private static LoginPage loginPage;
    private String name = "Ivan";
    private String passwordFalse = "test";
    private String email;

    public void setUp(String password) {
        driver = new ChromeDriver();
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        email = Randomizer.getRandomEmail();
        driver.get(Url.getRegistrationPageUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        registrationPage.userRegistration(Randomizer.getText(), email, password);

    }

    @Test
    public void registrationFalsePasswordLessThan6SymbolsTest() {
        ChooseBrowser.initBrowser(Browsers.YANDEX);
        WebDriverManager.chromedriver().setup();
        setUp(passwordFalse);
        Assert.assertTrue(registrationPage.getIncorrectPasswordMessage().isDisplayed());

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
