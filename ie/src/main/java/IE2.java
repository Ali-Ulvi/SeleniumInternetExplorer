import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class IE2 {
    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.ie.driver", "C:\\selenium\\driver\\IEDriverServer_Win32_3.14.0\\IEDriverServer.exe");
        System.setProperty("webdriver.ie.driver", "C:\\Users\\Ulvi\\Downloads\\IEDriverServer_Win32_3.14.0\\IEDriverServer.exe");

        //Initialize InternetExplorerDriver Instance.
        DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
        // cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability("ie.ensureCleanSession", true);
        cap.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "http://www.bing.com/");
        cap.setCapability("nativeEvents", false);
        cap.setCapability("unexpectedAlertBehaviour", "accept");
        cap.setCapability("ignoreProtectedModeSettings", true);
        cap.setCapability("disable-popup-blocking", true);
        cap.setCapability("enablePersistentHover", true);
        cap.setCapability("ignoreZoomSetting", true);
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability("IE.switches", Arrays.asList("--start-maximized", "--ignore-certificate-errors",
                "--disable-popup-blocking", "--disable-default-apps", "--auto-launch-at-startup", "--always-authorize-plugins"));

        driver = new InternetExplorerDriver(cap);

        driver.get("http://10.144.15.143:7782/frameset/login.action");
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
        ///***login way 1
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement username= driver.findElement(By.id("loginID"));
        driver.findElement(new By.ById("loginID")).sendKeys("vfpcteam");
        driver.findElement(new By.ById("password")).clear();
        driver.findElement(new By.ById("password")).sendKeys("Offer12345*");
        driver.findElement(new By.ById("login_submit")).click();
        ///***login way 2
     */
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement username= driver.findElement(By.id("loginID"));
        Thread.sleep(1000);

        username.sendKeys(Keys.DOWN);
        Thread.sleep(1000);

        username.sendKeys(Keys.DOWN);
        Thread.sleep(1000);

        username.sendKeys(Keys.RETURN);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Thread.sleep(1000);

        driver.findElement(new By.ById("login_submit")).click();
        Thread.sleep(1000);


        //driver.findElement(new By.ById("password")).sendKeys("Offer12345*");
        // driver.findElement(new By.ById("password")).sendKeys("");



        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(new By.ById("login_submit")));
        WebDriverWait wait = new WebDriverWait(driver, 15);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#moreInfoContainer")));
        WebElement more = driver.findElement(By.cssSelector("#moreInfoContainer")).findElement(By.tagName("a"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", more);
        String first_tab = driver.getWindowHandle();

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(new By.ById("overridelink")));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        while (i1.hasNext()) {
            String next_tab = i1.next();
            if (!first_tab.equalsIgnoreCase(next_tab)) {
                driver.switchTo().window(next_tab);

                System.out.println("Working on cbs");
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=\"Product\"]")));

        WebElement product = driver.findElement(By.xpath("//span[text()=\"Product\"]"));

        WebElement system = driver.findElement(By.xpath("//span[text()=\"System\"]"));
        click(system);

        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Offer Management")));

        //WebElement offerManagement = driver.findElement(By.linkText("Offer Management"));
        //((JavascriptExecutor)driver).executeScript("arguments[0].click();", offerManagement);

        //<A title="IP Address and Area Code" class=has_sub style="COLOR: #6a792f">IP Address and Area Code</A>
        WebDriverWait wait_30 = new WebDriverWait(driver,30);
     driver.switchTo().frame("pluginIFraplugin10");
        WebElement area_code = driver.findElement(By.xpath("//*[contains(@title,'IP Address and Area Code')]"));


        //wait_30.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("IP Address and Area Code")));
        //WebElement ip_add_area_code = driver.findElement(By.linkText("IP Address and Area Code"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", area_code);

        driver.switchTo().parentFrame();

        Thread.sleep(2000);
        driver.switchTo().frame("tabPageIfra1");
        //new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tabPageIfra1")));

        Thread.sleep(6000);
        driver.findElement(By.id("dijit_form_FilteringSelect_0")).sendKeys("90");
        driver.findElement(By.id("dijit_form_FilteringSelect_0")).sendKeys(Keys.RETURN);

        WebElement query = driver.findElement(By.xpath("//*[@value='Query']"));
        Thread.sleep(2000);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", query);

        try {
            Thread.sleep(9444);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //driver.close();

    }
    static   public void click(WebElement a)
    {
        try
        {
            String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
            ((JavascriptExecutor) driver).executeScript(mouseOverScript,a);
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript(mouseOverScript, driver.findElement(By.linkText("Number Analysis")));
            Thread.sleep(1000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("Number Analysis")));


        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

