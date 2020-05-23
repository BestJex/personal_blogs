package com.chengcheng.cases.type.enumtype.caseone;

/**
 *  测试
 */
public class JudgeRoleTest {

	public String judge( String roleName ) {
		// 一行代码搞定！之前的if/else灰飞烟灭
		return RoleEnum.valueOf(roleName).op();
	}

	public static void main(String[] args) {
		JudgeRoleTest judgeRoleTest = new JudgeRoleTest();
		// 这里可以输入三种定义的枚举类型来测试
		String role_root_admin = judgeRoleTest.judge("ROLE_ROOT_ADMIN");
		System.out.println(role_root_admin);
	}

}
