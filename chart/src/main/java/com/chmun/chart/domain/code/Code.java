package com.chmun.chart.domain.code;

import jakarta.persistence.*;

@Entity
@Table(name = "code")
public class Code {

    @Id
    @Column(name = "code", nullable = false, length = 10)
    private String code;

    @Column(name = "code_name", nullable = false, length = 10)
    private String codeName;

    @ManyToOne
    @JoinColumn(name = "code_group", nullable = false)
    private CodeGroup codeGroup;

    public Code() {

    }

    public Code(String code, String codeName, CodeGroup codeGroup) {
        this.code = code;
        this.codeName = codeName;
        this.codeGroup = codeGroup;
    }

    public String getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public CodeGroup getCodeGroup() {
        return codeGroup;
    }
}
