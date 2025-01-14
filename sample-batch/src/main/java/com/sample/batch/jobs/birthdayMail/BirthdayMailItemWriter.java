package com.sample.batch.jobs.birthdayMail;

import com.sample.batch.context.BatchContext;
import com.sample.batch.jobs.BaseItemWriter;
import com.sample.common.util.ListUtils;
import com.sample.domain.dao.system.SendMailQueueDao;
import com.sample.domain.dto.system.SendMailQueue;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BirthdayMailItemWriter extends BaseItemWriter<SendMailQueue> {

  @Autowired SendMailQueueDao sendMailQueueDao;

  @Override
  protected void doWrite(BatchContext context, List<SendMailQueue> items) {
    if (log.isDebugEnabled()) {
      log.debug("{} items to write.", ListUtils.size(items));
    }

    sendMailQueueDao.insert(items);
  }
}
