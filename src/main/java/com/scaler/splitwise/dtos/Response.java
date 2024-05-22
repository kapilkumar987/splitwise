package com.scaler.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response
{
    private ResponseStatus responseStatus;
    private String errorMessage;

    public static Response getSuccessResponse()
    {
        Response response = new Response();
        response.setResponseStatus(ResponseStatus.SUCCESS);
        return  response;
    }

    public static Response getFailureResponse(String errorMessage)
    {
        Response response = new Response();
        response.setResponseStatus(ResponseStatus.FAILURE);
        response.setErrorMessage(errorMessage);
        return response;
    }
}
