package examples;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import examples.pages.AccountsOverviewPage;
import examples.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Examples02Test {

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
                .open()
                .loginAs("john", "demo");

        new AccountsOverviewPage(page)
                .selectAccount("12345");

        assertThat(page)
                .hasTitle("ParaBank | Account Activity");
    }

    @AfterEach
    public void stopSession() {

        browser.close();
        playwright.close();
    }
}
