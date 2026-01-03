-- Migration: Create message table
-- Description: Creates the message table to store messages from Kafka consumer

CREATE TABLE IF NOT EXISTS message (
    id BIGSERIAL PRIMARY KEY,
    content VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create index on created_at for better query performance
CREATE INDEX IF NOT EXISTS idx_message_created_at ON message(created_at);

-- Add comment to table
COMMENT ON TABLE message IS 'Stores messages received from Kafka consumer';
COMMENT ON COLUMN message.id IS 'Primary key, auto-increment';
COMMENT ON COLUMN message.content IS 'Message content from Kafka';
COMMENT ON COLUMN message.created_at IS 'Timestamp when the message was created';

