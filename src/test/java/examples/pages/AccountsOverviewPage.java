package examples.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AccountsOverviewPage {

    private final Page page;

    public AccountsOverviewPage(Page page) {

        this.page = page;
    }

    public void selectAccount(String accountNumber) {

        this.page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(accountNumber)).click();
    }
}
