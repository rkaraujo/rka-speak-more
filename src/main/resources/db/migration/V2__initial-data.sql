-- plans
INSERT INTO PHONE_PLAN (name, minutes, created_at, updated_at) VALUES ('FaleMais 30', 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO PHONE_PLAN (name, minutes, created_at, updated_at) VALUES ('FaleMais 60', 60, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO PHONE_PLAN (name, minutes, created_at, updated_at) VALUES ('FaleMais 120', 120, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- costs
INSERT INTO PHONE_COST (source_ddd, destination_ddd, cost_per_minute, created_at, updated_at) VALUES (11, 16, 1.9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO PHONE_COST (source_ddd, destination_ddd, cost_per_minute, created_at, updated_at) VALUES (16, 11, 2.9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO PHONE_COST (source_ddd, destination_ddd, cost_per_minute, created_at, updated_at) VALUES (11, 17, 1.7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO PHONE_COST (source_ddd, destination_ddd, cost_per_minute, created_at, updated_at) VALUES (17, 11, 2.7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO PHONE_COST (source_ddd, destination_ddd, cost_per_minute, created_at, updated_at) VALUES (11, 18, 0.9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO PHONE_COST (source_ddd, destination_ddd, cost_per_minute, created_at, updated_at) VALUES (18, 11, 1.9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
