package com.squareup.okhttp.internal;

import android.util.Log;
import com.squareup.okhttp.Protocol;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.net.ssl.SSLSocket;
import okio.Buffer;

/* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/internal/Platform.class */
public class Platform {
    private static final Platform PLATFORM = findPlatform();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/internal/Platform$Android.class */
    public static class Android extends Platform {
        private static final int MAX_LOG_LENGTH = 4000;
        private final OptionalMethod<Socket> getAlpnSelectedProtocol;
        private final OptionalMethod<Socket> setAlpnProtocols;
        private final OptionalMethod<Socket> setHostname;
        private final OptionalMethod<Socket> setUseSessionTickets;
        private final Method trafficStatsTagSocket;
        private final Method trafficStatsUntagSocket;

        public Android(OptionalMethod<Socket> optionalMethod, OptionalMethod<Socket> optionalMethod2, Method method, Method method2, OptionalMethod<Socket> optionalMethod3, OptionalMethod<Socket> optionalMethod4) {
            this.setUseSessionTickets = optionalMethod;
            this.setHostname = optionalMethod2;
            this.trafficStatsTagSocket = method;
            this.trafficStatsUntagSocket = method2;
            this.getAlpnSelectedProtocol = optionalMethod3;
            this.setAlpnProtocols = optionalMethod4;
        }

        @Override // com.squareup.okhttp.internal.Platform
        public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
            if (str != null) {
                this.setUseSessionTickets.invokeOptionalWithoutCheckedException(sSLSocket, true);
                this.setHostname.invokeOptionalWithoutCheckedException(sSLSocket, str);
            }
            OptionalMethod<Socket> optionalMethod = this.setAlpnProtocols;
            if (optionalMethod == null || !optionalMethod.isSupported(sSLSocket)) {
                return;
            }
            this.setAlpnProtocols.invokeWithoutCheckedException(sSLSocket, concatLengthPrefixed(list));
        }

        @Override // com.squareup.okhttp.internal.Platform
        public void connectSocket(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
            try {
                socket.connect(inetSocketAddress, i);
            } catch (AssertionError e) {
                if (!Util.isAndroidGetsocknameError(e)) {
                    throw e;
                }
                throw new IOException(e);
            } catch (SecurityException e2) {
                IOException iOException = new IOException("Exception in connect");
                iOException.initCause(e2);
                throw iOException;
            }
        }

        @Override // com.squareup.okhttp.internal.Platform
        public String getSelectedProtocol(SSLSocket sSLSocket) {
            OptionalMethod<Socket> optionalMethod = this.getAlpnSelectedProtocol;
            if (optionalMethod == null || !optionalMethod.isSupported(sSLSocket)) {
                return null;
            }
            byte[] bArr = (byte[]) this.getAlpnSelectedProtocol.invokeWithoutCheckedException(sSLSocket, new Object[0]);
            String str = null;
            if (bArr != null) {
                str = new String(bArr, Util.UTF_8);
            }
            return str;
        }

