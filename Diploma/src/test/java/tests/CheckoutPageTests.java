package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.CheckoutPage;
import pages.MainPage;
import pages.ProductPage;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CheckoutPageTests {
    private WebDriver driver;
    private WebDriverWait wait;

    String product = "iPad 2020 32gb wi-fi"; // текст названия товара

    // Значения для полей при оформлении заказа
    String textFirstName = "Иван";
    String textLastName = "Иванов";
    String textAddress = "Мира, 7";
    String textCity = "Москва";
    String textState = "Московская";
    String textPostcode = "660001";
    String textPhone = "89119105678";
    String textEmail = "tester8@tester8.com";

    // Сертификат
    String textDiscount = "SERT500";
    BigDecimal numberDiscount = new BigDecimal(500);



    @BeforeEach
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1600, 1000));
        driver.navigate().to("https://intershop5.skillbox.ru");

        // Выполняем авторизацию
        driver.findElement(By.xpath("//div[@class='login-woocommerce']")).click();
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("tester_alma8");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1234qwer");
        driver.findElement(By.xpath("//button[@name='login']")).click();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    // 1. Добавить товар в корзину. Перейти к оформлению заказа. Не заполнять обязательные поля. Проверить отображение ошибки.
    @Test
    public void orderProductWithNotFilledParams_displayedErrors() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // act
        mainPage.searchFormLocator.sendKeys(product); // вводим названием продукта в окно поиска
        mainPage.searchFormButtonLocator.click(); // нажимаем кнопку поиска
        productPage.addToCartButtonLocator.click(); // нажимаем "В корзину"
        productPage.detailsLocator.click(); // нажимаем подробнее для перехода в корзину
        cartPage.checkoutButtonLocator.click(); // нажимаем кнопку "Оформить заказ" в корзине
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.checkoutButtonLocator));
        checkoutPage.checkoutButtonLocator.click(); // нажимаем кнопку "Оформить заказ" на странице оформления заказа

        // assert
        Assertions.assertAll(
                () -> Assertions.assertTrue(checkoutPage.errorLocator.isDisplayed(), "Не отобразилось предупреждение об обязательных для заполнения полях")
        );
    }

    // 2. Добавить товар в корзину. Перейти к оформлению заказа. Заполнить обязательные поля. Выбрать оплата при доставке. Проверить почту, сумму, метод оплаты, заголовок.
    @Test
    public void orderProductWithFilledParamsAndDirectPayment_displayedPositiveResult() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // act
        mainPage.searchFormLocator.sendKeys(product); // вводим названием продукта в окно поиска
        mainPage.searchFormButtonLocator.click(); // нажимаем кнопку поиска
        productPage.addToCartButtonLocator.click(); // нажимаем "В корзину"
        productPage.detailsLocator.click(); // нажимаем подробнее для перехода в корзину

        String textAmountForPayment = cartPage.amountForPaymentLocator.getText(); // запоминаем сумму к оплате
        textAmountForPayment = textAmountForPayment.replace(",", ".").replace("₽", ""); // меняем запятую на точку, убираем знак рубля
        BigDecimal numberAmountForPayment = new BigDecimal(textAmountForPayment); // переводим в формат BigDecimal

        cartPage.checkoutButtonLocator.click(); // нажимаем кнопку "Оформить заказ" в корзине
        checkoutPage.firstNameLocator.sendKeys(textFirstName); // заполняем поле имени
        checkoutPage.lastNameLocator.sendKeys(textLastName); // заполняем поле фамилии
        checkoutPage.addressLocator.sendKeys(textAddress); // заполняем поле адреса
        checkoutPage.cityLocator.sendKeys(textCity); // заполняем поле города
        checkoutPage.stateLocator.sendKeys(textState); // заполняем поле области
        checkoutPage.postcodeLocator.sendKeys(textPostcode); // заполняем поле индекса
        checkoutPage.phoneLocator.sendKeys(textPhone); // заполняем поле телефона
        checkoutPage.paymentMethodeUponDeliveryLocator.click(); // выбираем способ оплаты "Оплата при доставке"
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.checkoutButtonLocator));
        checkoutPage.checkoutButtonLocator.click(); // нажимаем кнопку "Оформить заказ" на странице оформления заказа

        String textCheckingEmail = checkoutPage.checkingEmailLocator.getText(); // получаем мейл из подтверждения

        String textСheckingAmount = checkoutPage.checkingAmountLocator.getText(); // запоминаем сумму к оплате из подтверждения
        textСheckingAmount = textСheckingAmount.replace(",", ".").replace("₽", ""); // меняем запятую на точку, убираем знак рубля
        BigDecimal numberСheckingAmount = new BigDecimal(textСheckingAmount); // переводим в формат BigDecimal

        String textСheckingPaymentMethod = checkoutPage.checkingPaymentMethodLocator.getText().toLowerCase(); // получаем метод оплаты из подтверждения
        String textApprove = checkoutPage.approveLocator.getText(); // получаем заголовок из подтверждения

        // assert
        Assertions.assertAll(
                // проверка заголовка:
                () -> Assertions.assertEquals("Заказ получен", textApprove, "Заказ не получен"),
                // проверка мейла:
                () -> Assertions.assertEquals(textEmail, textCheckingEmail, "Мейл не совпадает"),
                // проверка суммы к оплате:
                () -> Assertions.assertEquals(numberAmountForPayment, numberСheckingAmount, "Сумма к оплате не совпадает"),
                // проверка метода оплаты:
                () -> Assertions.assertEquals("оплата при доставке", textСheckingPaymentMethod, "Сумма к оплате не совпадает")
        );
    }

    // 3. Добавить товар в корзину. Перейти к оформлению заказа. Заполнить обязательные поля. Выбрать оплату прямым банковским переводом. Проверить почту, сумму, метод оплаты, заголовок.
    @Test
    public void orderProductWithFilledParamsAndBankPayment_displayedPositiveResult() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // act
        mainPage.searchFormLocator.sendKeys(product); // вводим названием продукта в окно поиска
        mainPage.searchFormButtonLocator.click(); // нажимаем кнопку поиска
        productPage.addToCartButtonLocator.click(); // нажимаем "В корзину"
        productPage.detailsLocator.click(); // нажимаем подробнее для перехода в корзину

        String textAmountForPayment = cartPage.amountForPaymentLocator.getText(); // запоминаем сумму к оплате
        textAmountForPayment = textAmountForPayment.replace(",", ".").replace("₽", ""); // меняем запятую на точку, убираем знак рубля
        BigDecimal numberAmountForPayment = new BigDecimal(textAmountForPayment); // переводим в формат BigDecimal

        cartPage.checkoutButtonLocator.click(); // нажимаем кнопку "Оформить заказ" в корзине
        checkoutPage.firstNameLocator.sendKeys(textFirstName); // заполняем поле имени
        checkoutPage.lastNameLocator.sendKeys(textLastName); // заполняем поле фамилии
        checkoutPage.addressLocator.sendKeys(textAddress); // заполняем поле адреса
        checkoutPage.cityLocator.sendKeys(textCity); // заполняем поле города
        checkoutPage.stateLocator.sendKeys(textState); // заполняем поле области
        checkoutPage.postcodeLocator.sendKeys(textPostcode); // заполняем поле индекса
        checkoutPage.phoneLocator.sendKeys(textPhone); // заполняем поле телефона
        checkoutPage.paymentMethodeTransferLocator.click(); // выбираем способ оплаты "Оплата при доставке"
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.checkoutButtonLocator));
        checkoutPage.checkoutButtonLocator.click(); // нажимаем кнопку "Оформить заказ" на странице оформления заказа

        String textCheckingEmail = checkoutPage.checkingEmailLocator.getText(); // получаем мейл из подтверждения

        String textСheckingAmount = checkoutPage.checkingAmountLocator.getText(); // запоминаем сумму к оплате из подтверждения
        textСheckingAmount = textСheckingAmount.replace(",", ".").replace("₽", ""); // меняем запятую на точку, убираем знак рубля
        BigDecimal numberСheckingAmount = new BigDecimal(textСheckingAmount); // переводим в формат BigDecimal

        String textСheckingPaymentMethod = checkoutPage.checkingPaymentMethodLocator.getText().toLowerCase(); // получаем метод оплаты из подтверждения
        String textApprove = checkoutPage.approveLocator.getText(); // получаем заголовок из подтверждения

        // assert
        Assertions.assertAll(
                // проверка заголовка:
                () -> Assertions.assertEquals("Заказ получен", textApprove, "Заказ не получен"),
                // проверка мейла:
                () -> Assertions.assertEquals(textEmail, textCheckingEmail, "Мейл не совпадает"),
                // проверка суммы к оплате:
                () -> Assertions.assertEquals(numberAmountForPayment, numberСheckingAmount, "Сумма к оплате не совпадает"),
                // проверка метода оплаты:
                () -> Assertions.assertEquals("прямой банковский перевод", textСheckingPaymentMethod, "Сумма к оплате не совпадает")
        );
    }

    // 4. Добавить товар в корзину. Перейти к оформлению заказа. Заполнить обязательные поля. Применить купон. Выбрать оплату наличными при доставке. Проверить почту, сумму, метод оплаты, заголовок.
    @Test
    public void orderProductWithFilledParamsAndBankPaymentWithDiscount_DisplayedPositiveResult() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // act
        mainPage.searchFormLocator.sendKeys(product); // вводим названием продукта в окно поиска
        mainPage.searchFormButtonLocator.click(); // нажимаем кнопку поиска
        productPage.addToCartButtonLocator.click(); // нажимаем "В корзину"
        productPage.detailsLocator.click(); // нажимаем подробнее для перехода в корзину

        String textTotalAmount = cartPage.totalAmountLocator.getText(); // запоминаем общую стоимость товара
        textTotalAmount = textTotalAmount.replace(",", ".").replace("₽", ""); // меняем запятую на точку, убираем знак рубля
        BigDecimal numberTotalAmount = new BigDecimal(textTotalAmount); // переводим в формат BigDecimal

        cartPage.checkoutButtonLocator.click(); // нажимаем кнопку "Оформить заказ" в корзине
        checkoutPage.pressToEnterCouponLocator.click(); // нажимаем для применения купона
        checkoutPage.couponInputLocator.sendKeys(textDiscount); // вводим купон
        checkoutPage.couponButtonLocator.click(); // нажимаем "Применить купон"
        checkoutPage.firstNameLocator.sendKeys(textFirstName); // заполняем поле имени
        checkoutPage.lastNameLocator.sendKeys(textLastName); // заполняем поле фамилии
        checkoutPage.addressLocator.sendKeys(textAddress); // заполняем поле адреса
        checkoutPage.cityLocator.sendKeys(textCity); // заполняем поле города
        checkoutPage.stateLocator.sendKeys(textState); // заполняем поле области
        checkoutPage.postcodeLocator.sendKeys(textPostcode); // заполняем поле индекса
        checkoutPage.phoneLocator.sendKeys(textPhone); // заполняем поле телефона
        checkoutPage.paymentMethodeUponDeliveryLocator.click(); // выбираем способ оплаты "Оплата при доставке"
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.checkoutButtonLocator));
        checkoutPage.checkoutButtonLocator.click(); // нажимаем кнопку "Оформить заказ" на странице оформления заказа
        String textCheckingEmail = checkoutPage.checkingEmailLocator.getText(); // получаем мейл из подтверждения

        String textСheckingAmount = checkoutPage.checkingAmountLocator.getText(); // запоминаем сумму к оплате из подтверждения
        textСheckingAmount = textСheckingAmount.replace(",", ".").replace("₽", ""); // меняем запятую на точку, убираем знак рубля
        BigDecimal numberСheckingAmount = new BigDecimal(textСheckingAmount); // переводим в формат BigDecimal

        String textСheckingPaymentMethod = checkoutPage.checkingPaymentMethodLocator.getText().toLowerCase(); // получаем метод оплаты из подтверждения
        String textApprove = checkoutPage.approveLocator.getText(); // получаем заголовок из подтверждения

        // assert
        Assertions.assertAll(
                // проверка заголовка:
                () -> Assertions.assertEquals("Заказ получен", textApprove, "Заказ не получен"),
                // проверка мейла:
                () -> Assertions.assertEquals(textEmail, textCheckingEmail, "Мейл не совпадает"),
                // проверка суммы к оплате:
                () -> Assertions.assertEquals(numberTotalAmount.subtract(numberDiscount), numberСheckingAmount, "Сумма к оплате не совпадает"),
                // проверка метода оплаты:
                () -> Assertions.assertEquals("оплата при доставке", textСheckingPaymentMethod, "Сумма к оплате не совпадает")
        );
    }
}