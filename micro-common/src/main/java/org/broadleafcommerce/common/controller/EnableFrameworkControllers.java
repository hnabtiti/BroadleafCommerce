/*
 * #%L
 * BroadleafCommerce Micro Common
 * %%
 * Copyright (C) 2009 - 2016 Broadleaf Commerce
 * %%
 * Licensed under the Broadleaf Fair Use License Agreement, Version 1.0
 * (the "Fair Use License" located  at http://license.broadleafcommerce.org/fair_use_license-1.0.txt)
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
 * #L%
 */
package org.broadleafcommerce.common.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.springframework.context.annotation.ComponentScan.Filter;

/**
 * Enables {@link FrameworkController} and {@link FrameworkRestController} annotations.
 * <p>
 * Scan all Broadleaf modules for {@link FrameworkController} and {@link FrameworkRestController} so that their {@link
 * org.springframework.web.bind.annotation.RequestMapping}s will get included in {@link
 * FrameworkControllerHandlerMapping} to provide default implementations of web endpoints.
 * <p>
 * If only some {@link FrameworkController}s are desired, then use {@link #excludeFilters()} to disable undesired
 * default controllers.
 * <p>
 * <b>DO NOT place this annotation on the same class as another {@link ComponentScan} or other annotations that compose
 * {@link ComponentScan} such as {@code @SpringBootApplication} as they will conflict when Spring performs annotation
 * composition.</b> Instead, you can create a nested class in your {@code @SprintBootApplication} class like this:
 * <pre>
 * {@code
 * @literal @EnableFrameworkControllers
 * public static class EnableBroadleafControllers {}
 * }
 * </pre>
 *
 * @author Philip Baggett (pbaggett)
 * @see FrameworkController
 * @see FrameworkRestController
 * @see FrameworkControllerHandlerMapping
 * @since 5.2
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan(
        useDefaultFilters = false,
        basePackages = {"org.broadleafcommerce", "com.broadleafcommerce"},
        includeFilters = @Filter({FrameworkController.class}))
public @interface EnableFrameworkControllers {

    /////////////////////////////////////////////////////////////////////////////////////
    //// BREAKING THIS CODE BECAUSE IT NEEDS TO BE ADDED TO 5.2 THEN MERGED INTO 6.0 ////
    //// ALSO JAVADOC NEEDS TO BE UPDATED ///////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    return false;

    /**
     * A set of {@link Filter}s that describe classes to exclude from component scanning.
     * <p>
     * This is most useful when you want to enable some framework controllers but exclude others. You can exclude
     * classes annotated with {@link FrameworkController} or {@link FrameworkRestController} by providing a filter like
     * {@code @EnableFrameworkControllers(excludeFilters = @Filter(value = DefaultCustomerController.class, type =
     * FilterType.ASSIGNABLE_TYPE))}
     *
     * @see ComponentScan#excludeFilters()
     * @see Filter
     */
    @AliasFor(annotation = ComponentScan.class, attribute = "excludeFilters")
    Filter[] excludeFilters() default {};
}
