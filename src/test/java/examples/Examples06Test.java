package examples;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Examples06Test {

    @Test
    public void mockBackendApiResponse() {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();

        Page page = browser.newPage();

        page.route("*/**/api/v1/fruits", route -> {

            // fulfill the route with the mock data
            route.fulfill(new Route.FulfillOptions().setBody("[{\"name\": \"ThisIsVisible\", \"id\": 1}]"));
        });

        // Go to the page
        page.navigate("https://demo.playwright.dev/api-mocking");

        // Assert that the Strawberry fruit is visible
        assertThat(page.getByText("ThisIsVisible")).isVisible();

        browser.close();
        playwright.close();
    }
}
