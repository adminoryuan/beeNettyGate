package com.bee.untils;

import com.bee.exec.ExecContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yh
 * @date 2022/10/14 下午1:54
 */
public class SpringBeanUntils {
    private static ApplicationContext context;

    public static void load()  {
        context=new ClassPathXmlApplicationContext("springConfig.xml");
    }

    public static <T> T getBean(Class<T> cls){
        return context.getBean(cls);
    }


}
