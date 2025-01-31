package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.TestListener;

@Listeners(TestListener.class)
public class ProductsTest extends BaseTest {

    @Test(testName = "Фильтр от высокой к низкой цене",
            description = "Включение фильтра от высокой к низкой цене", groups = {"Smoke"})
    @Description("Фильтр от высокой к низкой цене")
    @Severity(SeverityLevel.NORMAL)
    @Epic("SauceDemo-1")
    @Feature("Login in SauceDemo")
    @TmsLink("www.jira.com/ITM-1")
    public void CheckFilterPriceHighLow() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.selectFilter("Price (high to low)");
        Assert.assertEquals(productsPage.firstItemPrice(), "$49.99",
                "Первый товар не самый дорогой");
    }

    @Test(testName = "Фильтр от низкой к высокой цене",
            description = "Включение фильтра от низкой к высокой цене", groups = {"Smoke"})
    @Description("Фильтр от низкой к высокой цене")
    @Severity(SeverityLevel.NORMAL)
    @Epic("SauceDemo-1")
    @Feature("Login in SauceDemo")
    @TmsLink("www.jira.com/ITM-1")
    public void CheckFilterPriceLowHigh() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.selectFilter("Price (low to high)");
        Assert.assertEquals(productsPage.firstItemPrice(), "$7.99",
                "Первый товар не самый дешевый");
    }

    @Test(testName = "Фильтр от A до Z",
            description = "Включение алфавитного фильтра от A к Z", groups = {"Smoke"})
    @Description("Фильтр от A до Z")
    @Severity(SeverityLevel.NORMAL)
    @Epic("SauceDemo-1")
    @Feature("Login in SauceDemo")
    @TmsLink("www.jira.com/ITM-1")
    public void CheckFilterNameAtoZ() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.selectFilter("Name (A to Z)");
        Assert.assertEquals(productsPage.firstItemNameA(), "Sauce Labs Backpack",
                "Первый товар не первый по алфавиту");
    }

    @Test(testName = "Фильтр от Z до A",
            description = "Включение алфавитного фильтра от Z к A", groups = {"Smoke"})
    @Description("Фильтр от Z до A")
    @Severity(SeverityLevel.NORMAL)
    @Epic("SauceDemo-1")
    @Feature("Login in SauceDemo")
    @TmsLink("www.jira.com/ITM-1")
    public void CheckFilterNameZtoA() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.selectFilter("Name (Z to A)");
        Assert.assertEquals(productsPage.firstItemNameZ(), "Test.allTheThings() T-Shirt (Red)",
                "Первый товар не последний по алфавиту");
    }

}

