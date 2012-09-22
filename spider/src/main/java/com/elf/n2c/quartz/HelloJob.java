package com.elf.n2c.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * User: laichendong
 * Date: 12-9-5
 * Time: 下午3:06
 */
//@DisallowConcurrentExecution
public class HelloJob implements Job {

	static Log logger = LogFactory.getLog(HelloJob.class);

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		logger.info("job1 start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
        logger.info("job1 end");
	}
}
