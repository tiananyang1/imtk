package com.xiaomi.push;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Set;

/* renamed from: com.xiaomi.push.eg */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/eg.class */
public class C0599eg extends AbstractC0601ei {
    public C0599eg(Context context, int i) {
        super(context, i);
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 6;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public EnumC0699hz mo974a() {
        return EnumC0699hz.Bluetooth;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public String mo975a() {
        StringBuilder sb = new StringBuilder();
        try {
            Set<BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
            if (!C0488ad.m430a(bondedDevices)) {
                int i = 0;
                for (BluetoothDevice bluetoothDevice : bondedDevices) {
                    if (i > 10) {
                        break;
                    }
                    if (sb.length() > 0) {
                        sb.append(";");
                    }
                    sb.append(bluetoothDevice.getName());
                    sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    sb.append(bluetoothDevice.getAddress());
                    sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    if (Build.VERSION.SDK_INT >= 18) {
                        sb.append(bluetoothDevice.getType());
                    }
                    i++;
                }
            }
        } catch (Throwable th) {
        }
        return sb.toString();
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    protected boolean mo976a() {
        return this.f733a.getPackageManager().checkPermission("android.permission.BLUETOOTH", this.f733a.getPackageName()) == 0;
    }
}
