package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.TestListener;

import static org.testng.Assert.assertEquals;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @Test(testName = "Успешный вход на сайт", description = "Валидный логин и пароль",
            groups = {"Smoke"})
    @Description("Вход на сайт с верным логином и паролем")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("SauceDemo-1")
    @Feature("Login in SauceDemo")
    @TmsLink("www.jira.com/ITM-1")
    public void CheckPositiveSingUp() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(
                productsPage.getTitle(),
                "Products",
                "Вход не выполнен");
    }

    @DataProvider(name = "LoginData")
    public Object[][] LoginData() {
        return new Object[][]{
                {"123", "123", "Epic sadface: Username and password do not match any user in this service"},
                {"", "", "Epic sadface: Username is required"},
                {"user-name", "", "Epic sadface: Password is required"}
        };
    }

    @Test(dataProvider = "LoginData", testName = "Вход на сайт с ошибкой", description = "Не валидный логин и пароль",
            groups = {"Smoke"})
    @Description("Вход на сайт с не валидными логин и пароль")
    @Severity(SeverityLevel.NORMAL)
    @Epic("SauceDemo-1")
    @Feature("Login in SauceDemo")
    @TmsLink("www.jira.com/ITM-1")
    public void checkNegativeLogin(String user, String password, String message) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(
                loginPage.getErrorMessage(),
                message,
                "Сообщение об ошибке не появилось или не корректно");
    }
}
