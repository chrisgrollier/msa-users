package net.chrisgrollier.cloud.apps.sample.user.internal.validation.constraintvalidators;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.chrisgrollier.cloud.apps.sample.user.validation.constraints.ValueOfEnum;

/**
 * A validator for {@link ValueOfEnum} annotation.
 * 
 * @author Atos
 *
 */
public final class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {

	private List<String> acceptedValues;

	@Override
	public void initialize(ValueOfEnum annotation) {
		acceptedValues = Stream.of(annotation.enumClass().getEnumConstants()).map(Enum::name)
				.collect(Collectors.toList());
	}

	@Override
	public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return acceptedValues.contains(value.toString());
	}
}