package com.example.demo.playground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.info.TimerInfo;
import com.example.demo.jobs.HelloWorldJob;
import com.example.demo.services.SchedulerService;

@Service
public class PlaygroundService {
	private final SchedulerService scheduler;
	
	@Autowired
	public PlaygroundService(final SchedulerService scheduler) {
		this.scheduler = scheduler;
	}
	
	public void runHelloWorld() {
		TimerInfo info = new TimerInfo();
		
		info.setTotalFireCount(5);
		info.setRepeatIntervalMs(2000);
		info.setInitialOffsetMs(1000);
		info.setCallbackData("My callback data");
		
		scheduler.schedule(HelloWorldJob.class, info);
		
	}
}
