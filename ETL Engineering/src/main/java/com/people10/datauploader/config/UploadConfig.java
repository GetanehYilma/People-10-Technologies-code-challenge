package com.people10.datauploader.config;


import com.people10.datauploader.domain.Customer;
import com.people10.datauploader.listener.BatchCompletionListener;
import com.people10.datauploader.processor.CustomerItemProcessor;
import com.people10.datauploader.processor.FileVerificationSkipper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

/*this class defines the location of file, map the content to our model, verify its content, preprocess if necessary
* and configures how to upload and insert to our customers database */

@Configuration
@EnableBatchProcessing
public class UploadConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Bean
    public FlatFileItemReader<Customer> reader() {
        FlatFileItemReader<Customer> reader = new FlatFileItemReader<Customer>();
        reader.setResource(new ClassPathResource("data1.csv"));
        reader.setLineMapper(new DefaultLineMapper<Customer>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "signUpDate", "first","last","email", "latitude", "longitude", "ip" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {{
                setTargetType(Customer.class);
            }});
        }});
        return reader;
    }

    @Bean
    public CustomerItemProcessor processor(){
        return new CustomerItemProcessor();
    }

    @Bean
    public FileVerificationSkipper fileVerificationSkipper(){
        return new FileVerificationSkipper();
    }

    @Bean
    public JdbcBatchItemWriter<Customer> writer() {
        JdbcBatchItemWriter<Customer> writer = new JdbcBatchItemWriter<Customer>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Customer>());
        writer.setSql("INSERT INTO customers (sign_up_date, first, last, email, latitude, longitude, ip) VALUES (:signUpDate, :first, :last, :email, :latitude, :longitude, :ip)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Job importUserJob(BatchCompletionListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer> chunk(50)
                .reader(reader())
                .faultTolerant()
                .skipPolicy(fileVerificationSkipper())
                .processor(processor())
                .writer(writer())
                .build();
    }

//    @Bean
//    public Step skippingStep(
//            ItemProcessor<Customer, Customer> processor,
//            ItemWriter<Customer> writer) throws Exception {
//        return stepBuilderFactory
//                .get("skippingStep")
//                .<Customer, Customer>chunk(10)
////                .reader(itemReader(invalidInputCsv))
//                .reader(reader())
//                .processor(processor)
//                .writer(writer)
//                .faultTolerant()
//                .skipLimit(7)
//                .skip(Exception.class)
////                .skip(NegativeAmountException.class)
//                .build();
//    }
}