        @Override // com.squareup.okhttp.internal.Platform
        public void log(String str) {
            int min;
            int length = str.length();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    return;
                }
                int indexOf = str.indexOf(10, i2);
                if (indexOf == -1) {
                    indexOf = length;
                }
                while (true) {
                    min = Math.min(indexOf, i2 + MAX_LOG_LENGTH);
                    Log.d("OkHttp", str.substring(i2, min));
                    if (min >= indexOf) {
                        break;
                    } else {
                        i2 = min;
                    }
                }
                i = min + 1;
            }
        }

        @Override // com.squareup.okhttp.internal.Platform
        public void tagSocket(Socket socket) throws SocketException {
            Method method = this.trafficStatsTagSocket;
            if (method == null) {
                return;
            }
            try {
                method.invoke(null, socket);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }

        @Override // com.squareup.okhttp.internal.Platform
        public void untagSocket(Socket socket) throws SocketException {
            Method method = this.trafficStatsUntagSocket;
            if (method == null) {
                return;
            }
            try {
                method.invoke(null, socket);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/internal/Platform$JdkWithJettyBootPlatform.class */
    public static class JdkWithJettyBootPlatform extends Platform {
        private final Class<?> clientProviderClass;
        private final Method getMethod;
        private final Method putMethod;
        private final Method removeMethod;
        private final Class<?> serverProviderClass;

        public JdkWithJettyBootPlatform(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
            this.putMethod = method;
            this.getMethod = method2;
            this.removeMethod = method3;
            this.clientProviderClass = cls;
            this.serverProviderClass = cls2;
        }

        @Override // com.squareup.okhttp.internal.Platform
        public void afterHandshake(SSLSocket sSLSocket) {
            try {
                this.removeMethod.invoke(null, sSLSocket);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new AssertionError();
            }
        }

        @Override // com.squareup.okhttp.internal.Platform
        public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= size) {
                    try {
                        this.putMethod.invoke(null, sSLSocket, Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{this.clientProviderClass, this.serverProviderClass}, new JettyNegoProvider(arrayList)));
                        return;
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new AssertionError(e);
                    }
                }
                Protocol protocol = list.get(i2);
                if (protocol != Protocol.HTTP_1_0) {
                    arrayList.add(protocol.toString());
                }
                i = i2 + 1;
            }
        }

        @Override // com.squareup.okhttp.internal.Platform
        public String getSelectedProtocol(SSLSocket sSLSocket) {
            try {
                JettyNegoProvider jettyNegoProvider = (JettyNegoProvider) Proxy.getInvocationHandler(this.getMethod.invoke(null, sSLSocket));
                if (!jettyNegoProvider.unsupported && jettyNegoProvider.selected == null) {
                    Internal.logger.log(Level.INFO, "ALPN callback dropped: SPDY and HTTP/2 are disabled. Is alpn-boot on the boot class path?");
                    return null;
                }
                if (jettyNegoProvider.unsupported) {
                    return null;
                }
                return jettyNegoProvider.selected;
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new AssertionError();
            }
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/internal/Platform$JettyNegoProvider.class */
    private static class JettyNegoProvider implements InvocationHandler {
        private final List<String> protocols;
        private String selected;
        private boolean unsupported;

        public JettyNegoProvider(List<String> list) {
            this.protocols = list;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            Object[] objArr2 = objArr;
            if (objArr == null) {
                objArr2 = Util.EMPTY_STRING_ARRAY;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return true;
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.unsupported = true;
                return null;
            }
            if (name.equals("protocols") && objArr2.length == 0) {
                return this.protocols;
            }
            if ((!name.equals("selectProtocol") && !name.equals("select")) || String.class != returnType || objArr2.length != 1 || !(objArr2[0] instanceof List)) {
                if ((!name.equals("protocolSelected") && !name.equals("selected")) || objArr2.length != 1) {
                    return method.invoke(this, objArr2);
                }
                this.selected = (String) objArr2[0];
                return null;
            }
            List list = (List) objArr2[0];
            int size = list.size();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= size) {
                    String str = this.protocols.get(0);
                    this.selected = str;
                    return str;
                }
                if (this.protocols.contains(list.get(i2))) {
                    String str2 = (String) list.get(i2);
                    this.selected = str2;
                    return str2;
                }
                i = i2 + 1;
            }
        }
    }

    static byte[] concatLengthPrefixed(List<Protocol> list) {
        Buffer buffer = new Buffer();
        int size = list.size();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= size) {
                return buffer.readByteArray();
            }
            Protocol protocol = list.get(i2);
            if (protocol != Protocol.HTTP_1_0) {
                buffer.writeByte(protocol.toString().length());
                buffer.writeUtf8(protocol.toString());
            }
            i = i2 + 1;
        }
    }

    private static Platform findPlatform() {
        OptionalMethod optionalMethod;
        Method method;
        Method method2;
        try {
            try {
                Class.forName("com.android.org.conscrypt.OpenSSLSocketImpl");
            } catch (ClassNotFoundException e) {
                Class.forName("org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl");
            }
            OptionalMethod optionalMethod2 = null;
            OptionalMethod optionalMethod3 = new OptionalMethod(null, "setUseSessionTickets", Boolean.TYPE);
            OptionalMethod optionalMethod4 = new OptionalMethod(null, "setHostname", String.class);
            try {
                Class<?> cls = Class.forName("android.net.TrafficStats");
                method = cls.getMethod("tagSocket", Socket.class);
                try {
                    method2 = cls.getMethod("untagSocket", Socket.class);
                    try {
                        Class.forName("android.net.Network");
                        optionalMethod = new OptionalMethod(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
                        try {
                            optionalMethod2 = new OptionalMethod(null, "setAlpnProtocols", byte[].class);
                        } catch (ClassNotFoundException | NoSuchMethodException e2) {
                        }
                    } catch (ClassNotFoundException | NoSuchMethodException e3) {
                        optionalMethod = null;
                    }
                } catch (ClassNotFoundException | NoSuchMethodException e4) {
                    method2 = null;
                    optionalMethod = null;
                }
            } catch (ClassNotFoundException | NoSuchMethodException e5) {
                optionalMethod = null;
                method = null;
                method2 = null;
            }
            return new Android(optionalMethod3, optionalMethod4, method, method2, optionalMethod, optionalMethod2);
        } catch (ClassNotFoundException e6) {
            try {
                Class<?> cls2 = Class.forName("org.eclipse.jetty.alpn.ALPN");
                Class<?> cls3 = Class.forName("org.eclipse.jetty.alpn.ALPN$Provider");
                return new JdkWithJettyBootPlatform(cls2.getMethod("put", SSLSocket.class, cls3), cls2.getMethod("get", SSLSocket.class), cls2.getMethod("remove", SSLSocket.class), Class.forName("org.eclipse.jetty.alpn.ALPN$ClientProvider"), Class.forName("org.eclipse.jetty.alpn.ALPN$ServerProvider"));
            } catch (ClassNotFoundException | NoSuchMethodException e7) {
                return new Platform();
            }
        }
    }

    public static Platform get() {
        return PLATFORM;
    }

    public void afterHandshake(SSLSocket sSLSocket) {
    }

    public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
    }

    public void connectSocket(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }

    public String getPrefix() {
        return "OkHttp";
    }

    public String getSelectedProtocol(SSLSocket sSLSocket) {
        return null;
    }

    public void log(String str) {
        System.out.println(str);
    }

    public void logW(String str) {
        System.out.println(str);
    }

    public void tagSocket(Socket socket) throws SocketException {
    }

    public void untagSocket(Socket socket) throws SocketException {
    }
}
