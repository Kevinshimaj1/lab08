package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static java.lang.Thread.sleep;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.api.DeathNoteImpl;

class TestDeathNote {

    private DeathNote deathNote;

    @BeforeEach
    public void setUp(){
        this.deathNote = new DeathNoteImpl();
    }

    @Test
    public void TestGetRule(){
        try{
            deathNote.getRule(0);
            deathNote.getRule(-1);
            Assertions.fail("Rule smaller than 1 or larger then number of rules didn't throw an exception");
        }catch(IllegalArgumentException e){
            assertEquals("The given rule number must not be 0 or less than zero! ", e.getMessage());
        }
    }

    @Test
    public void TestEmptyRule(){
        for(String rule : DeathNote.RULES){
            assertNotNull(rule);
            assertFalse(rule.isBlank());
        }
    }

    @Test
    public void TestHumanDeath(){
        final String name = "Antonio";
        final String wrongName = "Vittorio";
        assertFalse(deathNote.isNameWritten(name));
        deathNote.writeName(name);
        assertTrue(deathNote.isNameWritten(name));
        assertFalse(deathNote.isNameWritten(wrongName));
        assertFalse(deathNote.isNameWritten(""));
    }
    
    @Test
    public void TestDeathCause() throws InterruptedException{
        try{
            final String name = "Luca";
            final String cause = "car accident";
            deathNote.writeDeathCause(cause);
            deathNote.writeName(name);
            Assertions.fail("writing a cause of death before writing a name didn't thrown an exception");
        } catch(IllegalStateException e){
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        final String name = "SurrealPower";
        deathNote.writeName(name);
        assertEquals("hearth attack", deathNote.getDeathCause(name));
        deathNote.writeName("Gianluca");
        assertTrue(deathNote.writeDeathCause("karting accident"));
        assertEquals("karting accident", deathNote.getDeathCause("Gianluca"));
        sleep(100);
        assertFalse(deathNote.writeDeathCause("He was shot"));
        assertEquals("karting accident", deathNote.getDeathCause("Gianluca"));
        }

    @Test
    public void TestWriteTime() throws InterruptedException{
        try{
            deathNote.writeDetails("Infarct");
            Assertions.fail("Writing death cause before writing name didn't throw an exception");
        }catch(IllegalStateException e){
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        deathNote.writeName("Lucia");
        assertTrue(deathNote.getDeathDetails("Lucia").isBlank());
        assertTrue(deathNote.writeDetails("ran for too long"));
        assertEquals("ran for too long", deathNote.getDeathDetails("Lucia"));
        deathNote.writeName("Mattia");
        sleep(6100L);
        deathNote.writeDetails("car accident");
        assertTrue(deathNote.getDeathDetails("Mattia").isBlank());
    }

    


}