package answers;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import examples.pages.AccountsOverviewPage;
import examples.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Answers06Test {

    @Test
    public void mockBackendApiResponse() {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();

        // TODO: add a new mock response to the page. This should mock the call to
        //  https://parabank.parasoft.com/parabank/services_proxy/bank/customers/12212/accounts
        //  and return a list with a single account with #99999. The mock response body looks like this:
        //  [{"id":99999,"customerId":12212,"type":"CHECKING","balance":9999.99}]
        page.route("https://parabank.parasoft.com/parabank/services_proxy/bank/customers/12212/accounts", route -> {
            route.fulfill(new Route.FulfillOptions().setBody("[{\"id\":99999,\"customerId\":12212,\"type\":\"CHECKING\",\"balance\":9999.99}]"));
        });

        // Go to the page
        new LoginPage(page)
                .open()
                .loginAs("john", "demo");

        // TODO: write an assertion that checks that the page shows a link to the account details for account
        //  with number 99999 (i.e., a link with its text equal to '99999').
        assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("99999"))).isVisible();

        // TODO: EXTRA: (warning: this exercise likely won't result in passing tests ;)
        //  Add two more mock responses:
        //  - one that should be replayed when the user clicks the link to go to the account details
        //  - one that returns a transaction for that account so that shows up in the account details page, too
        //  Find out what the responses should look like for yourself.
        page.route("https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/99999", route -> {
            route.fulfill(new Route.FulfillOptions().setBody("[{\"id\":99999,\"customerId\":12212,\"type\":\"CHECKING\",\"balance\":9999.99}]"));
        });

        page.route("https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/99999/transactions", route -> {
            route.fulfill(new Route.FulfillOptions().setBody("[{\"id\": 45454, \"accountId\": 99999, \"type\": \"Credit\", \"date\": 1670774400000, \"amount\": 300.00, \"description\": \"Check # 1234\"}]"));
        });

        new AccountsOverviewPage(page)
                .selectAccount("99999");

        // TODO: EXTRA: Can you explain why this doesn't work?

        browser.close();
        playwright.close();
    }
}
