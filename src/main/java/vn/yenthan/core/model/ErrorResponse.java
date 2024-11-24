package vn.yenthan.core.model;

import lombok.Getter;
import lombok.Setter;
import vn.yenthan.core.util.BaseResponse;

@Getter
@Setter
public class ErrorResponse extends BaseResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

//    public ErrorResponse(Integer status, Integer code, String message) {
//        super(status, code);
//        this.message = message;
//    }
}
