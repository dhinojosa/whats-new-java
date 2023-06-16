module whats.newjava {
    requires jdk.incubator.concurrent;
    requires org.reactivestreams;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    exports com.xyzcorp.httpclient;
    opens com.xyzcorp.httpclient;
}
