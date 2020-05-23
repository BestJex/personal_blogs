package com.chengcheng.cases.type.switchcase;

/**
 *  演示: 当switch括号内的变量类型为String并且此变量为外部参数时, 必须先进行null判断.
 */
public class SwitchDemo {
    public static void method(String param) {
        switch (param) {
            // 肯定不是进入这里
            case "sth":
                System.out.println("it's sth");
                break;
            // 也不是进入这里
            case "null":
                System.out.println("it's null");
                break;
            // 也不是进入这里
            default:
                System.out.println("default");
        }
    }

    /* 测试 */
    public static void main(String[] args) {
        method(null);  // 直接null会报错, 防止报错要先进行null值判断.
    }

}
