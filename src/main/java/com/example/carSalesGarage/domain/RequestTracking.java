package com.example.carSalesGarage.domain;


import com.example.carSalesGarage.domain.enumeration.SuccessStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "request_tracking")
public class  RequestTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "method_name")
    @NotNull
    private String methodName;

    @Column(name = "inputs", columnDefinition = "TEXT")
    private String inputParameters;

    @Column(name = "outputs", columnDefinition = "TEXT")
    private String output;

    @Column(name = "processing_time_millis")
    @NotNull
    private Long processingTimeMillis;

    @Column(name = "date")
    @NotNull
    private LocalDateTime callingDateTime;

    @Column(name = "success_status")
    @Enumerated(EnumType.STRING)
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
        RequestTracking that = (RequestTracking) o;
        return Objects.equals(id, that.id) && Objects.equals(methodName, that.methodName) && Objects.equals(inputParameters, that.inputParameters) && Objects.equals(output, that.output) && processingTimeMillis.equals(that.processingTimeMillis) && callingDateTime.equals(that.callingDateTime) && successStatus == that.successStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, methodName, inputParameters, output, processingTimeMillis, callingDateTime, successStatus);
    }

    @Override
    public String toString() {
        return "RequestTracking{" +
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
