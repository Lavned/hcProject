package io.ionic.ylnewapp.bean.response;

import java.util.List;

/**
 * Created by mogojing on 2018/5/16/0016.
 */

public class DIGBean {


    private List<DIGBean> DIG;

    public List<DIGBean> getDIG() {
        return DIG;
    }

    public void setDIG(List<DIGBean> DIG) {
        this.DIG = DIG;
    }

        /**
         * _id : 5a9e34c2777d663e7f9e1831
         * week : 90天
         * name : 数字资产一季
         * pid : e20170001
         * title : 全新的投资方式 币币对冲套利
         * titleRate : 今日年化收益率
         * titleWeek : 期限
         * btn : 立即投资
         * content : ["分散投资","优质项目"]
         * rate : 4.8%
         * join : 1
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
        private String join;
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

        public String getJoin() {
            return join;
        }

        public void setJoin(String join) {
            this.join = join;
        }

        public List<String> getContent() {
            return content;
        }

        public void setContent(List<String> content) {
            this.content = content;
        }
}
