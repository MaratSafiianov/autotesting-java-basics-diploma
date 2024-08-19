package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v126.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends Page {
    private final WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[@class='product_title entry-title']")
    public WebElement productCardNameLocator;

    @FindBy(xpath = "//div[@class='woocommerce-breadcrumb accesspress-breadcrumb']//span")
    public WebElement breadcrumbLocator;

    @FindBy(xpath = "//button[@class='single_add_to_cart_button button alt']")
    public WebElement addToCartButtonLocator;

    @FindBy(xpath = "//div[@class='woocommerce-message']//a")
    public WebElement detailsLocator;

    @FindBy(xpath = "//div[contains(@class, 'product')]")
    public WebElement productCardLocator;

    @FindBy(xpath = "//p[@class='price']//bdi")
    public WebElement productPriceLocator;
}