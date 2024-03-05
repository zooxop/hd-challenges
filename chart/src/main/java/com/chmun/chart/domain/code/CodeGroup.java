package com.chmun.chart.domain.code;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "code_group")
public class CodeGroup {
    @Id
    @Column(name = "code_group", nullable = false, length = 10)
    private String codeGroup;

    @Column(name = "code_group_name", nullable = false, length = 10)
    private String codeGroupName;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @OneToMany(mappedBy = "codeGroup", cascade = CascadeType.ALL)
    private Set<Code> codeSet;

    public CodeGroup() {

    }

    public CodeGroup(String codeGroup, String codeGroupName, String description, Set<Code> codeSet) {
        this.codeGroup = codeGroup;
        this.codeGroupName = codeGroupName;
        this.description = description;
        this.codeSet = codeSet;
    }

    public String getCodeGroup() {
        return codeGroup;
    }

    public String getCodeGroupName() {
        return codeGroupName;
    }

    public String getDescription() {
        return description;
    }

    public Set<Code> getCodeSet() {
        return codeSet;
    }
}
