package exercises;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Exercises01Test {

    @Test
    public void aFirstPlaywrightTest() {

        // Create a new Playwright session
        // TODO: your code goes here

        // Start a new Chromium browser
        // TODO: your code goes here

        // Create a new Page
        // TODO: your code goes here

        // Navigate to https://www.saucedemo.com
        // TODO: your code goes here

        // Login by:
        // Typing 'standard_user' into the text field with placeholder 'Username'
        // Typing 'secret_sauce' into the text field with placeholder 'Password'
        // Clicking the button with name 'Login' (see the example for details)
        // TODO: your code goes here

        // Check that the element with text 'Products' is visible
        // TODO: your code goes here

        // Close the page, the browser and the Playwright session
        // TODO: your code goes here
    }
}
