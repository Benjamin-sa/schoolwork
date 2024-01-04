module be.inf1.flappybird2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.graphics;

    opens be.inf1.flappybird2 to javafx.fxml;
    exports be.inf1.flappybird2;
    exports be.inf1.flappybird2.model to javafx.graphics;
}
