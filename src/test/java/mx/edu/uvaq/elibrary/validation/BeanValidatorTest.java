package mx.edu.uvaq.elibrary.validation;

import org.hibernate.validator.constraints.NotBlank;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.Errors;

import javax.validation.constraints.NotNull;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: arcesino
 * Date: 1/1/12
 * Time: 9:53 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/mx/edu/uvaq/elibrary/spring/application-beans.xml"})
public class BeanValidatorTest {

  @Autowired
  private BeanValidator validator;

  @Test
  public void validationWorksAsExpected() {
    ValidationTarget target = new ValidationTarget();
    target.setId(1L);
    target.setValue("value");
    Errors errors = validator.validate(target);
    assertFalse(errors.hasErrors());
  }

  @Test
  public void validationErrorsRaiseWhenConstraintAreViolated() {
    ValidationTarget target = new ValidationTarget();
    Errors errors = validator.validate(target);
    assertTrue(errors.hasFieldErrors("id"));
    assertTrue(errors.hasFieldErrors("value"));
  }

//  @Test
//  public void validationAvoidedForExcludedFields() {
//    ValidationTarget target = new ValidationTarget();
//    Errors errors = validator.validate(target, "value");
//    assertTrue(errors.hasFieldErrors("id"));
//    assertFalse(errors.hasFieldErrors("value"));
//  }

  private class ValidationTarget {
    @NotNull
    private Long id;

    @NotBlank
    private String value;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }
  }
}
