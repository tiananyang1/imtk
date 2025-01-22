package com.microsoft.codepush.react;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes08-dex2jar.jar:com/microsoft/codepush/react/FileUtils.class */
public class FileUtils {
    private static final int WRITE_BUFFER_SIZE = 8192;

    public static void copyDirectoryContents(String str, String str2) throws IOException {
        BufferedInputStream bufferedInputStream;
        FileInputStream fileInputStream;
        File file = new File(str);
        File file2 = new File(str2);
        if (!file2.exists()) {
            file2.mkdir();
        }
        File[] listFiles = file.listFiles();
        int length = listFiles.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return;
            }
            File file3 = listFiles[i2];
            if (file3.isDirectory()) {
                copyDirectoryContents(CodePushUtils.appendPathComponent(str, file3.getName()), CodePushUtils.appendPathComponent(str2, file3.getName()));
            } else {
                File file4 = new File(file2, file3.getName());
                byte[] bArr = new byte[WRITE_BUFFER_SIZE];
                FileOutputStream fileOutputStream = null;
                try {
                    fileInputStream = new FileInputStream(file3);
                    try {
                        BufferedInputStream bufferedInputStream2 = new BufferedInputStream(fileInputStream);
                        try {
                            FileOutputStream fileOutputStream2 = new FileOutputStream(file4);
                            while (true) {
                                try {
                                    int read = bufferedInputStream2.read(bArr);
                                    if (read <= 0) {
                                        try {
                                            break;
                                        } catch (IOException e) {
                                            throw new CodePushUnknownException("Error closing IO resources.", e);
                                        }
                                    }
                                    fileOutputStream2.write(bArr, 0, read);
                                } catch (Throwable th) {
                                    th = th;
                                    fileOutputStream = fileOutputStream2;
                                    bufferedInputStream = bufferedInputStream2;
                                    if (fileInputStream != null) {
                                        try {
                                            fileInputStream.close();
                                        } catch (IOException e2) {
                                            throw new CodePushUnknownException("Error closing IO resources.", e2);
                                        }
                                    }
                                    if (bufferedInputStream != null) {
                                        bufferedInputStream.close();
                                    }
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                    throw th;
                                }
                            }
                            fileInputStream.close();
                            bufferedInputStream2.close();
                            fileOutputStream2.close();
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedInputStream = bufferedInputStream2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedInputStream = null;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    bufferedInputStream = null;
                    fileInputStream = null;
                }
            }
            i = i2 + 1;
        }
    }

    public static void deleteDirectoryAtPath(String str) {
        if (str == null) {
            CodePushUtils.log("deleteDirectoryAtPath attempted with null directoryPath");
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            deleteFileOrFolderSilently(file);
        }
    }

    public static void deleteFileAtPathSilently(String str) {
        deleteFileOrFolderSilently(new File(str));
    }

    public static void deleteFileOrFolderSilently(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    break;
                }
                File file2 = listFiles[i2];
                if (file2.isDirectory()) {
                    deleteFileOrFolderSilently(file2);
                } else {
                    file2.delete();
                }
                i = i2 + 1;
            }
        }
        if (file.delete()) {
            return;
        }
        CodePushUtils.log("Error deleting file " + file.getName());
    }

    public static boolean fileAtPathExists(String str) {
        return new File(str).exists();
    }

    public static void moveFile(File file, String str, String str2) {
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        File file3 = new File(str, str2);
        if (file.renameTo(file3)) {
            return;
        }
        throw new CodePushUnknownException("Unable to move file from " + file.getAbsolutePath() + " to " + file3.getAbsolutePath() + ".");
    }

    public static String readFileToString(String str) throws IOException {
        BufferedReader bufferedReader;
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(new File(str));
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            } catch (Throwable th) {
                th = th;
                bufferedReader = null;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
            fileInputStream = null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    String sb2 = sb.toString();
                    bufferedReader.close();
                    fileInputStream.close();
                    return sb2;
                }
                sb.append(readLine);
                sb.append("\n");
            }
        } catch (Throwable th3) {
            th = th3;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }

    public static void unzipFile(File file, String str) throws IOException {
        BufferedInputStream bufferedInputStream;
        FileInputStream fileInputStream;
        ZipInputStream zipInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                try {
                    zipInputStream = new ZipInputStream(bufferedInputStream);
                } catch (Throwable th) {
                    th = th;
                    zipInputStream = null;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = null;
                zipInputStream = null;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
            fileInputStream = null;
            zipInputStream = null;
        }
        try {
            File file2 = new File(str);
            if (file2.exists()) {
                deleteFileOrFolderSilently(file2);
            }
            file2.mkdirs();
            byte[] bArr = new byte[WRITE_BUFFER_SIZE];
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    try {
                        zipInputStream.close();
                        bufferedInputStream.close();
                        fileInputStream.close();
                        return;
                    } catch (IOException e) {
                        throw new CodePushUnknownException("Error closing IO resources.", e);
                    }
                }
                File file3 = new File(file2, nextEntry.getName());
                if (nextEntry.isDirectory()) {
                    file3.mkdirs();
                } else {
                    File parentFile = file3.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file3);
                    while (true) {
                        try {
                            int read = zipInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            } else {
                                fileOutputStream.write(bArr, 0, read);
                            }
                        } finally {
                        }
                    }
                }
                long time = nextEntry.getTime();
                if (time > 0) {
                    file3.setLastModified(time);
                }
            }
        } catch (Throwable th4) {
            th = th4;
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (IOException e2) {
                    throw new CodePushUnknownException("Error closing IO resources.", e2);
                }
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }

    public static void writeStringToFile(String str, String str2) throws IOException {
        PrintWriter printWriter;
        PrintWriter printWriter2;
        try {
            printWriter2 = new PrintWriter(str2);
        } catch (Throwable th) {
            th = th;
            printWriter = null;
        }
        try {
            printWriter2.print(str);
            printWriter2.close();
        } catch (Throwable th2) {
            printWriter = printWriter2;
            th = th2;
            if (printWriter != null) {
                printWriter.close();
            }
            throw th;
        }
    }
}
