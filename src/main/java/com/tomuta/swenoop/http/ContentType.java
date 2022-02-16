package com.tomuta.swenoop.http;

public enum ContentType {
    HTML("text/html"),
    JSON("application/json");

    public final String type;

    ContentType(String type) {
        this.type = type;
    }
}
