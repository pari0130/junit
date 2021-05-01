package com.study.junit;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Study {
    private StudyStatus status1 = StudyStatus.DRAFT;
    private StudyStatus status2;
    private int limit;
    private String name;

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
}
