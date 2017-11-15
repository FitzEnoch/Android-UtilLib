package vip.devkit.library;
/**
 * ****************************** Copyright (c)*********************************\
 * *
 * *                 (c) Copyright 2017, DevKit.vip, china, qd. sd
 * *                          All Rights Reserved
 * *
 * *                           By(K)
 * *
 * *-----------------------------------版本信息------------------------------------
 * * 版    本: V0.1
 * *
 * *------------------------------------------------------------------------------
 * *******************************End of Head************************************\
 */

import android.annotation.SuppressLint;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * 文 件 名: WebViewManager
 * 创 建 人: By k
 * 创建日期: 2017/11/1 10:26
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：WebView管理器，提供常用设置
 */
public class WebViewManager {
    private WebView webView;
    private WebSettings webSettings;

    /**
     * @param webView
     */
    public WebViewManager(WebView webView){
        this.webView = webView;
        webSettings = webView.getSettings();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
    }

    /**
     * 开启自适应功能
     */
    public WebViewManager enableAdaptive(){
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        return this;
    }

    /**
     * 禁用自适应功能
     */
    public WebViewManager disableAdaptive(){
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        return this;
    }

    /**
     * 开启缩放功能
     */
    public WebViewManager enableZoom(){
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        return this;
    }

    /**
     * 禁用缩放功能
     */
    public WebViewManager disableZoom(){
        webSettings.setSupportZoom(false);
        webSettings.setUseWideViewPort(false);
        webSettings.setBuiltInZoomControls(false);
        return this;
    }

    /**
     * 开启JavaScript
     */
    @SuppressLint("SetJavaScriptEnabled")
    public WebViewManager enableJavaScript(){
        webSettings.setJavaScriptEnabled(true);
        return this;
    }

    /**
     * 禁用JavaScript
     */
    public WebViewManager disableJavaScript(){
        webSettings.setJavaScriptEnabled(false);
        return this;
    }

    /**
     * 开启JavaScript自动弹窗
     */
    public WebViewManager enableJavaScriptOpenWindowsAutomatically(){
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        return this;
    }

    /**
     * 禁用JavaScript自动弹窗
     */
    public WebViewManager disableJavaScriptOpenWindowsAutomatically(){
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        return this;
    }

    /**
     * 返回
     * @return true：已经返回，false：到头了没法返回了
     */
    public boolean goBack(){
        if(webView.canGoBack()){
            webView.goBack();
            return true;
        }else{
            return false;
        }
    }
}
