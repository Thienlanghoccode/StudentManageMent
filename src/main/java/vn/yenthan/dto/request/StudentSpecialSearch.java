package vn.yenthan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentSpecialSearch {
    private String studentCode;
    private String studentName;
}
