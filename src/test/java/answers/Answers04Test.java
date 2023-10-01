package answers;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Answers04Test {

    private Playwright playwright;

    private APIRequestContext request;

    @BeforeEach
    public void startSession() {

        playwright = Playwright.create();

        // TODO: initialize the request context, using the base URL 'https://jsonplaceholder.typicode.com'
        request = playwright.request().newContext(
                new APIRequest.NewContextOptions()
                        .setBaseURL("https://jsonplaceholder.typicode.com")
        );
    }

    @Test
    public void retrievingAndVerifyingUserDetails() {

        // TODO: retrieve the details for user with ID 1 by performing a GET request to "/users/1".
        //  Store the response in an object of type APIResponse.
        APIResponse response = request.get("/users/1");

        // TODO: verify that the response status code equals 200.
        assertEquals(200, response.status());

        // TODO: verify that the response body contains an header "content-type" (all lowercase!)
        //  with value "application/json; charset=utf-8".
        //  Hint: you can retrieve the headers of a response using response.headers()
        assertEquals("application/json; charset=utf-8", response.headers().get("content-type"));
    }

    @Test
    public void postingContentToAnAPI() {

        // TODO: Have a look at https://playwright.dev/java/docs/api-testing#write-tests

        // TODO: create a new HashMap and add three entries:
        //  userId = 1,
        //  title = "My new post title",
        //  body = "My awesome new post body"
        HashMap<String, Object> data = new HashMap<>();
        data.put("userId", 1);
        data.put("title", "My new post title");
        data.put("body", "My awesome new post body");

        // TODO: Perform an HTTP POST to "/posts" and pass the HashMap as request data
        APIResponse response = request.post("/posts", RequestOptions.create().setData(data));

        // TODO: Print the response body to the standard output using
        //  System.out.println(new String(response.body()));
        //  to check that your input has been echoed successfully in the response
        System.out.println(new String(response.body()));

        // TODO: verify that the response status code is equal to 201
        assertEquals(201, response.status());
    }

    @AfterEach
    public void stopSession() {

        // TODO: properly dispose the request context. See the examples on how to do this.
        if (request != null) {
            request.dispose();
            request = null;
        }

        playwright.close();
    }
}
