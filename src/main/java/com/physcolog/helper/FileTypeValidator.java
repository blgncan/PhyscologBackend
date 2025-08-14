package com.physcolog.helper;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class FileTypeValidator {

    // Geçerli resim formatlarını kontrol eder
    private static final String[] VALID_IMAGE_TYPES = {"image/jpeg","image/jpg", "image/png", "image/gif"};

    /**
     * Dosyanın resim formatında olup olmadığını kontrol eder.
     *
     * @param file MultipartFile nesnesi
     * @return true (resim formatında) veya false
     * @throws IOException Dosya okunamazsa
     */
    public static boolean isImageFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return false;
        }

        // MIME türünü kontrol et
        String mimeType = file.getContentType();
        for (String validType : VALID_IMAGE_TYPES) {
            if (validType.equalsIgnoreCase(mimeType)) {
                return true;
            }
        }

        // Alternatif olarak, resim dosyası olup olmadığını doğrula
        try (var inputStream = file.getInputStream()) {
            BufferedImage image = ImageIO.read(inputStream);
            return image != null; // Resim okunabildiyse geçerli bir resimdir
        } catch (IOException e) {
            return false;
        }
    }
}
