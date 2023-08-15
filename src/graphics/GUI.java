package graphics;


import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import Command.*;

public class GUI implements ActionListener {

    private JFrame frame;
    private JTextArea textarea;
    private JScrollPane scrollBar;
    private JMenuBar menu;

    private File file;

    private UndoManager um = new UndoManager();

    private Command commandManagerEdit = new CommandManager(this);

    private Mediator mediator;

    private FileCommand fileCommandMng = new CommandFIleManager(this);

    public GUI() {
        createWIndow();
        createTextArea();
        createMenuBar();
        frame.setVisible(true);
    }

    public UndoManager getUm() {
        return um;
    }

    public Font getFontText() {
        return textarea.getFont();
    }

    public JTextArea getTextarea() {
        return textarea;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File f) {
        this.file = f;
    }

    private void createMenuBar() {
        menu = new JMenuBar();
        //file
        JMenu file = new JMenu("File");
        menu.add(file);
        JMenuItem newF = new JMenuItem("New");
        file.add(newF);
        newF.addActionListener(this);
        JMenuItem open = new JMenuItem("Open");
        file.add(open);
        open.addActionListener(this);
        JMenuItem save = new JMenuItem("Save");
        file.add(save);
        save.addActionListener(this);
        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);
        exit.addActionListener(this);

        //format
        JMenu format = new JMenu("Format");
        menu.add(format);
        JMenuItem size = new JMenuItem("Size");
        format.add(size);
        size.addActionListener(this);

        //edit
        JMenu edit = new JMenu("Edit");
        menu.add(edit);
        JMenuItem undo = new JMenuItem("Undo");
        edit.add(undo);
        undo.addActionListener(this);
        JMenuItem redo = new JMenuItem("Redo");
        edit.add(redo);
        redo.addActionListener(this);

        frame.setJMenuBar(menu);

    }

    private void createWIndow() {
        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Notepad");
    }

    private void createTextArea() {
        textarea = new JTextArea();
        textarea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                um.addEdit(e.getEdit());
            }
        });
        scrollBar = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Exit":
                fileCommandMng.exit();
                break;
            case "Save":
                fileCommandMng.saveFile();
                break;
            case "Undo":
                commandManagerEdit.undo();
                break;
            case "Redo":
                commandManagerEdit.redo();
                break;
            case "Size":
                FontSize fs = new FontSize();
                mediator = new GUIMediator(this, fs);
                fs.setMediator(mediator);
                break;
            case "Open":
                fileCommandMng.openFile();
                break;
            case "New":
                fileCommandMng.newFile();
                break;
        }
    }


}
