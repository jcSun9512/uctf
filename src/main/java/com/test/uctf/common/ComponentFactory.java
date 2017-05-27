package com.test.uctf.common;

import com.test.uctf.modal.Component;
import com.test.uctf.standart.BasePath;
import com.test.uctf.standart.Process;
import com.test.uctf.standart.annotation.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
  * @author jiacai.sjc
  * @version $Id: ComponentFactory.java, v 0.1 2017-04-24 下午5:22 jiacai.sjc Exp $$
  */
public class ComponentFactory {

    private final static Logger LOGGER = Logger.getLogger(ComponentFactory.class);

    private String path;

    private List<Component> componentList = new ArrayList<Component>();

    private List<Component> logicList = new ArrayList<Component>();

    private List<Component> prepareList = new ArrayList<Component>();

    private List<Component> mockList = new ArrayList<Component>();

    private List<Component> checkList = new ArrayList<Component>();

    private List<Component> clearList = new ArrayList<Component>();

    private List<Component> aloneComponent = new ArrayList<Component>();


    public ComponentFactory() {}

    public void load(String path) {
        LOGGER.info("加载自定义组件, 扫描文件路径：" + path);
        this.path = path;
        File baseFile = new File(path);
        if (!baseFile.exists()) {
            LOGGER.error(String.format("未找到指定路径，path=[%s]", path));
            return;
        }
        loadFileTree(baseFile);
        LOGGER.info("加载自定义组件完成");
    }

    public Component getComponent(String id) {
        for(Component component : componentList) {
            if(component.getId().equals(id)) return component;
        }
        return null;
    }

    private void loadFileTree(File baseFile) {
        if (baseFile == null || !baseFile.exists()) return;
        if (baseFile.isFile() && baseFile.getName().endsWith(".java")) {
            try {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                String pack = baseFile.getAbsolutePath();
                pack = pack.split(BasePath.TEST_JAVA)[1].split("\\.")[0].replace("/", ".");
                Class<?> clazz = loader.loadClass(pack);

                Method[] methods = clazz.getMethods();
                if (methods == null) return;
                for (Method method : methods) {
                    Component component = null;
                    if(method.isAnnotationPresent(Logic.class)) {
                        Logic logic = method.getAnnotation(Logic.class);
                        component = new Component(
                                Process.logic, logic.id(), logic.description(),
                                clazz, method, pack);
                        logicList.add(component);
                        if(logic.alone()) aloneComponent.add(component);
                        componentList.add(component);
                    }
                    if(method.isAnnotationPresent(Prepare.class)) {
                        Prepare prepare = method.getAnnotation(Prepare.class);
                        component = new Component(
                                Process.prepare, prepare.id(), prepare.description(),
                                clazz, method, pack);
                        prepareList.add(component);
                        componentList.add(component);
                    }
                    if(method.isAnnotationPresent(Mock.class)) {
                        Mock mock = method.getAnnotation(Mock.class);
                        component = new Component(
                                Process.mock, mock.id(), mock.description(),
                                clazz, method, pack);
                        mockList.add(component);
                        componentList.add(component);
                    }
                    if(method.isAnnotationPresent(Check.class)) {
                        Check check = method.getAnnotation(Check.class);
                        component = new Component(
                                Process.check, check.id(), check.description(),
                                clazz, method, pack);
                        checkList.add(component);
                        componentList.add(component);
                    }
                    if(method.isAnnotationPresent(Clear.class)) {
                        Clear clear = method.getAnnotation(Clear.class);
                        component = new Component(
                                Process.clear, clear.id(), clear.description(),
                                clazz, method, pack);
                        clearList.add(component);
                        componentList.add(component);
                    }
                }
            } catch (Exception e) {
                LOGGER.info(String.format("解析失败：%s", baseFile.getName()), e);
            }
            return;
        }
        File[] list = baseFile.listFiles();
        if (list == null) return;
        for (File file : list) {
            loadFileTree(file);
        }
    }

    /**
     * Getter method for property path.
     *
     * @return property value of path
     */
    public String getPath() {
        return path;
    }

    /**
     * Getter method for property componentList.
     *
     * @return property value of componentList
     */
    public List<Component> getComponentList() {
        return componentList;
    }

    /**
     * Setter method for property path.
     *
     * @param path value to be assigned to property path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Setter method for property componentList.
     *
     * @param componentList value to be assigned to property componentList
     */
    public void setComponentList(List<Component> componentList) {
        this.componentList = componentList;
    }

    /**
     * Getter method for property logicList.
     *
     * @return property value of logicList
     */
    public List<Component> getLogicList() {
        return logicList;
    }

    /**
     * Getter method for property prepareList.
     *
     * @return property value of prepareList
     */
    public List<Component> getPrepareList() {
        return prepareList;
    }

    /**
     * Getter method for property mockList.
     *
     * @return property value of mockList
     */
    public List<Component> getMockList() {
        return mockList;
    }

    /**
     * Getter method for property checkList.
     *
     * @return property value of checkList
     */
    public List<Component> getCheckList() {
        return checkList;
    }

    /**
     * Getter method for property clearList.
     *
     * @return property value of clearList
     */
    public List<Component> getClearList() {
        return clearList;
    }

    /**
     * Getter method for property aloneComponent.
     *
     * @return property value of aloneComponent
     */
    public List<Component> getAloneComponent() {
        return aloneComponent;
    }
}