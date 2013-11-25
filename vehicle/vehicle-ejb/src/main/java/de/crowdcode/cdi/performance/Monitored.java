package de.crowdcode.cdi.performance;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;

@InterceptorBinding
@Target({METHOD,TYPE, FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Monitored {

}
