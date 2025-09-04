package com.PetCareReminder;

import java.time.LocalDate;

public class PetReminder {
    private int petId;
    private String petName;
    private String petType;
    private String remindertype;
    private LocalDate reminderDate;
   

    // Constructor for INSERT (no id)
    public PetReminder(String petName, String petType, String remindertype, LocalDate reminderDate) {
        this.petName = petName;
        this.petType = petType;
        this.remindertype = remindertype;
        this.reminderDate = reminderDate;
    }

    // Constructor for SELECT/UPDATE (has id)
    public PetReminder(int petId, String petName, String petType, String remindertype, LocalDate reminderDate) {
        this.petId = petId;
        this.petName = petName;
        this.petType = petType;
        this.remindertype = remindertype;
        this.reminderDate = reminderDate;
    }

    // getters & setters...
    public int getPetId() { return petId; }
    public String getPetName() { return petName; }
    public String getPetType() { return petType; }
    public String getReminderType() { return remindertype; }
    public LocalDate getReminderDate() { return reminderDate; }
   
    public void setPetId(int petId) { this.petId = petId; }
    public void setPetName(String petName) { this.petName = petName; }
    public void setPetType(String petType) { this.petType = petType; }
    public void setRemindertype(String remindertype) { this.remindertype = remindertype; }
    public void setReminderDate(LocalDate reminderDate) { this.reminderDate = reminderDate; }
    

    @Override
    public String toString() {
        return String.format("#%d  %s (%s)  on %s  - %s",
                petId, petName, petType,remindertype, reminderDate == null ? "" :remindertype);
    }
}
