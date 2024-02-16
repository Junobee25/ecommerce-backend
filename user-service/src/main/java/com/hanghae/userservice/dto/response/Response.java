package com.hanghae.userservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {
    private String status;
    private T result;

    public static <T> Response<T> success() {
        return new Response<T>("SUCCESS", null);
    }

    public static <T> Response<T> success(T result) {
        return new Response<T>("SUCCESS", result);
    }

    public static Response<Void> error(String resultCode) {
        return new Response<Void>(resultCode, null);
    }

    public String toStream() {
        if (result == null) {
            return "{" +
                    "\"status\":" + "\"" + status + "\"," +
                    "\"result\":" + null +
                    "}";
        }
        return "{" +
                "\"status\":" + "\"" + status + "\"," +
                "\"result\":" + "\"" + result + "\"," +
                "}";
    }
}
