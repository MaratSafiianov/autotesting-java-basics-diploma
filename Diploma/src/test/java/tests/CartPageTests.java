package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.CheckoutPage;
import pages.MainPage;
import pages.ProductPage;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CartPageTests {
    private WebDriver driver;
    private WebDriverWait wait;
    String product = "iPad 2020 32gb wi-fi";
    String textDiscount = "SERT500"; // текст сертификата
    BigDecimal numberDiscount = BigDecimal.valueOf(500); // значение сертификата



    @BeforeEach
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.navigate().to("https://intershop5.skillbox.ru");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    // 1. Добавить товар в 1 кол. в корзину, проверить общую стоимость и сумму к оплате.
    @Test
    public void addProductToCart_shownInCartWithCorrectSum() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        // act
        mainPage.searchFormLocator.sendKeys(product); // вводим названием продукта в окно поиска
        mainPage.searchFormButtonLocator.click(); // нажимаем кнопку поиска
        productPage.addToCartButtonLocator.click(); // нажимаем "В корзину"

        String textProductPrice = productPage.productPriceLocator.getText(); // запоминаем цену товара
        textProductPrice = textProductPrice.replace(",", ".").replace("₽", ""); // меняем запятую на точку, убираем знак рубля
        BigDecimal numberProductPrice = new BigDecimal(textProductPrice); // переводим в формат BigDecimal

        String textProductQuantity = cartPage.productQuantityLocator.getAttribute("value"); // запоминаем количество товара
        BigDecimal numberProductQuantity = new BigDecimal(textProductQuantity); // переводим в формат BigDecimal

        cartPage.detailsLocator.click(); // нажимаем подробнее для перехода в корзину

        String textTotalAmount = cartPage.totalAmountLocator.getText(); // запоминаем общую стоимость товара
        textTotalAmount = textTotalAmount.replace(",", ".").replace("₽", ""); // меняем запятую на точку, убираем знак рубля
        BigDecimal numberTotalAmount = new BigDecimal(textTotalAmount); // переводим в формат BigDecimal

        String textAmountForPayment = cartPage.amountForPaymentLocator.getText(); // запоминаем сумму к оплате
        textAmountForPayment = textAmountForPayment.replace(",", ".").replace("₽", ""); // меняем запятую на точку, убираем знак рубля
        BigDecimal numberAmountForPayment = new BigDecimal(textAmountForPayment); // переводим в формат BigDecimal

        // assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(numberProductPrice.multiply(numberProductQuantity), numberTotalAmount, "Общая стоимость не соответствует количеству товара"),
                () -> Assertions.assertEquals(numberProductPrice.multiply(numberProductQuantity), numberAmountForPayment, "Сумма к оплате не соответствует количеству товара")
        );
    }

    // 2. Добавить товар в 1 кол. в корзину, ввести и применить купон (SERT500). Проверить общую стоимость и к оплате.
    @Test
    public void applyDiscount_shownDiscountedAmount() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        // act
        mainPage.searchFormLocator.sendKeys(product); // вводим названием продукта в окно поиска
        mainPage.searchFormButtonLocator.click(); // нажимаем кнопку поиска
        productPage.addToCartButtonLocator.click(); // нажимаем "В корзину"

        String textProductPrice = productPage.productPriceLocator.getText(); // запоминаем цену товара
        textProductPrice = textProductPrice.replace(",", ".").replace("₽", ""); // меняем запятую на точку, убираем знак рубля
        BigDecimal numberProductPrice = new BigDecimal(textProductPrice); // переводим в формат BigDecimal

        String textProductQuantity = cartPage.productQuantityLocator.getAttribute("value"); // запоминаем количество товара
        BigDecimal numberProductQuantity = new BigDecimal(textProductQuantity); // переводим в формат BigDecimal

        cartPage.detailsLocator.click(); // нажимаем подробнее для перехода в корзину

        cartPage.couponInputLocator.sendKeys(textDiscount); // вводим текст купона
        cartPage.couponButtonLocator.click(); // нажимаем "Применить купон"

        String textTotalAmount = cartPage.totalAmountLocator.getText(); // запоминаем общую стоимость товара
        textTotalAmount = textTotalAmount.replace(",", ".").replace("₽", ""); // меняем запятую на точку, убираем знак рубля
        BigDecimal numberTotalAmount = new BigDecimal(textTotalAmount); // переводим в формат BigDecimal

        String textAmountForPayment = cartPage.amountForPaymentLocator.getText(); // запоминаем сумму к оплате
        textAmountForPayment = textAmountForPayment.replace(",", ".").replace("₽", ""); // меняем запятую на точку, убираем знак рубля
        BigDecimal numberAmountForPayment = new BigDecimal(textAmountForPayment); // переводим в формат BigDecimal

        // assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(numberProductPrice.multiply(numberProductQuantity), numberTotalAmount, "Общая стоимость не соответствует количеству товара"),
                () -> Assertions.assertEquals((numberProductPrice.multiply(numberProductQuantity)).subtract(numberDiscount), numberAmountForPayment.subtract(numberDiscount), "Сумма к оплате не соответствует количеству товара")
        );
    }

    // 3. Добавить товар в 1 кол. в корзину. Удалить товар. Проверить, что корзина пуста.
    @Test
    public void deleteProductFromCart_emptyCart() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        // act
        mainPage.searchFormLocator.sendKeys(product); // вводим названием продукта в окно поиска
        mainPage.searchFormButtonLocator.click(); // нажимаем кнопку поиска
        productPage.addToCartButtonLocator.click(); // нажимаем "В корзину"
        productPage.detailsLocator.click(); // нажимаем подробнее для перехода в корзину
        cartPage.deleteProductButtonLocator.click(); // удаляем товар из корзины
        String textEmptyCart = cartPage.emptyCartLocator.getText();

        // assert
        Assertions.assertAll(
                () -> Assertions.assertEquals( "Корзина пуста.", textEmptyCart, "Корзина не пуста")
        );
    }

    // 4. Добавить товар в 1 кол. в корзину. Удалить товар. Проверить, что корзина пуста. Восстановить товар, проверить, что товар снова в корзине.
    @Test
    public void deleteProductFromCartThenReturn_productReturnedToCart() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        // act
        mainPage.searchFormLocator.sendKeys(product); // вводим названием продукта в окно поиска
        mainPage.searchFormButtonLocator.click(); // нажимаем кнопку поиска
        productPage.addToCartButtonLocator.click(); // нажимаем "В корзину"
        productPage.detailsLocator.click(); // нажимаем подробнее для перехода в корзину
        cartPage.deleteProductButtonLocator.click(); // удаляем товар из корзины
        cartPage.returnProductLocator.click(); // возвращаем товар в корзину
        String textProductNameFromCart = cartPage.productNameFromCartLocator.getText();

        // assert
        Assertions.assertAll(
                () -> Assertions.assertEquals( product, textProductNameFromCart, "Товар не вернулся в корзину")
        );
    }

    // 5. Добавить товар в 1 кол. в корзину. Нажать кнопку "Оформить заказ". Проверить, что открылась страница оформления заказа.
    @Test
    public void pressCheckoutButton_openedCheckoutPage() {
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
        cartPage.deleteProductButtonLocator.click(); // удаляем товар из корзины
        cartPage.returnProductLocator.click(); // возвращаем товар в корзину
        cartPage.checkoutButtonLocator.click(); // нажимаем "Оформить заказ"
        String textCheckoutPageName = checkoutPage.checkoutPageNameLocator.getText().toLowerCase();
        String textCheckoutBreadcrumbName = checkoutPage.breadcrumbLocator.getText().toLowerCase();

        // assert
        Assertions.assertAll(
                () -> Assertions.assertEquals("оформление заказа", textCheckoutPageName, "Название заголовка страницы неверное"),
                () -> Assertions.assertEquals("оформление заказа", textCheckoutBreadcrumbName, "Название страницы в пути навигации неверное")
        );
    }

    // 6. Тест на то, что для товара с акцией в корзине отображается верная цена. Важно, чтобы после применения скидочного купона цена на товар с акцией не сбрасывалась на доакционную.
    // Тест не стабильный
    @Test
    public void addSaleProductToCart_shownInCartWithCorrectSumWithDiscount() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        CartPage cartPage = new CartPage(driver);

        // act
        String textSaleSectionFirstProductPrice = mainPage.saleSectionFirstProductPriceLocator.getText(); // запоминаем цену акционного товара
        textSaleSectionFirstProductPrice = textSaleSectionFirstProductPrice.replace(",", ".").replace("₽", ""); // меняем запятую на точку, убираем знак рубля
        BigDecimal numberProductPrice = new BigDecimal(textSaleSectionFirstProductPrice); // переводим в формат BigDecimal

        mainPage.saleSectionFirstProductButtonLocator.click(); // добавляем акционный товар в корзину
        mainPage.saleSectionFirstProductDetailsLocator.click(); // нажимаем "Подробнее"

        String textProductQuantity = cartPage.productQuantityLocator.getAttribute("value"); // запоминаем количество товара
        BigDecimal numberProductQuantity = new BigDecimal(textProductQuantity); // переводим в формат BigDecimal

        String textTotalAmount = cartPage.totalAmountLocator.getText(); // запоминаем общую стоимость товара
        textTotalAmount = textTotalAmount.replace(",", ".").replace("₽", ""); // меняем запятую на точку, убираем знак рубля
        BigDecimal numberTotalAmount = new BigDecimal(textTotalAmount); // переводим в формат BigDecimal

        cartPage.couponInputLocator.sendKeys(textDiscount); // вводим текст купона
        cartPage.couponButtonLocator.click(); // нажимаем "Применить купон"

        String textAmountForPayment = cartPage.amountForPaymentLocator.getText(); // запоминаем сумму к оплате
        textAmountForPayment = textAmountForPayment.replace(",", ".").replace("₽", ""); // меняем запятую на точку, убираем знак рубля
        BigDecimal numberAmountForPayment = new BigDecimal(textAmountForPayment); // переводим в формат BigDecimal

        // assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(numberProductPrice.multiply(numberProductQuantity), numberTotalAmount, "Общая стоимость не соответствует количеству товара"),
                () -> Assertions.assertEquals((numberProductPrice.multiply(numberProductQuantity)).subtract(numberDiscount), numberAmountForPayment.subtract(numberDiscount), "Сумма к оплате не соответствует количеству товара")
        );
    }
}