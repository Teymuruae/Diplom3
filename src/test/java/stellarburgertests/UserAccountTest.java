package stellarburgertests;

import api.UserMethods;
import browsers.Browsers;
import browsers.ChooseBrowser;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.UserAccountPage;
import utils.Randomizer;
import utils.Url;

import java.time.Duration;

public class UserAccountTest {

    private static WebDriver driver;
    private static LoginPage loginPage;
    private UserAccountPage userAccountPage;
    private static MainPage mainPage;
    private static String email;
    private static String password;
    private static String token;
    private static UserMethods userMethods;
    private static ValidatableResponse createUserResponse;


    @BeforeClass
    public static void beforeAll() {

        email = Randomizer.getRandomEmail();
        password = Randomizer.getText();
        userMethods = new UserMethods();

        createUserResponse = userMethods.createUser(email, password, Randomizer.getText(), HttpStatus.SC_OK);
        token = createUserResponse.extract().path("accessToken");
    }


    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get(Url.getLoginPageUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        loginPage = new LoginPage(driver);
        Thread.sleep(2000);
        loginPage.login(email, password);

        mainPage = new MainPage(driver);
        mainPage.waitH1Text();
        mainPage.clickPersonalAccountLink();

        userAccountPage = new UserAccountPage(driver);
        Assert.assertTrue(userAccountPage.getInfoTextWebElement().isDisplayed());

    }

    @Test
    public void enterUserAccountTest() throws InterruptedException {
        ChooseBrowser.initBrowser(Browsers.CHROME);
        setUp();
    }


    @Test
    public void exitFromUserAccountTest() throws InterruptedException {
        ChooseBrowser.initBrowser(Browsers.YANDEX);
        setUp();
        userAccountPage.clickExitButton();
        Assert.assertTrue(loginPage.getH2VhodText().isDisplayed());

    }

    @After
    public void tearDown() {
        driver.quit();
    }


    @AfterClass
    public static void clear() {
        userMethods = new UserMethods();
        userMethods.deleteUser(token, HttpStatus.SC_ACCEPTED);
    }
}
