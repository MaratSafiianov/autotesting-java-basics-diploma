package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v126.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='site-logo']")
    public WebElement logoLocator;

    @FindBy(xpath = "//div[@class='login-woocommerce']")
    public WebElement enterButtonHeadingLocator;

    @FindBy(xpath = "//li[@id='menu-item-46']//a")
    public WebElement mainMenuElementCatalogLocator;

    @FindBy(xpath = "//li[@id='menu-item-30']//a")
    public WebElement mainMenuElementMyAccountLocator;

    @FindBy(xpath = "//li[@id='menu-item-31']//a")
    public WebElement mainMenuElementCheckoutLocator;

    @FindBy(xpath = "//li[@id='menu-item-29']//a")
    public WebElement mainMenuElementShoppingCartLocator;

    @FindBy(xpath = "//div[@class='search-form']//input[@type='text']")
    public WebElement searchFormLocator;

    @FindBy(xpath = "//form[@class='searchform']//button")
    public WebElement searchFormButtonLocator;

    @FindBy(xpath = "//section[@id='promo-section1']")
    public WebElement promoSectionLocator;

    @FindBy(xpath = "//aside[@id='accesspress_storemo-2']//h4")
    public WebElement firstPromoElementHeadingLocator;

    @FindBy(xpath = "//aside[@id='accesspress_storemo-3']//h4")
    public WebElement secondPromoElementHeadingLocator;

    @FindBy(xpath = "//aside[@id='accesspress_storemo-4']//h4")
    public WebElement thirdPromoElementHeadingLocator;

    @FindBy(xpath = "//div[@class='promo-product1 clearfix']//aside[1]//span")
    public WebElement firstPromoElementButtonLocator;

    @FindBy(xpath = "//div[@class='promo-product1 clearfix']//aside[2]//span")
    public WebElement secondPromoElementButtonLocator;

    @FindBy(xpath = "//div[@class='promo-product1 clearfix']//aside[3]//span")
    public WebElement thirdPromoElementButtonLocator;

    @FindBy(xpath = "//section[@id='product1']//li[@data-slick-index='0']//a[@class='button product_type_simple add_to_cart_button ajax_add_to_cart']")
    public WebElement saleSectionFirstProductButtonLocator;

    @FindBy(xpath = "//section[@id='product1']//li[@data-slick-index='0']//a[@class='added_to_cart wc-forward']")
    public WebElement saleSectionFirstProductDetailsLocator;

    @FindBy(xpath = "(//section[@id='product1']//li[@data-slick-index='0']//span[@class='woocommerce-Price-amount amount'])[2]")
    public WebElement saleSectionFirstProductPriceLocator;

    @FindBy(xpath = "//section[@id='promo-section2']//a")
    public WebElement checkProductRefLocator;

    @FindBy(xpath = "(//section[@id='product2']//li[@data-slick-index='0']//a)[1]")
    public WebElement firstOfNewProductArrivalsLocator;

    @FindBy(xpath = "//aside[@id='woocommerce_recently_viewed_products-2']")
    public WebElement viewedProductsSectionLocator;

    @FindBy(xpath = "//li[contains(@class, 'page-item-33')]//a")
    public WebElement allProductsFooterLocator;

    @FindBy(xpath = "//li[contains(@class, 'page-item-39')]//a")
    public WebElement mainPageFooterLocator;

    @FindBy(xpath = "//li[contains(@class, 'page-item-20')]//a")
    public WebElement cartFooterLocator;

    @FindBy(xpath = "//li[contains(@class, 'page-item-22')]//a")
    public WebElement myAccountFooterLocator;

    @FindBy(xpath = "//li[contains(@class, 'page-item-24')]//a")
    public WebElement orderFooterLocator;

    @FindBy(xpath = "//li[contains(@class, 'page-item-141')]//a")
    public WebElement registrationFooterLocator;
}