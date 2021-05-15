package com.study.junit.domain;

import com.study.junit.study.StudyStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Study {
    private StudyStatus status1 = StudyStatus.DRAFT;
    private StudyStatus status2;
    private StudyStatus status;
    private int limit;
    private String name;
    private Member owner;
    private LocalDateTime openedDateTime;

    public Study(int limit) {
        if(limit < 0){
          throw new IllegalArgumentException("limit 은 0보다 커야한다.");
        }

        this.limit = limit;
    }

    public Study(int limit, String name) {
        if(limit < 0){
            throw new IllegalArgumentException("limit 은 0보다 커야한다.");
        }

        this.limit = limit;
        this.name = name;
    }

    public void open(){
        this.openedDateTime = LocalDateTime.now();
        this.status = StudyStatus.OPENED;
    }
}
