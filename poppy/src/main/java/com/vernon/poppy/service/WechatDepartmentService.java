package com.vernon.poppy.service;

import com.jayway.jsonpath.DocumentContext;
import com.vernon.poppy.dto.DepartmentDTO;
import com.vernon.poppy.service.impl.BaseWechatApiImpl;

public abstract class WechatDepartmentService extends BaseWechatApiImpl {

    // 创建部门
    public abstract DocumentContext createDepartment(DepartmentDTO departmentDTO);

    // 获取子部门ID列表
    public abstract DocumentContext getSimpleList(DepartmentDTO departmentDTO);

    // 删除部门
    public abstract DocumentContext delDepartment(DepartmentDTO departmentDTO);
}
