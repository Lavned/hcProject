package io.ionic.ylnewapp.bean.response;

import java.util.List;

/**
 * Created by mogojing on 2018/5/16/0016.
 */

public class AIBean {


    private List<AIBean> AI;

    public List<AIBean> getAI() {
        return AI;
    }

    public void setAI(List<AIBean> AI) {
        this.AI = AI;
    }

        /**
         * _id : 5aa7a1c9f05c91139e989681
         * week : 全天
         * name : 区块链智能投顾一号
         * pid : ai001
         * title : 全新的投资方式 币币对冲套利
         * titleRate : 预期年化收益率
         * titleWeek : 申购时间
         * btn : 立即投资
         * content : ["随存随取","优质项目","1000起投"]
         * rate : 4~15%+浮动收益
         * join : 1000
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
