package examples;

import com.microsoft.playwright.*;
import examples.pages.AccountsOverviewPage;
import examples.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Examples07Test {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;

    @BeforeEach
    public void startSession() {

        playwright = Playwright.create();
        browser = playwright.chromium().launch();
        browserContext = browser.newContext();
    }

    @Test
    public void usingPageObjects() {

        browserContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        Page page = browserContext.newPage();

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

        browserContext.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("playwright/traces/trace.zip")));

        browser.close();
        playwright.close();
    }
}
