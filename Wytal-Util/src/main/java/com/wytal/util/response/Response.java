package com.wytal.util.response;

import com.wytal.util.error.Errors;

public class Response {
    protected Errors errors;

   public Errors getErrors() {
           return errors;
   }

   public void setErrors(Errors errors) {
           this.errors = errors;
   }

}