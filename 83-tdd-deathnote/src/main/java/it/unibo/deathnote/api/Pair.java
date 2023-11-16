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

    public boolean setCause(String cause){
        if(this.cause != DeathNoteImpl.DFL_CAUSE){
            return false;
        }
        this.cause = cause;
        return true;
    }

    public boolean setDetails(String details){
        if(!this.cause.isBlank()){
            return false;
        }
        this.details = details;
        return true;
    }
}
