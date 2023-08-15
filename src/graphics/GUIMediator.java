package graphics;

import java.awt.*;

public class GUIMediator implements Mediator{
    private GUI gui;
    private FontSize  fontsize;
    public GUIMediator(GUI gui, FontSize fontsize) {
        this.gui = gui;
        this.fontsize = fontsize;
    }

    @Override
    public void setFont() {
        Font f=gui.getFontText();
        int size=fontsize.getFontsize();
        Font nuovo=new Font(f.getName(),f.getStyle(), size);
        gui.getTextarea().setFont(nuovo);
    }
}
