package examples.pages;

import com.microsoft.playwright.Page;

public class RequestLoanPage {

    private final Page page;

    private String url = "https://parabank.parasoft.com/parabank/requestloan.htm";

    public RequestLoanPage(Page page) {

        this.page = page;
    }

    public RequestLoanPage open() {

        this.page.navigate(url);
        return this;
    }
}
