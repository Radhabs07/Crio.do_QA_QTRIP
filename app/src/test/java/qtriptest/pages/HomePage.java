package qtriptest.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class HomePage {
    WebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/";

    @FindBy(xpath = "//a[@class='nav-link login register']")
    private WebElement registerElement;

    public HomePage(WebDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 20);
        PageFactory.initElements(factory, this);
    }

    public void navigateToRegister() {
        String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
        if(this.driver.getCurrentUrl() != url){
            this.driver.get(url);
        }
        registerElement.click();
    }

    // public void navigateToHome() {
    //     String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
    //     if(this.driver.getCurrentUrl() != url){
    //         this.driver.get(url);
    //     }    }

    public void searchCity(String city, String category_filter, String duration_filter, String expectedFilterResults, String expectedUnfilterResults) throws InterruptedException{
        
        WebElement searchCity = driver.findElement(By.xpath("//input[@class='hero-input']"));
        searchCity.click();
        Thread.sleep(1000);
        searchCity.sendKeys(city);
        Thread.sleep(2000);
        WebElement searchSuggestion = driver.findElement(By.xpath("//ul[@id='results']/a"));
        Assert.assertTrue(searchSuggestion.isDisplayed(), "Searched City is Present");

        searchSuggestion.click();

        Thread.sleep(1000);

        Assert.assertTrue(driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/?" +"city=" + city.toLowerCase()), "Success");

        WebElement category_dropdown_element = driver.findElement(By.xpath("//select[@id='category-select']"));

       // String duration_filter = ""; duration_dropdown_element //select[@name='duration']

        Select category_dropdown = new Select(category_dropdown_element);
        Thread.sleep(2000);
        category_dropdown.selectByVisibleText(category_filter);

        //Thread.sleep(2000);


        //String category_dropdown_value = category_dropdown_element.  //div[@class='category-filter']//div

        // WebElement category_dropdown_visible_value = driver.findElement(By.xpath("//div[@class='category-filter']//div"));

        // String category_dropdown_value = category_dropdown_visible_value.getText();

        // Assert.assertEquals(category_dropdown_value, category_dropdown_value);

        Thread.sleep(3000);
        WebElement duration_dropdown_element = driver.findElement(By.xpath("//select[@id='duration-select']"));

       // String category_filter ="";
        Select duration_dropdown = new Select(duration_dropdown_element);
        Thread.sleep(2000);
        duration_dropdown.selectByVisibleText(duration_filter);
 
        //Thread.sleep(1000);

        Thread.sleep(3000);

      List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='col-6 col-lg-3 mb-4']//a"));

      Thread.sleep(2000);

      String filtersearchResultsCount = String.valueOf(searchResults.size());

      Assert.assertEquals(filtersearchResultsCount, expectedFilterResults);

      Thread.sleep(5000);

      WebElement category_clear = driver.findElement(By.xpath("//div[@onclick='clearCategory(event)']"));
      category_clear.click();

      Thread.sleep(10000); //select[@id='category-select']/following-sibling::div[@class='ms-3'] //div[@onclick="clearCategory(event)"]

      WebElement duration_clear = driver.findElement(By.xpath("//div[@onclick='clearDuration(event)']"));
      duration_clear.click();

      Thread.sleep(5000);
      
      List<WebElement> searchResultafterclear = driver.findElements(By.xpath("//div[@class='col-6 col-lg-3 mb-4']//a"));
      String unfiltersearchResultsCount = String.valueOf(searchResultafterclear.size());

      Assert.assertEquals(unfiltersearchResultsCount, expectedUnfilterResults);

        
    }

    public void search_city(String city) throws InterruptedException{
        Thread.sleep(2000);


        WebElement searchCity = driver.findElement(By.xpath("//input[@id='autocomplete']"));
        searchCity.click();
        Thread.sleep(1000);
        searchCity.sendKeys(city);
        Thread.sleep(2000);
        WebElement searchSuggestion = driver.findElement(By.xpath("//ul[@id='results']/a"));
        Assert.assertTrue(searchSuggestion.isDisplayed(), "Searched City is Present");

        searchSuggestion.click();

        Thread.sleep(1000);
    }

}

