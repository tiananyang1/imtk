package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import im.imkey.imkeylibrary.common.Constants;
import io.fabric.sdk.android.BuildConfig;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/oned/rss/expanded/decoders/FieldParser.class */
final class FieldParser {
    private static final Object[][] TWO_DIGIT_DATA_LENGTH = {new Object[]{Constants.BIND_STATUS_UNBOUND, 18}, new Object[]{"01", 14}, new Object[]{"02", 14}, new Object[]{"10", VARIABLE_LENGTH, 20}, new Object[]{"11", 6}, new Object[]{"12", 6}, new Object[]{"13", 6}, new Object[]{"15", 6}, new Object[]{"17", 6}, new Object[]{"20", 2}, new Object[]{"21", VARIABLE_LENGTH, 20}, new Object[]{BuildConfig.BUILD_NUMBER, VARIABLE_LENGTH, 29}, new Object[]{"30", VARIABLE_LENGTH, 8}, new Object[]{"37", VARIABLE_LENGTH, 8}, new Object[]{"90", VARIABLE_LENGTH, 30}, new Object[]{"91", VARIABLE_LENGTH, 30}, new Object[]{"92", VARIABLE_LENGTH, 30}, new Object[]{"93", VARIABLE_LENGTH, 30}, new Object[]{"94", VARIABLE_LENGTH, 30}, new Object[]{"95", VARIABLE_LENGTH, 30}, new Object[]{"96", VARIABLE_LENGTH, 30}, new Object[]{"97", VARIABLE_LENGTH, 30}, new Object[]{"98", VARIABLE_LENGTH, 30}, new Object[]{"99", VARIABLE_LENGTH, 30}};
    private static final Object[][] THREE_DIGIT_DATA_LENGTH = {new Object[]{"240", VARIABLE_LENGTH, 30}, new Object[]{"241", VARIABLE_LENGTH, 30}, new Object[]{"242", VARIABLE_LENGTH, 6}, new Object[]{"250", VARIABLE_LENGTH, 30}, new Object[]{"251", VARIABLE_LENGTH, 30}, new Object[]{"253", VARIABLE_LENGTH, 17}, new Object[]{"254", VARIABLE_LENGTH, 20}, new Object[]{"400", VARIABLE_LENGTH, 30}, new Object[]{"401", VARIABLE_LENGTH, 30}, new Object[]{"402", 17}, new Object[]{"403", VARIABLE_LENGTH, 30}, new Object[]{"410", 13}, new Object[]{"411", 13}, new Object[]{"412", 13}, new Object[]{"413", 13}, new Object[]{"414", 13}, new Object[]{"420", VARIABLE_LENGTH, 20}, new Object[]{"421", VARIABLE_LENGTH, 15}, new Object[]{"422", 3}, new Object[]{"423", VARIABLE_LENGTH, 15}, new Object[]{"424", 3}, new Object[]{"425", 3}, new Object[]{"426", 3}};
    private static final Object[][] THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH = {new Object[]{"310", 6}, new Object[]{"311", 6}, new Object[]{"312", 6}, new Object[]{"313", 6}, new Object[]{"314", 6}, new Object[]{"315", 6}, new Object[]{"316", 6}, new Object[]{"320", 6}, new Object[]{"321", 6}, new Object[]{"322", 6}, new Object[]{"323", 6}, new Object[]{"324", 6}, new Object[]{"325", 6}, new Object[]{"326", 6}, new Object[]{"327", 6}, new Object[]{"328", 6}, new Object[]{"329", 6}, new Object[]{"330", 6}, new Object[]{"331", 6}, new Object[]{"332", 6}, new Object[]{"333", 6}, new Object[]{"334", 6}, new Object[]{"335", 6}, new Object[]{"336", 6}, new Object[]{"340", 6}, new Object[]{"341", 6}, new Object[]{"342", 6}, new Object[]{"343", 6}, new Object[]{"344", 6}, new Object[]{"345", 6}, new Object[]{"346", 6}, new Object[]{"347", 6}, new Object[]{"348", 6}, new Object[]{"349", 6}, new Object[]{"350", 6}, new Object[]{"351", 6}, new Object[]{"352", 6}, new Object[]{"353", 6}, new Object[]{"354", 6}, new Object[]{"355", 6}, new Object[]{"356", 6}, new Object[]{"357", 6}, new Object[]{"360", 6}, new Object[]{"361", 6}, new Object[]{"362", 6}, new Object[]{"363", 6}, new Object[]{"364", 6}, new Object[]{"365", 6}, new Object[]{"366", 6}, new Object[]{"367", 6}, new Object[]{"368", 6}, new Object[]{"369", 6}, new Object[]{"390", VARIABLE_LENGTH, 15}, new Object[]{"391", VARIABLE_LENGTH, 18}, new Object[]{"392", VARIABLE_LENGTH, 15}, new Object[]{"393", VARIABLE_LENGTH, 18}, new Object[]{"703", VARIABLE_LENGTH, 30}};
    private static final Object VARIABLE_LENGTH = new Object();
    private static final Object[][] FOUR_DIGIT_DATA_LENGTH = {new Object[]{"7001", 13}, new Object[]{"7002", VARIABLE_LENGTH, 30}, new Object[]{"7003", 10}, new Object[]{"8001", 14}, new Object[]{"8002", VARIABLE_LENGTH, 20}, new Object[]{"8003", VARIABLE_LENGTH, 30}, new Object[]{"8004", VARIABLE_LENGTH, 30}, new Object[]{"8005", 6}, new Object[]{"8006", 18}, new Object[]{"8007", VARIABLE_LENGTH, 30}, new Object[]{"8008", VARIABLE_LENGTH, 12}, new Object[]{"8018", 18}, new Object[]{"8020", VARIABLE_LENGTH, 25}, new Object[]{"8100", 6}, new Object[]{"8101", 10}, new Object[]{"8102", 2}, new Object[]{"8110", VARIABLE_LENGTH, 70}, new Object[]{"8200", VARIABLE_LENGTH, 70}};

