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

        Pair pairFile = getFileName(filePath);

        String fileNameZip = pairFile.getItem1() + "\\" + pairFile.getItem2() + ".zip";

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileNameZip);
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

            FileInputStream fileInputStream = new FileInputStream(file);
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zipOutputStream.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];

            int len = fileInputStream.read(bytes);

            while (len >= 0) {
                zipOutputStream.write(bytes, 0, len);
                len = fileInputStream.read(bytes);
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

    public void zipDirectory(String directoryPath) {
        Path pathDir = Paths.get(directoryPath);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream((pathDir.getParent() + "\\" + pathDir.getFileName()).toString() + ".zip");
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
            File fileZip = new File(directoryPath);
            zipDirFile(fileZip, fileZip.getName(), zipOutputStream);
            zipOutputStream.close();
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Pair getFileName(String filePath) {
        Path path = Paths.get(filePath);
        String fileName = path.getFileName().toString().split(".", 1)[0];
        String pathOfFile = path.getParent().toString();

        return new Pair(pathOfFile, fileName);
    }

    private void zipDirFile(File fileToZip, String fileName, ZipOutputStream zipOutputStream) {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                try {
                    zipOutputStream.putNextEntry(new ZipEntry(fileName));
                    zipOutputStream.closeEntry();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    zipOutputStream.putNextEntry(new ZipEntry(fileName + "/"));
                    zipOutputStream.closeEntry();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            File[] files = fileToZip.listFiles();

            for (File file : files) {
                zipDirFile(file, fileName + "\\" + file.getName(), zipOutputStream);
            }
            return;
        }

        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(fileToZip);

            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOutputStream.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];

            int len = fileInputStream.read(bytes);
            while (len >= 0) {
                zipOutputStream.write(bytes, 0, len);
                len = fileInputStream.read(bytes);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
