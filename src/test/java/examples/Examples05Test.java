package examples;

import com.microsoft.playwright.*;
import examples.pages.AccountsOverviewPage;
import examples.pages.LoginPage;
import examples.pages.RequestLoanPage;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Examples05Test {

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
                .loginAs("john", "demo");

        loginContext.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("playwright/.auth/state.json")));
    }

    @BeforeEach
    public void startSession() {

        context = browser.newContext(
                new Browser.NewContextOptions().setStorageStatePath(Paths.get("playwright/.auth/state.json")));

        page = context.newPage();
    }

    @Test
    public void gotoAccountDetails() {

        new AccountsOverviewPage(page)
                .open()
                .selectAccount("12345");

        assertThat(page)
                .hasTitle("ParaBank | Account Activity");
    }

    @Test
    public void gotoRequestLoanPage() {

        new RequestLoanPage(page)
                .open();

        assertThat(page).hasTitle("ParaBank | Loan Request");
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
