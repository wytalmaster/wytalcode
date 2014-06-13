package com.wytal.util.exception;

import org.codehaus.jackson.map.annotate.JsonSerialize;




@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WytalException extends Exception {
        protected String description;
        protected String details;
        protected WytalException(String errorCode){
                super(errorCode);
        }
        private static final long serialVersionUID = 1L;
}
