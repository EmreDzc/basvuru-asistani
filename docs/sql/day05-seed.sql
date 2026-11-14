
BEGIN;

-- 1. Test kullanıcısını oluştur
INSERT INTO users (email, password_hash, full_name)
VALUES (
    'emre@example.com',
    'fake-hash-for-now',
    'Emre Test'
)
RETURNING id AS user_id \gset

-- 2. Kullanıcıya ait şikâyet oluştur
INSERT INTO consumer_cases (
    user_id,
    description,
    requested_remedy,
    status,
    dispute_amount
)
VALUES (
    :user_id,
    'Telefon arizali cikti ve satici iade kabul etmiyor.',
    'REFUND',
    'DRAFT',
    18000.00
)
RETURNING id AS case_id \gset

-- 3. Şikâyetin belgelerini oluştur
INSERT INTO documents (
    case_id,
    document_type,
    file_name
)
VALUES
    (:case_id, 'INVOICE', 'fatura.pdf'),
    (:case_id, 'PRODUCT_PHOTO', 'telefon.jpg');

-- 4. Gerekli belge kontrol listesini oluştur
INSERT INTO checklist_items (
    case_id,
    document_type,
    mandatory,
    completed
)
VALUES
    (:case_id, 'INVOICE', TRUE, TRUE),
    (:case_id, 'PRODUCT_PHOTO', TRUE, TRUE),
    (:case_id, 'SERVICE_REPORT', TRUE, FALSE),
    (:case_id, 'SELLER_CORRESPONDENCE', TRUE, FALSE);

COMMIT;
