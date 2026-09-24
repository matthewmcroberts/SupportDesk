INSERT INTO categories (name, description) VALUES
                                               ('Technical Support', 'Issues with software, hardware, applications, or other technical problems.'),
                                               ('Account & Access', 'Problems with user accounts, passwords, login, authentication, or access permissions.'),
                                               ('Billing & Payments', 'Questions or issues related to invoices, payments, refunds, or billing information.'),
                                               ('Network & Connectivity', 'Issues involving internet connections, Wi-Fi, VPNs, network access, or connectivity.'),
                                               ('Hardware', 'Problems involving computers, monitors, printers, peripherals, or other physical equipment.'),
                                               ('Software', 'Issues involving applications, operating systems, installations, updates, or software configuration.'),
                                               ('Security', 'Security concerns, suspicious activity, compromised accounts, malware, or other security-related issues.'),
                                               ('Email & Communication', 'Problems with email, messaging systems, notifications, or other communication tools.'),
                                               ('General Inquiry', 'General questions or requests that do not fit into another support category.'),
                                               ('Other', 'Support requests that do not fit into any of the available categories.');

INSERT INTO users (id, email, password, role) VALUES
                                                  (1, 'admin@supportdesk.com', '$2a$10$VWM1C51wLA7njE7KXKuQTuQRaSP5ScxECbaZ0BJkA5h1.Z1F/rQzK', 'ADMIN'),
                                                  (2, 'mike.user@example.com', '$2a$10$VWM1C51wLA7njE7KXKuQTuQRaSP5ScxECbaZ0BJkA5h1.Z1F/rQzK', 'USER'),
                                                  (3, 'jane.user@example.com', '$2a$10$VWM1C51wLA7njE7KXKuQTuQRaSP5ScxECbaZ0BJkA5h1.Z1F/rQzK', 'USER'),
                                                  (4, 'bob.user@example.com', '$2a$10$VWM1C51wLA7njE7KXKuQTuQRaSP5ScxECbaZ0BJkA5h1.Z1F/rQzK', 'USER');
ALTER TABLE users ALTER COLUMN id RESTART WITH 5;

INSERT INTO ticket (
    id,
    title,
    description,
    status,
    priority,
    category_id,
    created_by_id,
    assigned_to_id,
    created_at,
    updated_at
) VALUES
      (
          1,
          'Cannot log into account',
          'I am unable to log into my account. My password is being rejected even though I believe it is correct.',
          'OPEN',
          'HIGH',
          2,
          4,
          2,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP
      ),
      (
          2,
          'Laptop running very slowly',
          'My laptop has become extremely slow over the past few days. Applications take several minutes to open.',
          'IN_PROGRESS',
          'MEDIUM',
          5,
          4,
          3,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP
      ),
      (
          3,
          'VPN connection keeps dropping',
          'The company VPN disconnects every 10 to 15 minutes while I am working remotely.',
          'OPEN',
          'HIGH',
          4,
          4,
          2,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP
      ),
      (
          4,
          'Request for software installation',
          'I need the latest version of IntelliJ IDEA installed on my workstation for development work.',
          'OPEN',
          'LOW',
          6,
          4,
          3,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP
      ),
      (
          5,
          'Suspicious login notification',
          'I received an email notifying me of a login from a location that I do not recognize.',
          'IN_PROGRESS',
          'HIGH',
          7,
          2,
          2,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP
      ),
      (
          6,
          'Email not sending',
          'I can receive emails normally, but emails I send remain stuck in my outbox.',
          'RESOLVED',
          'MEDIUM',
          8,
          3,
          3,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP
      ),
      (
          7,
          'Monitor is not displaying anything',
          'My external monitor suddenly stopped displaying an image. The computer still detects the monitor.',
          'OPEN',
          'MEDIUM',
          5,
          3,
          2,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP
      ),
      (
          8,
          'Question about monthly invoice',
          'I have a question about a charge that appeared on my latest invoice.',
          'CLOSED',
          'LOW',
          3,
          2,
          2,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP
      ),
      (
          9,
          'Application crashes on startup',
          'The internal reporting application crashes immediately after I try to launch it.',
          'IN_PROGRESS',
          'HIGH',
          6,
          3,
          3,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP
      ),
      (
          10,
          'General support question',
          'I have a question about how to request access to additional company resources.',
          'OPEN',
          'LOW',
          9,
          4,
          NULL,
          CURRENT_TIMESTAMP,
          CURRENT_TIMESTAMP
      );
ALTER TABLE ticket ALTER COLUMN id RESTART WITH 11;