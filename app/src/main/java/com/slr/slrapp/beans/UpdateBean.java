package com.slr.slrapp.beans;

/**
 * Created by admin on 2016/8/14.
 */
public class UpdateBean {

    /**
     * message : 获取成功！
     * ex : {"editionFile":"http://192.168.0.188:8080/service/upload/comapp/app-debug.apk","editionName":"1.0","editionModel":"1"}
     * code : 200
     */

    private String message;
    /**
     * editionFile : http://192.168.0.188:8080/service/upload/comapp/app-debug.apk
     * editionName : 1.0
     * editionModel : 1
     */

    private ExBean ex;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExBean getEx() {
        return ex;
    }

    public void setEx(ExBean ex) {
        this.ex = ex;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class ExBean {
        private String editionFile;
        private String editionName;
        private String editionModel;

        public String getEditionFile() {
            return editionFile;
        }

        public void setEditionFile(String editionFile) {
            this.editionFile = editionFile;
        }

        public String getEditionName() {
            return editionName;
        }

        public void setEditionName(String editionName) {
            this.editionName = editionName;
        }

        public String getEditionModel() {
            return editionModel;
        }

        public void setEditionModel(String editionModel) {
            this.editionModel = editionModel;
        }
    }
}
