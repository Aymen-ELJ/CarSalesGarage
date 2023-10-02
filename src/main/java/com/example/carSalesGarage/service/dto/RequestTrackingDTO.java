package com.example.carSalesGarage.service.dto;

import com.example.carSalesGarage.domain.enumeration.SuccessStatus;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

public class RequestTrackingDTO {
    private Long id;

    private String methodName;

    private String inputParameters;

    private String output;

    @NotNull
    private Long processingTimeMillis;

    @NotNull
    private LocalDateTime callingDateTime;

    @NotNull
    private SuccessStatus successStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getInputParameters() {
        return inputParameters;
    }

    public void setInputParameters(String inputParameters) {
        this.inputParameters = inputParameters;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Long getProcessingTimeMillis() {
        return processingTimeMillis;
    }

    public void setProcessingTimeMillis(Long processingTimeMillis) {
        this.processingTimeMillis = processingTimeMillis;
    }

    public LocalDateTime getCallingDateTime() {
        return callingDateTime;
    }

    public void setCallingDateTime(LocalDateTime callingDateTime) {
        this.callingDateTime = callingDateTime;
    }

    public SuccessStatus getSuccessStatus() {
        return successStatus;
    }

    public void setSuccessStatus(SuccessStatus successStatus) {
        this.successStatus = successStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestTrackingDTO that = (RequestTrackingDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(methodName, that.methodName) && Objects.equals(inputParameters, that.inputParameters) && Objects.equals(output, that.output) && processingTimeMillis.equals(that.processingTimeMillis) && callingDateTime.equals(that.callingDateTime) && successStatus == that.successStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, methodName, inputParameters, output, processingTimeMillis, callingDateTime, successStatus);
    }

    @Override
    public String toString() {
        return "RequestTrackingDTO{" +
                "id=" + id +
                ", methodName='" + methodName + '\'' +
                ", inputParameters='" + inputParameters + '\'' +
                ", output='" + output + '\'' +
                ", processingTimeMillis=" + processingTimeMillis +
                ", callingDateTime=" + callingDateTime +
                ", successStatus=" + successStatus +
                '}';
    }
}
