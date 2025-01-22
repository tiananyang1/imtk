package com.subgraph.orchid.circuits.p002hs;

import com.subgraph.orchid.crypto.TorPublicKey;
import com.subgraph.orchid.crypto.TorRandom;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.Timestamp;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/HSDescriptor.class */
public class HSDescriptor {
    private static final long MS_24_HOURS = 86400000;
    private HexDigest descriptorId;
    private final HiddenService hiddenService;
    private List<IntroductionPoint> introductionPoints = new ArrayList();
    private TorPublicKey permanentKey;
    private int[] protocolVersions;
    private Timestamp publicationTime;
    private HexDigest secretIdPart;

    public HSDescriptor(HiddenService hiddenService) {
        this.hiddenService = hiddenService;
    }

    private List<IntroductionPoint> shuffle(List<IntroductionPoint> list) {
        TorRandom torRandom = new TorRandom();
        int size = list.size();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= size) {
                return list;
            }
            swap(list, i2, torRandom.nextInt(size));
            i = i2 + 1;
        }
    }

    private void swap(List<IntroductionPoint> list, int i, int i2) {
        if (i == i2) {
            return;
        }
        IntroductionPoint introductionPoint = list.get(i);
        list.set(i, list.get(i2));
        list.set(i2, introductionPoint);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addIntroductionPoint(IntroductionPoint introductionPoint) {
        this.introductionPoints.add(introductionPoint);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HexDigest getDescriptorId() {
        return this.descriptorId;
    }

    HiddenService getHiddenService() {
        return this.hiddenService;
    }

    List<IntroductionPoint> getIntroductionPoints() {
        return new ArrayList(this.introductionPoints);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TorPublicKey getPermanentKey() {
        return this.permanentKey;
    }

    int[] getProtocolVersions() {
        return this.protocolVersions;
    }

    Timestamp getPublicationTime() {
        return this.publicationTime;
    }

    HexDigest getSecretIdPart() {
        return this.secretIdPart;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<IntroductionPoint> getShuffledIntroductionPoints() {
        return shuffle(getIntroductionPoints());
    }

    int getVersion() {
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isExpired() {
        return System.currentTimeMillis() - this.publicationTime.getTime() > MS_24_HOURS;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDescriptorId(HexDigest hexDigest) {
        this.descriptorId = hexDigest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPermanentKey(TorPublicKey torPublicKey) {
        this.permanentKey = torPublicKey;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setProtocolVersions(int[] iArr) {
        this.protocolVersions = iArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPublicationTime(Timestamp timestamp) {
        this.publicationTime = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSecretIdPart(HexDigest hexDigest) {
        this.secretIdPart = hexDigest;
    }
}
