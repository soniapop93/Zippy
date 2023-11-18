package ZipAndUnzip;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void zipFile(String filePath) {
        File file = new File(filePath);
        FileOutputStream fileOutputStream = null;

        Pair pairFile = getFileName(filePath);

        String fileNameZip = pairFile.getItem1() + "\\" + pairFile.getItem2() + ".zip";

        try {
            fileOutputStream = new FileOutputStream(fileNameZip);
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

            FileInputStream fileInputStream = new FileInputStream(file);
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zipOutputStream.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fileInputStream.read(bytes)) >= 0) {
                zipOutputStream.write(bytes, 0, length);
            }

            zipOutputStream.close();
            fileInputStream.close();
            fileOutputStream.close();

            System.out.println("Zip file created successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void zipFiles(ArrayList<String> filePaths) {
        for (int i = 0; i < filePaths.size(); i++) {
            zipFile(filePaths.get(i));
        }
    }

    private Pair getFileName(String filePath) {
        Path path = Paths.get(filePath);
        String fileName = path.getFileName().toString().split(".", 1)[0];
        String pathOfFile = path.getParent().toString();

        return new Pair(pathOfFile, fileName);
    }

    private void createDirectory(String path) {
        File dir = new File(path);

        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
