package org.springframework.samples.ocayparchis.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

public class OcaGameAnnotation {
	@Documented
	@Constraint(validatedBy = OcaGameValidator.class)
	@Target( { ElementType.METHOD, ElementType.FIELD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface PlayersNumberConstraint {
	String message() default "el número de jugadores debe estar entre 2 y 4";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	}

}
