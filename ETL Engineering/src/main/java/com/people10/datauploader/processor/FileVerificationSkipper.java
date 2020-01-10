package com.people10.datauploader.processor;


import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;


public class FileVerificationSkipper implements SkipPolicy {

    @Autowired
    private Environment environment;

    @Override
    public boolean shouldSkip(Throwable exception, int skipCount) throws SkipLimitExceededException {

//        int skipErrorsRecords = Integer.valueOf(environment.getProperty("max.error.record.count"));
        int skipErrorsRecords = 10;
        if (exception instanceof FileNotFoundException) {
            return false;
        } else if (exception instanceof FlatFileParseException && (skipErrorsRecords < 0 || skipCount <= (skipErrorsRecords-1))) {
            FlatFileParseException fileParseException = (FlatFileParseException) exception;
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append((skipCount+1)+", Error is found on the file, " + fileParseException.getLineNumber() + " records of the file. The Error records:\n");
            errorMessage.append(fileParseException.getInput() + "\n");
            return true;
        } else {
            return false;
        }
    }
}