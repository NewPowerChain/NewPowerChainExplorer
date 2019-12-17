package com.entity;

import javax.persistence.*;

@Entity
@Table(name = "COMPONENT_TYPE")
public class Component {
    @GeneratedValue
    @Id
    Long id ;
    @Column(name = "PROJECT_CODE")
    String projectCode;
    @Column(name = "ASSEMBLY_COUNT")
    Integer assemblyCount;
    @Column(name = "GROUP_COUNT")
    Integer groupCount;

    public Long getId() {
        return id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public Integer getAssemblyCount() {
        return assemblyCount;
    }

    public Integer getGroupCount() {
        return groupCount;
    }
}
