package com.paypal.infrastructure.job.listener;

import com.paypal.infrastructure.model.entity.JobExecutionInformationEntity;
import com.paypal.infrastructure.model.job.JobStatus;
import com.paypal.infrastructure.repository.JobExecutionInformationRepository;
import com.paypal.infrastructure.util.DateUtil;
import com.paypal.infrastructure.util.TimeMachine;
import org.quartz.*;
import org.quartz.listeners.JobListenerSupport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Listener that populates information data in executed jobs
 */
@Service
public class JobExecutionInformationListener extends JobListenerSupport {

	public static final String RUNNING_JOB_ENTITY = "runningInstanceId";

	@Resource
	protected JobExecutionInformationRepository jobExecutionInformationRepository;

	@Resource
	private Scheduler scheduler;

	@Override
	public String getName() {
		return "jobDeltaListener";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void jobToBeExecuted(final JobExecutionContext context) {
		getLog().info("Started [{}] at {}", context.getJobDetail().getKey().getName(), LocalDateTime.now());
		saveStartJobExecutionInformation(context);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void jobWasExecuted(final JobExecutionContext context, final JobExecutionException jobException) {
		getLog().info("Finished [{}] at {}", context.getJobDetail().getKey().getName(), LocalDateTime.now());
		saveExecutedJobExecutionInformation(context);
	}

	/**
	 * Stores the {@link JobExecutionInformationEntity} with {@code lastExecutionTime} as
	 * {@link TimeMachine#now()} and {@code job} as the simple name of the job running
	 * @param context the {@link JobExecutionContext}
	 */
	protected void saveStartJobExecutionInformation(final JobExecutionContext context) {
		final JobExecutionInformationEntity jobExecutionInformationEntity = new JobExecutionInformationEntity();
		jobExecutionInformationEntity.setType(context.getJobDetail().getJobClass().getSimpleName());
		jobExecutionInformationEntity.setName(context.getJobDetail().getKey().getName());
		jobExecutionInformationEntity.setStartTime(DateUtil.convertToDate(TimeMachine.now(), ZoneId.systemDefault()));
		jobExecutionInformationEntity.setStatus(JobStatus.RUNNING);

		final var savedInformation = jobExecutionInformationRepository.save(jobExecutionInformationEntity);

		final var jobDataMap = context.getJobDetail().getJobDataMap();
		jobDataMap.put(RUNNING_JOB_ENTITY, savedInformation);

		final JobDetail jobDetail = context.getJobDetail().getJobBuilder().usingJobData(jobDataMap).build();
		//@formatter:on
		try {
			scheduler.addJob(jobDetail, true);
		}
		catch (final SchedulerException e) {
			getLog().error(e.getMessage(), e);
		}
	}

	protected void saveExecutedJobExecutionInformation(final JobExecutionContext context) {
		final var savedInformation = (JobExecutionInformationEntity) context.getJobDetail().getJobDataMap()
				.get(RUNNING_JOB_ENTITY);
		savedInformation.setEndTime(DateUtil.convertToDate(TimeMachine.now(), ZoneId.systemDefault()));
		savedInformation.setStatus(JobStatus.COMPLETED);
		jobExecutionInformationRepository.save(savedInformation);
	}

}
