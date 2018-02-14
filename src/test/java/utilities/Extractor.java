package utilities;

import org.apache.commons.io.FileUtils;
import org.rauschig.jarchivelib.ArchiveFormat;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;
import org.rauschig.jarchivelib.CompressionType;
import java.io.*;
import java.util.Enumeration;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Extractor {

    public File extract(File compressedFile, String export) throws IOException {
        File file = null;

      if (compressedFile.getName().toLowerCase().endsWith("tar.gz")) {
            file = unTarGz(compressedFile);
        } else if (compressedFile.getName().toLowerCase().endsWith("gz")) {
            file = unGzip(compressedFile);
        } else {
            file = unZip(compressedFile);
        }
        File result = file.getAbsoluteFile();
        result.setExecutable(true);
        return result;
    }

    public File unZip(File compressedFile) throws IOException {
        File file = null;
        try (ZipFile zipFolder = new ZipFile(compressedFile)) {
            Enumeration<?> enu = zipFolder.entries();

            while (enu.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) enu.nextElement();

                String name = zipEntry.getName();

                file = new File(
                        compressedFile.getParentFile() + File.separator + name);
                if (!file.exists()) {
                    if (name.endsWith("/")) {
                        file.mkdirs();
                        continue;
                    }

                    File parent = file.getParentFile();
                    if (parent != null) {
                        parent.mkdirs();
                    }

                    try (InputStream is = zipFolder.getInputStream(zipEntry)) {
                        File temporaryFile = new File(parent, UUID.randomUUID().toString());
                        FileUtils.copyInputStreamToFile(is, temporaryFile);
                        temporaryFile.renameTo(file);
                    }
                    file.setExecutable(true);
                } else {
                }

            }
        }

        return file;
    }

    public File unGzip(File archive) throws IOException {
        String fileName = archive.getName();
        int iDash = fileName.indexOf("-");
        if (iDash != -1) {
            fileName = fileName.substring(0, iDash);
        }
        int iDot = fileName.indexOf(".");
        if (iDot != -1) {
            fileName = fileName.substring(0, iDot);
        }
        File target = new File(
                archive.getParentFile() + File.separator + fileName);

        try (GZIPInputStream in = new GZIPInputStream(
                new FileInputStream(archive))) {
            try (FileOutputStream out = new FileOutputStream(target)) {
                for (int c = in.read(); c != -1; c = in.read()) {
                    out.write(c);
                }
            }
        }

        if (!target.getName().toLowerCase().contains(".exe")
                && target.exists()) {
            target.setExecutable(true);
        }

        return target;
    }

    public File unTarGz(File archive) throws IOException {
        Archiver archiver = ArchiverFactory.createArchiver(ArchiveFormat.TAR,
                CompressionType.GZIP);
        archiver.extract(archive, archive.getParentFile());

        return archive;
    }



}
