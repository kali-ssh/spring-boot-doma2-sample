package com.sample.domain.repository.system;

import static com.sample.domain.util.DomaUtils.createSelectOptions;
import static java.util.stream.Collectors.toList;

import com.sample.domain.dao.system.CodeCategoryDao;
import com.sample.domain.dto.system.CodeCategory;
import com.sample.domain.dto.system.CodeCategoryCriteria;
import com.sample.domain.exception.NoDataFoundException;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/** コード分類リポジトリ */
@RequiredArgsConstructor
@Repository
public class CodeCategoryRepository {

  @NonNull final CodeCategoryDao codeCategoryDao;

  /**
   * コード分類を全件取得します。
   *
   * @return
   */
  public List<CodeCategory> fetchAll() {
    val pageable = Pageable.unpaged();
    val options = createSelectOptions(pageable).count();
    return codeCategoryDao.selectAll(new CodeCategoryCriteria(), options, toList());
  }

  /**
   * コード分類を複数取得します。
   *
   * @param criteria
   * @param pageable
   * @return
   */
  public Page<CodeCategory> findAll(CodeCategoryCriteria criteria, Pageable pageable) {
    val options = createSelectOptions(pageable).count();
    val data = codeCategoryDao.selectAll(criteria, options, toList());
    return new PageImpl<>(data, pageable, options.getCount());
  }

  /**
   * コード分類を取得します。
   *
   * @param criteria
   * @return
   */
  public Optional<CodeCategory> findOne(CodeCategoryCriteria criteria) {
    return codeCategoryDao.select(criteria);
  }

  /**
   * コード分類を取得します。
   *
   * @return
   */
  public CodeCategory findById(final Long id) {
    return codeCategoryDao
        .selectById(id)
        .orElseThrow(() -> new NoDataFoundException("codeCategory_id=" + id + " のデータが見つかりません。"));
  }

  /**
   * コード分類を追加します。
   *
   * @param inputCodeCategory
   * @return
   */
  public CodeCategory create(final CodeCategory inputCodeCategory) {
    codeCategoryDao.insert(inputCodeCategory);
    return inputCodeCategory;
  }

  /**
   * コード分類を更新します。
   *
   * @param inputCodeCategory
   * @return
   */
  public CodeCategory update(final CodeCategory inputCodeCategory) {
    int updated = codeCategoryDao.update(inputCodeCategory);

    if (updated < 1) {
      throw new NoDataFoundException(
          "code_category_id=" + inputCodeCategory.getId() + " のデータが見つかりません。");
    }

    return inputCodeCategory;
  }

  /**
   * コード分類を論理削除します。
   *
   * @return
   */
  public CodeCategory delete(final Long id) {
    val codeCategory =
        codeCategoryDao
            .selectById(id)
            .orElseThrow(
                () -> new NoDataFoundException("code_category_id=" + id + " のデータが見つかりません。"));

    int updated = codeCategoryDao.delete(codeCategory);

    if (updated < 1) {
      throw new NoDataFoundException("code_category_id=" + id + " は更新できませんでした。");
    }

    return codeCategory;
  }
}
