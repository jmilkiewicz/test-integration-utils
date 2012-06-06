package pl.softmil.test.integration.utils.http;

import java.io.IOException;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;

public class HttpResourceRetriever {
    private AbstractHttpClient httpClient = new DefaultHttpClient();

    public HttpResourceRetriever withCookies(Set<Cookie> cookiesToSet) {
        httpClient.getCookieStore().clear();
        for (Cookie cookie : cookiesToSet) {
            httpClient.getCookieStore().addCookie(cookie);
        }
        return this;
    }

    public HttpResponseHelper execute(HttpRequestBase httpRequest) throws ClientProtocolException, IOException {
        HttpResponse response = httpClient.execute(httpRequest);
        return new HttpResponseHelper(response);
    }
}
