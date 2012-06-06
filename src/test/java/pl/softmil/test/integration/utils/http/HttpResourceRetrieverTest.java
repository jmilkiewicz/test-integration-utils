package pl.softmil.test.integration.utils.http;

import static org.hamcrest.Matchers.is;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.junit.*;

import pl.softmil.simpeweb.containers.*;
import pl.softmil.simpeweb.junit.StartStopSimpleWebServerRule;
import pl.softmil.simpeweb.router.SimpleRouter;

public class HttpResourceRetrieverTest {
    private CompositeContainer compositeContainer = new CompositeContainer();
    private HttpResourceRetriever httpResourceRetriever = new HttpResourceRetriever();

    @Rule
    public StartStopSimpleWebServerRule startStopSimpleWebServerRule = new StartStopSimpleWebServerRule(
            34556, compositeContainer);

    @Test
    public void testRetrieveResource() throws ClientProtocolException, IOException {
        int responseCode = HttpStatus.SC_BAD_GATEWAY;
        compositeContainer
                .withContainer(new ResponseCodeContainer(responseCode));
        HttpResponseHelper responseHelper = httpResourceRetriever
                .execute(new HttpHead("http://localhost:34556"));
        responseHelper.assertCodeStatusMatches(is(responseCode));
    }

    @Test(timeout=1000)
    public void testFollowsRedirectionOutOfBox()
            throws ClientProtocolException, IOException {
        int targetUrlResponseCode = HttpStatus.SC_BAD_GATEWAY;
        compositeContainer.withContainer(new SimpleRouter().addHandler("/red",
                new RedirectContainer("/target")).addHandler("/target",
                new ResponseCodeContainer(targetUrlResponseCode)));

        HttpResponseHelper responseHelper = httpResourceRetriever
                .execute(new HttpHead("http://localhost:34556/red"));
        responseHelper.assertCodeStatusMatches(is(targetUrlResponseCode));
    }

    @Test(timeout=1000)
    public void shouldNotFollowRedirectsWhenNoRedirectionFollowingON()
            throws Exception {
        RedirectContainer redirectContainer = new RedirectContainer("/target");
        compositeContainer.withContainer(redirectContainer);

        HttpResponseHelper responseHelper = httpResourceRetriever
                .withNoRedirectionFollowing().execute(
                        new HttpGet("http://localhost:34556"));

        responseHelper.assertCodeStatusMatches(is(redirectContainer
                .getRedirectCode()));
    }
}
