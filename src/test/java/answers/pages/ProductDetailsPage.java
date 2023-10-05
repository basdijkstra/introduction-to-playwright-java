package answers.pages;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ProductDetailsPage {

    private final Page page;

    public ProductDetailsPage(Page page) {

        this.page = page;
    }

    public void verifyThatProductDetailsAreVisibleFor(String productName) {

        assertThat(this.page.locator(
                String.format("xpath=//div[contains(@class,'inventory_details_name') and text()='%s']", productName
                )
        )).isVisible();
    }
}
