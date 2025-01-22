package im.imkey.imkeylibrary.device;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/Applet.class */
public class Applet {
    public static final String BTC_AID = "695F627463";
    public static final String BTC_NAME = "BTC";
    public static final String COSMOS_AID = "695F636F736D6F73";
    public static final String COSMOS_NAME = "COSMOS";
    public static final String EOS_AID = "695F656F73";
    public static final String EOS_NAME = "EOS";
    public static final String ETH_AID = "695F657468";
    public static final String ETH_NAME = "ETH";
    public static final String IMK_AID = "695F696D6B";
    public static final String IMK_NAME = "IMK";

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    public static String appletName2instanceAid(String str) {
        boolean z;
        switch (str.hashCode()) {
            case 66097:
                if (str.equals(BTC_NAME)) {
                    z = false;
                    break;
                }
                z = -1;
                break;
            case 68841:
                if (str.equals(EOS_NAME)) {
                    z = 2;
                    break;
                }
                z = -1;
                break;
            case 68985:
                if (str.equals(ETH_NAME)) {
                    z = true;
                    break;
                }
                z = -1;
                break;
            case 72615:
                if (str.equals(IMK_NAME)) {
                    z = 3;
                    break;
                }
                z = -1;
                break;
            case 1993660458:
                if (str.equals(COSMOS_NAME)) {
                    z = 4;
                    break;
                }
                z = -1;
                break;
            default:
                z = -1;
                break;
        }
        return z ? !z ? z != 2 ? z != 3 ? z != 4 ? "" : COSMOS_AID : IMK_AID : EOS_AID : ETH_AID : BTC_AID;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    public static String instanceAid2AppletName(String str) {
        boolean z;
        switch (str.hashCode()) {
            case -498260790:
                if (str.equals(BTC_AID)) {
                    z = false;
                    break;
                }
                z = -1;
                break;
            case -495502689:
                if (str.equals(EOS_AID)) {
                    z = 2;
                    break;
                }
                z = -1;
                break;
            case -495490222:
                if (str.equals(ETH_AID)) {
                    z = true;
                    break;
                }
                z = -1;
                break;
            case -491810543:
                if (str.equals(IMK_AID)) {
                    z = 3;
                    break;
                }
                z = -1;
                break;
            case 668379991:
                if (str.equals(COSMOS_AID)) {
                    z = 4;
                    break;
                }
                z = -1;
                break;
            default:
                z = -1;
                break;
        }
        return z ? !z ? z != 2 ? z != 3 ? z != 4 ? "" : COSMOS_NAME : IMK_NAME : EOS_NAME : ETH_NAME : BTC_NAME;
    }
}
