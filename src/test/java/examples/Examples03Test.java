package examples;

import com.deque.html.axecore.playwright.AxeBuilder;
import com.deque.html.axecore.results.AxeResults;
import com.deque.html.axecore.results.Rule;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import examples.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Examples03Test {

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

        new LoginPage(page)
                .open();

        AxeResults accessibilityScanResults = new AxeBuilder(page).analyze();

        for(Rule rule : accessibilityScanResults.getViolations()) {

            System.out.printf("a11y violation: %s - %s\n", rule.getId(), rule.getDescription());
        }

        assertEquals(0, accessibilityScanResults.getViolations().size());
    }

    @AfterEach
    public void stopSession() {

        browser.close();
        playwright.close();
    }
}
