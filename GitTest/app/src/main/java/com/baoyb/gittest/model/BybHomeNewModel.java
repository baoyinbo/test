package com.baoyb.gittest.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baoyb on 2017/3/16.
 */
/**
 * result": [
 {
 "atitle": "普京露面破外媒失踪谣言",
 "content": "<em>普京</em>自本月5日会见来访的意大利总理伦齐后,便未再公开露面。11日,俄发布俄罗斯、哈萨克斯坦、白俄罗斯三国领导人会晤推迟举行的消息,西方媒体借此猜测会晤推迟原因是\"<em>普京</em>生病\"。俄方予以否认,坚称<em>普京</em>身体状况\"绝对健康\"。13日,俄总统新闻秘书佩斯科夫否认了关于<em>普京</em>\"新添...",
 "img_width": "",
 "full_title": "普京露面破外媒失踪谣言",
 "pdate": "39分钟前",
 "src": "光明网",
 "img_length": "",
 "img": "",
 "url": "http://world.gmw.cn/newspaper/2015-03/17/content_105205337.htm",
 "pdate_src": "2015-03-17 10:55:00"
 },
 */

public class BybHomeNewModel implements Serializable {
    private String ret;
    private List<BybHomeNewsModel> data;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public List<BybHomeNewsModel> getData() {
        return data;
    }

    public void setData(List<BybHomeNewsModel> data) {
        this.data = data;
    }
}
