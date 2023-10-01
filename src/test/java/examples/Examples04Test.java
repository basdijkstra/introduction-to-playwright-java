package examples;

import com.microsoft.playwright.*;
import examples.pages.AccountsOverviewPage;
import examples.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class Examples04Test {

    private Playwright playwright;
    private Browser browser;

    private APIRequestContext request;

    @BeforeEach
    public void startSession() {

        playwright = Playwright.create();
        browser = playwright.chromium().launch();

        request = playwright.request().newContext(
                new APIRequest.NewContextOptions()
                        .setBaseURL("https://parabank.parasoft.com")
        );

        // This API call initializes the database before every test
        APIResponse response = request.post("/parabank/services/bank/initializeDB");
        assertEquals(204, response.status());
    }

    @Test
    public void callingAnAPI() {

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

        if (request != null) {
            request.dispose();
            request = null;
        }

        browser.close();
        playwright.close();
    }
}
