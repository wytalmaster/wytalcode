package com.wytal.util.error;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Errors {
        private List<Error> error;

        public List<Error> getError() {
                return error;
        }

        public void setError(List<Error> error) {
                this.error = error;
        }

}