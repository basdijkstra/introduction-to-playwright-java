package examples;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Examples01Test {

    @Test
    public void aFirstPlaywrightTest() {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();

        // Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();

        page.navigate("https://parabank.parasoft.com");

        page.locator("xpath=//input[@name='username']").fill("john");
        page.locator("xpath=//input[@name='password']").fill("demo");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log In")).click();

        assertThat(page).hasTitle("ParaBank | Accounts Overview");

        browser.close();
        playwright.close();
    }
}
