import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

public class Test {
    private final static Color LABEL_ACTION_16 = Color.fromString("#f84147");
    private final static Color LABEL_ACTION = Color.fromString("rgb(248, 65, 71)");
    private final static Color LABEL_TOP_SELLING = Color.fromString("rgb(255, 169, 0)");
    private final static Color LABEL_SUPER_PRICE = Color.fromString("rgb(255, 92, 0)");

    static void checkColorsOfLabels() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://rozetka.com.ua/");
            WebElement searchRozetka = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='search']")));
            searchRozetka.sendKeys("Ноутбуки");
            searchRozetka.sendKeys(Keys.ENTER);

            Color label16procentBgColor = Color
                    .fromString(driver.findElement(By.xpath("//span[@class='goods-tile__label promo-label promo-label_type_action ng-star-inserted']"))
                            .getCssValue("background-color"));
            System.out.println(label16procentBgColor.equals(LABEL_ACTION_16));

            Color labelActionBgColor = Color
                    .fromString(driver.findElement(By.xpath("//span[@class='goods-tile__label promo-label promo-label_type_action ng-star-inserted']"))
                            .getCssValue("background-color"));
            System.out.println(labelActionBgColor.equals(LABEL_ACTION));

            Color labelTopOfSellBgColor = Color
                    .fromString(driver.findElement(By.xpath("//span[@class='goods-tile__label promo-label promo-label_type_popularity ng-star-inserted']"))
                            .getCssValue("background-color"));
            System.out.println(labelTopOfSellBgColor.equals(LABEL_TOP_SELLING));

            Color labelSuperPriceBgColor = Color
                    .fromString(driver.findElement(By.xpath("//span[@class='goods-tile__label promo-label promo-label_type_superprice ng-star-inserted']"))
                            .getCssValue("background-color"));
            System.out.println(labelSuperPriceBgColor.equals(LABEL_SUPER_PRICE));

        } finally {
            driver.quit();
        }
    }

    static void buyNotebook(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://rozetka.com.ua/");
            WebElement searchRozetka = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='search']")));
            searchRozetka.sendKeys("Ноутбуки");
            searchRozetka.sendKeys(Keys.ENTER);

            WebElement selectElement = driver.findElement(By.xpath("//select[@class='select-css ng-untouched ng-pristine ng-valid ng-star-inserted']"));
            Select selectObject = new Select(selectElement);
            selectObject.selectByIndex(5);

            String mySelectedOption = selectObject.getFirstSelectedOption().getText();
            System.out.println(mySelectedOption);

            WebElement buyButton = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='buy-button goods-tile__buy-button ng-star-inserted']")));
            Actions action = new Actions(driver);
            action.moveToElement(buyButton).perform();
            buyButton.click();

            WebElement basket = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='header__button ng-star-inserted header__button--active']")));
            basket.click();

            WebElement cartProduct = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cart-product__main']")));
            System.out.println(cartProduct.getText());

            getScreenShot(driver);

        } finally {
            driver.quit();
        }
    }

    public static void getScreenShot(WebDriver driver) {
        TakesScreenshot scr = ((TakesScreenshot) driver);
        byte[] screenshot = scr.getScreenshotAs(OutputType.BYTES);

        String fileName = "screenshot.png";
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            fileOutputStream.write(screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        //checkColorsOfLabels();
        buyNotebook();
    }
}
