package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utils.AllureUtils.takeScreenshot;

public class CartPage {
    WebDriver driver;
    By goToCart = By.cssSelector("[data-test=shopping-cart-link]");
    By pushCart = By.cssSelector("[data-test=shopping-cart-badge]");
    By itemNameCart = By.cssSelector("[data-test=inventory-item-name]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToCart() {
        driver.findElement(goToCart).click();
        takeScreenshot(driver);
    }

    public String pushCart() {
        return driver.findElement(pushCart).getText();
    }

    public String itemNameCart() {
        return driver.findElement(itemNameCart).getText();
    }

    public boolean isBadgeDisplay() {
        return driver.findElements(pushCart).isEmpty();}

    public boolean firstItemInCart() {
        return driver.findElements(itemNameCart).isEmpty();
    }
}
