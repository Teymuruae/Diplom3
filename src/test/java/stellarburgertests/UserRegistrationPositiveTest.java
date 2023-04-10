package stellarburgertests;

import api.UserMethods;
import browsers.Browsers;
import browsers.ChooseBrowser;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.WebStorage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import utils.Randomizer;
import utils.Url;

import java.time.Duration;

@RunWith(Parameterized.class)
public class UserRegistrationPositiveTest {
    private static WebDriver driver;
    private static RegistrationPage registrationPage;
    private static LoginPage loginPage;
    private String name = "Ivan";
    private String password;

    private String email;
    private MainPage mainPage;
    private String token;

    public UserRegistrationPositiveTest(String password) {
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] setData() {
        return new Object[][]{
                {"testovyy123"},
                {"qwer12"}
        };
    }

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
    public void registrationTruePasswordMoreThan6SymbolsTest() {
        ChooseBrowser.initBrowser(Browsers.YANDEX);

        setUp(password);
        Assert.assertTrue(loginPage.getH2VhodText().isDisplayed());


    }

    @After
    public void tearDown() {
        loginPage = new LoginPage(driver);
        loginPage.waitTextVhod();
        loginPage.login(email, password);

        mainPage = new MainPage(driver);
        mainPage.waitH1Text();

        token = ((WebStorage) driver).getLocalStorage().getItem("accessToken");
        UserMethods userMethods = new UserMethods();
        userMethods.deleteUser(token, HttpStatus.SC_ACCEPTED);
        driver.quit();
    }
}

