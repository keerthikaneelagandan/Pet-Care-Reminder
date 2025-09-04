
CREATE DATABASE IF NOT EXISTS petcarereminder;
USE petcarereminder;

CREATE TABLE IF NOT EXISTS reminders (
  pet_id INT PRIMARY KEY AUTO_INCREMENT,
  pet_name VARCHAR(50) NOT NULL,
  pet_type VARCHAR(50) NOT NULL,
  reminder_type VARCHAR(50) NOT NULL,  
  reminder_date DATE NOT NULL,
  description VARCHAR(100)
);
INSERT INTO reminders( pet_name, pet_type,reminder_type, reminder_date)
VALUES
('Pinky', 'cat', 'Vet Visit', '2025-09-05'),
('Nimme', 'cat', 'Vaccination', '2025-09-10'),
('Blacky','dog', 'Deworming', '2025-09-15');

SELECT * FROM reminders;

USE petcarereminder;
ALTER TABLE reminders
DROP COLUMN description;
DESC reminders;
