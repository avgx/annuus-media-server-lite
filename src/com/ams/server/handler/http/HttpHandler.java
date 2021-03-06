package com.ams.server.handler.http;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ams.io.network.Connection;
import com.ams.protocol.http.DefaultServlet;
import com.ams.protocol.http.HttpRequest;
import com.ams.protocol.http.HttpResponse;
import com.ams.protocol.http.ServletContext;
import com.ams.server.handler.IProtocolHandler;

public class HttpHandler implements IProtocolHandler {
    final private Logger logger = LoggerFactory.getLogger(HttpHandler.class);

    private Connection connection;
    private ServletContext context;

    public HttpHandler(Connection connection, ServletContext context) {
        this.connection = connection;
        this.context = context;
    }

    public void run() {
        HttpRequest request = new HttpRequest(connection.getInputStream());
        HttpResponse response = new HttpResponse(connection.getOutputStream());
        try {
            DefaultServlet servlet = new DefaultServlet(context);

            request.parse();
            servlet.service(request, response);
            if (request.isKeepAlive()) {
                connection.close(true);
            } else {
                connection.close();
            }
        } catch (IOException e) {
            logger.debug(e.getMessage());
            close();
        }
    }

    public void close() {
        connection.close();
    }

    public boolean isKeepAlive() {
        return false;
    }

}