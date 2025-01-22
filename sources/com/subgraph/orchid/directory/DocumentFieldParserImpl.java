package com.subgraph.orchid.directory;

import com.subgraph.orchid.TorException;
import com.subgraph.orchid.TorParsingException;
import com.subgraph.orchid.crypto.TorMessageDigest;
import com.subgraph.orchid.crypto.TorPublicKey;
import com.subgraph.orchid.crypto.TorSignature;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.data.Timestamp;
import com.subgraph.orchid.directory.parsing.DocumentFieldParser;
import com.subgraph.orchid.directory.parsing.DocumentObject;
import com.subgraph.orchid.directory.parsing.DocumentParsingHandler;
import com.subgraph.orchid.directory.parsing.NameIntegerParameter;
import com.subgraph.orchid.encoders.Base64;
import com.xiaomi.mipush.sdk.Constants;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/DocumentFieldParserImpl.class */
public class DocumentFieldParserImpl implements DocumentFieldParser {
    private static final String BEGIN_TAG = "-----BEGIN";
    private static final String DEFAULT_DELIMITER = " ";
    private static final String END_TAG = "-----END";
    private static final String TAG_DELIMITER = "-----";
    private static final Logger logger = Logger.getLogger(DocumentFieldParserImpl.class.getName());
    private DocumentParsingHandler callbackHandler;
    private List<String> currentItems;
    private int currentItemsPosition;
    private String currentKeyword;
    private final SimpleDateFormat dateFormat;
    private final ByteBuffer inputBuffer;
    private StringBuilder rawDocumentBuffer;
    private boolean recognizeOpt;
    private TorMessageDigest signatureDigest;
    private TorMessageDigest signatureDigest256;
    private String signatureIgnoreToken;
    private String delimiter = DEFAULT_DELIMITER;
    private boolean isProcessingSignedEntity = false;

    public DocumentFieldParserImpl(ByteBuffer byteBuffer) {
        byteBuffer.rewind();
        this.inputBuffer = byteBuffer;
        this.rawDocumentBuffer = new StringBuilder();
        this.dateFormat = createDateFormat();
    }

