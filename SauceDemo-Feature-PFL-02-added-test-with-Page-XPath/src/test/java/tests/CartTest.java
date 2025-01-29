package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

public class CartTest {
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
    public void CheckAddProduct() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Bike Light");
        softAssert.assertEquals(cartPage.pushCart(),
                "1",
                "Кол-во товаров не соотвествует");
        cartPage.goToCart();
        softAssert.assertEquals(cartPage.itemNameCart(),
                productsPage.nameProduct("Sauce Labs Bike Light"),
                "Товар отличается от добавленого");
        softAssert.assertAll();
    }

    @Test
    public void CheckRemoveProduct() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Bike Light");
        softAssert.assertEquals(cartPage.pushCart(),
                "1",
                "Кол-во товаров не соотвествует");
        productsPage.addToCart("Sauce Labs Bike Light");
        softAssert.assertTrue(cartPage.isBadgeDisplay(),
                "Кол-во товаров около корзины отображается");
        cartPage.goToCart();
        softAssert.assertTrue(cartPage.firstItemInCart(),
                "В корзине товар отображается");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void quit() {
        driver.quit();
    }
}
