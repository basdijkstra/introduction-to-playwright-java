package answers.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {

    private final Page page;
    private final Locator textfieldUsername;
    private final Locator textfieldPassword;
    private final Locator buttonSubmit;

    private final String url = "https://www.saucedemo.com";

    public LoginPage(Page page) {

        this.page = page;
        this.textfieldUsername = page.getByPlaceholder("Username");
        this.textfieldPassword = page.getByPlaceholder("Password");
        this.buttonSubmit = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
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
