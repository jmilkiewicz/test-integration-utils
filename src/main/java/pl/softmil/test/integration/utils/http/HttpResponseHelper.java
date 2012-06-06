package pl.softmil.test.integration.utils.http;

import static org.junit.Assert.assertThat;

import org.apache.http.HttpResponse;
import org.hamcrest.Matcher;

public class HttpResponseHelper {
    private final HttpResponse originalHttpResponse;

    public HttpResponseHelper(HttpResponse response) {
        this.originalHttpResponse = response;
    }

    public void assertCodeStatusMatches(
            Matcher<? super Integer> statusCodeMatcher) {
        assertThat("invalid http response status code", getStatusLineCode(),
                statusCodeMatcher);
    }
    
    public HttpResponse getOriginalHttpResponse() {
        return originalHttpResponse;
    }


    private int getStatusLineCode() {
        return originalHttpResponse.getStatusLine().getStatusCode();
    }
    
}
