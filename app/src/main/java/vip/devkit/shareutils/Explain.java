package vip.devkit.shareutils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import cn.k_tools.shareutils.R;
import vip.devkit.library.DESUtil;
import vip.devkit.library.DateUtil;
import vip.devkit.library.Logcat;

/**
 * 作者 by K
 * 时间：on 2017/1/12 11:03
 * 邮箱 by vip@devkit.vip
 * <p/>
 * <p/>
 * <p/>
 * 这个库参考了众多网络的中的代码,在此对这些无私奉献的人致以最诚挚的感谢。
 * <p/>
 * <p/>
 * <p/>
 * License
 * <p/>
 * Copyright (C)  2016 android@19code.com
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class Explain extends Activity {
    EditText mEditText;
    TextView mTextView;
    String enData=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logcat.init(true,"App");
        final String DES_DATA = "wewewewescvzcasdfcasd";
        final String DES_KEY = "20170616 16:06:02";
        mEditText = (EditText) findViewById(R.id.et_a);
        mTextView = (TextView) findViewById(R.id.tv);
        Date date = new Date();
        Logcat.i("当前时间："+DateUtil.date2Str2(date));
        Logcat.i(DateUtil.getDataCompare(date,"d")+"/"+"123".getBytes());
        findViewById(R.id.btn_jm1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Logcat.i(mTextView.getText().toString().trim());
                    System.out.println("加密前的数据："+DES_DATA);
                    System.out.println("DES开始加密......");

                     enData=DESUtil.encryptBASE64(DESUtil.encrypt(DES_DATA.getBytes(), DES_KEY.getBytes()));
                    System.out.println("加密后的数据"+enData);

                    System.out.println("DES开始解密.....");
                    String deData=new String(DESUtil.decrypt(DESUtil.decryptBASE64("GnvAZKLuVd0="), DES_KEY.getBytes()));
                    System.out.println("DES解密后的数据:"+deData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btn_jm2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Logcat.i(enData+"-------------------");
                    System.out.println("DES开始解密.....");
                    String deData=new String(DESUtil.decrypt(DESUtil.decryptBASE64("GnvAZKLuVd0="), DES_KEY.getBytes()));
                    Logcat.i("DES解密后的数据 - 用户名 :"+deData);
                    String deData2=new String(DESUtil.decrypt(DESUtil.decryptBASE64("p8C3jWzRb/dYGsTr7nJ9QrW/AVuuHzUNJXqEj5cYcFnBWTFsJhT6/g=="), "GnvAZKLuVd0=".getBytes()));
                    Logcat.i("DES解密后的数据 - 密码 :"+deData2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}