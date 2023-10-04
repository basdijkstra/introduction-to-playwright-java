package answers.pages;

import com.microsoft.playwright.Page;

public class ShoppingCartPage {

    private final Page page;

    private final String url = "https://www.saucedemo.com/cart.html";

    public ShoppingCartPage(Page page) {

        this.page = page;
    }

    public ShoppingCartPage open() {

        this.page.navigate(this.url);
        return this;
    }
}
