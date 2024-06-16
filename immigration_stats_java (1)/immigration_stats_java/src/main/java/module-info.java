module org.example.immigration_stats_java {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example.immigration_stats_java to javafx.fxml;
    exports org.example.immigration_stats_java;
}