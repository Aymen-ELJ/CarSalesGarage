package com.example.carSalesGarage.tracking;


import com.example.carSalesGarage.domain.enumeration.SuccessStatus;
import com.example.carSalesGarage.service.RequestTrackingService;
import com.example.carSalesGarage.service.dto.RequestTrackingDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private RequestTrackingService requestTrackingService;


    @Around("execution(* com.example.carSalesGarage.resource.*.*(..))")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnedValue;
        RequestTrackingDTO requestTrackingToSave = new RequestTrackingDTO();
        requestTrackingToSave.setCallingDateTime(LocalDateTime.now());
        requestTrackingToSave.setInputParameters(Arrays.asList(joinPoint.getArgs()).toString());
        requestTrackingToSave.setMethodName(joinPoint.getSignature().getName());

        try{
            long startTime = System.currentTimeMillis();
            returnedValue = joinPoint.proceed();
            long endTime = System.currentTimeMillis();

            requestTrackingToSave.setSuccessStatus(SuccessStatus.OK);
            requestTrackingToSave.setProcessingTimeMillis(endTime - startTime);
            if(returnedValue instanceof ResponseEntity){
                requestTrackingToSave.setOutput(((ResponseEntity<?>) returnedValue).getBody().toString());
            }else {
                requestTrackingToSave.setOutput(returnedValue.toString());
            }
            requestTrackingService.save(requestTrackingToSave);
        } catch (Exception ex) {
            requestTrackingToSave.setSuccessStatus(SuccessStatus.KO);
            requestTrackingToSave.setProcessingTimeMillis(0L);
            requestTrackingToSave.setOutput(ex.getMessage());
            requestTrackingService.save(requestTrackingToSave);
            throw ex;
        }
        return returnedValue;
    }

}
