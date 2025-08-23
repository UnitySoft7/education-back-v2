package com.school.dormitory.core.common;

import com.school.dormitory.query.api.response.CategoryResponse;
public class Category {
    public static CategoryResponse masculine() {
        return new CategoryResponse("Masculine");
    }

    public static CategoryResponse feminine() {
        return new CategoryResponse("Feminine");
    }
}


