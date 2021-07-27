import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;


public class WikiTest {
    WebDriver webDriver;

    @BeforeEach
    public void Init() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no sandbox");
        options.addArguments("--disable--dev-shn-usage");
        options.addArguments("--headless");
        webDriver = new ChromeDriver(options);

        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @Test
    public void TestEnglishButton() {
        webDriver.get("https://www.wikipedia.org/");
        WebElement englishButton = webDriver.findElement(By.id("js-link-box-en"));
        englishButton.click();
        String expectedTitle = "Welcome to Wikipedia,";
        WebElement titleDiv = webDriver.findElement(By.xpath("//*[@id=\"mp-welcome\"]"));
        Assertions.assertEquals(expectedTitle, titleDiv.getText());

    }
    @AfterEach
    public void Close(){
        webDriver.quit();
    }

}
