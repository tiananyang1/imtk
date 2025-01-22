package com.microsoft.codepush.react;

import android.content.Context;
import android.util.Base64;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;
import com.xiaomi.mipush.sdk.Constants;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes08-dex2jar.jar:com/microsoft/codepush/react/CodePushUpdateUtils.class */
public class CodePushUpdateUtils {
    public static final String NEW_LINE = System.getProperty("line.separator");

    private static void addContentsOfFolderToManifest(String str, String str2, ArrayList<String> arrayList) {
        String str3;
        File[] listFiles = new File(str).listFiles();
        int length = listFiles.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return;
            }
            File file = listFiles[i2];
            String name = file.getName();
            String absolutePath = file.getAbsolutePath();
            StringBuilder sb = new StringBuilder();
            if (str2.isEmpty()) {
                str3 = "";
            } else {
                str3 = str2 + "/";
            }
            sb.append(str3);
            sb.append(name);
            String sb2 = sb.toString();
            if (!isHashIgnored(sb2)) {
                if (file.isDirectory()) {
                    addContentsOfFolderToManifest(absolutePath, sb2, arrayList);
                } else {
                    try {
                        arrayList.add(sb2 + Constants.COLON_SEPARATOR + computeHash(new FileInputStream(file)));
                    } catch (FileNotFoundException e) {
                        throw new CodePushUnknownException("Unable to compute hash of update contents.", e);
                    }
                }
            }
            i = i2 + 1;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x003e, code lost:            r9 = move-exception;     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003f, code lost:            r9.printStackTrace();     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String computeHash(java.io.InputStream r9) {
        /*
            r0 = 0
            r13 = r0
            r0 = 0
            r12 = r0
            r0 = r12
            r11 = r0
            java.lang.String r0 = "SHA-256"
            java.security.MessageDigest r0 = java.security.MessageDigest.getInstance(r0)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L7b java.security.NoSuchAlgorithmException -> L82
            r14 = r0
            r0 = r12
            r11 = r0
            java.security.DigestInputStream r0 = new java.security.DigestInputStream     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L7b java.security.NoSuchAlgorithmException -> L82
            r1 = r0
            r2 = r9
            r3 = r14
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L7b java.security.NoSuchAlgorithmException -> L82
            r12 = r0
            r0 = 8192(0x2000, float:1.148E-41)
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L61 java.security.NoSuchAlgorithmException -> L65
            r11 = r0
        L21:
            r0 = r12
            r1 = r11
            int r0 = r0.read(r1)     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L61 java.security.NoSuchAlgorithmException -> L65
            r10 = r0
            r0 = r10
            r1 = -1
            if (r0 == r1) goto L2f
            goto L21
        L2f:
            r0 = r12
            r0.close()     // Catch: java.io.IOException -> L3e
            r0 = r9
            if (r0 == 0) goto L43
            r0 = r9
            r0.close()     // Catch: java.io.IOException -> L3e
            goto L43
        L3e:
            r9 = move-exception
            r0 = r9
            r0.printStackTrace()
        L43:
            java.lang.String r0 = "%064x"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = r1
            r3 = 0
            java.math.BigInteger r4 = new java.math.BigInteger
            r5 = r4
            r6 = 1
            r7 = r14
            byte[] r7 = r7.digest()
            r5.<init>(r6, r7)
            r2[r3] = r4
            java.lang.String r0 = java.lang.String.format(r0, r1)
            return r0
        L5d:
            r11 = move-exception
            goto L91
        L61:
            r11 = move-exception
            goto L66
        L65:
            r11 = move-exception
        L66:
            r0 = r11
            r13 = r0
            r0 = r12
            r11 = r0
            r0 = r13
            r12 = r0
            goto L86
        L71:
            r13 = move-exception
            r0 = r11
            r12 = r0
            r0 = r13
            r11 = r0
            goto L91
        L7b:
            r12 = move-exception
            r0 = r13
            r11 = r0
            goto L86
        L82:
            r12 = move-exception
            r0 = r13
            r11 = r0
        L86:
            com.microsoft.codepush.react.CodePushUnknownException r0 = new com.microsoft.codepush.react.CodePushUnknownException     // Catch: java.lang.Throwable -> L71
            r1 = r0
            java.lang.String r2 = "Unable to compute hash of update contents."
            r3 = r12
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L71
            throw r0     // Catch: java.lang.Throwable -> L71
        L91:
            r0 = r12
            if (r0 == 0) goto L9c
            r0 = r12
            r0.close()     // Catch: java.io.IOException -> Lad
            goto L9c
        L9c:
            r0 = r9
            if (r0 == 0) goto Lab
            r0 = r9
            r0.close()     // Catch: java.io.IOException -> Lad
            goto Lab
        La7:
            r0 = r9
            r0.printStackTrace()
        Lab:
            r0 = r11
            throw r0
        Lad:
            r9 = move-exception
            goto La7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.codepush.react.CodePushUpdateUtils.computeHash(java.io.InputStream):java.lang.String");
    }

    public static void copyNecessaryFilesFromCurrentPackage(String str, String str2, String str3) throws IOException {
        FileUtils.copyDirectoryContents(str2, str3);
        try {
            JSONArray jSONArray = CodePushUtils.getJsonObjectFromFile(str).getJSONArray("deletedFiles");
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= jSONArray.length()) {
                    return;
                }
                File file = new File(str3, jSONArray.getString(i2));
                if (file.exists()) {
                    file.delete();
                }
                i = i2 + 1;
            }
        } catch (JSONException e) {
            throw new CodePushUnknownException("Unable to copy files from current package during diff update", e);
        }
    }

    public static String findJSBundleInUpdateContents(String str, String str2) {
        File[] listFiles = new File(str).listFiles();
        int length = listFiles.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return null;
            }
            File file = listFiles[i2];
            String appendPathComponent = CodePushUtils.appendPathComponent(str, file.getName());
            if (file.isDirectory()) {
                String findJSBundleInUpdateContents = findJSBundleInUpdateContents(appendPathComponent, str2);
                if (findJSBundleInUpdateContents != null) {
                    return CodePushUtils.appendPathComponent(file.getName(), findJSBundleInUpdateContents);
                }
            } else {
                String name = file.getName();
                if (name.equals(str2)) {
                    return name;
                }
            }
            i = i2 + 1;
        }
    }

    public static String getHashForBinaryContents(Context context, boolean z) {
        try {
            return CodePushUtils.getStringFromInputStream(context.getAssets().open(CodePushConstants.CODE_PUSH_HASH_FILE_NAME));
        } catch (IOException e) {
            try {
                return CodePushUtils.getStringFromInputStream(context.getAssets().open(CodePushConstants.CODE_PUSH_OLD_HASH_FILE_NAME));
            } catch (IOException e2) {
                if (z) {
                    return null;
                }
                CodePushUtils.log("Unable to get the hash of the binary's bundled resources - \"codepush.gradle\" may have not been added to the build definition.");
                return null;
            }
        }
    }

    public static String getSignature(String str) {
        try {
            return FileUtils.readFileToString(getSignatureFilePath(str));
        } catch (IOException e) {
            CodePushUtils.log(e.getMessage());
            CodePushUtils.log(e.getStackTrace().toString());
            return null;
        }
    }

    public static String getSignatureFilePath(String str) {
        return CodePushUtils.appendPathComponent(CodePushUtils.appendPathComponent(str, "CodePush"), CodePushConstants.BUNDLE_JWT_FILE);
    }

    public static boolean isHashIgnored(String str) {
        return str.startsWith("__MACOSX/") || str.equals(".DS_Store") || str.endsWith("/.DS_Store") || str.equals(CodePushConstants.BUNDLE_JWT_FILE) || str.endsWith("/.codepushrelease");
    }

    public static PublicKey parsePublicKey(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str.replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "").replace(NEW_LINE, "").getBytes(), 0)));
        } catch (Exception e) {
            CodePushUtils.log(e.getMessage());
            CodePushUtils.log(e.getStackTrace().toString());
            return null;
        }
    }

    public static Map<String, Object> verifyAndDecodeJWT(String str, PublicKey publicKey) {
        try {
            SignedJWT m3709parse = SignedJWT.m3709parse(str);
            if (!m3709parse.verify(new RSASSAVerifier((RSAPublicKey) publicKey))) {
                return null;
            }
            Map<String, Object> claims = m3709parse.getJWTClaimsSet().getClaims();
            CodePushUtils.log("JWT verification succeeded, payload content: " + claims.toString());
            return claims;
        } catch (Exception e) {
            CodePushUtils.log(e.getMessage());
            CodePushUtils.log(e.getStackTrace().toString());
            return null;
        }
    }

    public static void verifyFolderHash(String str, String str2) {
        CodePushUtils.log("Verifying hash for folder path: " + str);
        ArrayList arrayList = new ArrayList();
        addContentsOfFolderToManifest(str, "", arrayList);
        Collections.sort(arrayList);
        JSONArray jSONArray = new JSONArray();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            jSONArray.put((String) it.next());
        }
        String replace = jSONArray.toString().replace("\\/", "/");
        CodePushUtils.log("Manifest string: " + replace);
        String computeHash = computeHash(new ByteArrayInputStream(replace.getBytes()));
        CodePushUtils.log("Expected hash: " + str2 + ", actual hash: " + computeHash);
        if (!str2.equals(computeHash)) {
            throw new CodePushInvalidUpdateException("The update contents failed the data integrity check.");
        }
        CodePushUtils.log("The update contents succeeded the data integrity check.");
    }

    public static void verifyUpdateSignature(String str, String str2, String str3) throws CodePushInvalidUpdateException {
        CodePushUtils.log("Verifying signature for folder path: " + str);
        PublicKey parsePublicKey = parsePublicKey(str3);
        if (parsePublicKey == null) {
            throw new CodePushInvalidUpdateException("The update could not be verified because no public key was found.");
        }
        String signature = getSignature(str);
        if (signature == null) {
            throw new CodePushInvalidUpdateException("The update could not be verified because no signature was found.");
        }
        Map<String, Object> verifyAndDecodeJWT = verifyAndDecodeJWT(signature, parsePublicKey);
        if (verifyAndDecodeJWT == null) {
            throw new CodePushInvalidUpdateException("The update could not be verified because it was not signed by a trusted party.");
        }
        String str4 = (String) verifyAndDecodeJWT.get("contentHash");
        if (str4 == null) {
            throw new CodePushInvalidUpdateException("The update could not be verified because the signature did not specify a content hash.");
        }
        if (!str4.equals(str2)) {
            throw new CodePushInvalidUpdateException("The update contents failed the code signing check.");
        }
        CodePushUtils.log("The update contents succeeded the code signing check.");
    }
}
