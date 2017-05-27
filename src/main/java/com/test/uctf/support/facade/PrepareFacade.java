package com.test.uctf.support.facade;

import com.test.uctf.standart.BasePath;
import com.test.uctf.util.YamlUtil;

import java.util.HashMap;
import java.util.Map;

/**
  * @author jiacai.sjc
  * @version $Id: PrepareFacade.java, v 0.1 2017-05-22 下午4:35 jiacai.sjc Exp $$
  */
public class PrepareFacade {

    /**
     * 用于通过数据文件准备数据的接口
     * @param path 提供文件路径, 与src/test/resources/相对的相对路径。
     * @param key 用于选择yaml文件中对应的数据。
     * @param params 提供用于渲染
     * @return
     */
    public static <T> T prepareObject(String path, String key, Map<String, Object> params) {
        if(params == null) params = new HashMap<String, Object>();
        path = BasePath.TEST_RESOURCES + path;
        return (T)YamlUtil.loadYaml(path, key, params);
    }

    public static void prepareDB(String path, String key, Map<String, Object> params) {
        if(params == null) params = new HashMap<String, Object>();
    }
}