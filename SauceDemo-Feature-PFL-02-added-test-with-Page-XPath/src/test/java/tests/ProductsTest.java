package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

public class ProductsTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void CheckFilterPriceHighLow() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.selectFilter("Price (high to low)");
        Assert.assertEquals(productsPage.firstItemPrice(), "$49.99",
                "Первый товар не самый дорогой");
    }

    @Test
    public void CheckFilterPriceLowHigh() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.selectFilter("Price (low to high)");
        Assert.assertEquals(productsPage.firstItemPrice(), "$7.99",
                "Первый товар не самый дешевый");
    }

    @Test
    public void CheckFilterNameAtoZ() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.selectFilter("Name (A to Z)");
        Assert.assertEquals(productsPage.firstItemNameA(), "Sauce Labs Backpack",
                "Первый товар не первый по алфавиту");
    }

    @Test
    public void CheckFilterNameZtoA() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.selectFilter("Name (Z to A)");
        Assert.assertEquals(productsPage.firstItemNameZ(), "Test.allTheThings() T-Shirt (Red)",
                "Первый товар не последний по алфавиту");
    }

    @AfterMethod(alwaysRun = true)
    public void quit() {
        driver.quit();
    }
}

