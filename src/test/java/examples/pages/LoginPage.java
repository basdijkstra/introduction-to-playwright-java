package examples.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {

    private final Page page;
    private final Locator textfieldUsername;
    private final Locator textfieldPassword;
    private final Locator buttonSubmit;

    private final String url = "https://parabank.parasoft.com";

    public LoginPage(Page page) {

        this.page = page;
        this.textfieldUsername = page.locator("xpath=//input[@name='username']");
        this.textfieldPassword = page.locator("xpath=//input[@name='password']");
        this.buttonSubmit = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log In"));
    }

    public LoginPage open() {

        this.page.navigate(url);
        return this;
    }

    public void loginAs(String username, String password) {

        this.textfieldUsername.fill(username);
        this.textfieldPassword.fill(password);
        this.buttonSubmit.click();
    }
}
