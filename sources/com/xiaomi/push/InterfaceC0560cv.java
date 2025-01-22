package com.xiaomi.push;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.List;

/* renamed from: com.xiaomi.push.cv */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/cv.class */
public interface InterfaceC0560cv extends IInterface {

    /* renamed from: com.xiaomi.push.cv$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/cv$a.class */
    public static abstract class a extends Binder implements InterfaceC0560cv {

        /* renamed from: com.xiaomi.push.cv$a$a, reason: collision with other inner class name */
        /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/cv$a$a.class */
        private static class C0952a implements InterfaceC0560cv {

            /* renamed from: a */
            private IBinder f625a;

            C0952a(IBinder iBinder) {
                this.f625a = iBinder;
            }

            @Override // com.xiaomi.push.InterfaceC0560cv
            /* renamed from: a */
            public int mo815a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaomi.metoknlp.geofencing.IGeoFencing");
                    this.f625a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaomi.push.InterfaceC0560cv
            /* renamed from: a */
            public String mo816a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaomi.metoknlp.geofencing.IGeoFencing");
                    this.f625a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaomi.push.InterfaceC0560cv
            /* renamed from: a */
            public List<String> mo817a(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaomi.metoknlp.geofencing.IGeoFencing");
                    obtain.writeString(str);
                    this.f625a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaomi.push.InterfaceC0560cv
            /* renamed from: a */
            public void mo818a(double d, double d2, float f, long j, PendingIntent pendingIntent, String str, String str2) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaomi.metoknlp.geofencing.IGeoFencing");
                    obtain.writeDouble(d);
                    obtain.writeDouble(d2);
                    obtain.writeFloat(f);
                    obtain.writeLong(j);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.f625a.transact(7, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaomi.push.InterfaceC0560cv
            /* renamed from: a */
            public void mo819a(double d, double d2, float f, long j, String str, String str2, String str3) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaomi.metoknlp.geofencing.IGeoFencing");
                    obtain.writeDouble(d);
                    obtain.writeDouble(d2);
                    obtain.writeFloat(f);
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.f625a.transact(6, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaomi.push.InterfaceC0560cv
            /* renamed from: a */
            public void mo820a(PendingIntent pendingIntent) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaomi.metoknlp.geofencing.IGeoFencing");
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f625a.transact(9, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaomi.push.InterfaceC0560cv
            /* renamed from: a */
            public void mo821a(String str) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaomi.metoknlp.geofencing.IGeoFencing");
                    obtain.writeString(str);
                    this.f625a.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaomi.push.InterfaceC0560cv
            /* renamed from: a */
            public void mo822a(String str, String str2) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaomi.metoknlp.geofencing.IGeoFencing");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.f625a.transact(8, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.xiaomi.push.InterfaceC0560cv
            /* renamed from: a */
            public void mo823a(List<String> list, String str) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaomi.metoknlp.geofencing.IGeoFencing");
                    obtain.writeStringList(list);
                    obtain.writeString(str);
                    this.f625a.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f625a;
            }
        }

        /* renamed from: a */
        public static InterfaceC0560cv m824a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.xiaomi.metoknlp.geofencing.IGeoFencing");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof InterfaceC0560cv)) ? new C0952a(iBinder) : (InterfaceC0560cv) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1598968902) {
                parcel2.writeString("com.xiaomi.metoknlp.geofencing.IGeoFencing");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.xiaomi.metoknlp.geofencing.IGeoFencing");
                    mo823a(parcel.createStringArrayList(), parcel.readString());
                    return true;
                case 2:
                    parcel.enforceInterface("com.xiaomi.metoknlp.geofencing.IGeoFencing");
                    mo817a(parcel.readString());
                    return true;
                case 3:
                    parcel.enforceInterface("com.xiaomi.metoknlp.geofencing.IGeoFencing");
                    String a = mo815a();
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case 4:
                    parcel.enforceInterface("com.xiaomi.metoknlp.geofencing.IGeoFencing");
                    List<String> a2 = mo817a(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStringList(a2);
                    return true;
                case 5:
                    parcel.enforceInterface("com.xiaomi.metoknlp.geofencing.IGeoFencing");
                    int a3 = mo815a();
                    parcel2.writeNoException();
                    parcel2.writeInt(a3);
                    return true;
                case 6:
                    parcel.enforceInterface("com.xiaomi.metoknlp.geofencing.IGeoFencing");
                    mo819a(parcel.readDouble(), parcel.readDouble(), parcel.readFloat(), parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readString());
                    return true;
                case 7:
                    parcel.enforceInterface("com.xiaomi.metoknlp.geofencing.IGeoFencing");
                    mo818a(parcel.readDouble(), parcel.readDouble(), parcel.readFloat(), parcel.readLong(), parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString());
                    return true;
                case 8:
                    parcel.enforceInterface("com.xiaomi.metoknlp.geofencing.IGeoFencing");
                    mo822a(parcel.readString(), parcel.readString());
                    return true;
                case 9:
                    parcel.enforceInterface("com.xiaomi.metoknlp.geofencing.IGeoFencing");
                    PendingIntent pendingIntent = null;
                    if (parcel.readInt() != 0) {
                        pendingIntent = (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    mo820a(pendingIntent);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    /* renamed from: a */
    int mo815a();

    /* renamed from: a */
    String mo816a();

    /* renamed from: a */
    List<String> mo817a(String str);

    /* renamed from: a */
    void mo818a(double d, double d2, float f, long j, PendingIntent pendingIntent, String str, String str2);

    /* renamed from: a */
    void mo819a(double d, double d2, float f, long j, String str, String str2, String str3);

    /* renamed from: a */
    void mo820a(PendingIntent pendingIntent);

    /* renamed from: a */
    void mo821a(String str);

    /* renamed from: a */
    void mo822a(String str, String str2);

    /* renamed from: a */
    void mo823a(List<String> list, String str);
}
