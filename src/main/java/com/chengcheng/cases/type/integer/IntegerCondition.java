package com.chengcheng.cases.type.integer;

/**
 *  演示: 三目运算符 condition? 表达式1: 表达式2中, 高度注意表达式1和 2 在类型对齐时, 可能抛出因自动拆箱导致的NPE异常.
 */

public class IntegerCondition {

    Integer a = 1;
    Integer b = 2;
    Integer c = null;

    Boolean flag = false;

    // a*b 的结果是int类型, 那么c会强hi拆箱成int类型, 抛出NPE异常.
    Integer result = (flag? a*b : c);


}
