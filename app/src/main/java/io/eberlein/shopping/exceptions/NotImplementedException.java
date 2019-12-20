package io.eberlein.shopping.exceptions;

public class NotImplementedException extends UnsupportedOperationException {
    public NotImplementedException(String methodName){
        super(String.format("Method '%s' should be overwritten. Do not call this method directly.", methodName));
    }
}
