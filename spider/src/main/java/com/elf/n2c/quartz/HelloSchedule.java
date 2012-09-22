package com.elf.n2c.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;



/**
 * User: laichendong
 * Date: 12-9-5
 * Time: 下午3:53
 */
public class HelloSchedule {
	static Log logger = LogFactory.getLog(HelloSchedule.class);

	public static void main(String[] args) {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			JobDetail jobDetail = JobBuilder.newJob()
					.ofType(HelloJob.class)
					.withIdentity("helloJob")
					.build();

            JobDetail jobDetail2 = JobBuilder.newJob()
					.ofType(HelloJob2.class)
					.withIdentity("helloJob2")
					.build();

			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("helloJobTrigger1")
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(1)
						.repeatForever())
					.build();

			Trigger trigger2 = TriggerBuilder.newTrigger()
					.withIdentity("helloJobTrigger2")
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(1)
						.repeatForever())
					.build();

			scheduler.scheduleJob(jobDetail, trigger);
//			scheduler.scheduleJob(jobDetail2, trigger2);

			scheduler.start();
			logger.info("Scheduler started at " + new Date());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
