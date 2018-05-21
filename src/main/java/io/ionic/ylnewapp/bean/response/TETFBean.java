package io.ionic.ylnewapp.bean.response;

import java.util.List;

/**
 * Created by mogojing on 2018/5/16/0016.
 */

public class TETFBean {


    private List<TETFBean> TETF;

    public List<TETFBean> getTETF() {
        return TETF;
    }

    public void setTETF(List<TETFBean> TETF) {
        this.TETF = TETF;
    }

        /**
         * _id : 592f74671c13454e43dbe139
         * name : 陆家嘴比特币一号【二期】
         * week : 365
         * btn : 25000
         * content : ["未锁定","周期:365","净值:￥49217"]
         * orderid : 5620*******5655
         * date : 2017-06-01T01:58:53.384Z
         */

        private String _id;
        private String name;
        private int week;
        private String btn;
        private String orderid;
        private String date;
        private List<String> content;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWeek() {
            return week;
        }

        public void setWeek(int week) {
            this.week = week;
        }

        public String getBtn() {
            return btn;
        }

        public void setBtn(String btn) {
            this.btn = btn;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<String> getContent() {
            return content;
        }

        public void setContent(List<String> content) {
            this.content = content;
        }
}
