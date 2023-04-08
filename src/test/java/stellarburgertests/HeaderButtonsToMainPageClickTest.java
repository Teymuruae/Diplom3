package stellarburgertests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import utils.Randomizer;
import utils.Url;

@RunWith(Parameterized.class)
public class HeaderButtonsToMainPageClickTest {
    private static WebDriver driver;
    private MainPage mainPage;
    private Header header;
    private static RegistrationPage registrationPage;
    private static LoginPage loginPage;
    private static UserAccountPage userAccountPage;
    private String button;
    private static String email;
    private static String password;

    public HeaderButtonsToMainPageClickTest(String button) {
        this.button = button;
    }


    @Parameterized.Parameters
    public static Object[][] setData() {
        return new Object[][]{
                {"konstruktor"},
                {"img"}
        };
    }


    @BeforeClass
    public static void actsBeforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Url.getRegistrationPageUrl());
        email = Randomizer.getRandomEmail();
        password = Randomizer.getText();

        registrationPage = new RegistrationPage(driver);
        registrationPage.userRegistration(Randomizer.getText(), email, password);

        loginPage = new LoginPage(driver);
        loginPage.waitTextVhod();
        driver.quit();
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

        if (button.equals("konstruktor")) {
            header.clickKonstruktorButton();
        } else {
            header.clickStellarBurgerImg();
        }
        Assert.assertTrue(mainPage.getH1Text().isDisplayed());
    }

    @After
    public void tearDown() {
        mainPage.clickPersonalAccountLink();
        userAccountPage.waitInfoText();
        userAccountPage.clickExitButton();
        driver.quit();
    }

    @Test
    public void headerButtonToMainPageInChromeTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\barat\\Documents\\YandexDriver\\yandexdriver.exe");
        setUp();

    }

    @Test
    public void headerButtonToMainPageInYandexBrowserTest() {
        WebDriverManager.chromedriver().setup();
        setUp();

    }


}
