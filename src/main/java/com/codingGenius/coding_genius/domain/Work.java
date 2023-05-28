package com.codingGenius.coding_genius.domain;

import com.codingGenius.coding_genius.dto.WorkRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
public class Work {

    private String name;

    @Setter
    private boolean complete;

    private String memo;

    public Work(WorkRequestDto workRequestDto) {
        this.name = workRequestDto.getWorkName();
        this.complete = false;
        this.memo = workRequestDto.getMemo();
    }
}
