package com.people10.datauploader.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CustomerController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;


    @GetMapping("/home")
    public String showHome(){

        return "customers";
    }


    @GetMapping("/save")
    @ResponseBody
    public String saveStudents() throws Exception{

        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(job, jobParameters);


        return "You succesfully saved customers in the Database!";
    }
}
