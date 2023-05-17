package com.micro.demo.Exception;

public class LicenseNotValidException extends RuntimeException{
    public LicenseNotValidException() {
        super("License not valid");
    }

}
