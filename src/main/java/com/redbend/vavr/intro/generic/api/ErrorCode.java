package com.redbend.vavr.intro.generic.api;


import io.helidon.common.http.Http;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static io.helidon.common.http.Http.Status.BAD_REQUEST_400;
import static io.helidon.common.http.Http.Status.INTERNAL_SERVER_ERROR_500;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    VALIDATION_ERROR(2001, "Validation error: %s", BAD_REQUEST_400),
    INVALID_REQUEST(2002, "Invalid request: {}", BAD_REQUEST_400),
    MAX_UPLOAD_FILE_SIZE_EXCEEDED(2003, "Maximum upload file size exceeded. {}", BAD_REQUEST_400),
    CORRUPTED_JSON_FILE(2006, "Corrupted json file", BAD_REQUEST_400),
    MALFORMED_JSON(2009, "Did not find element: {} in json file", BAD_REQUEST_400),
    REPORT_CREATION_FAILURE(2010, "Failed to create operation com.redbend.vavr.intro.report: %s", BAD_REQUEST_400),

    UNKNOWN_EXCEPTION(9999, "Unknown exception occurred", INTERNAL_SERVER_ERROR_500);

    private final int code;
    private final String message;
    private final Http.Status httpStatus;

}
