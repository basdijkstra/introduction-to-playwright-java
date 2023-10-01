package exercises;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercises04Test {

    private Playwright playwright;

    private APIRequestContext request;

    @BeforeEach
    public void startSession() {

        playwright = Playwright.create();

        // TODO: initialize the request context, using the base URL 'https://jsonplaceholder.typicode.com'

    }

    @Test
    public void retrievingAndVerifyingUserDetails() {

        // TODO: retrieve the details for user with ID 1 by performing a GET request to "/users/1".
        //  Store the response in an object of type APIResponse.


        // TODO: verify that the response status code equals 200.


        // TODO: verify that the response body contains an header "content-type" (all lowercase!)
        //  with value "application/json; charset=utf-8".
        //  Hint: you can retrieve the headers of a response using response.headers()

    }

    @Test
    public void postingContentToAnAPI() {

        // TODO: Have a look at https://playwright.dev/java/docs/api-testing#write-tests

        // TODO: create a new HashMap and add three entries:
        //  userId = 1,
        //  title = "My new post title",
        //  body = "My awesome new post body"


        // TODO: Perform an HTTP POST to "/posts" and pass the HashMap as request data


        // TODO: Print the response body to the standard output using
        //  System.out.println(new String(response.body()));
        //  to check that your input has been echoed successfully in the response


        // TODO: verify that the response status code is equal to 201

    }

    @AfterEach
    public void stopSession() {

        // TODO: properly dispose the request context. See the examples on how to do this.


        playwright.close();
    }
}
