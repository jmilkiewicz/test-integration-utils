package pl.softmil.test.integration.utils.http;

import static org.hamcrest.Matchers.is;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpHead;
import org.junit.*;

import pl.softmil.simpeweb.containers.*;
import pl.softmil.simpeweb.junit.StartStopSimpleWebServerRule;

public class HttpResourceRetrieverTest {
    private CompositeContainer compositeContainer = new CompositeContainer();
    private HttpResourceRetriever httpResourceRetriever = new HttpResourceRetriever();

    @Rule
    public StartStopSimpleWebServerRule startStopSimpleWebServerRule = new StartStopSimpleWebServerRule(
            34556, compositeContainer);

    @Test
    public void testExecute() throws ClientProtocolException, IOException {
        int responseCode = HttpStatus.SC_BAD_GATEWAY;
        compositeContainer
                .withContainer(new ResponseCodeContainer(responseCode));
        HttpResponseHelper responseHelper = httpResourceRetriever
                .execute(new HttpHead("http://localhost:34556"));
        responseHelper.assertCodeStatusMatches(is(responseCode));
    }

}
