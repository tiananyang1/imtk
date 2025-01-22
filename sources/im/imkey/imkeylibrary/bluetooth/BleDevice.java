package im.imkey.imkeylibrary.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.ftsafe.bluetooth.key.jkey.FTBluetoothDevice;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/bluetooth/BleDevice.class */
public class BleDevice implements Parcelable {
    public static final Parcelable.Creator<BleDevice> CREATOR = new Parcelable.Creator<BleDevice>() { // from class: im.imkey.imkeylibrary.bluetooth.BleDevice.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BleDevice createFromParcel(Parcel parcel) {
            return new BleDevice(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BleDevice[] newArray(int i) {
            return new BleDevice[i];
        }
    };
    private final BluetoothDevice bluetoothDevice;
    private final int devRssi;
    private final int devType;
    private final String radioDevName;
    private final String radioDevUUID;
    private final String radioManufacturerData;

    public BleDevice(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
        this.devType = 0;
        this.radioDevName = "";
        this.radioDevUUID = "";
        this.devRssi = 0;
        this.radioManufacturerData = "";
    }

    public BleDevice(BluetoothDevice bluetoothDevice, int i, String str, String str2, int i2, String str3) {
        this.bluetoothDevice = bluetoothDevice;
        this.devType = i;
        this.radioDevName = str;
        this.radioDevUUID = str2;
        this.devRssi = i2;
        this.radioManufacturerData = str3;
    }

    protected BleDevice(Parcel parcel) {
        this.bluetoothDevice = (BluetoothDevice) parcel.readParcelable(BluetoothDevice.class.getClassLoader());
        this.devType = parcel.readInt();
        this.devRssi = parcel.readInt();
        this.radioDevName = parcel.readString();
        this.radioDevUUID = parcel.readString();
        this.radioManufacturerData = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof FTBluetoothDevice) && this.bluetoothDevice.equals(((FTBluetoothDevice) obj).getBluetoothDevice());
    }

    public final BluetoothDevice getBluetoothDevice() {
        return this.bluetoothDevice;
    }

    public final int getDevRssi() {
        return this.devRssi;
    }

    public final int getDevType() {
        return this.devType;
    }

    public final String getRadioDevName() {
        return this.radioDevName;
    }

    public final String getRadioManufacturerData() {
        return this.radioManufacturerData;
    }

    public final String getRadioUUID() {
        return this.radioDevUUID;
    }

    public final int hashCode() {
        return this.bluetoothDevice.hashCode();
    }

    public final String toString() {
        String name = this.bluetoothDevice.getName();
        StringBuilder sb = new StringBuilder();
        String str = name;
        if (TextUtils.isEmpty(name)) {
            str = this.radioDevName;
        }
        sb.append(str);
        sb.append("\n");
        sb.append(this.bluetoothDevice.getAddress());
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.bluetoothDevice, i);
        parcel.writeInt(this.devType);
        parcel.writeInt(this.devRssi);
        parcel.writeString(this.radioDevName);
        parcel.writeString(this.radioDevUUID);
        parcel.writeString(this.radioManufacturerData);
    }
}
