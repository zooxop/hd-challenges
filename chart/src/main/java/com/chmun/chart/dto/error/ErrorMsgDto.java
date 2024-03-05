package com.chmun.chart.dto.error;

public class ErrorMsgDto {
    private String title;
    private String message;

    public ErrorMsgDto(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}
