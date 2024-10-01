package com.vernon.poppy.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "hogwarts_test_task")
public class HogwartsTestTask extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 运行测试的Jenkins服务器id
     */
    @Column(name = "test_jenkins_id")
    private Long testJenkinsId;

    /**
     * Jenkins的构建url
     */
    @Column(name = "build_url")
    private String buildUrl;

    /**
     * 创建人id
     */
    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 用例数量
     */
    @Column(name = "case_count")
    private Long caseCount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 任务类型 1 执行测试任务 2 一键执行测试的任务
     */
    @Column(name = "task_type")
    private Byte taskType;

    /**
     * 状态 0 无效 1 新建 2 执行中 3 执行完成
     */
    private Byte status;

    /**
     * Jenkins执行测试时的命令脚本
     */
    @Column(name = "test_command")
    private String testCommand;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取任务名称
     *
     * @return name - 任务名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置任务名称
     *
     * @param name 任务名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取运行测试的Jenkins服务器id
     *
     * @return test_jenkins_id - 运行测试的Jenkins服务器id
     */
    public Long getTestJenkinsId() {
        return testJenkinsId;
    }

    /**
     * 设置运行测试的Jenkins服务器id
     *
     * @param testJenkinsId 运行测试的Jenkins服务器id
     */
    public void setTestJenkinsId(Long testJenkinsId) {
        this.testJenkinsId = testJenkinsId;
    }

    /**
     * 获取Jenkins的构建url
     *
     * @return build_url - Jenkins的构建url
     */
    public String getBuildUrl() {
        return buildUrl;
    }

    /**
     * 设置Jenkins的构建url
     *
     * @param buildUrl Jenkins的构建url
     */
    public void setBuildUrl(String buildUrl) {
        this.buildUrl = buildUrl;
    }

    /**
     * 获取创建人id
     *
     * @return create_user_id - 创建人id
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建人id
     *
     * @param createUserId 创建人id
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取用例数量
     *
     * @return case_count - 用例数量
     */
    public Long getCaseCount() {
        return caseCount;
    }

    /**
     * 设置用例数量
     *
     * @param caseCount 用例数量
     */
    public void setCaseCount(Long caseCount) {
        this.caseCount = caseCount;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取任务类型 1 执行测试任务 2 一键执行测试的任务
     *
     * @return task_type - 任务类型 1 执行测试任务 2 一键执行测试的任务
     */
    public Byte getTaskType() {
        return taskType;
    }

    /**
     * 设置任务类型 1 执行测试任务 2 一键执行测试的任务
     *
     * @param taskType 任务类型 1 执行测试任务 2 一键执行测试的任务
     */
    public void setTaskType(Byte taskType) {
        this.taskType = taskType;
    }

    /**
     * 获取状态 0 无效 1 新建 2 执行中 3 执行完成
     *
     * @return status - 状态 0 无效 1 新建 2 执行中 3 执行完成
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态 0 无效 1 新建 2 执行中 3 执行完成
     *
     * @param status 状态 0 无效 1 新建 2 执行中 3 执行完成
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取Jenkins执行测试时的命令脚本
     *
     * @return test_command - Jenkins执行测试时的命令脚本
     */
    public String getTestCommand() {
        return testCommand;
    }

    /**
     * 设置Jenkins执行测试时的命令脚本
     *
     * @param testCommand Jenkins执行测试时的命令脚本
     */
    public void setTestCommand(String testCommand) {
        this.testCommand = testCommand;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}