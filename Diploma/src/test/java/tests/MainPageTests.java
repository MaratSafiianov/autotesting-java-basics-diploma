package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainPageTests {
    private WebDriver driver;
    private WebDriverWait wait;

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
    public void tearDown()
    {
        driver.quit();
    }

    // 1. Нажатие на кнопку меню "КАТАЛОГ". Открылся Каталог.
    @Test
    public void pressCatalogInMainMenu_openedCatalog() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        // act
        String textFromMainMenu = mainPage.mainMenuElementCatalogLocator.getText().toLowerCase();
        mainPage.mainMenuElementCatalogLocator.click();
        String textHeadingCatalogPage = catalogPage.pageNameLocator.getText().toLowerCase(Locale.ROOT);
        String textBreadcrumbCatalog = catalogPage.breadcrumbCatalogLocator.getText().toLowerCase();

        // assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(textFromMainMenu, textHeadingCatalogPage, "Название заголовка страницы не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(textFromMainMenu, textBreadcrumbCatalog, "Название страницы в пути навигации не соответствует названию элемента в меню на главной странице")
        );
    }

    // 2. Нажатие на кнопку меню "МОЙ АККАУНТ". Открылась страница аккаунта.
    @Test
    public void pressMyAccountInMainMenu_openedMyAccount() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        AccountPage accountPage = new AccountPage(driver);

        // act
        String textFromMainMenu = mainPage.mainMenuElementMyAccountLocator.getText().toLowerCase();
        mainPage.mainMenuElementMyAccountLocator.click();
        String textHeadingMyAccountPage = accountPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumbMyAccount = accountPage.breadcrumbLocator.getText().toLowerCase();

        // assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(textFromMainMenu, textHeadingMyAccountPage, "Название заголовка страницы не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(textFromMainMenu, textBreadcrumbMyAccount, "Название страницы в пути навигации не соответствует названию элемента в меню на главной странице")
        );
    }

    // 3. Нажатие на кнопку меню "КОРЗИНА". Открылась корзина.
    @Test
    public void pressShoppingCartInMainMenu_openedShoppingCart() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        CartPage cartPage = new CartPage(driver);

        // act
        String textFromMainMenu = mainPage.mainMenuElementShoppingCartLocator.getText().toLowerCase();
        mainPage.mainMenuElementShoppingCartLocator.click();
        String textBreadcrumbShoppingCart = cartPage.breadcrumbLocator.getText().toLowerCase();

        // assert
        Assertions.assertEquals(textFromMainMenu, textBreadcrumbShoppingCart, "Название страницы в пути навигации не соответствует названию элемента в меню на главной странице");
    }


    // 4. Нажатие на кнопку меню "ОФОРМЛЕНИЕ ЗАКАЗА" при пустой корзине. Открылась страница оформления заказа.
    @Test
    public void pressCheckoutInMainMenu_openedCheckout() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // act
        String textFromMainMenu = mainPage.mainMenuElementCheckoutLocator.getText().toLowerCase();
        mainPage.mainMenuElementCheckoutLocator.click();
        String textBreadcrumbShoppingCart = checkoutPage.breadcrumbLocator.getText().toLowerCase();

        // assert
        Assertions.assertEquals(textFromMainMenu, textBreadcrumbShoppingCart, "Название страницы в пути навигации не соответствует названию элемента в меню на главной странице");
    }

    // 5. Нажатие на значок поиска. Открылась страница с результатами поиска.
    @Test
    public void pressSearchButton_openedResults() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        String resultPageName = "результаты поиска";

        // act
        mainPage.searchFormButtonLocator.click();
        String textHeadingSearchPage = catalogPage.pageNameLocator.getText().toLowerCase(Locale.ROOT);
        String textBreadcrumbSearch = catalogPage.breadcrumbCatalogLocator.getText().toLowerCase();

        // assert
        Assertions.assertAll(
                () -> Assertions.assertTrue(textHeadingSearchPage.contains(resultPageName), "Открывшаяся страница не содержит слова: результаты поиска"),
                () -> Assertions.assertTrue(textBreadcrumbSearch.contains(resultPageName), "Название элемента в пути навигации не содержит слова: результаты поиска")
        );
    }

    // 6. Сначала переход в каталог, затем нажатие на значок Skillbox в хэдере
    @Test
    public void pressBrand_openedMainPage() {
        // arrange
        MainPage mainPage = new MainPage(driver);

        // act
        mainPage.mainMenuElementCatalogLocator.click();
        mainPage.logoLocator.click();

        // assert
        Assertions.assertTrue(mainPage.promoSectionLocator.isDisplayed(), "Промо-секция не найдена на главной странице");
    }

    // 7. Нажатие на кнопку "ПРОСМОТРЕТЬ" в первом элементе промо-раздела. Открылась соответствующая страница.
    @Test
    public void pressFirstOfPromo_openedCatalogSection() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        // act
        wait.until(ExpectedConditions.visibilityOf(mainPage.firstPromoElementHeadingLocator));
        String textFromFirstPromoElement = mainPage.firstPromoElementHeadingLocator.getText().toLowerCase();
        mainPage.firstPromoElementButtonLocator.click();
        String textHeadingPage = catalogPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumb = catalogPage.breadcrumbCatalogLocator.getText().toLowerCase();

        // assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(textFromFirstPromoElement, textHeadingPage, "Название заголовка страницы не соответствует названию первого блока в промо-разделе"),
                () -> Assertions.assertEquals(textFromFirstPromoElement, textBreadcrumb, "Название страницы в пути навигации не соответствует названию первого блока в промо-разделе")
        );
    }

    // 8. Нажатие на кнопку "ПРОСМОТРЕТЬ" во втором элементе. Открылась соответствующая страница.
    @Test
    public void pressSecondOfPromo_openedCatalogSection() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        // act
        wait.until(ExpectedConditions.visibilityOf(mainPage.secondPromoElementHeadingLocator));
        String textFromSecondPromoElement = mainPage.secondPromoElementHeadingLocator.getText().toLowerCase();
        mainPage.secondPromoElementButtonLocator.click();
        String textHeadingPage = catalogPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumb = catalogPage.breadcrumbCatalogLocator.getText().toLowerCase();

        // assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(textFromSecondPromoElement, textHeadingPage, "Название заголовка страницы не соответствует названию первого блока в промо-разделе"),
                () -> Assertions.assertEquals(textFromSecondPromoElement, textBreadcrumb, "Название страницы в пути навигации не соответствует названию первого блока в промо-разделе")

        );
    }

    // 9. Нажатие на кнопку "ПРОСМОТРЕТЬ" в третьем элементе. Открылась соответствующая страница.
    @Test
    public void pressThirdOfPromo_openedCatalogSection() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        // act
        wait.until(ExpectedConditions.visibilityOf(mainPage.thirdPromoElementHeadingLocator));
        String textFromThirdPromoElement = mainPage.thirdPromoElementHeadingLocator.getText().toLowerCase();
        mainPage.thirdPromoElementButtonLocator.click();
        String textHeadingPage = catalogPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumb = catalogPage.breadcrumbCatalogLocator.getText().toLowerCase();

        // assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(textFromThirdPromoElement, textHeadingPage, "Название заголовка страницы не соответствует названию первого блока в промо-разделе"),
                () -> Assertions.assertEquals(textFromThirdPromoElement, textBreadcrumb, "Название страницы в пути навигации не соответствует названию первого блока в промо-разделе")
        );
    }

    // 10. Нажатие в разделе "РАСПРОДАЖА" на кнопку "В КОРЗИНУ" у первого товара. Кнопка изменилась на "ПОДРОБНЕЕ".
    @Test
    public void pressAddToCartButtonInSaleSection_buttonChangedName() {
        // arrange
        MainPage mainPage = new MainPage(driver);

        String newButtonValue = "подробнее";

        // act
        mainPage.saleSectionFirstProductButtonLocator.click();
        String changedButtonText = mainPage.saleSectionFirstProductButtonLocator.getText().toLowerCase();

        // assert
        Assertions.assertEquals(newButtonValue, changedButtonText, "Новое название кнопки не \"Подробнее\"");
    }

    // 11. Нажатие в разделе "Уже в продаже" на кнопку "ПРОСМОТРЕТЬ ТОВАР". Открылась страница товара.
    @Test
    public void pressButtonPromo_openedProductPage() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = new ProductPage(driver);

        // act
        mainPage.checkProductRefLocator.click();

        // assert
        Assertions.assertTrue(productPage.productCardLocator.isDisplayed(), "Карточка товара не отобразилась");
    }

    // 12. "Просмотренные товары". Нажать на товар, вернуться на главную.
    @Test
    public void viewProduct_appearedViewedProductsSection() {
        // arrange
        MainPage mainPage = new MainPage(driver);

        // act
        mainPage.firstOfNewProductArrivalsLocator.click();
        mainPage.logoLocator.click();

        Assertions.assertTrue(mainPage.viewedProductsSectionLocator.isDisplayed(), "Секция с просмотренными товарами не отображается");
    }

    // 13. Футер. Нажатие "Все товары"
    @Test
    public void pressAllProductsInFooter_openedPageAllProducts() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);

        // act
        String textAllProducts = mainPage.allProductsFooterLocator.getText().toLowerCase();
        mainPage.allProductsFooterLocator.click();
        String textTitleAllProductsPage = catalogPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumbAllProducts = catalogPage.breadcrumbCatalogLocator.getText().toLowerCase();

        Assertions.assertAll(
                () -> Assertions.assertEquals(textAllProducts, textTitleAllProductsPage, "Заголовок страницы не соответствует названию нажатого элемента в футере"),
                () -> Assertions.assertEquals(textAllProducts, textBreadcrumbAllProducts, "Название в пути навигации не соответствует названию нажатого элемента в футере")
        );
    }

    // 14. Футер. Нажатие "Главная"
    @Test
    public void pressMainInFooter_openedMainPage() {
        // arrange
        MainPage mainPage = new MainPage(driver);

        // act
        mainPage.mainPageFooterLocator.click();
        wait.until(ExpectedConditions.visibilityOf(mainPage.promoSectionLocator));

        // assert
        Assertions.assertTrue(mainPage.promoSectionLocator.isDisplayed(), "Главная страница не открылась");
    }

    // 15. Футер. Нажатие "Корзина"
    @Test
    public void pressCartInFooter_openedPageCart() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        CartPage cartPage = new CartPage(driver);

        // act
        String textCart = mainPage.cartFooterLocator.getText().toLowerCase();
        mainPage.cartFooterLocator.click();
        String textBreadcrumbCart = cartPage.breadcrumbLocator.getText().toLowerCase();

        // assert
        Assertions.assertEquals(textCart, textBreadcrumbCart, "Название страницы в пути навигации не соответствует названию нажатого элемента в футере");
    }

    // 16. Футер. Нажатие "Мой аккаунт"
    @Test
    public void pressMyAccountInFooter_openedPageMyAccount() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        AccountPage accountPage = new AccountPage(driver);

        // act
        String textMyAccount = mainPage.myAccountFooterLocator.getText().toLowerCase();
        mainPage.myAccountFooterLocator.click();
        String textTitleMyAccountPage = accountPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumbMyAccount = accountPage.breadcrumbLocator.getText().toLowerCase();

        // assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(textMyAccount, textTitleMyAccountPage, "Заголовок страницы не соответствует названию нажатого элемента в футере"),
                () -> Assertions.assertEquals(textMyAccount, textBreadcrumbMyAccount, "Название в пути навигации не соответствует названию нажатого элемента в футере")
        );
    }

    // 17. Футер. Нажатие "Оформление заказа"
    @Test
    public void pressOrderInFooter_openedPageOrder() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // act
        String textCart = mainPage.orderFooterLocator.getText().toLowerCase();
        mainPage.orderFooterLocator.click();
        String textBreadcrumbCart = checkoutPage.breadcrumbLocator.getText().toLowerCase();

        // assert
        Assertions.assertEquals(textCart, textBreadcrumbCart, "Название страницы в пути навигации не соответствует названию нажатого элемента в футере");
    }


    // 18. Футер. Нажатие "Регистрация"
    @Test
    public void pressRegistrationInFooter_openedPageMyAccount() {
        // arrange
        MainPage mainPage = new MainPage(driver);
        RegistrationAndAuthorizationPage registrationAndAuthorizationPage = new RegistrationAndAuthorizationPage(driver);

        // act
        String textMyAccount = mainPage.registrationFooterLocator.getText().toLowerCase();
        mainPage.registrationFooterLocator.click();
        String textTitleRegistrationPage = registrationAndAuthorizationPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumbRegistration = registrationAndAuthorizationPage.breadcrumbLocator.getText().toLowerCase();

        // assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(textMyAccount, textTitleRegistrationPage, "Заголовок страницы не соответствует названию нажатого элемента в футере"),
                () -> Assertions.assertEquals(textMyAccount, textBreadcrumbRegistration, "Название в пути навигации не соответствует названию нажатого элемента в футере")
        );
    }
}