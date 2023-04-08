package stellarburgertests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import utils.Url;

import java.time.Duration;

@RunWith(Parameterized.class)
public class PerehodyKRazdelamTest {

    private WebDriver driver;
    private String chooseButton;
    private MainPage mainPage;

    public PerehodyKRazdelamTest(String chooseButton) {
        this.chooseButton = chooseButton;
    }

    @Parameterized.Parameters
    public static Object[][] setData() {
        return new Object[][]{
                {"Bulki"},
                {"Sousy"},
                {"Nachinki"}
        };
    }


    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get(Url.getMainPageUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        mainPage = new MainPage(driver);
        mainPage.waitH1Text();

        switch (chooseButton) {
            case "Bulki":
                if (!mainPage.getBulkiButtonParentWebElement().getAttribute("class").contains("current")) {
                    mainPage.clickBulkiButton();
                    Thread.sleep(2000);
                }
                Assert.assertTrue(mainPage.getBulkiButtonParentWebElement()
                        .getAttribute("class").contains("current"));
                break;
            case "Sousy":
                if (!mainPage.getSousyButtonParentWebElement().getAttribute("class").contains("current")) {
                    mainPage.clickSousyButton();
                    Thread.sleep(2000);
                }
                Assert.assertTrue(mainPage.getSousyButtonParentWebElement()
                        .getAttribute("class").contains("current"));
                break;
            case "Nachinki":
                if (!mainPage.getNachinkiButtonParentWebElement().getAttribute("class").contains("current")) {
                    mainPage.clickNachinkiButton();
                    Thread.sleep(2000);
                }
                Assert.assertTrue(mainPage.getNachinkiButtonParentWebElement()
                        .getAttribute("class").contains("current"));
        }
    }


    @Test
    public void perehodyKRazdelamChromeBrowserTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        setUp();
    }

    @Test
    public void perehodyKRazdelamInYandexBrowserTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\barat\\Documents\\YandexDriver\\yandexdriver.exe");
        setUp();

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
