module com.example.tmp_kursovaya {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.tmp_kursovaya to javafx.fxml;
    exports com.example.tmp_kursovaya;
}