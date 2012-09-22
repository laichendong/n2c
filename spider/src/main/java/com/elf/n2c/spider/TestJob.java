package com.elf.n2c.spider;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
@Service 
public class TestJob {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TestJob.class);
	@Scheduled(cron="0/1 * * * * *")
	public void work()
    {
		if (logger.isDebugEnabled()) {
			logger.info("work() - start"); //$NON-NLS-1$
		}
		logger.info("Spring Quartz的TestJob任务被调用！"); 
		if (logger.isDebugEnabled()) {
			logger.debug("work() - end"); //$NON-NLS-1$
		}
    } 
}