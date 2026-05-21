package dev.ardalic.cloudresourcecleanup.exception;

public class InvalidAwsRegionException extends RuntimeException {

    public InvalidAwsRegionException(String region) {
        super("Invalid AWS region: " + region);
    }
}