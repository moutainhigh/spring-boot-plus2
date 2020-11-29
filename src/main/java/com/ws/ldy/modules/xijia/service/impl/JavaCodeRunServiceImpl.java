package com.ws.ldy.modules.xijia.service.impl;

import com.ws.ldy.common.utils.DynamicLoader;
import com.ws.ldy.modules.xijia.service.JavaCodeRunService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * java代码运行器
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/11/29 0029 10:45
 * @version 1.0.0
 */
@Service
public class JavaCodeRunServiceImpl implements JavaCodeRunService {

    /**
     * 这里会抛出警告内容，如下，可不必理会
     * 警告: Can't initialize javac processor due to (most likely) a class loader problem: java.lang.NoClassDefFoundError: com/sun/tools/javac/processing/JavacProcessingEnvironment
     */
    @Override
    public Object invoke(String javaSrc) {
        // 获取类名
        String className = javaSrc.substring(0, javaSrc.indexOf("{"));
        className = className.replace("public class", "").trim();
        // 动态生成类
        Map<String, byte[]> bytecode = DynamicLoader.compile(className + ".java", javaSrc);
        Method main = null;
        try {
            // 获取动态生成的类
            DynamicLoader.MemoryClassLoader classLoader = new DynamicLoader.MemoryClassLoader(bytecode);
            Class clazz = classLoader.loadClass(className);
            // 获取main方法, 并随意设置一个默认参数，然后通过invoke调用
            main = clazz.getMethod("main", String[].class);
        } catch (Exception e) {
            return "编译错误,请仔细检查代码";
        }
        // 获得控制台内容
        PrintStream oldPrintStream = System.out; // 将原来的System.out交给printStream 对象保存
        ByteArrayOutputStream bos = new ByteArrayOutputStream();   // 设置新的out
        System.setOut(new PrintStream(bos));
        try {
            // 执行
            String[] args = new String[]{"20211290"};
            main.invoke(null, (Object) args);
            // 获取动态执行代码打印的内容
            System.setOut(oldPrintStream);
            // 返回执行中打印的内容
            return bos.toString();
        } catch (Exception e) {
            // 错误类名
            String exceptionClass = e.getCause().getClass().getName();
            // String exceptionClassName = exceptionClass.substring(exceptionClass.lastIndexOf(".") + 1, exceptionClass.length());
            // 详细错误信息
            StringBuffer errorDesc = new StringBuffer();
            errorDesc.append("\r\n异常类:" + exceptionClass + "\r\n异常信息：" + e.getCause().toString() + "\r\n详细错误内容:\r\n");
            if (e.getCause().getStackTrace() != null) {
                Arrays.stream(e.getCause().getStackTrace()).forEach(i -> errorDesc.append(i.toString() + "\r\n"));
            }
            // 返回异常信息
            return errorDesc;
        }
    }
}
