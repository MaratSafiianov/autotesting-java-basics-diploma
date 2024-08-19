package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v126.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatalogPage extends Page {
    private final WebDriver driver;

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[@class='entry-title ak-container']")
    public WebElement pageNameLocator;

    @FindBy(xpath = "//div[@class='woocommerce-breadcrumb accesspress-breadcrumb']//span")
    public WebElement breadcrumbCatalogLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-15')]//a")
    public WebElement sideMenuElementNoCategoryNameLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-15')]//span")
    public WebElement sideMenuElementNoCategoryQuantityLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-20')]//a")
    public WebElement sideMenuElementAppliancesNameLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-20')]//span")
    public WebElement sideMenuElementAppliancesQuantityLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-19')]//a")
    public WebElement sideMenuElementCatalogNameLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-19')]//span")
    public WebElement sideMenuElementCatalogQuantityLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-28')]//a")
    public WebElement sideMenuElementBooksNameLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-28')]//span")
    public WebElement sideMenuElementBooksQuantityLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-17')]//a")
    public WebElement sideMenuElementClothesNameLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-17')]//span")
    public WebElement sideMenuElementClothesQuantityLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-26')]//a")
    public WebElement sideMenuElementPadNameLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-26')]//span")
    public WebElement sideMenuElementPadQuantityLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-22')]//a")
    public WebElement sideMenuElementWashingsMachinesNameLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-22')]//span")
    public WebElement sideMenuElementWashingsMachinesQuantityLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-25')]//a")
    public WebElement sideMenuElementTvNameLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-25')]//span")
    public WebElement sideMenuElementTvQuantityLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-24')]//a")
    public WebElement sideMenuElementPhonesNameLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-24')]//span")
    public WebElement sideMenuElementPhonesQuantityLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-27')]//a")
    public WebElement sideMenuElementPhotoVideoNameLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-27')]//span")
    public WebElement sideMenuElementPhotoVideoQuantityLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-21')]//a")
    public WebElement sideMenuElementRefrigeratorsNameLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-21')]//span")
    public WebElement sideMenuElementRefrigeratorsQuantityLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-23')]//a")
    public WebElement sideMenuElementWatchNameLocator;

    @FindBy(xpath = "//li[contains(@class, 'cat-item cat-item-23')]//span")
    public WebElement sideMenuElementWatchQuantityLocator;

    @FindBy(xpath = "//p[@class='woocommerce-result-count']")
    public WebElement resultQuantityStringLocator;

    @FindBy(xpath = "//form[@class='searchform']//input[1]")
    public WebElement searchFormLocator;

    @FindBy(xpath = "//form[@class='searchform']//button")
    public WebElement searchButtonLocator;

    public By resultProductsNameLocator = By.xpath("//ul[@class='products columns-4']//a[@class='collection_title']");

    @FindBy(xpath = "(//a[contains(@class, 'add_to_cart_button')])[1]")
    public WebElement firstPadAddToCartButtonLocator;

    @FindBy(xpath = "(//a[contains(@class, 'added_to_cart')])[1]")
    public WebElement detailsButtonLocator;

    @FindBy(xpath = "(//ul[@class='products columns-4']//a)[1]")
    public WebElement firstProductCardLocator;

    @FindBy(xpath = "(//ul[@class='products columns-4']//a[@class='collection_title'])[1]")
    public WebElement firstProductNameLocator;

    @FindBy(xpath = "(//ul[@class='product_list_widget']//a)[1]")
    public WebElement firstProductCardInProductsSectionLocator;

    @FindBy(xpath = "(//ul[@class='product_list_widget']//a//span)[1]")
    public WebElement firstProductNameInProductsSectionLocator;
}