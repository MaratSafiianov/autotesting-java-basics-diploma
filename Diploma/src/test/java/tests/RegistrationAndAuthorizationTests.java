package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.RegistrationAndAuthorizationPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RegistrationAndAuthorizationTests {
    private WebDriver driver;
    private WebDriverWait wait;

    String user = "tester_alma8";
    String email = "tester8@tester8.com";
    String password = "1234qwer";

    @BeforeEach
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1600, 1000));
        driver.navigate().to("https://intershop5.skillbox.ru");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    // 1. Регистрация на сайте
    @Test
    public void registerUser_userRegistered() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        RegistrationAndAuthorizationPage registrationAndAuthorizationPage = new RegistrationAndAuthorizationPage(driver);

        // act
        mainPage.enterButtonHeadingLocator.click(); // нажимаем на кнопку "Войти" в хэдере
        registrationAndAuthorizationPage.registerButtonLocator.click(); // нажимаем на кнопку "Зарегистрироваться"

        registrationAndAuthorizationPage.userInputLocator.sendKeys(user); // вводим имя
        registrationAndAuthorizationPage.emailInputLocator.sendKeys(email); // вводим мейл
        registrationAndAuthorizationPage.passwordInputLocator.sendKeys(password); // вводим пароль
        registrationAndAuthorizationPage.finalRegisterButtonLocator.click(); // нажимаем на кнопку "Зарегистрироваться" на странице регистрации
        String textRegistrationCompleted = registrationAndAuthorizationPage.registrationCompletedLocator.getText();

        // assert
        Assertions.assertEquals("Регистрация завершена", textRegistrationCompleted, "Регистрация не совершена");
    }

    // 2. Авторизация на сайте через имя пользователя
    @Test
    public void authorizeUserWithName_userAuthorized() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        RegistrationAndAuthorizationPage registrationAndAuthorizationPage = new RegistrationAndAuthorizationPage(driver);

        // act
        mainPage.enterButtonHeadingLocator.click(); // нажимаем на кнопку "Войти" в хэдере
        registrationAndAuthorizationPage.userNameOrEmailInputLocator.sendKeys(user); // вводим имя
        registrationAndAuthorizationPage.AuthorizationPasswordInputLocator.sendKeys(password); // вводим пароль
        registrationAndAuthorizationPage.enterButtonLocator.click(); // нажимаем на кнопку "Войти" на странице аккаунта
        String textGreeting = registrationAndAuthorizationPage.greetingLocator.getText();

        // assert
        Assertions.assertEquals(user, textGreeting, "Отображается неверное имя пользователя");
    }

    // 3. Авторизация на сайте через почту
    @Test
    public void authorizeUserWithEmail_userAuthorized() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        RegistrationAndAuthorizationPage registrationAndAuthorizationPage = new RegistrationAndAuthorizationPage(driver);

        // act
        mainPage.enterButtonHeadingLocator.click(); // нажимаем на кнопку "Войти" в хэдере
        registrationAndAuthorizationPage.userNameOrEmailInputLocator.sendKeys(email); // вводим мейл
        registrationAndAuthorizationPage.AuthorizationPasswordInputLocator.sendKeys(password); // вводим пароль
        registrationAndAuthorizationPage.enterButtonLocator.click(); // нажимаем на кнопку "Войти" на странице аккаунта
        String textGreeting = registrationAndAuthorizationPage.greetingLocator.getText();

        // assert
        Assertions.assertEquals(user, textGreeting, "Отображается неверное имя пользователя");
    }
}