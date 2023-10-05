package answers;

import answers.pages.LoginPage;
import answers.pages.ProductDetailsPage;
import answers.pages.ProductsOverviewPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Answers02Test {

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
        new LoginPage(page)
                .open()
                .loginAs("standard_user", "secret_sauce");

        // TODO: Create a new ProductsOverviewPage instance (the class already exists). Then,
        //  in the page object class, create a method to select the product with the name 'Sauce Labs Backpack'
        //  and then call it here
        new ProductsOverviewPage(page)
                .selectProduct("Sauce Labs Backpack");

        // After you completed the exercises, this assertion (and therefore the test) should pass
        // TODO: can you further improve the code by writing a new Page Object class
        //  with a method that returns whether or not the below locator is visible?
        new ProductDetailsPage(page)
                .verifyThatProductDetailsAreVisibleFor("Sauce Labs Backpack");
    }

    @AfterEach
    public void stopSession() {

        browser.close();
        playwright.close();
    }
}
