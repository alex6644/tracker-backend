-- =================================================================
-- This script initializes the database with sample data for the gym tracking application.
-- To use it, place this file in the `src/main/resources` directory of your Spring Boot project.
-- Spring Boot will automatically execute it on startup.
--
-- The password for the sample user 'max.mustermann@example.com' is 'password'.
-- =================================================================

-- Reset sequences for consistent IDs on re-runs (optional, useful for development)
ALTER SEQUENCE app_user_id_seq RESTART WITH 1;
ALTER SEQUENCE exercise_id_seq RESTART WITH 1;
ALTER SEQUENCE workout_template_id_seq RESTART WITH 1;
ALTER SEQUENCE template_exercise_id_seq RESTART WITH 1;
ALTER SEQUENCE workout_id_seq RESTART WITH 1;
ALTER SEQUENCE workout_set_id_seq RESTART WITH 1;


-- 1. Create a sample user
-- The password 'password' is hashed using BCrypt.
-- You can generate your own hash for different passwords.
INSERT INTO app_user (id, first_name, last_name, email, password, role)
VALUES (1, 'Alexander', 'Karrer', 'karrer.alexander@outlook.com', '$2a$10$xzFjUSsQCehGhDMQciOS8uMj/Kdan6mCCVAwuHxeMfx76mWTJEANy', 'USER');


-- 2. Populate the exercise library
INSERT INTO exercise (id, name, description, equipment, target_muscle) VALUES
                                                                           (1, 'Bankdrücken', 'Auf einer Flachbank liegend eine Langhantel zur Brust absenken und wieder hochdrücken.', 'LANGHANTEL', 'BRUST'),
                                                                           (2, 'Kniebeugen', 'Eine Langhantel auf den Schultern ablegen, in die Hocke gehen und wieder aufstehen.', 'LANGHANTEL', 'BEINE'),
                                                                           (3, 'Kreuzheben', 'Eine Langhantel vom Boden aufheben, bis der Körper aufrecht ist.', 'LANGHANTEL', 'RUECKEN'),
                                                                           (4, 'Schulterdrücken', 'Im Sitzen oder Stehen Kurzhanteln über den Kopf drücken.', 'KURZHANTEL', 'SCHULTERN'),
                                                                           (5, 'Klimmzüge', 'Sich an einer Stange hochziehen, bis das Kinn über der Stange ist.', 'KOERPERGEWICHT', 'RUECKEN'),
                                                                           (6, 'Dips', 'An Barren den Körper absenken und wieder hochdrücken.', 'KOERPERGEWICHT', 'TRIZEPS'),
                                                                           (7, 'Bizeps-Curls', 'Kurzhanteln mit den Armen beugen.', 'KURZHANTEL', 'BIZEPS'),
                                                                           (8, 'Seitheben', 'Kurzhanteln seitlich bis auf Schulterhöhe anheben.', 'KURZHANTEL', 'SCHULTERN'),
                                                                           (9, 'Trizepsdrücken am Kabelturm', 'Ein Seil oder eine Stange am Kabelturm nach unten drücken.', 'KABELTURM', 'TRIZEPS'),
                                                                           (10, 'Beinpresse', 'Mit den Beinen eine Plattform wegdrücken.', 'MASCHINE', 'BEINE');


-- 3. Create a workout template for the user (user_id = 1)
INSERT INTO workout_template (id, name, description, user_id) VALUES
    (1, 'Push Day A', 'Fokus auf Brust, Schultern und Trizeps.', 1);


-- 4. Add exercises to the workout template (template_id = 1)
INSERT INTO template_exercise (id, display_order, target_reps, target_sets, exercise_id, template_id) VALUES
                                                                                                          (1, 1, '8-12', 3, 1, 1), -- Bankdrücken
                                                                                                          (2, 2, '8-12', 3, 4, 1), -- Schulterdrücken
                                                                                                          (3, 3, '10-15', 3, 9, 1); -- Trizepsdrücken


-- 5. Create a completed workout in the past for the user (user_id = 1)
-- This workout happened yesterday and lasted 55 minutes.
INSERT INTO workout (id, name, notes, start_time, end_time, user_id) VALUES
    (1, 'Push Day A', 'Hat sich gut angefühlt, konnte mich steigern.', NOW() - INTERVAL '1 DAY', NOW() - INTERVAL '1 DAY' + INTERVAL '55 MINUTE', 1);


-- 6. Add completed sets to the past workout (workout_id = 1)
-- Sets for Bankdrücken (exercise_id = 1)
INSERT INTO workout_set (reps, weight, timestamp, exercise_id, workout_id) VALUES
                                                                               (12, 80, NOW() - INTERVAL '1 DAY' + INTERVAL '5 MINUTE', 1, 1),
                                                                               (10, 82.5, NOW() - INTERVAL '1 DAY' + INTERVAL '8 MINUTE', 1, 1),
                                                                               (8, 85, NOW() - INTERVAL '1 DAY' + INTERVAL '11 MINUTE', 1, 1);

-- Sets for Schulterdrücken (exercise_id = 4)
INSERT INTO workout_set (reps, weight, timestamp, exercise_id, workout_id) VALUES
                                                                               (12, 20, NOW() - INTERVAL '1 DAY' + INTERVAL '20 MINUTE', 4, 1),
                                                                               (11, 20, NOW() - INTERVAL '1 DAY' + INTERVAL '23 MINUTE', 4, 1),
                                                                               (10, 22.5, NOW() - INTERVAL '1 DAY' + INTERVAL '26 MINUTE', 4, 1);

-- Sets for Trizepsdrücken (exercise_id = 9)
INSERT INTO workout_set (reps, weight, timestamp, exercise_id, workout_id) VALUES
                                                                               (15, 30, NOW() - INTERVAL '1 DAY' + INTERVAL '35 MINUTE', 9, 1),
                                                                               (12, 35, NOW() - INTERVAL '1 DAY' + INTERVAL '38 MINUTE', 9, 1),
                                                                               (12, 35, NOW() - INTERVAL '1 DAY' + INTERVAL '41 MINUTE', 9, 1);
