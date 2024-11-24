package vn.yenthan.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import vn.yenthan.core.util.BaseResponse;

@Getter
@Setter
public class SuccessResponse<T> extends BaseResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public SuccessResponse(T data) {
        this.data = data;
    }

//    public SuccessResponse(Integer status, Integer code, T data) {
//        super(status, code);
//        this.data = data;
//    }
}
