module whats.newjava {
    requires org.reactivestreams;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    requires java.sql;
    requires java.naming;
    requires java.base;
    exports com.xyzcorp.httpclient;
    opens com.xyzcorp.httpclient;
}
