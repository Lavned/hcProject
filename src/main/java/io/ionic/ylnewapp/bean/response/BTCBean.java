package io.ionic.ylnewapp.bean.response;

import java.util.List;

/**
 * Created by mogojing on 2018/5/17/0017.
 */

public class BTCBean {


    private List<BTCBean> BTC;

    public List<BTCBean> getBTC() {
        return BTC;
    }

    public void setBTC(List<BTCBean> BTC) {
        this.BTC = BTC;
    }

        /**
         * _id : 5a9e34c2777d663e7f9e1835
         * name : BTC指数基金
         * pid : b20170000
         * week : 365天
         * title : 全新的投资方式 币币对冲套利
         * titleRate : 今日指数
         * titleWeek : 封闭期限
         * btn : 立即投资
         * content : ["浮动收益","5000起投","每日15时收盘价"]
         * rate : 每日15时收盘价
         */

        private String _id;
        private String name;
        private String pid;
        private String week;
        private String title;
        private String titleRate;
        private String titleWeek;
        private String btn;
        private String rate;
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

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitleRate() {
            return titleRate;
        }

        public void setTitleRate(String titleRate) {
            this.titleRate = titleRate;
        }

        public String getTitleWeek() {
            return titleWeek;
        }

        public void setTitleWeek(String titleWeek) {
            this.titleWeek = titleWeek;
        }

        public String getBtn() {
            return btn;
        }

        public void setBtn(String btn) {
            this.btn = btn;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public List<String> getContent() {
            return content;
        }

        public void setContent(List<String> content) {
            this.content = content;
        }
}
