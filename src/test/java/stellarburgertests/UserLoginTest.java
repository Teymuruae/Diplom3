package stellarburgertests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import utils.Randomizer;
import utils.Url;

@RunWith(Parameterized.class)
public class UserLoginTest {
    private static WebDriver driver;
    private String switcher;
    private LoginPage loginPage;
    private MainPage mainPage;
    private PasswordRecoveryPage passwordRecoveryPage;
    private static RegistrationPage registrationPage;
    private static String email;
    private static String password;
    private UserAccountPage userAccountPage;

    public UserLoginTest(String switcher) {
        this.switcher = switcher;
    }

    @Parameterized.Parameters
    public static Object[][] setData() {
        return new Object[][]{

                {"MainPage"},
                {"MainPage2"},
                {"PasswordRecoveryPage"},
                {"RegistrationPage"}
        };
    }

    public void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        registrationPage = new RegistrationPage(driver);
        userAccountPage = new UserAccountPage(driver);

        switch (switcher) {
            case "MainPage":
                driver.get(Url.getMainPageUrl());

                mainPage.waitH1Text();
                mainPage.clickEnterInAccountButton();
                break;
            case "MainPage2":
                driver.get(Url.getMainPageUrl());

                mainPage.waitH1Text();
                mainPage.clickPersonalAccountLink();
                break;
            case "PasswordRecoveryPage":
                driver.get(Url.getRecoveryPasswordPageUrl());

                passwordRecoveryPage.waitForH2Text();
                passwordRecoveryPage.clickEnterAccountLink();
                break;
            case "RegistrationPage":
                driver.get(Url.getRegistrationPageUrl());

                registrationPage.h2TextWait();
                registrationPage.pressLoginButton();
                break;
        }
        loginPage.login(email, password);

    }

    @BeforeClass
    public static void before() {
        driver = new ChromeDriver();
        driver.get(Url.getRegistrationPageUrl());
        email = Randomizer.getRandomEmail();
        password = Randomizer.getText();
        registrationPage = new RegistrationPage(driver);
        registrationPage.userRegistration(Randomizer.getText(), email, password);
        driver.quit();
    }


    @Test
    public void loginUserInYandexBrowserTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\barat\\Documents\\YandexDriver\\yandexdriver.exe");
        setUp();

    }

    @Test
    public void loginUserInChromeBrowserTest() {
        WebDriverManager.chromedriver().setup();
        setUp();
    }

    @After
    public void tearDown() {
        mainPage.waitH1Text();
        mainPage.clickPersonalAccountLink();
        userAccountPage.waitInfoText();
        userAccountPage.clickExitButton();
        driver.quit();
    }


}
