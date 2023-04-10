package stellarburgertests;

import api.UserMethods;
import browsers.Browsers;
import browsers.ChooseBrowser;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import utils.Randomizer;
import utils.Url;

import java.time.Duration;


public class UserLoginTest {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static MainPage mainPage;
    private PasswordRecoveryPage passwordRecoveryPage;
    private static RegistrationPage registrationPage;
    private static String email;
    private static String password;
    private UserAccountPage userAccountPage;
    private static String token;
    private static ValidatableResponse createUserResponse;
    private static UserMethods userMethods;


    public void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        registrationPage = new RegistrationPage(driver);
        userAccountPage = new UserAccountPage(driver);

    }

    @BeforeClass
    public static void before() {
        email = Randomizer.getRandomEmail();
        password = Randomizer.getText();
        userMethods = new UserMethods();

        createUserResponse = userMethods.createUser(email, password, Randomizer.getText(), HttpStatus.SC_OK);
        token = createUserResponse.extract().path("accessToken");
    }

    @AfterClass
    public static void clear() {
        userMethods = new UserMethods();
        userMethods.deleteUser(token, HttpStatus.SC_ACCEPTED);
    }


    @Test
    public void loginUserFromMainPageClickButtonTest() {
        ChooseBrowser.initBrowser(Browsers.YANDEX);
        setUp();
        driver.get(Url.getMainPageUrl());

        mainPage.waitH1Text();
        mainPage.clickEnterInAccountButton();
        loginPage.login(email, password);

    }

    @Test
    public void loginUserFromMainPageClickLinkTest() {
        ChooseBrowser.initBrowser(Browsers.YANDEX);
        setUp();
        driver.get(Url.getMainPageUrl());

        mainPage.waitH1Text();
        mainPage.clickPersonalAccountLink();
        loginPage.login(email, password);

    }

    @Test
    public void loginUserFromPasswordRecoveryPageTest() {
        ChooseBrowser.initBrowser(Browsers.YANDEX);
        setUp();
        driver.get(Url.getRecoveryPasswordPageUrl());

        passwordRecoveryPage.waitForH2Text();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        passwordRecoveryPage.clickEnterAccountLink();
        loginPage.login(email, password);

    }

    @Test
    public void loginUserFromRegistrationPageTest() {
        ChooseBrowser.initBrowser(Browsers.YANDEX);
        setUp();
        driver.get(Url.getRegistrationPageUrl());

        registrationPage.h2TextWait();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        registrationPage.pressLoginButton();
        loginPage.login(email, password);


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
