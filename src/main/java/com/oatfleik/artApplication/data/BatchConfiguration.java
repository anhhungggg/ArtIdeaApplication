package com.oatfleik.artApplication.data;

import com.oatfleik.artApplication.model.Art;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    private final String[] FIELD_NAMES = new String[] {"objectNumber","isHighlight","isTimelineWork","isPublicDomain","id","galleryNumber","department","accessionYear","objectName","title","culture","period","dynasty","reign","portfolio","constituentId","artistRole","artistPrefix","artistDisplayName","artistDisplayBio","artistSuffix","artistAlphaSort","artistNationality","artistBeginDate","artistEndDate","artistGender","artistULANUrl","artistWikidataUrl","objectDate","objectBeginDate","objectEndDate","medium","dimensions","creditLine","geographyType","city","state","county","country","region","subregion","locale","locus","excavation","river","classification","rightsAndReproduction","linkResource","objectWikidataUrl","metadataDate","repository","tags","tagsAATUrl","tagsWikidataUrl"};
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<ArtInput> reader() {
        return new FlatFileItemReaderBuilder<ArtInput>()
                .name("artItemReader")
                .resource(new ClassPathResource("MetObjects.csv"))
                .delimited()
                .names(FIELD_NAMES)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<ArtInput>() {{
                    setTargetType(ArtInput.class);
                }})
                .build();
    }

    @Bean
    public ArtProcessor processor() {
        return new ArtProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Art> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Art>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO ART (id, is_highlight, is_timeline_work, is_public_domain, object_name, title, link_resource, artist_display_name)" +
                        " VALUES (:id, :isHighlight, :isTimelineWork, :isPublicDomain, :objectName, :title, :linkResource, :artistDisplayName)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Art> writer) {
        return stepBuilderFactory.get("step1")
                .<ArtInput, Art> chunk(10)
                .reader(reader())
                .faultTolerant()
                .skipLimit(1)
                .skip(FlatFileParseException.class)
                .processor(processor())
                .writer(writer)
                .build();
    }
}
