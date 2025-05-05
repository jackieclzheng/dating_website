-- 创建用户表
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    gender VARCHAR(10),
    age INT,
    bio TEXT,
    avatar_url VARCHAR(255),
    location VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 创建消息表
CREATE TABLE IF NOT EXISTS messages (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(id),
    FOREIGN KEY (receiver_id) REFERENCES users(id)
);

-- 创建用户关系表
CREATE TABLE IF NOT EXISTS user_relationships (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    target_user_id BIGINT NOT NULL,
    relationship_type VARCHAR(20) NOT NULL, -- 'LIKE', 'MATCH', 'BLOCK'
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (target_user_id) REFERENCES users(id)
);

-- 创建用户兴趣表
CREATE TABLE IF NOT EXISTS user_interests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    interest_name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 插入测试数据
-- 插入用户
INSERT INTO users (username, password, email, gender, age, bio, location) VALUES
('user1', '$2a$10$rDkPvvAFV6GgJjXpYWxqUOQZxXxXxXxXxXxXxXxXxXxXxXxXxXx', 'user1@example.com', 'MALE', 25, '喜欢运动和音乐', '北京'),
('user2', '$2a$10$rDkPvvAFV6GgJjXpYWxqUOQZxXxXxXxXxXxXxXxXxXxXxXxXxXx', 'user2@example.com', 'FEMALE', 23, '喜欢读书和旅行', '上海'),
('user3', '$2a$10$rDkPvvAFV6GgJjXpYWxqUOQZxXxXxXxXxXxXxXxXxXxXxXxXxXx', 'user3@example.com', 'MALE', 28, '喜欢美食和电影', '广州');

-- 插入消息
INSERT INTO messages (sender_id, receiver_id, content) VALUES
(1, 2, '你好，很高兴认识你！'),
(2, 1, '你好，我也很高兴认识你！'),
(1, 3, '最近在忙什么呢？'),
(3, 1, '在准备一个项目，你呢？');

-- 插入用户关系
INSERT INTO user_relationships (user_id, target_user_id, relationship_type) VALUES
(1, 2, 'LIKE'),
(2, 1, 'LIKE'),
(1, 3, 'MATCH');

-- 插入用户兴趣
INSERT INTO user_interests (user_id, interest_name) VALUES
(1, '运动'),
(1, '音乐'),
(2, '读书'),
(2, '旅行'),
(3, '美食'),
(3, '电影'); 