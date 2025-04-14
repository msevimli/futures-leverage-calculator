module com.plife.leveragecalc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires org.json;

    opens com.plife.leveragecalc to javafx.fxml;
    exports com.plife.leveragecalc;
}