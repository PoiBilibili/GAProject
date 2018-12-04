package edu.neu.info6205.ga;

import java.awt.*;

public class ShowImage extends Frame {



    String filename;

    public ShowImage (String filename) {

        this.setSize(420,350);

        this.setVisible(true);

        this.filename = filename;

    }



    public void paint (Graphics g) {

        Image image = this.getToolkit().getImage(filename);

        g.drawImage(image, 0, 0, this);

    }


}
