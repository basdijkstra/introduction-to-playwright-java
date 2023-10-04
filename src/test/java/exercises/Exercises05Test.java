package exercises;

import answers.pages.LoginPage;
import answers.pages.ProductsOverviewPage;
import answers.pages.ShoppingCartPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Exercises05Test {

    // Shared between all tests in this class.
    private static Playwright playwright;
    private static Browser browser;

    // New instance for each test method.
    private BrowserContext context;
    private Page page;

    @BeforeAll
    public static void storeLoginState() {

        playwright = Playwright.create();
        browser = playwright.chromium().launch();
        BrowserContext loginContext = browser.newContext();

        Page page = loginContext.newPage();

        new LoginPage(page)
                .open()
                .loginAs("standard_user", "secret_sauce");

        // TODO: store the current, logged-in state from the current context in a file "playwright/.auth/state.json"
        // <your code goes here>
    }

    @BeforeEach
    public void startSession() {

        // TODO: retrieve the logged-in state from "playwright/.auth/state.json" and inject it into the 'context'
        // <your code goes here>

        // TODO: initialize the 'page' variable by creating a new Page that uses / derives from the 'context'
        // <your code goes here>
    }

    @Test
    public void checkThatProductsDetailsPageCanBeOpened() {

        // TODO: remove the login sequence from the test method and check that the test passes
        new LoginPage(page)
                .open()
                .loginAs("standard_user", "secret_sauce");

        new ProductsOverviewPage(page)
                .open()
                .selectProduct("Sauce Labs Backpack");

        assertThat(
                page.locator("xpath=//div[contains(@class,'inventory_details_name') and text()='Sauce Labs Backpack']"))
                .isVisible();
    }

    @Test
    public void checkThatCartPageCanBeOpened() {

        // TODO: remove the login sequence from the test method and check that the test passes
        new LoginPage(page)
                .open()
                .loginAs("standard_user", "secret_sauce");

        new ShoppingCartPage(page)
                .open();

        assertThat(
                page.locator("xpath=//span[@class='title' and text()='Your Cart']"))
                .isVisible();
    }

    @AfterEach
    public void stopSession() {

        context.close();
    }

    @AfterAll
    public static void stop() {

        browser.close();
        playwright.close();
    }
}
