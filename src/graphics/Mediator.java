package graphics;

import java.awt.*;

public interface Mediator {
    default void startFontSize(){
        new FontSize();
    }

    void setFont();


}
