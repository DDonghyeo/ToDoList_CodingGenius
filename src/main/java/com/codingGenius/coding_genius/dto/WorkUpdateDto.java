package com.codingGenius.coding_genius.dto;

import lombok.Getter;

@Getter
public class WorkUpdateDto {

    private String todoName;

    private String oldName;

    private String newName;

    private boolean complete;

    private String memo;
}
