package stellarburgertests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.RegistrationPage;
import utils.Randomizer;

import java.time.Duration;

@RunWith(Parameterized.class)
public class UserRegistrationTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private String registrationUrl = "https://stellarburgers.nomoreparties.site/register";
    private String password;
    private String name = "Ivan";

    public UserRegistrationTest(String password) {
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] setData() {
        return new Object[][]{
                {"testovyy123"},
                {"test"}
        };
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        registrationPage = new RegistrationPage(driver);
        driver.get(registrationUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        loginPage = new LoginPage(driver);

    }


    @Test
    public void successfulRegistrationTest() {

        registrationPage.userRegistration(name, Randomizer.getRandomEmail(), password);
        if (password.length() >= 6) {
            Assert.assertTrue(loginPage.getH2VhodText().isDisplayed());
        } else {
            Assert.assertTrue(registrationPage.getIncorrectPasswordMessage().isDisplayed());
        }


    }
}

