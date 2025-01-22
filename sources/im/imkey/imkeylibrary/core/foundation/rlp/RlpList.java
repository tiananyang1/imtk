package im.imkey.imkeylibrary.core.foundation.rlp;

import java.util.Arrays;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/foundation/rlp/RlpList.class */
public class RlpList implements RlpType {
    private final List<RlpType> values;

    public RlpList(List<RlpType> list) {
        this.values = list;
    }

    public RlpList(RlpType... rlpTypeArr) {
        this.values = Arrays.asList(rlpTypeArr);
    }

    public List<RlpType> getValues() {
        return this.values;
    }
}
