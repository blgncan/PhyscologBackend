package com.physcolog.helper;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ImageResizer {

    public static File resizeImage(InputStream inputStream, String outputPath, int width, int height) throws IOException {
        File outputFile = new File(outputPath);

        // Resmi yeniden boyutlandırma
        Thumbnails.of(inputStream)
                .forceSize(width, height) // En-boy oranını zorlar
                .toFile(outputFile);
        return outputFile;
    }
}
