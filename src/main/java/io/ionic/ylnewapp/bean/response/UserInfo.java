package io.ionic.ylnewapp.bean.response;

/**
 * Created by mogojing on 2018/5/11/0011.
 */

public class UserInfo {


   public String password ;	//用户密码(md5加密)	linestring	12		修改删除
    public String header;	//用户头像	linestring	12		修改删除
    public String qq	 ;	//用户QQ	linestring	12		修改删除
    public String 微信	  ;	//用户微信	linestring	12		修改删除
    public String  email	 ;	//用户邮箱	linestring	12		修改删除
    public String remoteip	 ;	//登录者的ip[Array]	linestring	12		修改删除
    public String qianbao ;	//	钱包	numeric	12	0	修改删除
    public String zijinPay	 ;	//资金密码（MD5加密）	linestring	12		修改删除
    public String zhifuPay	 ;	//支付宝	linestring	12		修改删除
    public boolean  is_admin	 ;	//账号类型	blob	12	false	修改删除
    public String userStatus	 ;	//用户状态，是否认证 0没有认证 1认证中 2 已认证	linestring	12	0	修改删除
    public String roletype	 ;	//用户类型，默认0， 1是业务员	linestring	12	0	修改删除
    public String idcard	 ;	//身份证号	linestring	12		修改删除
    public String name	 ;	//真实姓名	linestring	12		修改删除
    public String bankcode	 ;	//银行代码	linestring	12		修改删除
    public String  bankcount	 ;	//银行账号	linestring	19		修改删除
    public String  wAddress	 ;	//钱包(以太坊地址)[Boolean]	linestring	42		修改删除
    public String bAddress	 ;	//钱包(比特币地址)[Boolean]	linestring	42		修改删除
    public String updated	 ;	//更新时间	date	12		修改删除
    public String   created ;	//
}
