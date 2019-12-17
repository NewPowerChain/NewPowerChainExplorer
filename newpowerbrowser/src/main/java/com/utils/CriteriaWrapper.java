package com.utils;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Thoughtworks on 16/10/6.
 */
public class CriteriaWrapper<T> {
    Predicate predict;
    List<Expression<Boolean>> expressions;
    Root<T> root;
    CriteriaBuilder criteriaBuilder;
    CriteriaQuery<?> criteriaQuery;

    public CriteriaWrapper(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        this.predict = criteriaBuilder.conjunction();
        this.expressions = this.predict.getExpressions();
        this.root = root;
        this.criteriaBuilder = criteriaBuilder;
        this.criteriaQuery = criteriaQuery;
        criteriaQuery.where(this.predict);
    }

    /**
     * 按照page信息从list中提取page
     * @param list
     * @param pageable
     * @param <T>
     * @return
     */
//    public static <T> Page<T> extractListToPage(List<T> list, Pageable pageable) {
//        int begin = pageable.getOffset();
//        int end = pageable.getOffset() + pageable.getPageSize();
//        if (end > list.size()) {
//            end = list.size();
//        }
//        List result = list.subList(begin, end);
//        return new PageImpl<T>(result, pageable, list.size());
//    }

    /**
     * 将整个list包装成page
     * @param list
     * @param pageable
     * @param <T>
     * @return
     */
    public static <T> Page<T> wrapperListToPage(List<T> list, Pageable pageable) {
        return new PageImpl<T>(list, pageable, list.size());
    }


    Expression<?> getValue(String... values) {
        Path<?> p = root.get(values[0]);
        for (int i = 1; i < values.length; i++) {
            p = p.get(values[i]);
        }
        return p;
    }

    public <R> void orEq(Expression expression, List<R> values){
        int i = 0;
        Predicate p = criteriaBuilder.or(criteriaBuilder.equal(expression, values.get(i++)));
        while(i < values.size()){
            p = criteriaBuilder.or(p, criteriaBuilder.equal(expression, values.get(i++)));
        }
        expressions.add(p);
    }

    public <R> void orNotEq(Expression expression, List<R> values) {
        int i = 0;
        Predicate p = criteriaBuilder.and(criteriaBuilder.notEqual(expression, values.get(i++)));
        while(i < values.size()){
            p = criteriaBuilder.and(p, criteriaBuilder.notEqual(expression, values.get(i++)));
        }
        expressions.add(p);
    }

    public <R> void andEq(Expression expression, List<R> values){
        int i = 0;
        Predicate p = criteriaBuilder.or(criteriaBuilder.equal(expression, values.get(i++)));
        while(i < values.size()){
            p = criteriaBuilder.or(p, criteriaBuilder.equal(expression, values.get(i++)));
        }
        expressions.add(p);
    }
    public <R> void expEq(Expression expression, R value) {
        expressions.add(criteriaBuilder.equal(expression, value));
    }

    public <R> void eq(R value, String... values) {
        expressions.add(criteriaBuilder.equal(getValue(values), value));
    }

    public <R> void noEq(R value, String... values) {
        expressions.add(criteriaBuilder.notEqual(getValue(values), value));
    }

    public void expLike(Expression<String> expression, String value) {
        expressions.add(criteriaBuilder.like(expression, "%" + value + "%"));
    }

    public void like(String value, String keyword) {
        expressions.add(criteriaBuilder.like(root.get(keyword), "%" + value + "%"));
    }

    public void noLike(String value, String keyword) {
        expressions.add(criteriaBuilder.notLike(root.get(keyword), "%" + value + "%"));
    }

    public void le(Number value, String str) {
        expressions.add(criteriaBuilder.le(root.get(str), value));
    }

    public void ge(Number value, String str) {
        expressions.add(criteriaBuilder.ge(root.get(str), value));
    }

    public void between(Number begin, Number end, String str) {
        if (begin != null) {
            le(begin, str);
        }
        if (null != end) {
            ge(end, str);
        }
    }

    public void dateBetween(Date begin, Date end, String str) {
        expressions.add(criteriaBuilder.between(root.get(str), begin, end));
    }

    public Predicate getRestriction() {
        return criteriaQuery.getRestriction();
    }

    public void orderBy(OrderOption type, String... values) {
        if (type == OrderOption.desc) {
            this.criteriaQuery.orderBy(criteriaBuilder.desc(getValue(values)));
        } else if (type == OrderOption.asc) {
            this.criteriaQuery.orderBy(criteriaBuilder.asc(getValue(values)));
        }
    }

    public void orderByExpress(OrderOption type, Expression<?> expression) {
        if (type == OrderOption.desc) {
            this.criteriaQuery.orderBy(criteriaBuilder.desc(expression));
        } else if (type == OrderOption.asc) {
            this.criteriaQuery.orderBy(criteriaBuilder.asc(expression));
        }
    }

    public <Y extends Comparable<? super Y>> void greaterThanOrEqualTo(Y value, String... values) {
        expressions.add(criteriaBuilder.greaterThanOrEqualTo((Expression<? extends Y>)getValue(values), value));
    }

    public <Y extends Comparable<? super Y>>  void lessThanOrEqualTo(Y value, String... values) {
        expressions.add(criteriaBuilder.lessThanOrEqualTo((Expression<? extends Y>) getValue(values), value));
    }

    /**
     * @lss
     * @param begin
     * @param end
     * @param Object
     * @param str
     */
    public void dateBetweenManyObject(Date begin, Date end, Path<T> Object, String str) {
        expressions.add(criteriaBuilder.between(Object.get(str), begin, end));
    }

    public enum OrderOption {
        asc,
        desc,
    }

    public <R> void orLike(List<Expression<String>> expression, String value) {
        int i = 0;
        Predicate p = criteriaBuilder.or(criteriaBuilder.like(expression.get(i++), "%" + value + "%"));
        while (i < expression.size()) {
            p = criteriaBuilder.or(p, criteriaBuilder.like(expression.get(i++), value));
        }
    }
}
