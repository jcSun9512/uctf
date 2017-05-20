package com.test.uctf.common;

import com.test.uctf.common.component.ComponentProcess;
import com.test.uctf.modal.Component;
import com.test.uctf.standart.BasePath;
import com.test.uctf.standart.Process;
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

    private List<Component> executeList = new ArrayList<Component>();

    private List<Component> checkList = new ArrayList<Component>();

    private List<Component> clearList = new ArrayList<Component>();

    @Autowired
    private List<ComponentProcess> processList;


    public ComponentFactory() {}

    public void load(String path) {
        this.path = path;
        File baseFile = new File(path);
        if (!baseFile.exists()) {
            LOGGER.error(String.format("未找到指定路径，path=[%s]", path));
            return;
        }
        loadFileTree(baseFile);
        split();
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
                    for(ComponentProcess process : processList) {
                        if(process.handle(method)) {
                            componentList.add(
                                    new Component(
                                            process.getProcess(), process.getId(method),
                                            process.getDescription(method), clazz, method, baseFile.getPath()));
                        }
                    }
                }


            } catch (ClassNotFoundException e) {
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

    private void split() {
        for(Component component : componentList) {
            if(component.getProcess() == Process.logic)
                logicList.add(component);
            if(component.getProcess() == Process.prepare)
                prepareList.add(component);
            if(component.getProcess() == Process.mock)
                mockList.add(component);
            if(component.getProcess() == Process.execute)
                executeList.add(component);
            if(component.getProcess() == Process.check)
                checkList.add(component);
            if(component.getProcess() == Process.clear)
                checkList.add(component);
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
     * Getter method for property processList.
     *
     * @return property value of processList
     */
    public List<ComponentProcess> getProcessList() {
        return processList;
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
     * Setter method for property processList.
     *
     * @param processList value to be assigned to property processList
     */
    public void setProcessList(List<ComponentProcess> processList) {
        this.processList = processList;
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
     * Getter method for property executeList.
     *
     * @return property value of executeList
     */
    public List<Component> getExecuteList() {
        return executeList;
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
}