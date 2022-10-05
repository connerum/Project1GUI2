module edu.bsu.cs222.project1gui2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.path;
    requires json.smart;


    opens edu.bsu.cs222.project1gui2 to javafx.fxml;
    exports edu.bsu.cs222.project1gui2;
}