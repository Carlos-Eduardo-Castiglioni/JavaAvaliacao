module org.example.avaliacao {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.avaliacao to javafx.fxml;
    exports org.example.avaliacao;
}