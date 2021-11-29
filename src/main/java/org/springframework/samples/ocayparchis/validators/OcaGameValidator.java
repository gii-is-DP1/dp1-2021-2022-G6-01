package org.springframework.samples.ocayparchis.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.samples.ocayparchis.validators.OcaGameAnnotation.PlayersNumberConstraint;

public class OcaGameValidator implements ConstraintValidator<PlayersNumberConstraint, Integer> {
	@Override
	public void initialize(PlayersNumberConstraint playerNumber) {
	}
	@Override
	public boolean isValid(Integer players, ConstraintValidatorContext cxt) {
		return players != null&& (players>=2)&& (players<=4);
	}
}