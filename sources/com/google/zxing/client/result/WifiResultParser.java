package com.google.zxing.client.result;

import com.google.zxing.Result;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/client/result/WifiResultParser.class */
public final class WifiResultParser extends ResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    public WifiParsedResult parse(Result result) {
        String matchSinglePrefixedField;
        String massagedText = getMassagedText(result);
        if (!massagedText.startsWith("WIFI:") || (matchSinglePrefixedField = matchSinglePrefixedField("S:", massagedText, ';', false)) == null || matchSinglePrefixedField.isEmpty()) {
            return null;
        }
        String matchSinglePrefixedField2 = matchSinglePrefixedField("P:", massagedText, ';', false);
        String matchSinglePrefixedField3 = matchSinglePrefixedField("T:", massagedText, ';', false);
        String str = matchSinglePrefixedField3;
        if (matchSinglePrefixedField3 == null) {
            str = "nopass";
        }
        return new WifiParsedResult(str, matchSinglePrefixedField, matchSinglePrefixedField2, Boolean.parseBoolean(matchSinglePrefixedField("H:", massagedText, ';', false)));
    }
}
