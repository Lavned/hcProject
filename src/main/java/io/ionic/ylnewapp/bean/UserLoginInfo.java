package io.ionic.ylnewapp.bean;

import java.util.List;

/**
 * Created by mogojing on 2018/5/11/0011.
 */

public class UserLoginInfo {


    /**
     * status : 200
     * msg : 登录成功
     * body : {"_id":"5ac47f83608b1d1980905c8e","password":"96e79218965eb72c92a549dd5a330112","username":"17858968657","mobStatus":"0","created":"2018-04-04T07:32:19.604Z","updated":"2018-04-04T07:32:19.604Z","address":[{"address":"11111111111111111111111","type":1,"id":"20180514154108"},{"address":"5555555","type":2,"id":"20180514154108"},{"count":"0xd4F55c460ccc61914d1D5f321952574A94c8191b","name":"1","id":"20180514161323"}],"banks":[{"count":"guguggjgj","name":"建设银行"},{"count":"guguggjgj","name":"建设银行2"}],"bankcount":"","bankcode":"","name":"name","idcard":"330721199603264812","roletype":"0","is_admin":false,"zhifuPay":null,"zijinPay":"","qianbao":5000,"email":null,"weixin":null,"qq":null,"header":""}
     * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyIkX18iOnsic3RyaWN0TW9kZSI6dHJ1ZSwic2VsZWN0ZWQiOnsibW9TdGF0dXMiOjAsIm1haWxTZWMiOjAsInVzZXJTdGF0dXMiOjAsIm90aGVyVGVsIjowLCJfX3YiOjAsImxhc3RUaW1lIjowLCJub3dUaW1lIjowLCJyZW1vdGVpcCI6MH0sImdldHRlcnMiOnt9LCJfaWQiOiI1YWM0N2Y4MzYwOGIxZDE5ODA5MDVjOGUiLCJ3YXNQb3B1bGF0ZWQiOmZhbHNlLCJhY3RpdmVQYXRocyI6eyJwYXRocyI6eyJwYXNzd29yZCI6ImluaXQiLCJ1c2VybmFtZSI6ImluaXQiLCJoZWFkZXIiOiJpbml0IiwicXEiOiJpbml0Iiwid2VpeGluIjoiaW5pdCIsImVtYWlsIjoiaW5pdCIsInFpYW5iYW8iOiJpbml0IiwiemlqaW5QYXkiOiJpbml0IiwiemhpZnVQYXkiOiJpbml0IiwiaXNfYWRtaW4iOiJpbml0Iiwicm9sZXR5cGUiOiJpbml0IiwiaWRjYXJkIjoiaW5pdCIsIm5hbWUiOiJpbml0IiwiYmFua2NvZGUiOiJkZWZhdWx0IiwiYmFua2NvdW50IjoiZGVmYXVsdCIsImJhbmtzIjoiaW5pdCIsImFkZHJlc3MiOiJpbml0IiwidXBkYXRlZCI6ImluaXQiLCJjcmVhdGVkIjoiaW5pdCIsIl9pZCI6ImluaXQifSwic3RhdGVzIjp7Imlnbm9yZSI6e30sImRlZmF1bHQiOnsiYmFua2NvZGUiOnRydWUsImJhbmtjb3VudCI6dHJ1ZX0sImluaXQiOnsiYmFua3MiOnRydWUsImFkZHJlc3MiOnRydWUsImhlYWRlciI6dHJ1ZSwicXEiOnRydWUsIndlaXhpbiI6dHJ1ZSwiZW1haWwiOnRydWUsInFpYW5iYW8iOnRydWUsInppamluUGF5Ijp0cnVlLCJ6aGlmdVBheSI6dHJ1ZSwiaXNfYWRtaW4iOnRydWUsInJvbGV0eXBlIjp0cnVlLCJpZGNhcmQiOnRydWUsIm5hbWUiOnRydWUsInVwZGF0ZWQiOnRydWUsImNyZWF0ZWQiOnRydWUsInVzZXJuYW1lIjp0cnVlLCJwYXNzd29yZCI6dHJ1ZSwiX2lkIjp0cnVlfSwibW9kaWZ5Ijp7fSwicmVxdWlyZSI6e319LCJzdGF0ZU5hbWVzIjpbInJlcXVpcmUiLCJtb2RpZnkiLCJpbml0IiwiZGVmYXVsdCIsImlnbm9yZSJdfSwicGF0aHNUb1Njb3BlcyI6e30sImVtaXR0ZXIiOnsiZG9tYWluIjpudWxsLCJfZXZlbnRzIjp7fSwiX2V2ZW50c0NvdW50IjowLCJfbWF4TGlzdGVuZXJzIjowfSwiJG9wdGlvbnMiOnRydWV9LCJpc05ldyI6ZmFsc2UsIl9kb2MiOnsiaGVhZGVyIjoiIiwicXEiOm51bGwsIndlaXhpbiI6bnVsbCwiZW1haWwiOm51bGwsInFpYW5iYW8iOjUwMDAsInppamluUGF5IjoiIiwiemhpZnVQYXkiOm51bGwsImlzX2FkbWluIjpmYWxzZSwicm9sZXR5cGUiOiIwIiwiaWRjYXJkIjoiMzMwNzIxMTk5NjAzMjY0ODEyIiwibmFtZSI6Im5hbWUiLCJiYW5rY29kZSI6IiIsImJhbmtjb3VudCI6IiIsImJhbmtzIjpbeyJuYW1lIjoi5bu66K6-6ZO26KGMIiwiY291bnQiOiJndWd1Z2dqZ2oifSx7Im5hbWUiOiLlu7rorr7pk7booYwyIiwiY291bnQiOiJndWd1Z2dqZ2oifV0sImFkZHJlc3MiOlt7ImlkIjoiMjAxODA1MTQxNTQxMDgiLCJ0eXBlIjoxLCJhZGRyZXNzIjoiMTExMTExMTExMTExMTExMTExMTExMTEifSx7ImlkIjoiMjAxODA1MTQxNTQxMDgiLCJ0eXBlIjoyLCJhZGRyZXNzIjoiNTU1NTU1NSJ9LHsiaWQiOiIyMDE4MDUxNDE2MTMyMyIsIm5hbWUiOiIxIiwiY291bnQiOiIweGQ0RjU1YzQ2MGNjYzYxOTE0ZDFENWYzMjE5NTI1NzRBOTRjODE5MWIifV0sInVwZGF0ZWQiOiIyMDE4LTA0LTA0VDA3OjMyOjE5LjYwNFoiLCJjcmVhdGVkIjoiMjAxOC0wNC0wNFQwNzozMjoxOS42MDRaIiwibW9iU3RhdHVzIjoiMCIsInVzZXJuYW1lIjoiMTc4NTg5Njg2NTciLCJwYXNzd29yZCI6Ijk2ZTc5MjE4OTY1ZWI3MmM5MmE1NDlkZDVhMzMwMTEyIiwiX2lkIjoiNWFjNDdmODM2MDhiMWQxOTgwOTA1YzhlIn0sIiRpbml0Ijp0cnVlLCJpYXQiOjE1MjY2MjM0NzgsImV4cCI6MTUyNjYyNzA3OH0.kZl8KIl5RIGFtCudhxsPnU9Fp2QeNWpTAAFi4jNt91U
     */

