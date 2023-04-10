package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChooseBrowser {

    public static void initBrowser(Browsers browser) {
        if (browser.equals(Browsers.YANDEX)) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\barat\\Documents\\YandexDriver\\yandexdriver.exe");

        } else {
            WebDriverManager.chromedriver().setup();
        }

    }
}
