module ru.nsu.belozerov {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.belozerov to javafx.fxml;
    exports ru.nsu.belozerov;
    exports ru.nsu.belozerov.javafx;
    opens ru.nsu.belozerov.javafx to javafx.fxml;
}