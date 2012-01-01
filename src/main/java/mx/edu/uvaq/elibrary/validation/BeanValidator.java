package mx.edu.uvaq.elibrary.validation;

import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by IntelliJ IDEA.
 * User: arcesino
 * Date: 12/31/11
 * Time: 8:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class BeanValidator {
  private Validator validatorImpl;

  public void setValidatorImpl(Validator validatorImpl) {
    this.validatorImpl = validatorImpl;
  }

  public Errors validate(Object target) {
    DataBinder binder = new DataBinder(target, target.getClass().getSimpleName());
    binder.setValidator(validatorImpl);
    binder.validate();
    return binder.getBindingResult();
  }
}
