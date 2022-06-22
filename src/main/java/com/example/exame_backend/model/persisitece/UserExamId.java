package com.example.exame_backend.model.persisitece;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserExamId implements Serializable {

    @Column(name = "u_id")
    private Long userId;

    @Column(name = "exam_id")
    private Long examId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserExamId that = (UserExamId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(examId, that.examId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, examId);
    }
}
