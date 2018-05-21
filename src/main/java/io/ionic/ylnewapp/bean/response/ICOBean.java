package io.ionic.ylnewapp.bean.response;

import java.util.List;

/**
 * Created by mogojing on 2018/5/17/0017.
 */

public class ICOBean {

    private List<ICOBean> ICO;

    public List<ICOBean> getICO() {
        return ICO;
    }

    public void setICO(List<ICOBean> ICO) {
        this.ICO = ICO;
    }

        /**
         * _id : 5a9e34c2777d663e7f9e1834
         * week : 365天
         * name : ICO基金
         * pid : ico001
         * title : 全新的投资方式 币币对冲套利
         * titleRate : 预期年化收益率
         * titleWeek : 封闭期限
         * btn : 立即投资
         * content : ["分散投资","优质项目"]
         * rate : 请关注QQ群
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

        public List<String> getContent() {
            return content;
        }

        public void setContent(List<String> content) {
            this.content = content;
        }
}
