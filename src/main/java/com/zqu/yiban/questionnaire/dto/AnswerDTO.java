package com.zqu.yiban.questionnaire.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AnswerDTO {
    @JsonProperty(value = "qNo")
    private String qNo;
    @JsonProperty(value = "qAnswer")
    private Integer qAnswer;
}