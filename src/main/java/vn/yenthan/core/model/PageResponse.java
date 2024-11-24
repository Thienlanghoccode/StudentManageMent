package vn.yenthan.core.model;

import lombok.Getter;
import lombok.Setter;
import vn.yenthan.core.util.BaseResponse;

@Getter
@Setter
public class PageResponse<T> extends BaseResponse {
    private T data;

    private Long total;

    public PageResponse(T data) {
        this.data = data;
    }

//    public PageResponse(T data, Long total) {
//        this(null, null, data, total);
//        this.data = data;
//        this.total = total;
//    }
//
    public PageResponse(Integer status, Integer code, T data, Long total) {
        super(status, code);
        this.data = data;
        this.total = total;
    }
}
