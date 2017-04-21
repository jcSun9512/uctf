package com.test.uctf.util;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.StringReader;
import java.util.Map;

/**
  * @author jiacai.sjc
  * @version $Id: YamlUtil.java, v 0.1 2017-04-21 下午2:31 jiacai.sjc Exp $$
  */
public class YamlUtil {
    private static final Logger LOGGER = Logger.getLogger(YamlUtil.class);

    /**
     * 从文件中读取对象，需要指定文件路径，文件中数据的下标，参数替换时的数据集
     * @param filePath 文件路径，需要指定一个相对工程的路径
     * @param key 指定文件中的数据下标
     * @param paramMap 指定对读取数据中进行替换是的数据集
     * @param <T>
     * @return
     */
    public static <T> T loadYaml(String filePath, String key, Map<String, Object> paramMap) {
        File file = new File(filePath);
        if(!file.exists()) {
            LOGGER.debug(String.format("yaml文件不存在，文件路径=[%s]", filePath, key));
            return null;
        }
        return loadYaml(file, key, paramMap);
    }

    /**
     * 从文件中读取对象，需要指定文件路径，文件中数据的下标，参数替换时的数据集
     * @param file 指定读取的文件
     * @param key 指定文件中的数据下标
     * @param paramMap 指定对读取数据中进行替换是的数据集
     * @param <T>
     * @return
     */
    public static <T> T loadYaml(File file, String key, Map<String, Object> paramMap) {
        StringReader reader = new StringReader(VelocityUtil.evaluateFile(file, paramMap));
        Yaml yaml = new Yaml();
        Map<String, Object> result = (Map<String, Object>) yaml.load(reader);
        for(Map.Entry entry : result.entrySet()) {
            if(entry.getKey().equals(key)) {
                return (T) entry.getValue();
            }
        }
        return null;
    }


}