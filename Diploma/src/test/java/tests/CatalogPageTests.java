package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.CatalogPage;
import pages.ProductPage;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CatalogPageTests {
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
        driver.findElement(By.xpath("(//li[@id='menu-item-46']//a)[1]")).click();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    // 1. Категории товаров. Нажатие на "Без категории".
    @Test
    public void pressSideMenuElementNoCategory_shownNoCategoryProducts() {
        // arrange
        CatalogPage catalogPage = new CatalogPage(driver);
        Pattern pattern = Pattern.compile("\\d+");

        // act
        String textFromSideMenu = catalogPage.sideMenuElementNoCategoryNameLocator.getText().toLowerCase();
        String quantityFromSideMenu = catalogPage.sideMenuElementNoCategoryQuantityLocator.getText().replace("(", "").replace(")", "");
        catalogPage.sideMenuElementNoCategoryNameLocator.click();
        String textHeadingNoCategoryPage = catalogPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumbNoCategory = catalogPage.breadcrumbCatalogLocator.getText().toLowerCase();
        String quantityFromNoCategoryPage = catalogPage.resultQuantityStringLocator.getText();
        Matcher matcher = pattern.matcher(quantityFromNoCategoryPage);
        String lastNumber = null;

        while (matcher.find()) {
            lastNumber = matcher.group();
        }

        // assert
        String finalResultQuantity = lastNumber;
        Assertions.assertAll(
                () -> Assertions.assertEquals(textFromSideMenu, textHeadingNoCategoryPage, "Название заголовка страницы не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(textFromSideMenu, textBreadcrumbNoCategory, "Название страницы в пути навигации не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(quantityFromSideMenu, finalResultQuantity, "Количество товара из бокового меню не соответствует количеству товара в категории")
        );
    }

    // 2. Категории товаров. Нажатие на "Бытовая техника".
    @Test
    public void pressSideMenuElementAppliances_shownAppliances() {
        // arrange
        CatalogPage catalogPage = new CatalogPage(driver);
        Pattern pattern = Pattern.compile("\\d+");

        // act
        String textFromSideMenu = catalogPage.sideMenuElementAppliancesNameLocator.getText().toLowerCase();
        String quantityFromSideMenu = catalogPage.sideMenuElementAppliancesQuantityLocator.getText().replace("(", "").replace(")", "");
        catalogPage.sideMenuElementAppliancesNameLocator.click();
        String textHeadingAppliancesPage = catalogPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumbAppliances = catalogPage.breadcrumbCatalogLocator.getText().toLowerCase();
        String quantityFromAppliancesPage = catalogPage.resultQuantityStringLocator.getText();
        Matcher matcher = pattern.matcher(quantityFromAppliancesPage);
        String lastNumber = null;

        while (matcher.find()) {
            lastNumber = matcher.group();
        }

        // assert
        String finalResultQuantity = lastNumber;
        Assertions.assertAll(
                () -> Assertions.assertEquals(textFromSideMenu, textHeadingAppliancesPage, "Название заголовка страницы не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(textFromSideMenu, textBreadcrumbAppliances, "Название страницы в пути навигации не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(quantityFromSideMenu, finalResultQuantity, "Количество товара из бокового меню не соответствует количеству товара в категории")
        );
    }

    // 3. Категории товаров. Нажатие на "Каталог".
    @Test
    public void pressSideMenuElementCatalog_shownCatalog() {
        // arrange
        CatalogPage catalogPage = new CatalogPage(driver);
        Pattern pattern = Pattern.compile("\\d+");

        // act
        var textFromSideMenu = catalogPage.sideMenuElementCatalogNameLocator.getText().toLowerCase();
        var quantityFromSideMenu = catalogPage.sideMenuElementCatalogQuantityLocator.getText().replace("(", "").replace(")", "");
        catalogPage.sideMenuElementCatalogNameLocator.click();
        String textHeadingCatalogPage = catalogPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumbCatalog = catalogPage.breadcrumbCatalogLocator.getText().toLowerCase();
        String quantityFromCatalogPage = catalogPage.resultQuantityStringLocator.getText();
        Matcher matcher = pattern.matcher(quantityFromCatalogPage);
        String lastNumber = null;

        while (matcher.find()) {
            lastNumber = matcher.group();
        }

        // assert
        String finalResultQuantity = lastNumber;
        Assertions.assertAll(
                () -> Assertions.assertEquals(textFromSideMenu, textHeadingCatalogPage, "Название заголовка страницы не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(textFromSideMenu, textBreadcrumbCatalog, "Название страницы в пути навигации не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(quantityFromSideMenu, finalResultQuantity, "Количество товара из бокового меню не соответствует количеству товара в категории")
        );
    }

    // 4. Категории товаров. Нажатие на "Книги".
    @Test
    public void pressSideMenuElementBooks_shownBooks() {
        // arrange
        CatalogPage catalogPage = new CatalogPage(driver);
        Pattern pattern = Pattern.compile("\\d+");

        // act
        String textFromSideMenu = catalogPage.sideMenuElementBooksNameLocator.getText().toLowerCase();
        String quantityFromSideMenu = catalogPage.sideMenuElementBooksQuantityLocator.getText().replace("(", "").replace(")", "");
        catalogPage.sideMenuElementBooksNameLocator.click();
        String textHeadingBooksPage = catalogPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumbBooks = catalogPage.breadcrumbCatalogLocator.getText().toLowerCase();
        String quantityFromBooksPage = catalogPage.resultQuantityStringLocator.getText();
        Matcher matcher = pattern.matcher(quantityFromBooksPage);
        String lastNumber = null;

        while (matcher.find()) {
            lastNumber = matcher.group();
        }

        // assert
        String finalResultQuantity = lastNumber;
        Assertions.assertAll(
                () -> Assertions.assertEquals(textFromSideMenu, textHeadingBooksPage, "Название заголовка страницы не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(textFromSideMenu, textBreadcrumbBooks, "Название страницы в пути навигации не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(quantityFromSideMenu, finalResultQuantity, "Количество товара из бокового меню не соответствует количеству товара в категории")
        );
    }

    // 5. Категории товаров. Нажатие на "Одежда".
    @Test
    public void pressSideMenuElementClothes_shownClothes() {
        // arrange
        CatalogPage catalogPage = new CatalogPage(driver);
        Pattern pattern = Pattern.compile("\\d+");

        // act
        String textFromSideMenu = catalogPage.sideMenuElementClothesNameLocator.getText().toLowerCase();
        String quantityFromSideMenu = catalogPage.sideMenuElementClothesQuantityLocator.getText().replace("(", "").replace(")", "");
        catalogPage.sideMenuElementClothesNameLocator.click();
        String textHeadingClothesPage = catalogPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumbClothes = catalogPage.breadcrumbCatalogLocator.getText().toLowerCase();
        String quantityFromClothesPage = catalogPage.resultQuantityStringLocator.getText();
        Matcher matcher = pattern.matcher(quantityFromClothesPage);
        String lastNumber = null;

        while (matcher.find()) {
            lastNumber = matcher.group();
        }

        // assert
        String finalResultQuantity = lastNumber;
        Assertions.assertAll(
                () -> Assertions.assertEquals(textFromSideMenu, textHeadingClothesPage, "Название заголовка страницы не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(textFromSideMenu, textBreadcrumbClothes, "Название страницы в пути навигации не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(quantityFromSideMenu, finalResultQuantity, "Количество товара из бокового меню не соответствует количеству товара в категории")
        );
    }

    // 6. Категории товаров. Нажатие на "Планшеты".
    @Test
    public void pressSideMenuElementPad_shownPads() {
        // arrange
        CatalogPage catalogPage = new CatalogPage(driver);
        Pattern pattern = Pattern.compile("\\d+");

        // act
        String textFromSideMenu = catalogPage.sideMenuElementPadNameLocator.getText().toLowerCase();
        String quantityFromSideMenu = catalogPage.sideMenuElementPadQuantityLocator.getText().replace("(", "").replace(")", "");
        catalogPage.sideMenuElementPadNameLocator.click();
        String textHeadingPadPage = catalogPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumbPad = catalogPage.breadcrumbCatalogLocator.getText().toLowerCase();
        String quantityFromPadPage = catalogPage.resultQuantityStringLocator.getText();
        Matcher matcher = pattern.matcher(quantityFromPadPage);
        String lastNumber = null;

        while (matcher.find()) {
            lastNumber = matcher.group();
        }

        // assert
        String finalResultQuantity = lastNumber;
        Assertions.assertAll(
                () -> Assertions.assertEquals(textFromSideMenu, textHeadingPadPage, "Название заголовка страницы не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(textFromSideMenu, textBreadcrumbPad, "Название страницы в пути навигации не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(quantityFromSideMenu, finalResultQuantity, "Количество товара из бокового меню не соответствует количеству товара в категории")
        );
    }

    // 7. Категории товаров. Нажатие на "Стиральные машины".
    @Test
    public void pressSideMenuElementWashingsMachines_shownWashingsMachines() {
        // arrange
        CatalogPage catalogPage = new CatalogPage(driver);
        Pattern pattern = Pattern.compile("\\d+");

        // act
        String textFromSideMenu = catalogPage.sideMenuElementWashingsMachinesNameLocator.getText().toLowerCase();
        String quantityFromSideMenu = catalogPage.sideMenuElementWashingsMachinesQuantityLocator.getText().replace("(", "").replace(")", "");
        catalogPage.sideMenuElementWashingsMachinesNameLocator.click();
        String textHeadingWashingsMachinesPage = catalogPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumbWashingsMachines = catalogPage.breadcrumbCatalogLocator.getText().toLowerCase();
        String quantityFromWashingsMachinesPage = catalogPage.resultQuantityStringLocator.getText();
        Matcher matcher = pattern.matcher(quantityFromWashingsMachinesPage);
        String lastNumber = null;

        while (matcher.find()) {
            lastNumber = matcher.group();
        }

        // assert
        String finalResultQuantity = lastNumber;
        Assertions.assertAll(
                () -> Assertions.assertEquals(textFromSideMenu, textHeadingWashingsMachinesPage, "Название заголовка страницы не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(textFromSideMenu, textBreadcrumbWashingsMachines, "Название страницы в пути навигации не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(quantityFromSideMenu, finalResultQuantity, "Количество товара из бокового меню не соответствует количеству товара в категории")
        );
    }

    // 8. Категории товаров. Нажатие на "Телевизоры".
    @Test
    public void pressSideMenuElementTv_shownTv() {
        // arrange
        CatalogPage catalogPage = new CatalogPage(driver);
        Pattern pattern = Pattern.compile("\\d+");

        // act
        String textFromSideMenu = catalogPage.sideMenuElementTvNameLocator.getText().toLowerCase();
        var quantityFromSideMenu = catalogPage.sideMenuElementTvQuantityLocator.getText().replace("(", "").replace(")", "");
        catalogPage.sideMenuElementTvNameLocator.click();
        String textHeadingTvPage = catalogPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumbTv = catalogPage.breadcrumbCatalogLocator.getText().toLowerCase();
        String quantityFromTvPage = catalogPage.resultQuantityStringLocator.getText();
        Matcher matcher = pattern.matcher(quantityFromTvPage);
        String lastNumber = null;

        while (matcher.find()) {
            lastNumber = matcher.group();
        }

        // assert
        String finalResultQuantity = lastNumber;
        Assertions.assertAll(
                () -> Assertions.assertEquals(textFromSideMenu, textHeadingTvPage, "Название заголовка страницы не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(textFromSideMenu, textBreadcrumbTv, "Название страницы в пути навигации не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(quantityFromSideMenu, finalResultQuantity, "Количество товара из бокового меню не соответствует количеству товара в категории")
        );
    }

    // 9. Категории товаров. Нажатие на "Телефоны".
    @Test
    public void pressSideMenuElementPhones_shownPhones() {
        // arrange
        CatalogPage catalogPage = new CatalogPage(driver);
        Pattern pattern = Pattern.compile("\\d+");

        // act
        String textFromSideMenu = catalogPage.sideMenuElementPhonesNameLocator.getText().toLowerCase();
        String quantityFromSideMenu = catalogPage.sideMenuElementPhonesQuantityLocator.getText().replace("(", "").replace(")", "");
        catalogPage.sideMenuElementPhonesNameLocator.click();
        String textHeadingPhonesPage = catalogPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumbPhones = catalogPage.breadcrumbCatalogLocator.getText().toLowerCase();
        String quantityFromPhonesPage = catalogPage.resultQuantityStringLocator.getText();
        Matcher matcher = pattern.matcher(quantityFromPhonesPage);
        String lastNumber = null;

        while (matcher.find()) {
            lastNumber = matcher.group();
        }

        // assert
        String finalResultQuantity = lastNumber;
        Assertions.assertAll(
                () -> Assertions.assertEquals(textFromSideMenu, textHeadingPhonesPage, "Название заголовка страницы не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(textFromSideMenu, textBreadcrumbPhones, "Название страницы в пути навигации не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(quantityFromSideMenu, finalResultQuantity, "Количество товара из бокового меню не соответствует количеству товара в категории")
        );
    }

    // 10. Категории товаров. Нажатие на "Фото/видео".
    @Test
    public void pressSideMenuElementPhotoVideo_shownPhotoVideo() {
        // arrange
        CatalogPage catalogPage = new CatalogPage(driver);
        Pattern pattern = Pattern.compile("\\d+");

        // act
        String textFromSideMenu = catalogPage.sideMenuElementPhotoVideoNameLocator.getText().toLowerCase();
        String quantityFromSideMenu = catalogPage.sideMenuElementPhotoVideoQuantityLocator.getText().replace("(", "").replace(")", "");
        catalogPage.sideMenuElementPhotoVideoNameLocator.click();
        String textHeadingPhotoVideoPage = catalogPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumbPhotoVideo = catalogPage.breadcrumbCatalogLocator.getText().toLowerCase();
        String quantityFromPhotoVideoPage = catalogPage.resultQuantityStringLocator.getText();
        Matcher matcher = pattern.matcher(quantityFromPhotoVideoPage);
        String lastNumber = null;

        while (matcher.find()) {
            lastNumber = matcher.group();
        }

        // assert
        String finalResultQuantity = lastNumber;
        Assertions.assertAll(
                () -> Assertions.assertEquals(textFromSideMenu, textHeadingPhotoVideoPage, "Название заголовка страницы не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(textFromSideMenu, textBreadcrumbPhotoVideo, "Название страницы в пути навигации не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(quantityFromSideMenu, finalResultQuantity, "Количество товара из бокового меню не соответствует количеству товара в категории")
        );
    }

    // 11. Категории товаров. Нажатие на "Холодильники".
    @Test
    public void pressSideMenuElementRefrigerators_shownRefrigerators() {
        // arrange
        CatalogPage catalogPage = new CatalogPage(driver);
        Pattern pattern = Pattern.compile("\\d+");

        // act
        String textFromSideMenu = catalogPage.sideMenuElementRefrigeratorsNameLocator.getText().toLowerCase();
        String quantityFromSideMenu = catalogPage.sideMenuElementRefrigeratorsQuantityLocator.getText().replace("(", "").replace(")", "");
        catalogPage.sideMenuElementRefrigeratorsNameLocator.click();
        String textHeadingRefrigeratorsPage = catalogPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumbRefrigerators = catalogPage.breadcrumbCatalogLocator.getText().toLowerCase();
        String quantityFromRefrigeratorsPage = catalogPage.resultQuantityStringLocator.getText();
        Matcher matcher = pattern.matcher(quantityFromRefrigeratorsPage);
        String lastNumber = null;

        while (matcher.find()) {
            lastNumber = matcher.group();
        }

        // assert
        String finalResultQuantity = lastNumber;
        Assertions.assertAll(
                () -> Assertions.assertEquals(textFromSideMenu, textHeadingRefrigeratorsPage, "Название заголовка страницы не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(textFromSideMenu, textBreadcrumbRefrigerators, "Название страницы в пути навигации не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(quantityFromSideMenu, finalResultQuantity, "Количество товара из бокового меню не соответствует количеству товара в категории")
        );
    }

    // 12. Категории товаров. Нажатие на "Часы".
    @Test
    public void pressSideMenuElementWatch_shownWatches() {
        // arrange
        CatalogPage catalogPage = new CatalogPage(driver);
        Pattern pattern = Pattern.compile("\\d+");

        // act
        String textFromSideMenu = catalogPage.sideMenuElementWatchNameLocator.getText().toLowerCase();
        String quantityFromSideMenu = catalogPage.sideMenuElementWatchQuantityLocator.getText().replace("(", "").replace(")", "");
        catalogPage.sideMenuElementWatchNameLocator.click();
        String textHeadingWatchPage = catalogPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumbWatch = catalogPage.breadcrumbCatalogLocator.getText().toLowerCase();
        String quantityFromWatchPage = catalogPage.resultQuantityStringLocator.getText();
        Matcher matcher = pattern.matcher(quantityFromWatchPage);
        String lastNumber = null;

        while (matcher.find()) {
            lastNumber = matcher.group();
        }

        // assert
        String finalResultQuantity = lastNumber;
        Assertions.assertAll(
                () -> Assertions.assertEquals(textFromSideMenu, textHeadingWatchPage, "Название заголовка страницы не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(textFromSideMenu, textBreadcrumbWatch, "Название страницы в пути навигации не соответствует названию элемента в меню на главной странице"),
                () -> Assertions.assertEquals(quantityFromSideMenu, finalResultQuantity, "Количество товара из бокового меню не соответствует количеству товара в категории")
        );
    }

    // 13. Окно поиска. Ввод в окно поиска "телефон" -> найти. Проверить содержание пути навигации на "Результаты поиска "Телефон".
    @Test
    public void fillingOutSearchingForm_shownProducts() {
        // arrange
        CatalogPage catalogPage = new CatalogPage(driver);
        String product = "телефон";

        // act
        catalogPage.searchFormLocator.sendKeys(product);
        catalogPage.searchButtonLocator.click();
        String textPageHeadingResul = catalogPage.pageNameLocator.getText().toLowerCase();
        String textBreadcrumbResult = catalogPage.breadcrumbCatalogLocator.getText().toLowerCase();

        // assert
        Assertions.assertAll(
                () -> Assertions.assertTrue(textPageHeadingResul.contains(product), "Заголовок не содержит искомый товар"),
                () -> Assertions.assertTrue(textBreadcrumbResult.contains(product), "Путь навигации не содержит искомый товар")
        );
    }

    // 14. Сравнение количества товаров по поиску. Найти через окно поиска одежду, записать количество. Найти в боковом меню через категории одежду, записать количество. Сравнить результаты.
    @Test
    public void fillingOutSearchingForm_comparedQuantityOfProducts() {
        // arrange
        CatalogPage catalogPage = new CatalogPage(driver);
        Pattern pattern = Pattern.compile("\\d+");

        // act
        String textFromSideMenu = catalogPage.sideMenuElementClothesNameLocator.getText();
        String quantityFromSideMenu = catalogPage.sideMenuElementClothesQuantityLocator.getText().replace("(", "").replace(")", "");
        catalogPage.searchFormLocator.sendKeys(textFromSideMenu);
        catalogPage.searchButtonLocator.click();
        String quantityFromResultPage = catalogPage.resultQuantityStringLocator.getText();
        Matcher matcher = pattern.matcher(quantityFromResultPage);
        String lastNumber = null;

        while (matcher.find()) {
            lastNumber = matcher.group();
        }

        // assert
        String finalResultQuantity = lastNumber;
        Assertions.assertAll(
                () -> Assertions.assertEquals(quantityFromSideMenu, finalResultQuantity, "Количество товара из бокового меню не соответствует количеству товара из результатов поиска через окно поиска")
        );
    }

    // 15. Внести в окно поиска iphone, нажать поиск. Среди отобразившихся товаров проверить, что все названия содержат iphone.
    @Test
    public void fillingOutSearchingForm_comparedNamesOfProducts() {
        // arrange
        CatalogPage catalogPage = new CatalogPage(driver);
        String productName ="iphone";

        // act
        catalogPage.searchFormLocator.sendKeys(productName);
        catalogPage.searchButtonLocator.click();
        List<WebElement> productsNameList = driver.findElements(catalogPage.resultProductsNameLocator);        boolean allNamesCorrect = true;

        for (WebElement nameElement : productsNameList) {
            String productNameFromResult = nameElement.getText().toLowerCase();
            if (!productNameFromResult.contains(productName.toLowerCase())) {
                allNamesCorrect = false;
                break;
            }
        }
        // assert
       Assertions.assertTrue(allNamesCorrect, "Минимум один из товаров содержит название не соответствующее поиску");
    }

    // 16. Выбрать планшеты, нажать у первого товара "В корзину", нажать "Подробнее". Открылась корзина.
    @Test
    public void pressToCartAndDetail_openedCart() {
        // arrange
        CatalogPage catalogPage = new CatalogPage(driver);
        CartPage cartPage = new CartPage(driver);

        // act
        catalogPage.sideMenuElementPadNameLocator.click();
        catalogPage.firstPadAddToCartButtonLocator.click();
        catalogPage.detailsButtonLocator.click();

        String textBreadcrumbPad = cartPage.breadcrumbLocator.getText().toLowerCase();

        // assert
        Assertions.assertAll(
                () -> Assertions.assertEquals("корзина", textBreadcrumbPad, "Не открылась страница корзины")
        );
    }

    // 17. Выбрать планшеты, нажать на карточку первого товара. Открылась страница товара.
    @Test
    public void pressToProduct_openedProductPage() {
        // arrange
        CatalogPage catalogPage = new CatalogPage(driver);
        ProductPage productPage = new ProductPage(driver);

        // act
        catalogPage.sideMenuElementPadNameLocator.click();
        String textFirstProductName = catalogPage.firstProductNameLocator.getText().toLowerCase();
        catalogPage.firstProductCardLocator.click();
        String textBreadcrumbPad = productPage.breadcrumbLocator.getText().toLowerCase();
        String textProductCardName = productPage.productCardNameLocator.getText().toLowerCase();


        // assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(textFirstProductName, textBreadcrumbPad, "Название товара в пути навигации не соответствует названию выбранного в каталоге товара"),
                () -> Assertions.assertEquals(textFirstProductName, textProductCardName, "Название товара в карточке товара не соответствует названию выбранного в каталоге товара")
        );
    }

    // 18. Под фильтром в разделе "Товары" нажать на карточку первого товара. Открылась страница товара.
    @Test
    public void pressToProductInLeftSection_openedProductPage() {
        // arrange
        CatalogPage catalogPage = new CatalogPage(driver);
        ProductPage productPage = new ProductPage(driver);

        // act
        String textFirstProductName = catalogPage.firstProductNameInProductsSectionLocator.getText().toLowerCase().replace("\"", "");
        catalogPage.firstProductCardInProductsSectionLocator.click();
        String textBreadcrumbPad = productPage.breadcrumbLocator.getText().toLowerCase().replace("«", "").replace("»", "");
        String textProductCardName = productPage.productCardNameLocator.getText().toLowerCase().replace("«", "").replace("»", "");


        // assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(textFirstProductName, textBreadcrumbPad, "Название товара в пути навигации не соответствует названию выбранного в каталоге товара"),
                () -> Assertions.assertEquals(textFirstProductName, textProductCardName, "Название товара в карточке товара не соответствует названию выбранного в каталоге товара")
        );
    }
}