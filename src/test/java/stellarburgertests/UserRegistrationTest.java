package stellarburgertests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.RegistrationPage;
import utils.Randomizer;
import utils.Url;

import java.time.Duration;

@RunWith(Parameterized.class)
public class UserRegistrationTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private String password;
    private String name = "Ivan";

    public UserRegistrationTest(String password) {
        this.password = password;

    }

    @Parameterized.Parameters
    public static Object[][] setData() {
        return new Object[][]{
                {"testovyy123"},
                {"qwer12"},
                {"test"}
        };
    }

    public void setUp() {
        driver = new ChromeDriver();
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        driver.get(Url.getRegistrationPageUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        registrationPage.userRegistration(Randomizer.getText(), Randomizer.getRandomEmail(), password);
        if (password.length() >= 6) {
            Assert.assertTrue(loginPage.getH2VhodText().isDisplayed());
        } else {
            Assert.assertTrue(registrationPage.getIncorrectPasswordMessage().isDisplayed());
        }
    }

    @Test
    public void registrationChromeBrowserTest() {
        WebDriverManager.chromedriver().setup();
        setUp();

    }

    @Test
    public void registrationYandexBrowserTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\barat\\Documents\\YandexDriver\\yandexdriver.exe");
        setUp();

    }


    @After
    public void tearDown() {
        driver.quit();
    }
}

