package com.vernon.poppy.service;

import com.jayway.jsonpath.DocumentContext;
import com.vernon.poppy.dto.DepartmentDTO;

public interface WechatDepartmentService {

    // 创建部门
    DocumentContext createDepartment(DepartmentDTO departmentDTO, String accessToken);

    // 获取子部门ID列表
    DocumentContext getSimpleList(DepartmentDTO departmentDTO, String accessToken);

    // 删除部门
    DocumentContext delDepartment(DepartmentDTO departmentDTO , String accessToken);
}
