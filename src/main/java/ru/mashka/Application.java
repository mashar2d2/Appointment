package ru.mashka;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;
import java.sql.SQLException;
import java.time.ZoneOffset;
import java.util.TimeZone;

public class Application {

    public static final String BASE_URI = "http://localhost:8080/";

    public static void main(String[] args) throws SQLException {
        startServer();
        System.out.printf("Jersey app started  " + "%sapplication.wadl%n", BASE_URI);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static HttpServer startServer() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
        final ResourceConfig jaxResources = new ResourceConfig()
                .packages("ru.mashka")
                .register(JacksonFeature.class)
                .register(new ApplicationBinder());
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), jaxResources);
    }
}