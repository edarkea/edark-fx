package com.edarkea.edark.icons;

import java.awt.image.BufferedImage;
import java.net.URL;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.util.SVGConstants;

/**
 *
 * @author Steeven Ayui
 */
public class IconLoader {

    public static Image getImageIcon(IconSize size, EdarkIcon icon) throws Exception {
        try {
            URL svgURL = IconLoader.class.getResource("/svg/" + icon.getText());
            EdarkTranscoder transcoder = new EdarkTranscoder();
            TranscodingHints hints = new TranscodingHints();
            hints.put(ImageTranscoder.KEY_WIDTH, size.getSize());
            hints.put(ImageTranscoder.KEY_HEIGHT, size.getSize());
            hints.put(ImageTranscoder.KEY_DOM_IMPLEMENTATION, SVGDOMImplementation.getDOMImplementation());
            hints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT_NAMESPACE_URI, SVGConstants.SVG_NAMESPACE_URI);
            hints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT, SVGConstants.SVG_SVG_TAG);
            hints.put(ImageTranscoder.KEY_XML_PARSER_VALIDATING, false);
            transcoder.setTranscodingHints(hints);
            TranscoderInput input = new TranscoderInput(svgURL.toExternalForm());
            transcoder.transcode(input, null);
            BufferedImage bufferedImage = transcoder.getImage();
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            return image;
        } catch (TranscoderException e) {
            throw new Exception(e.getMessage());
        }
    }
}
