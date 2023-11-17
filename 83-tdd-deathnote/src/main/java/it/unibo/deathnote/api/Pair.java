package it.unibo.deathnote.api;

public class Pair{
    
    private String cause;
    private String details;


    public Pair(final String cause, final String details){
        this.cause = cause;
        this.details = details;
    }

    public String getCause(){
        return this.cause;
    }

    public String getDetails(){
        return this.details;
    }

    public void setCause(String cause){
        this.cause = cause;
    }

    public void setDetails(String details){
        this.details = details;
    }
}
