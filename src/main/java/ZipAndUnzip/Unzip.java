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

                if (!zipEntry.isDirectory()) {

                    File parentFile = newFile.getParentFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                    int len;

                    while ((len = zipInputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, len);
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
        Path destinationDir = new Path(destinationPath);
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(filePath));

        ZipEntry zipEntry = zipInputStream.getNextEntry();

        if (zipEntry != null) {
            // todo: finish it
        }
    }
    private File newFile(File destinationDirectory, ZipEntry zipEntry) {
        File destinationFile = new File (destinationDirectory, zipEntry.getName());

        try {
            String destinationDirectoryPath = destinationDirectory.getCanonicalPath();
            String destinatioFilePath = destinationFile.getCanonicalPath();

            if (destinatioFilePath.startsWith(destinationDirectoryPath + File.separator)) {
                return destinationFile;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
