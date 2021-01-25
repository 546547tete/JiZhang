package com.example.jizhang.bean;

import java.util.List;

public class AddEntryBean {


    private List<DetailBean> detail;

    public List<DetailBean> getDetail() {
        return detail;
    }

    public void setDetail(List<DetailBean> detail) {
        this.detail = detail;
    }

    public static class DetailBean {
        /**
         * loc : ["string"]
         * msg : string
         * type : string
         */

        private String msg;
        private String type;
        private List<String> loc;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<String> getLoc() {
            return loc;
        }

        public void setLoc(List<String> loc) {
            this.loc = loc;
        }
    }
}
