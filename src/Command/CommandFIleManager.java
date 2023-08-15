package Command;

import graphics.GUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class CommandFIleManager implements FileCommand {

    private GUI gui;

    public CommandFIleManager(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void saveFile() {
        salvaFile();
    }

    @Override
    public void openFile() {
        apriFile();
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public void newFile() {
        salvaFile();
        gui.setFile(null);
        gui.getTextarea().setText("");
    }

    //AVREI DOVUTO FARE MEDIATOR PER GESTIONE DI TUTTI SOTTO COMANDI
    private void salvaFile() {
        if (gui.getFile() == null) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                scriviSuFile(selectedFile);
            }
            return;
        }
        scriviSuFile(gui.getFile());
    }

    //AVREI DOVUTO FARE MEDIATOR PER GESTIONE DI TUTTI SOTTO COMANDI
    private void scriviSuFile(File f) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(gui.getTextarea().getText());
            gui.setFile(f);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Salvataggio effettuato");
        }
    }

    //AVREI DOVUTO FARE MEDIATOR PER GESTIONE DI TUTTI SOTTO COMANDI
    private void apriFile() {
        JFileChooser jf = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        jf.setFileFilter(filter);
        int returnVal = jf.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    jf.getSelectedFile().getName());
            gui.setFile(jf.getSelectedFile());
        }
        try {
            FileReader fr = new FileReader(gui.getFile());
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                gui.getTextarea().append(line + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
