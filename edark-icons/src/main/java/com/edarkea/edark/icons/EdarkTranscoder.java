package com.edarkea.edark.icons;

import java.awt.image.BufferedImage;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;

/**
 *
 * @author Steeven Ayui
 */
public class EdarkTranscoder extends ImageTranscoder {

    private BufferedImage image = null;

    @Override
    public BufferedImage createImage(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        return image;
    }

    @Override
    public void writeImage(BufferedImage img, TranscoderOutput out) {
    }

    public BufferedImage getImage() {
        return image;
    }

}
