package answers;

import answers.pages.LoginPage;
import answers.pages.ProductsOverviewPage;
import com.deque.html.axecore.playwright.AxeBuilder;
import com.deque.html.axecore.results.AxeResults;
import com.deque.html.axecore.results.Rule;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Answers03Test {

    private Playwright playwright;
    private Browser browser;

    @BeforeEach
    public void startSession() {

        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @Test
    public void aFirstPlaywrightTest() {

        Page page = browser.newPage();

        new LoginPage(page)
                .open()
                .loginAs("standard_user", "secret_sauce");

        new ProductsOverviewPage(page)
                .selectProduct("Sauce Labs Backpack");

        // TODO: scan the product details page for accessibility violations
        //  and assert that there are 0 violations.
        //  Additionally, inspect the violations that are reported and see
        //  if they make sense to you and think how you would report on them to your developers.
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
