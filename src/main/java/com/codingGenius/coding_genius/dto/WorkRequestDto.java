package com.codingGenius.coding_genius.dto;

import com.codingGenius.coding_genius.domain.Work;
import lombok.Getter;

@Getter
public class WorkRequestDto {

    private String todoName;

    private String workName;

    private boolean complete;
}
