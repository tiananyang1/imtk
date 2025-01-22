package im.imkey.imkeylibrary.device.model;

import java.util.List;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/CommonResponse.class */
public class CommonResponse {
    private String _ReturnCode;
    private ReturnDataBean _ReturnData;
    private String _ReturnMsg;

    /* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/CommonResponse$ReturnDataBean.class */
    public class ReturnDataBean {
        private List<String> apduList;
        private String nextStepKey;
        private String seid;

        public ReturnDataBean() {
        }

        public List<String> getApduList() {
            return this.apduList;
        }

        public String getNextStepKey() {
            return this.nextStepKey;
        }

        public String getSeid() {
            return this.seid;
        }

        public void setApduList(List<String> list) {
            this.apduList = list;
        }

        public void setNextStepKey(String str) {
            this.nextStepKey = str;
        }

        public void setSeid(String str) {
            this.seid = str;
        }

        public String toString() {
            return "ReturnDataBean{seid='" + this.seid + "', nextStepKey='" + this.nextStepKey + "', apduList=" + this.apduList + '}';
        }
    }

    public String get_ReturnCode() {
        return this._ReturnCode;
    }

    public ReturnDataBean get_ReturnData() {
        return this._ReturnData;
    }

    public String get_ReturnMsg() {
        return this._ReturnMsg;
    }

    public void set_ReturnCode(String str) {
        this._ReturnCode = str;
    }

    public void set_ReturnData(ReturnDataBean returnDataBean) {
        this._ReturnData = returnDataBean;
    }

    public void set_ReturnMsg(String str) {
        this._ReturnMsg = str;
    }

    public String toString() {
        return "CommonResponse{_ReturnCode='" + this._ReturnCode + "', _ReturnMsg='" + this._ReturnMsg + "', _ReturnData=" + this._ReturnData + '}';
    }
}
