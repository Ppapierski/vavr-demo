package operation.client.model;


import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class ErrorResponse {

    private final int errorCode;
    private final String msg;


    public static ErrorResponse of(ErrorCode code, String msg) {
       return builder().errorCode(code.getCode())
                .msg(msg)
                .build();

    }
}
