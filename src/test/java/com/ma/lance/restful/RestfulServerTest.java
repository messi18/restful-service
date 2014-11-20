package com.ma.lance.restful;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.text.html.parser.Entity;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

/**
 * Created by malance on 14/11/20.
 */
public class RestfulServerTest {
    private static final String test_url = "http://%s:%s/rest/test";
    private RestfulServer restfulServer;
    private String host = "localhost";
    private int port = 8080;

    @Before
    public void  init(){
        restfulServer = new RestfulServer();
        restfulServer.init(port);
        restfulServer.start();
    }
    @Test
    public void shouldJettyServerWork(){
        String url = String.format(test_url,host,port);
        Client client = ClientBuilder.newClient();
        String ret = client.target(url).request().get(String.class);
        assertEquals("Hello world!",ret);
    }

    @After
    public void after() {
        restfulServer.stop();
    }
}
