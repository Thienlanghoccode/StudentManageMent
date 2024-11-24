package vn.yenthan.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentDeleteRequest {
    private Long id;
    private List<Long> ids;
}
