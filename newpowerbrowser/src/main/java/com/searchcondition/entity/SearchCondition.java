package com.searchcondition.entity;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

@Entity
@Indexed
public class SearchCondition {

    @GeneratedValue
    @Id
    Long id;

    @Field(index = Index.YES)
    String content;
    @Enumerated(EnumType.STRING)
    ConditionType conditionType;

    public SearchCondition() {
    }

    public SearchCondition(String content, ConditionType conditionType) {
        this.content = content;
        this.conditionType = conditionType;
    }

    public String getContent() {
        return content;
    }

    public ConditionType getConditionType() {
        return conditionType;
    }
}
