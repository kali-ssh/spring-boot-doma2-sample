package com.sample.domain.service.system;

import com.sample.domain.dto.system.MailTemplate;
import com.sample.domain.dto.system.MailTemplateCriteria;
import com.sample.domain.repository.system.MailTemplateRepository;
import com.sample.domain.service.BaseTransactionalService;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/** メールテンプレートサービス */
@RequiredArgsConstructor
@Service
public class MailTemplateService extends BaseTransactionalService {

  @NonNull final MailTemplateRepository mailTemplateRepository;

  /**
   * メールテンプレートを複数取得します。
   *
   * @return
   */
  @Transactional(readOnly = true) // 読み取りのみの場合は指定する
  public Page<MailTemplate> findAll(MailTemplateCriteria criteria, Pageable pageable) {
    Assert.notNull(criteria, "criteria must not be null");
    return mailTemplateRepository.findAll(criteria, pageable);
  }

  /**
   * メールテンプレートを取得します。
   *
   * @return
   */
  @Transactional(readOnly = true)
  public Optional<MailTemplate> findOne(MailTemplateCriteria criteria) {
    Assert.notNull(criteria, "criteria must not be null");
    return mailTemplateRepository.findOne(criteria);
  }

  /**
   * メールテンプレートを取得します。
   *
   * @return
   */
  @Transactional(readOnly = true)
  public MailTemplate findById(final Long id) {
    Assert.notNull(id, "id must not be null");
    return mailTemplateRepository.findById(id);
  }

  /**
   * メールテンプレートを追加します。
   *
   * @param inputMailTemplate
   * @return
   */
  public MailTemplate create(final MailTemplate inputMailTemplate) {
    Assert.notNull(inputMailTemplate, "inputMailTemplate must not be null");
    return mailTemplateRepository.create(inputMailTemplate);
  }

  /**
   * メールテンプレートを更新します。
   *
   * @param inputMailTemplate
   * @return
   */
  public MailTemplate update(final MailTemplate inputMailTemplate) {
    Assert.notNull(inputMailTemplate, "inputMailTemplate must not be null");
    return mailTemplateRepository.update(inputMailTemplate);
  }

  /**
   * メールテンプレートを論理削除します。
   *
   * @return
   */
  public MailTemplate delete(final Long id) {
    Assert.notNull(id, "id must not be null");
    return mailTemplateRepository.delete(id);
  }
}
