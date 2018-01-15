
package org.mengyun.tcctransaction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 事务补偿注解.
 * Created by changmingxie on 10/25/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Compensable {

    //提交方法
    public String confirmMethod() default "";

    //回滚方法
    public String cancelMethod() default "";
}