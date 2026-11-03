package org.data;

/** Configuration is intentionally supplied at runtime, never stored in source code. */
public class TestData {
    public static final String BASE_URL = System.getenv("QA_BASE_URL");
    public static final String USER_NAME = System.getenv("QA_USERNAME");
    public static final String USER_PASSWORD = System.getenv("QA_PASSWORD");
}
