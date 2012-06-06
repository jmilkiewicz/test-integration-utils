package pl.softmil.test.integration.utils.http;

import java.io.IOException;
import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;
import org.apache.http.params.*;

public class HttpResourceRetriever {
    private AbstractHttpClient httpClient = new DefaultHttpClient();

    public HttpResourceRetriever withCookies(Collection<Cookie> cookiesToSet) {
        httpClient.getCookieStore().clear();
        for (Cookie cookie : cookiesToSet) {
            httpClient.getCookieStore().addCookie(cookie);
        }
        return this;
    }
    
    public HttpResourceRetriever withNoRedirectionFollowing(){
        HttpParams params = new BasicHttpParams();
        params.setParameter(ClientPNames.HANDLE_REDIRECTS, false);
        httpClient.setParams(params);
        return this;
    }

    public HttpResponseHelper execute(HttpRequestBase httpRequest) throws ClientProtocolException, IOException {
        HttpResponse response = httpClient.execute(httpRequest);
        return new HttpResponseHelper(response);
    }
}
