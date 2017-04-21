package com.test.uctf.util;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.*;
import java.util.Map;

/**
  * @author jiacai.sjc
  * @version $Id: VelocityUtil.java, v 0.1 2017-04-21 下午3:02 jiacai.sjc Exp $$
  */
public class VelocityUtil {
    private static final Logger LOGGER = Logger.getLogger(VelocityUtil.class);

    public static String evaluate(String template, Map<String, Object> paramMap) {
        try{
            StringWriter writer = new StringWriter();
            VelocityContext context = new VelocityContext(paramMap);
            Velocity.evaluate(context, writer, "", template);
            return writer.toString();
        } catch (Exception e) {
            LOGGER.debug(String.format("使用velocity渲染字符串失败，字符串=[%s]", template), e);
            return template;
        }
    }

    public static String evaluateFile(File file, Map<String, Object> paramMap) {
        try{
            StringWriter writer = new StringWriter();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            VelocityContext context = new VelocityContext(paramMap);
            Velocity.evaluate(context, writer, "", reader);
            return writer.toString();
        } catch (Exception e) {
            LOGGER.debug(String.format("使用velocity渲染文件失败，字符串=[%s]", file.getAbsolutePath()), e);
            return null;
        }
    }
}