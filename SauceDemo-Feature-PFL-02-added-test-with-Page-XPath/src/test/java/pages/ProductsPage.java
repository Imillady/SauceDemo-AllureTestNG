package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static utils.AllureUtils.takeScreenshot;

public class ProductsPage {
    WebDriver driver;

    String itemsFilter = "//option[text()='%s']";
    By title = By.cssSelector("[data-test='title']");
    String addToCartPattern = "//div[text()='%s']//ancestor::div[@class='inventory_item']//button";
    String nameProduct = "//div[text()='%s']";
    String itemPrice = "//div[@data-test='inventory-item-price']";
    String itemName = "//div[@data-test='inventory-item-name']";

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public String nameProduct(String product) {
        return driver.findElement(By.xpath(String.format(nameProduct, product))).getText();
    }

    @Step("Добавление в крзину товара с именем: {product}")
    public void addToCart(String product) {
        driver.findElement(By.xpath(String.format(addToCartPattern, product))).click();
    }

    @Step("Выбор фильтра в каталоге товаров с именем: {filter}")
    public void selectFilter(String filter) {
        driver.findElement(By.xpath(String.format(itemsFilter, filter))).click();
        takeScreenshot(driver);
    }

    public String firstItemPrice() {
        List<WebElement> priceElements = driver.findElements(By.xpath(itemPrice));
        return priceElements.get(0).getText();
    }

    @Step("Очередность наименований после фильтра в каталоге товаров: A-Z")
    public String firstItemNameA() {
        List<WebElement> nameElements = driver.findElements(By.xpath(itemName));
        List<String> nameTexts = new ArrayList<>();
        for (WebElement element : nameElements) {
            nameTexts.add(element.getText());
        }
        Collections.sort(nameTexts);
        return nameTexts.get(0);
    }

    @Step("Очередность наименований после фильтра в каталоге товаров: Z-A")
    public String firstItemNameZ() {
        List<WebElement> nameElements = driver.findElements(By.xpath(itemName));
        List<String> nameTexts = new ArrayList<>();
        for (WebElement element : nameElements) {
            nameTexts.add(element.getText());
        }
        nameTexts.sort(Collections.reverseOrder());
        return nameTexts.get(0);
    }

}
