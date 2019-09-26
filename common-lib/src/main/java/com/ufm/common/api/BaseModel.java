package com.ufm.common.api;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName BaseModel
 * @Description 基础模型 实现tostring转换为json格式
 * @Author 胡蓝天
 * @Date 2019/8/29 2:19 下午
 * @Version 1.0
 */
public class BaseModel implements Serializable {

    @Override
    public String toString() {
        return JSONObject.toJSONString(this, SerializerFeature.WriteMapNullValue);
    }

    /**
      * entity与pojo之间的相互转换
      * @Date 2019/9/26 9:54
      * @Params obj
      * @Return T
      */
    public <T> T covert(T obj) {
        if (obj == null) {
            return null;
        }
        BeanUtils.copyProperties(this, obj);
        return obj;
    }
}
