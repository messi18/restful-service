package com.ma.lance.restful;

import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

import java.lang.management.ManagementFactory;

/**
 * Created by malance on 14/11/20.
 */
public class RestfulServer implements Lifecycle{
    private Server jettyServer;

    @Override
    public void init(int port) {
        jettyServer = new Server(port);
        MBeanContainer mBeanContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
        jettyServer.addBean(mBeanContainer);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.setContextPath("/");
        jettyServer.setHandler(servletContextHandler);

        ServletHolder servletHolder = new ServletHolder(new ServletContainer());
        servletHolder.setInitParameter(ServerProperties.PROVIDER_PACKAGES,"com.ma.lance.restful");
        servletHolder.setInitOrder(1);

        servletContextHandler.addServlet(servletHolder,"/*");
        jettyServer.setDumpAfterStart(true);


    }

    @Override
    public void start() {
        try {
            jettyServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            jettyServer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RestfulServer restfulServer = new RestfulServer();
        restfulServer.init(8060);
        restfulServer.start();
        Thread.sleep(1000000);
        restfulServer.stop();
    }
}
