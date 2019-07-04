package restapi.valid;

import restapi.valid.groups.ExtendedValidation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * Priority of validation annotations.
 */
@GroupSequence({Default.class, ExtendedValidation.class})
public interface ConstraintSequence {
}
