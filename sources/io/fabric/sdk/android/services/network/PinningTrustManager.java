package io.fabric.sdk.android.services.network;

import io.fabric.sdk.android.Fabric;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/network/PinningTrustManager.class */
class PinningTrustManager implements X509TrustManager {
    private static final X509Certificate[] NO_ISSUERS = new X509Certificate[0];
    private static final long PIN_FRESHNESS_DURATION_MILLIS = 15552000000L;
    private final long pinCreationTimeMillis;
    private final SystemKeyStore systemKeyStore;
    private final TrustManager[] systemTrustManagers;
    private final List<byte[]> pins = new LinkedList();
    private final Set<X509Certificate> cache = Collections.synchronizedSet(new HashSet());

    public PinningTrustManager(SystemKeyStore systemKeyStore, PinningInfoProvider pinningInfoProvider) {
        this.systemTrustManagers = initializeSystemTrustManagers(systemKeyStore);
        this.systemKeyStore = systemKeyStore;
        this.pinCreationTimeMillis = pinningInfoProvider.getPinCreationTimeInMillis();
        String[] pins = pinningInfoProvider.getPins();
        int length = pins.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return;
            }
            this.pins.add(hexStringToByteArray(pins[i2]));
            i = i2 + 1;
        }
    }

    private void checkPinTrust(X509Certificate[] x509CertificateArr) throws CertificateException {
        if (this.pinCreationTimeMillis != -1 && System.currentTimeMillis() - this.pinCreationTimeMillis > PIN_FRESHNESS_DURATION_MILLIS) {
            Fabric.getLogger().mo2878w(Fabric.TAG, "Certificate pins are stale, (" + (System.currentTimeMillis() - this.pinCreationTimeMillis) + " millis vs " + PIN_FRESHNESS_DURATION_MILLIS + " millis) falling back to system trust.");
            return;
        }
        X509Certificate[] cleanChain = CertificateChainCleaner.getCleanChain(x509CertificateArr, this.systemKeyStore);
        int length = cleanChain.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                throw new CertificateException("No valid pins found in chain!");
            }
            if (isValidPin(cleanChain[i2])) {
                return;
            } else {
                i = i2 + 1;
            }
        }
    }

    private void checkSystemTrust(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        TrustManager[] trustManagerArr = this.systemTrustManagers;
        int length = trustManagerArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return;
            }
            ((X509TrustManager) trustManagerArr[i2]).checkServerTrusted(x509CertificateArr, str);
            i = i2 + 1;
        }
    }

    private byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return bArr;
            }
            bArr[i2 / 2] = (byte) ((Character.digit(str.charAt(i2), 16) << 4) + Character.digit(str.charAt(i2 + 1), 16));
            i = i2 + 2;
        }
    }

    private TrustManager[] initializeSystemTrustManagers(SystemKeyStore systemKeyStore) {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
            trustManagerFactory.init(systemKeyStore.trustStore);
            return trustManagerFactory.getTrustManagers();
        } catch (KeyStoreException e) {
            throw new AssertionError(e);
        } catch (NoSuchAlgorithmException e2) {
            throw new AssertionError(e2);
        }
    }

    private boolean isValidPin(X509Certificate x509Certificate) throws CertificateException {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(x509Certificate.getPublicKey().getEncoded());
            Iterator<byte[]> it = this.pins.iterator();
            while (it.hasNext()) {
                if (Arrays.equals(it.next(), digest)) {
                    return true;
                }
            }
            return false;
        } catch (NoSuchAlgorithmException e) {
            throw new CertificateException(e);
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (this.cache.contains(x509CertificateArr[0])) {
            return;
        }
        checkSystemTrust(x509CertificateArr, str);
        checkPinTrust(x509CertificateArr);
        this.cache.add(x509CertificateArr[0]);
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        return NO_ISSUERS;
    }
}
