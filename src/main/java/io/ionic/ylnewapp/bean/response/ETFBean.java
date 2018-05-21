package io.ionic.ylnewapp.bean.response;

import java.util.List;

/**
 * Created by mogojing on 2018/5/16/0016.
 */

public class ETFBean {


    private List<ETFBean> ETF;

    public List<ETFBean> getETF() {
        return ETF;
    }

    public void setETF(List<ETFBean> ETF) {
        this.ETF = ETF;
    }

        /**
         * _id : 5acc234a94d4626eed591d51
         * week : 2018.05.02
         * name : 马六甲比特币一号【 十三期】
         * pid : s20170012
         * title : 全新的投资方式 币币对冲套利
         * titleRate : 历史收益率
         * titleWeek : 发售日期
         * btn : 立即起投
         * content : ["浮动收益","5000起投"]
         * rate : 0%
         * state : true
         * date : 2018-05-01T16:00:00.000Z
         */

        private String _id;
        private String week;
        private String name;
        private String pid;
        private String title;
        private String titleRate;
        private String titleWeek;
        private String btn;
        private String rate;
        private boolean state;
        private String date;
        private List<String> content;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
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

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
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
