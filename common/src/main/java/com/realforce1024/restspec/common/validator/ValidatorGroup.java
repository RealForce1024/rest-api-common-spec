package com.realforce1024.restspec.common.validator;


/**
 * @author 编程燃风 RealForce1024
 */
public interface ValidatorGroup  {
    interface CrudGroup extends ValidatorGroup{
        interface Create extends CrudGroup{
        }

        interface Retrive extends CrudGroup{
        }

        interface Update extends CrudGroup{
        }

        interface Delete extends CrudGroup{
        }
    }
}
