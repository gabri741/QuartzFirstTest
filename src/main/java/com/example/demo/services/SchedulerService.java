package com.example.demo.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.info.TimerInfo;
import com.example.demo.util.TimerUtils;

@Service
public class SchedulerService {
	
	private final Scheduler scheduler ; 
	
	private static final Logger LOG  = LoggerFactory.getLogger(SchedulerService.class);	

	
	@Autowired
	public SchedulerService(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	@PostConstruct
	public void init() {
		try {
			scheduler.start();
		}catch(SchedulerException e) {
			LOG.error(e.getMessage(), e);	
		}
		
	}
	
	@PreDestroy
	public void preDestroy() {
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			LOG.error(e.getMessage(),e);
		}
	}

	public void schedule(final Class jobClass, final TimerInfo info) {
		final JobDetail jobDetail = TimerUtils.buildJobDetail(jobClass, info);
		final Trigger trigger = TimerUtils.buildTrigger(jobClass, info);
		
		try {
			scheduler.scheduleJob(jobDetail,trigger);
		}catch(SchedulerException e ){
			LOG.error(e.getMessage(), e);
		}
		
		
		
	}
}
