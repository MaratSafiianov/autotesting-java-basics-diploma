package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v126.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationAndAuthorizationPage extends Page {
    private final WebDriver driver;

    public RegistrationAndAuthorizationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[@class='post-title']")
    public WebElement pageNameLocator;

    @FindBy(xpath = "//div[@id='accesspress-breadcrumb']//span")
    public WebElement breadcrumbLocator;

    @FindBy(xpath = "//button[@class='custom-register-button']")
    public WebElement registerButtonLocator;

    @FindBy(xpath = "//input[@id='reg_username']")
    public WebElement userInputLocator;

    @FindBy(xpath = "//input[@id='reg_email']")
    public WebElement emailInputLocator;

    @FindBy(xpath = "//input[@id='reg_password']")
    public WebElement passwordInputLocator;

    @FindBy(xpath = "//button[@name='register']")
    public WebElement finalRegisterButtonLocator;

    @FindBy(xpath = "//div[@class='content-page']")
    public WebElement registrationCompletedLocator;

    @FindBy(xpath = "//input[@name='username']")
    public WebElement userNameOrEmailInputLocator;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement AuthorizationPasswordInputLocator;

    @FindBy(xpath = "//button[@name='login']")
    public WebElement enterButtonLocator;

    @FindBy(xpath = "//div[@class='woocommerce-MyAccount-content']//strong")
    public WebElement greetingLocator;
}