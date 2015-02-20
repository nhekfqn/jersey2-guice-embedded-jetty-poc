package com.nhekfqn.poc.jersey2;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

// todo: http://stackoverflow.com/questions/17285388/how-to-use-guice-servlet-with-jersey-2-0
public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        // todo: resource handler

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/app");
        server.setHandler(context);

        ServletHolder servletHolder = new ServletHolder(new ServletContainer());
        servletHolder.setInitParameter("javax.ws.rs.Application", MyApplication.class.getCanonicalName());
        context.addServlet(servletHolder, "/*");

        server.start();
        server.join();
    }

}
