import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestHTMLTags {
    private WebDriver driver;
    private String baseUrl;
    public List<WebElement> elements;


    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://stackoverflow.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testHTML() throws Exception {
        driver.get(baseUrl);
        assertEquals("Script element without SRC attr is present", 0, driver.findElements(By.xpath(".//script[not(@src)]")).size());
        assertTrue("Script element is not 1", driver.findElements(By.xpath(".//script[@src]")).size() <= 1);
        assertTrue("Style element is not 1", driver.findElements(By.xpath(".//link[@rel='stylesheet']")).size() <= 1);
        assertTrue("Title tag is not present", driver.findElements(By.xpath(".//title")).size() > 0);
        assertTrue("Meta-tag Description is not present", driver.findElements(By.xpath("//meta[@name=\"description\"]")).size() > 0);
        assertTrue("Meta-tag Keywords is not present", driver.findElements(By.xpath("//meta[@name=\"keywords\"]")).size() > 0);

        elements = driver.findElements(By.xpath("//*[@id]"));
        ArrayList ids = new ArrayList();

        for (WebElement el : elements)
        {
            if (ids.contains(el.getAttribute("id"))) {
                assertTrue("Id is not unique: ".concat(el.getAttribute("id")), false);
            } else {
                ids.add(el.getAttribute("id"));
            }
        }
         assertTrue("H1 title is not 1", driver.findElements(By.xpath(".//h1")).size() <= 1);
        }


    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}


