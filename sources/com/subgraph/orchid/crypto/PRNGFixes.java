package com.subgraph.orchid.crypto;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.SecureRandomSpi;
import java.security.Security;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/PRNGFixes.class */
public final class PRNGFixes {
    private static final int VERSION_CODE_JELLY_BEAN = 16;
    private static final int VERSION_CODE_JELLY_BEAN_MR2 = 18;
    private static final Logger logger = Logger.getLogger(PRNGFixes.class.getName());
    private static final byte[] BUILD_FINGERPRINT_AND_DEVICE_SERIAL = getBuildFingerprintAndDeviceSerial();

    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/PRNGFixes$LinuxPRNGSecureRandom.class */
    public static class LinuxPRNGSecureRandom extends SecureRandomSpi {
        private static final File URANDOM_FILE = new File("/dev/urandom");
        private static final Object sLock = new Object();
        private static DataInputStream sUrandomIn;
        private static OutputStream sUrandomOut;
        private static final long serialVersionUID = 1;
        private boolean mSeeded;

        private DataInputStream getUrandomInputStream() {
            DataInputStream dataInputStream;
            synchronized (sLock) {
                if (sUrandomIn == null) {
                    try {
                        sUrandomIn = new DataInputStream(new FileInputStream(URANDOM_FILE));
                    } catch (IOException e) {
                        throw new SecurityException("Failed to open " + URANDOM_FILE + " for reading", e);
                    }
                }
                dataInputStream = sUrandomIn;
            }
            return dataInputStream;
        }

        private OutputStream getUrandomOutputStream() throws IOException {
            OutputStream outputStream;
            synchronized (sLock) {
                if (sUrandomOut == null) {
                    sUrandomOut = new FileOutputStream(URANDOM_FILE);
                }
                outputStream = sUrandomOut;
            }
            return outputStream;
        }

        @Override // java.security.SecureRandomSpi
        protected byte[] engineGenerateSeed(int i) {
            byte[] bArr = new byte[i];
            engineNextBytes(bArr);
            return bArr;
        }

        @Override // java.security.SecureRandomSpi
        protected void engineNextBytes(byte[] bArr) {
            DataInputStream urandomInputStream;
            if (!this.mSeeded) {
                engineSetSeed(PRNGFixes.access$100());
            }
            try {
                synchronized (sLock) {
                    urandomInputStream = getUrandomInputStream();
                }
                synchronized (urandomInputStream) {
                    urandomInputStream.readFully(bArr);
                }
            } catch (IOException e) {
                throw new SecurityException("Failed to read from " + URANDOM_FILE, e);
            }
        }

        @Override // java.security.SecureRandomSpi
        protected void engineSetSeed(byte[] bArr) {
            OutputStream urandomOutputStream;
            try {
                try {
                    synchronized (sLock) {
                        urandomOutputStream = getUrandomOutputStream();
                    }
                    urandomOutputStream.write(bArr);
                    urandomOutputStream.flush();
                } catch (IOException e) {
                    PRNGFixes.logger.warning("Failed to mix seed into " + URANDOM_FILE);
                }
            } finally {
                this.mSeeded = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/PRNGFixes$LinuxPRNGSecureRandomProvider.class */
    public static class LinuxPRNGSecureRandomProvider extends Provider {
        private static final long serialVersionUID = 1;

        public LinuxPRNGSecureRandomProvider() {
            super("LinuxPRNG", 1.0d, "A Linux-specific random number provider that uses /dev/urandom");
            put("SecureRandom.SHA1PRNG", LinuxPRNGSecureRandom.class.getName());
            put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
        }
    }

    private PRNGFixes() {
    }

    static /* synthetic */ byte[] access$100() {
        return generateSeed();
    }

    public static void apply() {
        applyOpenSSLFix();
        installLinuxPRNGSecureRandom();
    }

    private static void applyOpenSSLFix() throws SecurityException {
        int sdkVersion = getSdkVersion();
        if (sdkVersion < 16 || sdkVersion > 18) {
            return;
        }
        try {
            Class.forName("org.apache.harmony.xnet.provider.jsse.NativeCrypto").getMethod("RAND_seed", byte[].class).invoke(null, generateSeed());
            int intValue = ((Integer) Class.forName("org.apache.harmony.xnet.provider.jsse.NativeCrypto").getMethod("RAND_load_file", String.class, Long.TYPE).invoke(null, "/dev/urandom", 1024)).intValue();
            if (intValue == 1024) {
                return;
            }
            throw new IOException("Unexpected number of bytes read from Linux PRNG: " + intValue);
        } catch (Exception e) {
            throw new SecurityException("Failed to seed OpenSSL PRNG", e);
        }
    }

    private static byte[] generateSeed() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeLong(System.currentTimeMillis());
            dataOutputStream.writeLong(System.nanoTime());
            dataOutputStream.write(BUILD_FINGERPRINT_AND_DEVICE_SERIAL);
            dataOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new SecurityException("Failed to generate seed", e);
        }
    }

    private static String getBuildFingerprint() {
        try {
            return (String) Class.forName("android.os.Build").getField("FINGERPRINT").get(null);
        } catch (Exception e) {
            logger.warning("Could not get BUILD.FINGERPRINT value : " + e);
            return "";
        }
    }

    private static byte[] getBuildFingerprintAndDeviceSerial() {
        StringBuilder sb = new StringBuilder();
        String buildFingerprint = getBuildFingerprint();
        if (buildFingerprint != null) {
            sb.append(buildFingerprint);
        }
        String deviceSerialNumber = getDeviceSerialNumber();
        if (deviceSerialNumber != null) {
            sb.append(deviceSerialNumber);
        }
        try {
            return sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 encoding not supported");
        }
    }

    private static String getDeviceSerialNumber() {
        try {
            return (String) Class.forName("android.os.Build").getField("SERIAL").get(null);
        } catch (Exception e) {
            return null;
        }
    }

    private static int getSdkVersion() {
        try {
            return Class.forName("android.os.Build").getField("VERSION").getClass().getField("SDK_INT").getInt(null);
        } catch (Exception e) {
            logger.warning("Could not get Build.VERSION.SDK_INT value : " + e);
            return 0;
        }
    }

    private static void installLinuxPRNGSecureRandom() throws SecurityException {
        if (getSdkVersion() > 18) {
            return;
        }
        Provider[] providers = Security.getProviders("SecureRandom.SHA1PRNG");
        if (providers == null || providers.length < 1 || !LinuxPRNGSecureRandomProvider.class.equals(providers[0].getClass())) {
            Security.insertProviderAt(new LinuxPRNGSecureRandomProvider(), 1);
        }
        SecureRandom secureRandom = new SecureRandom();
        if (!LinuxPRNGSecureRandomProvider.class.equals(secureRandom.getProvider().getClass())) {
            throw new SecurityException("new SecureRandom() backed by wrong Provider: " + secureRandom.getProvider().getClass());
        }
        try {
            SecureRandom secureRandom2 = SecureRandom.getInstance("SHA1PRNG");
            if (LinuxPRNGSecureRandomProvider.class.equals(secureRandom2.getProvider().getClass())) {
                return;
            }
            throw new SecurityException("SecureRandom.getInstance(\"SHA1PRNG\") backed by wrong Provider: " + secureRandom2.getProvider().getClass());
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException("SHA1PRNG not available", e);
        }
    }
}
