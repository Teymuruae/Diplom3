package stellarburgertests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import utils.Url;

import java.time.Duration;


public class PerehodyKRazdelamTest {

    private WebDriver driver;
    private MainPage mainPage;

    public void setUp() {
        driver = new ChromeDriver();
        driver.get(Url.getMainPageUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        mainPage = new MainPage(driver);
        mainPage.waitH1Text();
    }

    @Test
    public void perehoKRazdeluBulkiTest() {
        setUp();
        if (!mainPage.getBulkiButtonParentWebElement().getAttribute("class").contains("current")) {
            mainPage.clickBulkiButton();

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        Assert.assertTrue(mainPage.getBulkiButtonParentWebElement()
                .getAttribute("class").contains("current"));
    }

    @Test
    public void perehoKRazdeluSousyTest() {
        setUp();
        if (!mainPage.getSousyButtonParentWebElement().getAttribute("class").contains("current")) {
            mainPage.clickSousyButton();

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        Assert.assertTrue(mainPage.getSousyButtonParentWebElement()
                .getAttribute("class").contains("current"));

    }

    @Test
    public void perehoKRazdeluNachinkiTest() {
        setUp();
        if (!mainPage.getNachinkiButtonParentWebElement().getAttribute("class").contains("current")) {
            mainPage.clickNachinkiButton();

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        Assert.assertTrue(mainPage.getNachinkiButtonParentWebElement()
                .getAttribute("class").contains("current"));

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
