package ZipAndUnzip;

import java.io.File;
import java.io.FileInputStream;
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
                //todo: implement it
            }

        } catch (Exception e) {
            e.printStackTrace();
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
