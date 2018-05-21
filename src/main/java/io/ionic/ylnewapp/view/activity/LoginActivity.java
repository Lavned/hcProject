package io.ionic.ylnewapp.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.jiangyy.easydialog.LoadingDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.UserLoginInfo;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.MD5Util;
import io.ionic.ylnewapp.utils.PhoneUtil;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.utils.SoftHideKeyBoardUtil;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class LoginActivity extends BaseActivity {


    private Context mContext;


    @ViewInject(R.id.login_layout)
    LinearLayout login_layout;
    @ViewInject(R.id.register_layout)
    LinearLayout register_layout;
    @ViewInject(R.id.view_login)
    View view_login;
    @ViewInject(R.id.view_register)
    View view_register;

    @ViewInject(R.id.ed_account)
    EditText account;
    @ViewInject(R.id.ed_login_pass)
    EditText loginPwd;
    @ViewInject(R.id.re_account)
    EditText reAccount;
    @ViewInject(R.id.re_account_code)
    EditText reCode;
    @ViewInject(R.id.re_login_pass)
    EditText rePwd;
    @ViewInject(R.id.re_account_yq)
    EditText reAccountYq;
    @ViewInject(R.id.re_login_repass)
    EditText re_login_repass;
    @ViewInject(R.id.checkbox)
    CheckBox checkbox;


    boolean isCheck ;

    @Event(type = View.OnClickListener.class , value = {R.id.btn_login ,R.id.tv_Login,
            R.id.tv_register,R.id.btn_register,R.id.getCode,R.id.tv_forget_pwd_re,R.id.tv_save_pwd_re})
    private void click(View view){
        switch (view.getId()){
            case R.id.btn_login :
                if(account.getText().toString().trim().equals("")){
                    T.showShort("用户名不可为空");
                    return;
                }
                if(loginPwd.getText().toString().trim().equals("")){
                    T.showShort("密码不可为空");
                    return;
                }
                if(!PhoneUtil.isPhoneNo(account.getText().toString().trim())){
                    T.showShort("请输入合法手机号");
                    return;
                }
                loginData();
                break;
            case R.id.btn_register :
                if(checkEmpty()){
                    if(!rePwd.getText().toString().trim().equals(re_login_repass.getText().toString().trim())){
                        T.showShort("两次输入的密码不一致，请重新输入");
                       return;
                    }
                    registerData();
                }
                break;
            case R.id.tv_Login :
                showLogin(1);
                break;
            case R.id.tv_register:
                showLogin(2);
                break;
            case R.id.getCode:
                getCode();
                break;
            case R.id.tv_save_pwd_re :
                T.showShort("kk");
                if(isCheck == true){
                    checkbox.setChecked(true);
//                    isCheck = false;
//                    PreferenceUtils.setPrefString(mContext,"pwd",loginPwd.getText().toString().trim());
                }else {
                    checkbox.setChecked(false);
//                    isCheck = true;
//                    PreferenceUtils.setPrefString(mContext,"pwd","");
                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        x.view().inject(this);
        SoftHideKeyBoardUtil.assistActivity(this);
        mContext = this;
        ImmersionBar.with(this).init();
        account.setText(PreferenceUtils.getPrefString(mContext,"account",""));
        loginPwd.setText(PreferenceUtils.getPrefString(mContext,"pwd",""));
    }


    //注册的非空验证
    boolean checkEmpty(){
        if(reAccount.getText().toString().trim().equals("")){
            T.showShort("用户名不可为空");
            return false;
        }
        if(reCode.getText().toString().trim().equals("")){
            T.showShort("验证码不可为空");
            return false;
        }
        if(rePwd.getText().toString().trim().equals("")){
            T.showShort("密码不可为空");
            return false;
        }
        if(re_login_repass.getText().toString().trim().equals("")){
            T.showShort("确认密码不可为空");
            return false;
        }
        if(!PhoneUtil.isPhoneNo(reAccount.getText().toString().trim())){
            T.showShort("请输入合法手机号");
            return false;
        }
        else{
            return true;
        }
    }


    //获取验证码
    private void getCode() {
        OkGo.<String>post(Constants.URL_BASE + "user/sms")//
                .tag(this)//
                .params("username", reAccount.getText().toString().trim())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            T.showShort(jsonObject.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showShort(response.toString());
                    }
                });
    }


    //控制页面展示
    private void showLogin(int type) {
        if(type ==1 ){
            login_layout.setVisibility(View.VISIBLE);
            register_layout.setVisibility(View.GONE);
            view_login.setVisibility(View.VISIBLE);
            view_register.setVisibility(View.GONE);
        }else {
            login_layout.setVisibility(View.GONE);
            register_layout.setVisibility(View.VISIBLE);
            view_login.setVisibility(View.GONE);
            view_register.setVisibility(View.VISIBLE);
        }

    }


    //注册
    private void registerData(){
        OkGo.<String>post(Constants.URL_BASE + "user/register")//
                .tag(this)//
//                .headers("Content-Type", "application/json")//
                .params("username", reAccount.getText().toString().trim())//
                .params("password", MD5Util.encrypt(rePwd.getText().toString().trim()))
                .params("passwordConfirm", MD5Util.encrypt(re_login_repass.getText().toString().trim()))//
                .params("smscode",reCode.getText().toString().trim())
                .params("inviteid",reAccountYq.getText().toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            T.showShort(jsonObject.getString("msg"));
                            if(jsonObject.getString("status").equals("200")){
                                showLogin(1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showShort(response.toString());
                    }
                });

    }

    //登录
    private void loginData(){
        OkGo.<String>post(Constants.URL_BASE + "user/login")//
                .tag(this)//
                .params("username", account.getText().toString())//
                .params("password",MD5Util.encrypt(loginPwd.getText().toString().trim()))
                .params("device", PhoneUtil.getSystemModel())//
                .params("ip",PhoneUtil.getHostIP())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//
                        Gson gson = new Gson();
                        UserLoginInfo userLoginInfoResp =gson.fromJson(data.toString(),UserLoginInfo.class);
                        T.showShort(userLoginInfoResp.getMsg());
                        if(userLoginInfoResp.getStatus() == 200){
                            saveUserLoginInfo(userLoginInfoResp);
                            finish();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showShort(response.toString());
                    }
                });

    }

    //保存登录信息
    private void saveUserLoginInfo(UserLoginInfo userLoginInfoResp) {
        PreferenceUtils.setPrefString(mContext,"token",userLoginInfoResp.getToken());
        PreferenceUtils.setPrefString(mContext,"account",account.getText().toString().trim());
        if(!checkbox.isChecked())
            PreferenceUtils.setPrefString(mContext,"pwd",loginPwd.getText().toString().trim());
        else
            PreferenceUtils.setPrefString(mContext,"pwd","");
        PreferenceUtils.setPrefBoolean(mContext,"login_status",true);
    }

    //验证token
    /*private void getMe(String token) {
        Log.i("OkGo",PreferenceUtils.getPrefString(mContext,"token","")+"lll");
        OkGo.<String>get(Constants.URL_BASE + "user/getMe")//
                .tag(this)//
                .headers("Authorization", "Bearer " + token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        T.showShort(data);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showShort(response.toString());
                    }
                });
    }*/


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //释放沉浸式Title,必须调用该方法，防止内存泄漏
    }

}
