package vn.yenthan.dto.response;

import lombok.Getter;
import lombok.Setter;
import vn.yenthan.entity.Subject;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class SubjectResponse implements Serializable {

    private Subject subject;

    private List<StudentGradesResponse> students;
}
