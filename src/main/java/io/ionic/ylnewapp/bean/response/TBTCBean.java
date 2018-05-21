package io.ionic.ylnewapp.bean.response;

import java.util.List;

/**
 * Created by mogojing on 2018/5/16/0016.
 */

public class TBTCBean {

    private List<TBTCBean> TBTC;

    public List<TBTCBean> getTBTC() {
        return TBTC;
    }

    public void setTBTC(List<TBTCBean> TBTC) {
        this.TBTC = TBTC;
    }

        /**
         * _id : 59fd2b735c8934093e2d5e74
         * name : BTC指数基金
         * btn : 18400
         * content : ["未锁定","份额:0.2535BTC","净值:￥14119"]
         * orderid : 59d2*******5200
         * date : 2017-10-27T06:28:33.623Z
         */

        private String _id;
        private String name;
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
