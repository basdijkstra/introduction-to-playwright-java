package exercises;

import exercises.pages.LoginPage;
import exercises.pages.ProductsOverviewPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Exercises02Test {

    private Playwright playwright;
    private Browser browser;

    @BeforeEach
    public void startSession() {

        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @Test
    public void usingPageObjects() {

        Page page = browser.newPage();

        // TODO: Create a new LoginPage instance (the class and methods already exist)
        //  then first call open() on it, then loginAs(), passing the credentials as arguments


        // TODO: Create a new ProductsOverviewPage instance (the class already exists). Then,
        //  in the page object class, create a method to select the product with the name 'Sauce Labs Backpack'
        //  and then call it here

        // After you completed the exercises, this assertion (and therefore the test) should pass
        // TODO: can you further improve the code by writing a new Page Object class
        //  with a method that returns whether or not the below locator is visible?
        assertThat(
                page.locator("xpath=//div[contains(@class,'inventory_details_name') and text()='Sauce Labs Backpack']"))
                .isVisible();
    }

    @AfterEach
    public void stopSession() {

        browser.close();
        playwright.close();
    }
}
