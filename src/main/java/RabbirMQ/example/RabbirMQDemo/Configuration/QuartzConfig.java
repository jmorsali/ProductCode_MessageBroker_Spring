package RabbirMQ.example.RabbirMQDemo.Configuration;

import RabbirMQ.example.RabbirMQDemo.Component.DailyReportGeneratorJob;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
public class QuartzConfig {


    @Bean
    public JobDetail myJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(DailyReportGeneratorJob.class);
        factoryBean.setDescription("My Job Description");
        factoryBean.setDurability(true);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean
    public SimpleTrigger myTrigger(JobDetail myJobDetail) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(myJobDetail);
        factoryBean.setRepeatInterval(10000); //*60*60 Interval in milliseconds
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactory(JobDetail myJobDetail, SimpleTrigger myTrigger) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setJobDetails(myJobDetail);
        schedulerFactory.setTriggers(myTrigger);
        schedulerFactory.setApplicationContextSchedulerContextKey("applicationContext");
        return schedulerFactory;
    }
}
