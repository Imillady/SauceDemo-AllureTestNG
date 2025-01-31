package tests;

import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.TestListener;

@Listeners(TestListener.class)
public class CartTest extends BaseTest {

    @Test(testName = "Добавление товара в корзину",
            description = "Товар добален в коризу и в ней отображается", groups = {"Smoke"})
    @Description("Добавление товара в коризину")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("SauceDemo-1")
    @TmsLink("www.jira.com/ITM-1")
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

    @Test(testName = "Удаление товара из коризины",
            description = "Товар добавляется и удаляется из корзины", groups = {"Smoke"})
    @Description("Удаление товара из коризины")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("SauceDemo-1")
    @TmsLink("www.jira.com/ITM-1")
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
}
