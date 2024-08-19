package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v126.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends Page {
    private final WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[@class='post-title']")
    public WebElement checkoutPageNameLocator;

    @FindBy(xpath = "//div[@id='accesspress-breadcrumb']//span")
    public WebElement breadcrumbLocator;

    @FindBy(xpath = "//ul[@class='woocommerce-error']")
    public WebElement errorLocator;

    @FindBy(xpath = "//input[@id='billing_first_name']")
    public WebElement firstNameLocator;

    @FindBy(xpath = "//input[@id='billing_last_name']")
    public WebElement lastNameLocator;

    @FindBy(xpath = "//input[@id='billing_address_1']")
    public WebElement addressLocator;

    @FindBy(xpath = "//input[@id='billing_city']")
    public WebElement cityLocator;

    @FindBy(xpath = "//input[@id='billing_state']")
    public WebElement stateLocator;

    @FindBy(xpath = "//input[@id='billing_postcode']")
    public WebElement postcodeLocator;

    @FindBy(xpath = "//input[@id='billing_phone']")
    public WebElement phoneLocator;

    @FindBy(xpath = "//input[@id='payment_method_cod']")
    public WebElement paymentMethodeUponDeliveryLocator;

    @FindBy(xpath = "//input[@id='payment_method_bacs']")
    public WebElement paymentMethodeTransferLocator;

    @FindBy(xpath = "//button[@id='place_order']")
    public WebElement checkoutButtonLocator;

    @FindBy(xpath = "//li[@class='woocommerce-order-overview__email email']//strong")
    public WebElement checkingEmailLocator;

    @FindBy(xpath = "//li[@class='woocommerce-order-overview__total total']//bdi")
    public WebElement checkingAmountLocator;

    @FindBy(xpath = "//li[@class='woocommerce-order-overview__payment-method method']//strong")
    public WebElement checkingPaymentMethodLocator;

    @FindBy(xpath = "//h2[@class='post-title']")
    public WebElement approveLocator;

    @FindBy(xpath = "//div[@class='woocommerce-info']//a")
    public WebElement pressToEnterCouponLocator;

    @FindBy(xpath = "//p[@class='form-row form-row-first']//input")
    public WebElement couponInputLocator;

    @FindBy(xpath = "//p[@class='form-row form-row-last']//button")
    public WebElement couponButtonLocator;
}