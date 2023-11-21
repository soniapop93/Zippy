package ZipAndUnzip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip {
    public void unzipFile(String filePath, String destinationPath) {
        File destinationDirectory = new File(destinationPath);

        byte[] buffer = new byte[1024];
        ZipInputStream zipInputStream = null;
        try {
            zipInputStream = new ZipInputStream(new FileInputStream(filePath));
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null) {
                File newFile = newFile(destinationDirectory, zipEntry);

                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        System.out.println("ERROR -> Could not create directory");
                    }
                }
                else {
                    File parentFile = newFile.getParentFile();

                    if (!parentFile.isDirectory() && !parentFile.mkdirs()) {
                        System.out.println("ERROR 2 -> Could not create directory");
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                    int len = zipInputStream.read(buffer);
                    while (len > 0) {
                        fileOutputStream.write(buffer, 0, len);
                        len = zipInputStream.read(buffer);
                    }
                    fileOutputStream.close();

                }
                zipEntry = zipInputStream.getNextEntry();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unzipDirectory(String filePath, String destinationPath) {
        unzipFile(filePath, destinationPath);
    }
    private File newFile(File destinationDirectory, ZipEntry zipEntry) {
        File destinationFile = new File (destinationDirectory, zipEntry.getName());

        return destinationFile;
    }
}
