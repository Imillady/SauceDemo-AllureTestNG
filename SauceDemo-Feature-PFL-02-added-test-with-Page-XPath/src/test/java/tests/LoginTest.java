package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.AllureUtils;

import static org.testng.Assert.assertEquals;

@Listeners(TestListener.class)
public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;


    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }

    @Test (testName = "Проверка успешного входа на сайт")
    @Description("Вход на сайт с верным логином и паролем")
    @Severity(SeverityLevel.NORMAL)
    @Epic("SauceDemo-1")
    @Feature("Login in SauceDemo")
    @Story("Успешный вход на сайт")
    @TmsLink("www.jira.com/ITM-1")
    @Issue("www.jira.com/ITM-2")
    @Flaky //символ нестабильности "бомба"
    public void CheckPositiveSingUp() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(
                productsPage.getTitle(),
                "Products",
                "Вход не выполнен");
    }

    @Test
    public void CheckNegativeSingUp1() {
        loginPage.open();
        loginPage.login("123", "123");
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Сообщение об ошибке не появилось или не корректно");
    }

    @Test
    public void CheckNegativeSingUp2() {
        loginPage.open();
        loginPage.login("", "");
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "Сообщение об ошибке не появилось или не корректно");
    }

    @Test
    public void CheckNegativeSingUp3() {
        loginPage.open();
        loginPage.login("user-name", "");
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "Сообщение об ошибке не появилось или не корректно");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()){
            AllureUtils.takeScreenshot(driver);
        }
        driver.quit();
    }
}
