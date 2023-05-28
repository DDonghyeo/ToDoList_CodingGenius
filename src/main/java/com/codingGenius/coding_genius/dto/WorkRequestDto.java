package com.codingGenius.coding_genius.dto;

import com.codingGenius.coding_genius.domain.Work;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WorkRequestDto {

    private String todoName;

    private String workName;

    private String memo;

    public WorkRequestDto(WorkUpdateDto workUpdateDto){
        this.todoName = workUpdateDto.getTodoName();
        this.workName = workUpdateDto.getNewName();
        this.memo = workUpdateDto.getMemo();
    }
}
