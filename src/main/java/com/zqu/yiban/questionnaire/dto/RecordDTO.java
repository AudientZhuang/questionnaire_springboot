package com.zqu.yiban.questionnaire.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RecordDTO {
    @JsonProperty(value = "sNo")
    private String sNo;
    @JsonProperty(value = "tNo")
    private String tNo;
    @JsonProperty(value = "tName")
    private String tName;
    @JsonProperty(value = "tType")
    private Integer tType;
    @JsonProperty(value = "answer")
    private List<AnswerDTO> answer;
}
