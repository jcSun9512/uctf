package com.test.uctf.support.facade;

import com.alibaba.fastjson.JSON;
import com.test.uctf.standart.BasePath;
import com.test.uctf.util.ReflectUtil;
import com.test.uctf.util.YamlUtil;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author jiacai.sjc
  * @version $Id: CheckFacade.java, v 0.1 2017-05-23 下午3:10 jiacai.sjc Exp $$
  */
public class CheckFacade {

    /**
     * 用于获取期望值
     * @param path 提供文件路径, 与src/test/resources/相对的相对路径。
     * @param key 用于选择yaml文件中对应的数据。
     * @param params 提供用于渲染
     * @return
     */
    public static Map<String, Object> getExceptionObject(String path, String key, Map<String, Object> params) {
        if(params == null) params = new HashMap<String, Object>();
        path = BasePath.TEST_RESOURCES + path;
        Map<String, Object> result = YamlUtil.loadYaml(path, key, params);
        return result;
    }

    public static void check(Object actualObj, Map<String, Object> expectObj) {
        for(Map.Entry<String, Object> entry : expectObj.entrySet()) {
            checkSingle(actualObj, entry.getKey(), entry.getValue());
        }
    }

    public static void checkSingle(Object actualObj, String key, Object expectObj) {
        if(key == null || key.length() == 0) return;
        /**
         * 0: 没有检测器
         * U: 无序 list
         * O: 有序 list
         */
        char flag = 0;
        if(key.endsWith(">")) {
            int length = key.length();
            flag = key.charAt(length-2);
            key = key.substring(0, length-3);
        }

        Object actual = ReflectUtil.getFieldValue(actualObj, key);

        if(flag == 'M') {
            Assert.assertEquals(JSON.toJSON(actual).toString(), JSON.toJSON(expectObj).toString());
        } else {
            if(expectObj instanceof Map) {
                Map<Object, Object> map = (Map<Object, Object>) expectObj;
                boolean isString = true;
                for(Map.Entry<Object, Object> entry : map.entrySet()) {
                    if(entry.getKey() instanceof String);
                    else isString = false;
                }

                if(isString) {
                    for(Map.Entry<Object, Object> entry : map.entrySet()) {
                        checkSingle(actual, (String)entry.getKey(), entry.getValue());
                    }
                } else {
                    Assert.assertEquals(JSON.toJSON(actual).toString(), JSON.toJSON(expectObj).toString());
                }
            } else if(expectObj instanceof List) {
                List<Object> actaulList = (List<Object>) actual;
                List<Object> expectList = (List<Object>) expectObj;

                if(flag == 'O') {
                    for(int i = 0; i < expectList.size(); i++) {
                        Assert.assertEquals(JSON.toJSON(
                                actaulList.get(i)).toString(), JSON.toJSON(expectList.get(i)).toString());

                    }
                } else if(flag == 'U') {
                    for(int i = 0; i < expectList.size(); i++) {
                        boolean result = false;
                        for(int j = 0; j < actaulList.size(); i++) {
                            if(!JSON.toJSON(expectList.get(i)).toString().equals(
                                    JSON.toJSON(actaulList.get(i)).toString())) {
                                result = true;
                                break;
                            }
                        }

                        if(!result) {
                            Assert.assertTrue(false, String.format("期望值不存在，期望值=[%s]", JSON.toJSON(expectList.get(i)).toString()));
                        }
                    }
                } else {
                    Assert.assertEquals(JSON.toJSON(actual).toString(), JSON.toJSON(expectObj).toString());
                }

            } else {
                Assert.assertEquals(JSON.toJSON(actual).toString(), JSON.toJSON(expectObj).toString());
            }
        }
    }

}