package com.test.uctf.modal;

import com.test.uctf.util.YamlUtil;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
  * @author jiacai.sjc
  * @version $Id: TestCase.java, v 0.1 2017-04-21 下午1:47 jiacai.sjc Exp $$
  */
public class TestCase {

    private static final Logger LOGGER = Logger.getLogger(TestCase.class);

    /** *yaml文件属性 */
    private String dataId;

    /** *yaml文件属性 */
    private String logicId;

    /** *yaml文件属性 */
    private Map<String, Object> dataSource;

    /** *yaml文件属性 */
    private String description;

    private String caseId;

    private Component logic;

    private String path;

    public TestCase(){}

    /**
     * 根据path，重新读取数据。
     */
    public TestCase reLoad() {

        if(dataId == null || dataId.length() == 0) return this;
        try {
            File file = new File(path);
            if(!file.exists()) {
                throw new RuntimeException(String.format("testCase重载失败，文件不存在，文件路径=[%s]", path));
            }
            List<TestCase> list = YamlUtil.loadTestCase(file);
            if(list == null) {
                throw new RuntimeException(String.format("testCase重载失败，获取testCase失败，文件路径=[%s]", path));
            }

            for(TestCase testCase : list) {
                if(testCase.getDataId().equals(dataId) && testCase.getLogicId().equals(logicId)) {
                    testCase.setPath(this.path);
                    testCase.setLogic(this.logic);
                    testCase.setCaseId(this.caseId);
                    return testCase;
                }
            }
            throw new RuntimeException(String.format("testCase重载失败，testCase不存在，文件路径=[%s]", path));

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return this;
    }

    /**
     * Getter method for property dataId.
     *
     * @return property value of dataId
     */
    public String getDataId() {
        return dataId;
    }

    /**
     * Getter method for property logicId.
     *
     * @return property value of logicId
     */
    public String getLogicId() {
        return logicId;
    }

    /**
     * Getter method for property dataSource.
     *
     * @return property value of dataSource
     */
    public Map<String, Object> getDataSource() {
        return dataSource;
    }

    /**
     * Getter method for property description.
     *
     * @return property value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter method for property caseId.
     *
     * @return property value of caseId
     */
    public String getCaseId() {
        return caseId;
    }

    /**
     * Getter method for property logic.
     *
     * @return property value of logic
     */
    public Component getLogic() {
        return logic;
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
     * Setter method for property dataId.
     *
     * @param dataId value to be assigned to property dataId
     */
    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    /**
     * Setter method for property logicId.
     *
     * @param logicId value to be assigned to property logicId
     */
    public void setLogicId(String logicId) {
        this.logicId = logicId;
    }

    /**
     * Setter method for property dataSource.
     *
     * @param dataSource value to be assigned to property dataSource
     */
    public void setDataSource(Map<String, Object> dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Setter method for property description.
     *
     * @param description value to be assigned to property description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter method for property caseId.
     *
     * @param caseId value to be assigned to property caseId
     */
    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    /**
     * Setter method for property logic.
     *
     * @param logic value to be assigned to property logic
     */
    public void setLogic(Component logic) {
        this.logic = logic;
    }

    /**
     * Setter method for property path.
     *
     * @param path value to be assigned to property path
     */
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "dataId='" + dataId + '\'' +
                ", logicId='" + logicId + '\'' +
                ", dataSource=" + dataSource +
                ", description='" + description + '\'' +
                ", caseId='" + caseId + '\'' +
                ", logic=" + logic +
                ", path='" + path + '\'' +
                '}';
    }
}