package com.primeton.tip.endpoint.ws.async.handlers.pool;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChannelHandlerPooling {
	String name() default "";

	int initial() default 10;

	int min() default 10;

	int max() default Integer.MAX_VALUE;

	Class<?> poolingProvider() default Object.class;
}
