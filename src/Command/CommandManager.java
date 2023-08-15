package Command;

import graphics.GUI;

public class CommandManager implements Command{

    private GUI gui;

    public CommandManager(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void undo() {
        gui.getUm().undo();
    }

    @Override
    public void redo() {
        gui.getUm().redo();
    }
}