    private int status;
    private String msg;
    private BodyBean body;
    private String token;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class BodyBean {
        /**
         * _id : 5ac47f83608b1d1980905c8e
         * password : 96e79218965eb72c92a549dd5a330112
         * username : 17858968657
         * mobStatus : 0
         * created : 2018-04-04T07:32:19.604Z
         * updated : 2018-04-04T07:32:19.604Z
         * address : [{"address":"11111111111111111111111","type":1,"id":"20180514154108"},{"address":"5555555","type":2,"id":"20180514154108"},{"count":"0xd4F55c460ccc61914d1D5f321952574A94c8191b","name":"1","id":"20180514161323"}]
         * banks : [{"count":"guguggjgj","name":"建设银行"},{"count":"guguggjgj","name":"建设银行2"}]
         * bankcount :
         * bankcode :
         * name : name
         * idcard : 330721199603264812
         * roletype : 0
         * is_admin : false
         * zhifuPay : null
         * zijinPay :
         * qianbao : 5000
         * email : null
         * weixin : null
         * qq : null
         * header :
         */

        private String _id;
        private String password;
        private String username;
        private String mobStatus;
        private String created;
        private String updated;
        private String bankcount;
        private String bankcode;
        private String name;
        private String idcard;
        private String roletype;
        private boolean is_admin;
        private Object zhifuPay;
        private String zijinPay;
        private int qianbao;
        private Object email;
        private Object weixin;
        private Object qq;
        private String header;
        private List<AddressBean> address;
        private List<BanksBean> banks;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMobStatus() {
            return mobStatus;
        }

        public void setMobStatus(String mobStatus) {
            this.mobStatus = mobStatus;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public String getBankcount() {
            return bankcount;
        }

        public void setBankcount(String bankcount) {
            this.bankcount = bankcount;
        }

        public String getBankcode() {
            return bankcode;
        }

        public void setBankcode(String bankcode) {
            this.bankcode = bankcode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getRoletype() {
            return roletype;
        }

        public void setRoletype(String roletype) {
            this.roletype = roletype;
        }

        public boolean isIs_admin() {
            return is_admin;
        }

        public void setIs_admin(boolean is_admin) {
            this.is_admin = is_admin;
        }

        public Object getZhifuPay() {
            return zhifuPay;
        }

        public void setZhifuPay(Object zhifuPay) {
            this.zhifuPay = zhifuPay;
        }

        public String getZijinPay() {
            return zijinPay;
        }

        public void setZijinPay(String zijinPay) {
            this.zijinPay = zijinPay;
        }

        public int getQianbao() {
            return qianbao;
        }

        public void setQianbao(int qianbao) {
            this.qianbao = qianbao;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getWeixin() {
            return weixin;
        }

        public void setWeixin(Object weixin) {
            this.weixin = weixin;
        }

        public Object getQq() {
            return qq;
        }

        public void setQq(Object qq) {
            this.qq = qq;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public List<AddressBean> getAddress() {
            return address;
        }

        public void setAddress(List<AddressBean> address) {
            this.address = address;
        }

        public List<BanksBean> getBanks() {
            return banks;
        }

        public void setBanks(List<BanksBean> banks) {
            this.banks = banks;
        }

        public static class AddressBean {
            /**
             * address : 11111111111111111111111
             * type : 1
             * id : 20180514154108
             * count : 0xd4F55c460ccc61914d1D5f321952574A94c8191b
             * name : 1
             */

            private String address;
            private int type;
            private String id;
            private String count;
            private String name;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class BanksBean {
            /**
             * count : guguggjgj
             * name : 建设银行
             */

            private String count;
            private String name;

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
