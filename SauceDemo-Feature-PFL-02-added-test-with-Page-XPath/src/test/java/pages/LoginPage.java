package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utils.AllureUtils.takeScreenshot;

public class LoginPage {
    WebDriver driver;

    By userField = By.xpath("//*[@id='user-name']");
    By passwordField = By.xpath("//*[@id='password']");
    By loginButton = By.xpath("//*[@id='login-button']");
    By errorMessage = By.cssSelector("[data-test=error]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Открытие страницы LoginPage")
    public void open() {
        driver.get("https://www.saucedemo.com/");
        takeScreenshot(driver);
    }

    @Step("Вход в систему с логином: {user} и паролем {password}")
    public void login(String user, String password) {
        driver.findElement(userField).sendKeys(user);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        takeScreenshot(driver);
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
