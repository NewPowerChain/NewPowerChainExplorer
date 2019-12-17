package com.reptile;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;

import java.lang.reflect.Type;

public class FeignFormSupportEncoder implements Encoder {
    @Override
    public void encode(Object o, Type type, RequestTemplate rt) throws EncodeException {
        StringBuffer sb = new StringBuffer();
        String oStr = JSONObject.toJSONString(o);
        JSONObject jsonObject = JSON.parseObject(oStr);
        for (String s : jsonObject.keySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb
                    .append(s)
                    .append("=")
                    .append(jsonObject.getString(s));
        }

        rt.header("Content-Type", "application/x-www-form-urlencoded");
        rt.body(sb.toString());
    }
}
