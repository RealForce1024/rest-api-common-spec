package com.realforce1024.restspec.common.validator;

import jakarta.validation.groups.Default;

/**
 * @author 编程燃风 RealForce1024
 */
public interface ValidatorGroup extends Default {
    interface CrudValidatorGroup extends ValidatorGroup {
        interface CreateGroup extends CrudValidatorGroup {
        }

        interface RetriveGroup extends CrudValidatorGroup {
        }

        interface UpdateGroup extends CrudValidatorGroup {
        }

        interface DeleteGroup extends CrudValidatorGroup {
        }

    }
}
