package excel;

/**
 * 代码是一点点码起来的 Codeing
 * <p>
 * User: AugusDuan
 * Data: 2018/10/30
 * Time: 16:35
 * Proj: java-study
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelName {
    String name() default "";
}
