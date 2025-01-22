package im.imkey.imkeylibrary.device.key;

import android.content.Context;
import im.imkey.imkeylibrary.bluetooth.Ble;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.exception.ImkeyException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/key/KeyFileManager.class */
public class KeyFileManager {
    private static String fileName = "keys";

    public static String getKeysFromLocalFile(String str) {
        Context context = Ble.getInstance().getContext();
        if (context == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_BLE_NOT_INITIALIZE);
        }
        File file = new File(context.getFilesDir(), fileName + str.substring(str.length() - 8));
        FileInputStream fileInputStream = null;
        try {
            if (!file.exists()) {
                return null;
            }
            try {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[fileInputStream2.available()];
                    fileInputStream2.read(bArr);
                    String str2 = new String(bArr);
                    try {
                        fileInputStream2.close();
                        return str2;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return str2;
                    }
                } catch (IOException e2) {
                    throw new ImkeyException(Messages.IMKEY_KEYFILE_IO_ERROR);
                } catch (Throwable th) {
                    fileInputStream = fileInputStream2;
                    th = th;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void saveKeysToLocalFile(String str, String str2) {
        Context context = Ble.getInstance().getContext();
        if (context == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_BLE_NOT_INITIALIZE);
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                File filesDir = context.getFilesDir();
                StringBuilder sb = new StringBuilder();
                sb.append(fileName);
                sb.append(str2.substring(str2.length() - 8));
                File file = new File(filesDir, sb.toString());
                if (!file.exists() && !file.createNewFile()) {
                    throw new ImkeyException(Messages.IMKEY_KEYFILE_CREATE_ERROR);
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    fileOutputStream2.write(str.getBytes());
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e2) {
                    throw new ImkeyException(Messages.IMKEY_KEYFILE_IO_ERROR);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