    private FieldParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String parseFieldsInGeneralPurpose(String str) throws NotFoundException {
        if (str.isEmpty()) {
            return null;
        }
        if (str.length() < 2) {
            throw NotFoundException.getNotFoundInstance();
        }
        String substring = str.substring(0, 2);
        Object[][] objArr = TWO_DIGIT_DATA_LENGTH;
        int length = objArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < length) {
                Object[] objArr2 = objArr[i2];
                if (objArr2[0].equals(substring)) {
                    return objArr2[1] == VARIABLE_LENGTH ? processVariableAI(2, ((Integer) objArr2[2]).intValue(), str) : processFixedAI(2, ((Integer) objArr2[1]).intValue(), str);
                }
                i = i2 + 1;
            } else {
                if (str.length() < 3) {
                    throw NotFoundException.getNotFoundInstance();
                }
                String substring2 = str.substring(0, 3);
                Object[][] objArr3 = THREE_DIGIT_DATA_LENGTH;
                int length2 = objArr3.length;
                int i3 = 0;
                while (true) {
                    int i4 = i3;
                    if (i4 < length2) {
                        Object[] objArr4 = objArr3[i4];
                        if (objArr4[0].equals(substring2)) {
                            return objArr4[1] == VARIABLE_LENGTH ? processVariableAI(3, ((Integer) objArr4[2]).intValue(), str) : processFixedAI(3, ((Integer) objArr4[1]).intValue(), str);
                        }
                        i3 = i4 + 1;
                    } else {
                        Object[][] objArr5 = THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH;
                        int length3 = objArr5.length;
                        int i5 = 0;
                        while (true) {
                            int i6 = i5;
                            if (i6 < length3) {
                                Object[] objArr6 = objArr5[i6];
                                if (objArr6[0].equals(substring2)) {
                                    return objArr6[1] == VARIABLE_LENGTH ? processVariableAI(4, ((Integer) objArr6[2]).intValue(), str) : processFixedAI(4, ((Integer) objArr6[1]).intValue(), str);
                                }
                                i5 = i6 + 1;
                            } else {
                                if (str.length() < 4) {
                                    throw NotFoundException.getNotFoundInstance();
                                }
                                String substring3 = str.substring(0, 4);
                                Object[][] objArr7 = FOUR_DIGIT_DATA_LENGTH;
                                int length4 = objArr7.length;
                                int i7 = 0;
                                while (true) {
                                    int i8 = i7;
                                    if (i8 >= length4) {
                                        throw NotFoundException.getNotFoundInstance();
                                    }
                                    Object[] objArr8 = objArr7[i8];
                                    if (objArr8[0].equals(substring3)) {
                                        return objArr8[1] == VARIABLE_LENGTH ? processVariableAI(4, ((Integer) objArr8[2]).intValue(), str) : processFixedAI(4, ((Integer) objArr8[1]).intValue(), str);
                                    }
                                    i7 = i8 + 1;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static String processFixedAI(int i, int i2, String str) throws NotFoundException {
        if (str.length() < i) {
            throw NotFoundException.getNotFoundInstance();
        }
        String substring = str.substring(0, i);
        int i3 = i2 + i;
        if (str.length() < i3) {
            throw NotFoundException.getNotFoundInstance();
        }
        String substring2 = str.substring(i, i3);
        String substring3 = str.substring(i3);
        String str2 = "(" + substring + ')' + substring2;
        String parseFieldsInGeneralPurpose = parseFieldsInGeneralPurpose(substring3);
        if (parseFieldsInGeneralPurpose == null) {
            return str2;
        }
        return str2 + parseFieldsInGeneralPurpose;
    }

    private static String processVariableAI(int i, int i2, String str) throws NotFoundException {
        String substring = str.substring(0, i);
        int i3 = i2 + i;
        int i4 = i3;
        if (str.length() < i3) {
            i4 = str.length();
        }
        String substring2 = str.substring(i, i4);
        String str2 = "(" + substring + ')' + substring2;
        String parseFieldsInGeneralPurpose = parseFieldsInGeneralPurpose(str.substring(i4));
        if (parseFieldsInGeneralPurpose == null) {
            return str2;
        }
        return str2 + parseFieldsInGeneralPurpose;
    }
}
