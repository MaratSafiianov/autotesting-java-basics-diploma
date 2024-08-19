package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v126.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends Page {
    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='accesspress-breadcrumb']//span")
    public WebElement breadcrumbLocator;

    @FindBy(xpath = "//div[@class='quantity']//input")
    public WebElement productQuantityLocator;

    @FindBy(xpath = "//div[@class='woocommerce-message']//a")
    public WebElement detailsLocator;

    @FindBy(xpath = "(//span[@class='woocommerce-Price-amount amount']//bdi)[2]")
    public WebElement totalAmountLocator;

    @FindBy(xpath = "(//span[@class='woocommerce-Price-amount amount']//bdi)[2]")
    public WebElement amountForPaymentLocator;

    @FindBy(xpath = "//input[@id='coupon_code']")
    public WebElement couponInputLocator;

    @FindBy(xpath = "//button[@name='apply_coupon']")
    public WebElement couponButtonLocator;

    @FindBy(xpath = "//td[@class='product-remove']//a")
    public WebElement deleteProductButtonLocator;

    @FindBy(xpath = "//p[@class='cart-empty woocommerce-info']")
    public WebElement emptyCartLocator;

    @FindBy(xpath = "//td[@class='product-name']//a")
    public WebElement productNameFromCartLocator;

    @FindBy(xpath = "//div[@class='woocommerce-message']//a")
    public WebElement returnProductLocator;

    @FindBy(xpath = "//div[@class='wc-proceed-to-checkout']//a")
    public WebElement checkoutButtonLocator;


}