    private static SimpleDateFormat createDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        simpleDateFormat.setLenient(false);
        return simpleDateFormat;
    }

    private String getItem() {
        if (this.currentItemsPosition >= this.currentItems.size()) {
            throw new TorParsingException("Overrun while reading arguments");
        }
        List<String> list = this.currentItems;
        int i = this.currentItemsPosition;
        this.currentItemsPosition = i + 1;
        return list.get(i);
    }

    private String nextLineFromInputBuffer() {
        char c;
        if (!this.inputBuffer.hasRemaining()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        while (this.inputBuffer.hasRemaining() && (c = (char) (this.inputBuffer.get() & 255)) != '\n') {
            if (c != '\r') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private void parseObjectBody(DocumentObject documentObject, String str) {
        String str2 = "-----END " + str + TAG_DELIMITER;
        while (true) {
            String readLine = readLine();
            if (readLine == null) {
                throw new TorParsingException("EOF reached before end of '" + str + "' object.");
            }
            if (readLine.equals(str2)) {
                documentObject.addFooterLine(readLine);
                return;
            }
            parseObjectContent(documentObject, readLine);
        }
    }

    private void parseObjectContent(DocumentObject documentObject, String str) {
        documentObject.addContent(str);
    }

    private String parseObjectHeader(String str) {
        if (str.startsWith(BEGIN_TAG) && str.endsWith(TAG_DELIMITER)) {
            return str.substring(11, str.length() - 5);
        }
        throw new TorParsingException("Did not find expected object start tag.");
    }

    private boolean processLine(String str) {
        List<String> asList = Arrays.asList(str.split(this.delimiter));
        if (asList.size() == 0 || asList.get(0).length() == 0) {
            return false;
        }
        this.currentKeyword = asList.get(0);
        this.currentItems = asList;
        this.currentItemsPosition = 1;
        if (!this.recognizeOpt || !this.currentKeyword.equals("opt") || asList.size() <= 1) {
            return true;
        }
        this.currentKeyword = asList.get(1);
        this.currentItemsPosition = 2;
        return true;
    }

    private String readLine() {
        String nextLineFromInputBuffer = nextLineFromInputBuffer();
        if (nextLineFromInputBuffer != null) {
            updateCurrentSignature(nextLineFromInputBuffer);
            updateRawDocument(nextLineFromInputBuffer);
        }
        return nextLineFromInputBuffer;
    }

    private void updateCurrentSignature(String str) {
        if (this.isProcessingSignedEntity) {
            String str2 = this.signatureIgnoreToken;
            if (str2 == null || !str.startsWith(str2)) {
                this.signatureDigest.update(str + "\n");
                this.signatureDigest256.update(str + "\n");
            }
        }
    }

    private void updateRawDocument(String str) {
        this.rawDocumentBuffer.append(str);
        this.rawDocumentBuffer.append('\n');
    }

    private void validateParameterName(String str) {
        if (str.isEmpty()) {
            throw new TorParsingException("Parameter name cannot be empty");
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return;
            }
            char c = charArray[i2];
            if (!Character.isLetterOrDigit(c) && c != '_') {
                throw new TorParsingException("Parameter name can only contain letters.  Rejecting: " + str);
            }
            i = i2 + 1;
        }
    }

    private void verifyExpectedArgumentCount(String str, int i, int i2) {
        int argumentsRemaining = argumentsRemaining();
        if (i != -1 && argumentsRemaining < i) {
            throw new TorParsingException("Not enough arguments for keyword '" + str + "' expected " + i + " and got " + argumentsRemaining);
        }
        if (i2 == -1 || argumentsRemaining <= i2) {
            return;
        }
        throw new TorParsingException("Too many arguments for keyword '" + str + "' expected " + i2 + " and got " + argumentsRemaining);
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public int argumentsRemaining() {
        return this.currentItems.size() - this.currentItemsPosition;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public void endSignedEntity() {
        this.isProcessingSignedEntity = false;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public String getCurrentKeyword() {
        return this.currentKeyword;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public String getRawDocument() {
        return this.rawDocumentBuffer.toString();
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public TorMessageDigest getSignatureMessageDigest() {
        return this.signatureDigest;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public TorMessageDigest getSignatureMessageDigest256() {
        return this.signatureDigest256;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public void logDebug(String str) {
        logger.fine(str);
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public void logError(String str) {
        logger.warning(str);
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public void logWarn(String str) {
        logger.info(str);
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public IPv4Address parseAddress() {
        return IPv4Address.createFromString(getItem());
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public HexDigest parseBase32Digest() {
        return HexDigest.createFromBase32String(parseString());
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public byte[] parseBase64Data() {
        StringBuilder sb = new StringBuilder(getItem());
        int length = sb.length() % 4;
        if (length == 2) {
            sb.append("==");
        } else if (length == 3) {
            sb.append("=");
        }
        try {
            return Base64.decode(sb.toString().getBytes("ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            throw new TorException(e);
        }
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public boolean parseBoolean() {
        int parseInteger = parseInteger();
        if (parseInteger == 1) {
            return true;
        }
        if (parseInteger == 0) {
            return false;
        }
        throw new TorParsingException("Illegal boolean value: " + parseInteger);
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public String parseConcatenatedString() {
        StringBuilder sb = new StringBuilder();
        while (argumentsRemaining() > 0) {
            if (sb.length() > 0) {
                sb.append(DEFAULT_DELIMITER);
            }
            sb.append(getItem());
        }
        return sb.toString();
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public HexDigest parseFingerprint() {
        return HexDigest.createFromString(parseConcatenatedString());
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public HexDigest parseHexDigest() {
        return HexDigest.createFromString(parseString());
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public int parseInteger() {
        return parseInteger(getItem());
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public int parseInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new TorParsingException("Failed to parse expected integer value: " + str);
        }
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public int[] parseIntegerList() {
        String[] split = getItem().split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        int[] iArr = new int[split.length];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= iArr.length) {
                return iArr;
            }
            iArr[i2] = parseInteger(split[i2]);
            i = i2 + 1;
        }
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public String parseNickname() {
        return getItem();
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public byte[] parseNtorPublicKey() {
        byte[] parseBase64Data = parseBase64Data();
        if (parseBase64Data.length == 32) {
            return parseBase64Data;
        }
        throw new TorParsingException("NTor public key was not expected length after base64 decoding.  Length is " + parseBase64Data.length);
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public DocumentObject parseObject() {
        String readLine = readLine();
        String parseObjectHeader = parseObjectHeader(readLine);
        DocumentObject documentObject = new DocumentObject(parseObjectHeader, readLine);
        parseObjectBody(documentObject, parseObjectHeader);
        return documentObject;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public NameIntegerParameter parseParameter() {
        String item = getItem();
        int indexOf = item.indexOf(61);
        if (indexOf == -1) {
            throw new TorParsingException("Parameter not in expected form name=value");
        }
        String substring = item.substring(0, indexOf);
        validateParameterName(substring);
        return new NameIntegerParameter(substring, parseInteger(item.substring(indexOf + 1)));
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public int parsePort() {
        return parsePort(getItem());
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public int parsePort(String str) {
        int parseInteger = parseInteger(str);
        if (parseInteger >= 0 && parseInteger <= 65535) {
            return parseInteger;
        }
        throw new TorParsingException("Illegal port value: " + parseInteger);
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public TorPublicKey parsePublicKey() {
        return TorPublicKey.createFromPEMBuffer(parseObject().getContent());
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public TorSignature parseSignature() {
        return TorSignature.createFromPEMBuffer(parseObject().getContent());
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public String parseString() {
        return getItem();
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public Timestamp parseTimestamp() {
        String str = getItem() + DEFAULT_DELIMITER + getItem();
        try {
            return new Timestamp(this.dateFormat.parse(str));
        } catch (ParseException e) {
            throw new TorParsingException("Could not parse timestamp value: " + str);
        }
    }

    public DocumentObject parseTypedObject(String str) {
        DocumentObject parseObject = parseObject();
        if (str.equals(parseObject.getKeyword())) {
            return parseObject;
        }
        throw new TorParsingException("Unexpected object type.  Expecting: " + str + ", but got: " + parseObject.getKeyword());
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public void processDocument() {
        if (this.callbackHandler == null) {
            throw new TorException("DocumentFieldParser#processDocument() called with null callbackHandler");
        }
        while (true) {
            String readLine = readLine();
            if (readLine == null) {
                this.callbackHandler.endOfDocument();
                return;
            } else if (processLine(readLine)) {
                this.callbackHandler.parseKeywordLine();
            }
        }
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public void resetRawDocument() {
        this.rawDocumentBuffer = new StringBuilder();
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public void resetRawDocument(String str) {
        this.rawDocumentBuffer = new StringBuilder();
        this.rawDocumentBuffer.append(str);
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public void setDelimiter(String str) {
        this.delimiter = str;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public void setHandler(DocumentParsingHandler documentParsingHandler) {
        this.callbackHandler = documentParsingHandler;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public void setRecognizeOpt() {
        this.recognizeOpt = true;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public void setSignatureIgnoreToken(String str) {
        this.signatureIgnoreToken = str;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public void startSignedEntity() {
        this.isProcessingSignedEntity = true;
        this.signatureDigest = new TorMessageDigest();
        this.signatureDigest256 = new TorMessageDigest(true);
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public void verifyExpectedArgumentCount(String str, int i) {
        verifyExpectedArgumentCount(str, i, i);
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentFieldParser
    public boolean verifySignedEntity(TorPublicKey torPublicKey, TorSignature torSignature) {
        this.isProcessingSignedEntity = false;
        return torPublicKey.verifySignature(torSignature, this.signatureDigest);
    }
}
