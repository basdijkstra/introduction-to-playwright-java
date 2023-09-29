package answers.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ProductsOverviewPage {

    private final Page page;

    public ProductsOverviewPage(Page page) {

        this.page = page;
    }

    // TODO: Create a new method to select a product. It should take a single String argument
    //  that contains the name of the product to be selected, and it should click the link
    //  for the supplied product name. Let's create the locator ourselves.
    //  First, have a look at the selectAccount() method in
    //  src/test/java/examples/pages/AccountsOverviewPage.java and try a similar locator.
    //  What happens here?
    //  As a solution to this issue, try the following xpath locator: xpath=//a/div[text()='<productName>']
    //  This should work :)
    public void selectProduct(String productName) {

        this.page.locator(String.format("xpath=//a/div[text()='%s']", productName)).click();
    }
}
