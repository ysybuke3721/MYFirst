package com.slr.slrapp.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/8/13 0013.
 */
public class Filter13Bean {


    /**
     * message : 获取成功！
     * spaceList : [{"id":17,"storeName":"土家鸡蛋","isSelected":0},{"id":29,"storeName":"祖传秘制叫花鸡","isSelected":0},{"id":32,"storeName":"绝味鸡蛋","isSelected":0},{"id":33,"storeName":"周黑鸭","isSelected":0}]
     * code : 200
     * typeList : [{"id":"","name":"全部选择"},{"id":"2","name":"土鸡"},{"id":"3","name":"土鸭"},{"id":"4","name":"土鸡蛋"},{"id":"5","name":"土鸭蛋"}]
     */

    private String message;
    private int code;
    /**
     * id : 17
     * storeName : 土家鸡蛋
     * isSelected : 0
     */

    private List<SpaceListBean> spaceList;
    /**
     * id :
     * name : 全部选择
     */

    private List<TypeListBean> typeList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<SpaceListBean> getSpaceList() {
        return spaceList;
    }

    public void setSpaceList(List<SpaceListBean> spaceList) {
        this.spaceList = spaceList;
    }

    public List<TypeListBean> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<TypeListBean> typeList) {
        this.typeList = typeList;
    }

    public static class SpaceListBean {
        private int id;
        private String storeName;
        private int isSelected;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public int getIsSelected() {
            return isSelected;
        }

        public void setIsSelected(int isSelected) {
            this.isSelected = isSelected;
        }
    }

    public static class TypeListBean {
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
