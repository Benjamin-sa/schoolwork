module be.inf1.flappybird2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires com.google.gson;


    opens be.inf1.flappybird2 to javafx.fxml;
    exports be.inf1.flappybird2;
    requires transitive javafx.graphics;
}
