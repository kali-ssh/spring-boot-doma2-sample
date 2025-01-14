package com.sample.web.base.controller.html;

import static com.sample.web.base.WebConst.MAV_ERRORS;

import com.sample.common.FunctionNameAware;
import com.sample.web.base.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/** 基底HTMLコントローラー */
@Slf4j
public abstract class AbstractHtmlController extends BaseController implements FunctionNameAware {

  /**
   * 入力チェックエラーがある場合はtrueを返します。
   *
   * @param model
   * @return
   */
  public boolean hasErrors(Model model) {
    val errors = model.asMap().get(MAV_ERRORS);

    if (errors instanceof BeanPropertyBindingResult br) {
      if (br.hasErrors()) {
        return true;
      }
    }

    return false;
  }

  /**
   * リダイレクト先に入力エラーを渡します。
   *
   * @param attributes
   * @param result
   */
  public void setFlashAttributeErrors(RedirectAttributes attributes, BindingResult result) {
    attributes.addFlashAttribute(MAV_ERRORS, result);
  }
}
