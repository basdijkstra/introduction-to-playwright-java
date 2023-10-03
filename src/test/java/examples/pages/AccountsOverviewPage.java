package examples.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AccountsOverviewPage {

    private final Page page;

    private String url = "https://parabank.parasoft.com/parabank/overview.htm";

    public AccountsOverviewPage(Page page) {

        this.page = page;
    }

    public AccountsOverviewPage open() {

        this.page.navigate(url);
        return this;
    }

    public void selectAccount(String accountNumber) {

        this.page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(accountNumber)).click();
    }
}
