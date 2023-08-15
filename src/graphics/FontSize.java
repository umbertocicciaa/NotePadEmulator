package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FontSize implements ActionListener {

    private JFrame frame;
    private JTextField text;

    private Mediator mediator;

    private Integer fontsize;



    public FontSize() {
        createWidnow();
    }

    public Integer getFontsize() {
        return fontsize;
    }

    public void createWidnow() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setSize(200, 200);
        text = new JTextField();
        text.addActionListener(this);
        frame.add(text);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String testo = text.getText();
        try {
            fontsize = Integer.parseInt(testo);
            if (fontsize <= 0) throw new RuntimeException();
            mediator.setFont();
            frame.setVisible(false);
        } catch (RuntimeException r) {
            r.printStackTrace();
        }
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}
