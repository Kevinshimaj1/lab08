package it.unibo.deathnote.api;

import java.util.HashMap;
import java.util.Map;

public class DeathNoteImpl implements DeathNote{

    private final Map<String, Pair> deathNote = new HashMap<>();
    public static final String DFL_CAUSE= "hearth attack";
    private String lastNameWritten;
    private long timeOfDeath; //when the name has been written 
    
    
    @Override
    public String getRule(int ruleNumber){
        if(ruleNumber < 1 || ruleNumber > DeathNote.RULES.size()){
            throw new IllegalArgumentException("The given rule number must not be 0 or less than zero! ");
        }
        return DeathNote.RULES.get(ruleNumber - 1); 
    }       

    @Override
    public void writeName(String name) {
        if(name == null){
            throw new NullPointerException("Error");
        }
        this.lastNameWritten = name;
        this.deathNote.put(name, new Pair(DFL_CAUSE, ""));
        this.timeOfDeath = System.currentTimeMillis();
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if(cause == null || deathNote.isEmpty()){
            throw new IllegalStateException("Error");
        }
        if (System.currentTimeMillis() - this.timeOfDeath > 40){
            return false;
        }
        this.deathNote.get(this.lastNameWritten).setCause(cause);
        return true;
    }

    @Override
    public boolean writeDetails(String details) {
        if(details == null || deathNote.isEmpty()){
            throw new IllegalStateException("Error");
        }
        if(System.currentTimeMillis() - this.timeOfDeath > 6040){
            return false;
        }
        this.deathNote.get(this.lastNameWritten).setDetails(details);
        return true;
    }

    @Override
    public String getDeathCause(String name) {
        if (this.deathNote.get(name) == null){
            throw new IllegalArgumentException("Error");
        }
        return this.deathNote.get(name).getCause();
    }

    @Override
    public String getDeathDetails(String name) {
        if(this.deathNote.get(name) == null){
            throw new IllegalArgumentException("Error");
        }
       return this.deathNote.get(name).getDetails();
    }

    @Override
    public boolean isNameWritten(String name) {
        return this.deathNote.get(name) != null;
    }
    
    
}
