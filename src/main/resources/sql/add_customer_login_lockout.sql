-- One-time Oracle schema update for customer temporary login lockout.
--
-- Apply this script to each target schema before deploying application code that
-- reads customer_login_locked_until. The application does not run migrations
-- automatically.
--
-- Idempotency guidance for operators: run the ALTER TABLE only if this query
-- returns no rows for the connected schema:
--   SELECT column_name
--   FROM user_tab_cols
--   WHERE table_name = 'CUSTOMER_TABLE'
--     AND column_name = 'CUSTOMER_LOGIN_LOCKED_UNTIL';

ALTER TABLE customer_table
  ADD (customer_login_locked_until TIMESTAMP NULL);
