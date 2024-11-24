package vn.yenthan.core.util;

import org.springframework.data.domain.Page;
import vn.yenthan.core.model.ErrorResponse;
import vn.yenthan.core.model.PageResponse;
import vn.yenthan.core.model.SuccessResponse;

public class ResponseUtil {
    public static <T> SuccessResponse<T> ok(Integer code, T data ) {
        SuccessResponse ret = new SuccessResponse(data);
        ret.setStatus(0);
        ret.setCode(code);
        return ret;
    }

    public static <T> PageResponse<T> ok(Page<T> data) {
        PageResponse retPage = new PageResponse(data.getContent());
        retPage.setStatus(0);
        retPage.setCode(200);
        retPage.setTotal(data.getTotalElements());
        return retPage;
    }

    public static ErrorResponse err(String message, Integer code) {
        ErrorResponse ret = new ErrorResponse(message);
        ret.setStatus(1);
        ret.setCode(code);
        return ret;
    }
}
