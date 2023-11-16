package it.unibo.deathnote.api;

import java.util.HashMap;
import java.util.Map;

public class DeathNoteImpl implements DeathNote{

    private final Map<String, Pair> deathNote = new HashMap<>();
    public static final String DFL_CAUSE= "hearth attack";
    private String lastNameWritten;
    private long timeWrittenName;
    private long timeWrittenCause;
    
    
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
            throw new NullPointerException();
        }
        this.lastNameWritten = name;
        this.deathNote.put(name, new Pair(DFL_CAUSE, ""));
        this.timeWrittenName = System.currentTimeMillis();
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if(cause == null || deathNote.isEmpty()){
            throw new IllegalStateException();
        }
        if (System.currentTimeMillis() - this.timeWrittenName > 40){
            return false;
        }
        return this.deathNote.get(this.lastNameWritten).setCause(cause);
    }

    @Override
    public boolean writeDetails(String details) {
        if(details == null || deathNote.isEmpty()){
            throw new IllegalStateException();
        }
        if(System.currentTimeMillis() - this.timeWrittenCause > 6040){
            return false;
        }
        return this.deathNote.get(this.lastNameWritten).setDetails(details);
    }

    @Override
    public String getDeathCause(String name) {
        if (this.deathNote.get(name) == null){
            throw new IllegalArgumentException();
        }
        return this.deathNote.get(name).getCause();
    }

    @Override
    public String getDeathDetails(String name) {
        if(this.deathNote.get(name) == null){
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException("The provided name is not written in this DeathNote");
    }

    @Override
    public boolean isNameWritten(String name) {
        return this.deathNote.get(name) != null;
    }
    
    
}
