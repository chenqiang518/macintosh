package com.vernon.poppy.dto;

import lombok.Data;

@Data
public class DepartmentDTO {

    private String name;
    private String nameEn;
    private int parentId;
    private int order;
    private int id;

    public DepartmentDTO() {}

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "name='" + name + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", parentId=" + parentId +
                ", order=" + order +
                ", id=" + id +
                '}';
    }
}
