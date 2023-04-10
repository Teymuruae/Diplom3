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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.Header;
import pages.LoginPage;
import pages.MainPage;
import pages.UserAccountPage;
import utils.Randomizer;
import utils.Url;


public class HeaderButtonsToMainPageClickTest {
    private static WebDriver driver;
    private static MainPage mainPage;
    private Header header;
    private static LoginPage loginPage;
    private static UserAccountPage userAccountPage;
    private static String email;
    private static String password;
    private static String token;
    private static UserMethods userMethods;
    private static ValidatableResponse createUserResponse;

    @BeforeClass
    public static void actsBeforeClass() {
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


    public void setUp() {
        driver = new ChromeDriver();
        driver.get(Url.getLoginPageUrl());

        loginPage = new LoginPage(driver);
        loginPage.login(email, password);


        mainPage = new MainPage(driver);
        mainPage.waitH1Text();
        mainPage.clickPersonalAccountLink();

        userAccountPage = new UserAccountPage(driver);
        userAccountPage.waitInfoText();

        header = new Header(driver);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void headerButtonKonstruktorToMainPageTest() {
        ChooseBrowser.initBrowser(Browsers.YANDEX);
        setUp();
        header.clickKonstruktorButton();
    }

    @Test
    public void headerButtonStellarBurgerToMainPageTest() {
        ChooseBrowser.initBrowser(Browsers.YANDEX);
        setUp();
        header.clickStellarBurgerImg();
    }


}
