package com.chmun.chart.domain.code;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeGroupRepository extends JpaRepository<CodeGroup, String> {
    CodeGroup findAllByCodeGroup(String codeGroup);
}
