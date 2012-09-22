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
public class HelloJob2 implements Job {

	static Log logger = LogFactory.getLog(HelloJob2.class);

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		logger.info("job2 start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
        logger.info("job2 end");
    }
}
