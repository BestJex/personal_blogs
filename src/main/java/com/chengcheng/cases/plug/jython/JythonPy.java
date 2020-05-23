package com.chengcheng.cases.plug.jython;

import io.swagger.annotations.Api;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

// 参考: https://www.cnblogs.com/wuwuyong/p/10600749.html
@Api("使用Jython操作Python")
public class JythonPy {

	public static void main(String[] args) {

		// 1. 在Java类中直接执行Python语句
		/*PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.exec("word = 'hello world !'");
		interpreter.exec("print (word)");*/

		// 2. 在Java中直接调用Python脚本
//		PythonInterpreter interpreter = new PythonInterpreter();
//		interpreter.execfile( "D:\\IntelliJ IDEA\\ideaone\\personal-blogs\\src\\main\\java\\com\\chengcheng\\cases\\plug\\jython\\pyshell\\jython_one.py");

		// 3. 调用Python脚本中的函数
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.execfile("D:\\IntelliJ IDEA\\ideaone\\personal-blogs\\src\\main\\java\\com\\chengcheng\\cases\\plug\\jython\\pyshell\\jython_two.py");
		// 第一个参数为切望获得的函数(变量)的名字, 第二个参数为期望返回的对象类型
		PyFunction pyFunction = interpreter.get("add", PyFunction.class);
		int a = 5, b = 10;
		// 调用函数, 如果函数需要参数, 在Java中必须先将参数转化为对应的"Python类型"
		PyObject pyObject = pyFunction.__call__(new PyInteger(a), new PyInteger(b));
		System.out.println("the anwser is: " + pyObject);
	}
}
