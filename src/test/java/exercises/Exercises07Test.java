package exercises;

import answers.pages.LoginPage;
import answers.pages.ProductDetailsPage;
import answers.pages.ProductsOverviewPage;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class Exercises07Test {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;

    @BeforeEach
    public void startSession() {

        playwright = Playwright.create();
        browser = playwright.chromium().launch();

        // TODO: initialize the browser context

    }

    @Test
    public void usingPageObjects() {

        // TODO: Enable tracing within the current BrowserContext, including screenshots, snapshots and sources


        // TODO: derive the Page object from the BrowserContext instead of the Browser itself
        Page page = browser.newPage();

        new LoginPage(page)
                .open()
                .loginAs("standard_user", "secret_sauce");

        new ProductsOverviewPage(page)
                .selectProduct("Sauce Labs Backpack");

        new ProductDetailsPage(page)
                .verifyThatProductDetailsAreVisibleFor("Sauce Labs Backpack");
    }

    @AfterEach
    public void stopSession() {

        // TODO: after the test has completed, stop tracing and save the trace
        //  in a trace.zip file in the playwright/traces/ subfolder in the project.


        // TODO: upload the saved trace to https://trace.playwright.dev and have a look.
        //  Do you see any information that you think is worth reporting?

        browser.close();
        playwright.close();
    }
}
