package io.zbus.spring.boot.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Binds an {@link io.zbus.spring.boot.handler.EventHandler} to an Ant-style
 * event dispatch rule of the form {@code topic/tag/keys}, e.g.
 * {@code topic-a/tag-a/*}. The rule is used by the handler-chain resolver to
 * route incoming messages.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ZbusRule {

	/**
	 * @return the Ant-style dispatch rule (e.g. {@code topic-a/tag-a/*})
	 */
	String value();

}
