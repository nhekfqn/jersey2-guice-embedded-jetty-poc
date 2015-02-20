package com.nhekfqn.poc.jersey2;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javax.inject.Inject;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

//@ApplicationPath("/rest") todo: does not work
public class MyApplication extends ResourceConfig {

    @Inject
    public MyApplication(ServiceLocator serviceLocator) {
        packages(Resource.class.getPackage().getName()); // todo: try class

        // todo: test!

        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(Service.class);
            }
        });

        // todo: auth intercept (will not work with Guice - make dual mapping or use jersey interceptors or try jersey2-guice)

        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);

        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(injector);
    }

}
