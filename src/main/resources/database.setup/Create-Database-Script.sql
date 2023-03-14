CREATE TABLE currency_cutoff (
    iso VARCHAR(3) PRIMARY KEY,
    country VARCHAR(255) NOT NULL,
    today VARCHAR(255) NOT NULL,
    tomorrow VARCHAR(255) NOT NULL,
    after_tomorrow VARCHAR(255) NOT NULL
);

INSERT INTO currency_cutoff (iso, country, today, tomorrow, after_tomorrow) VALUES
('aed', 'united arab emirates', 'never possible', '14.00', 'always possible'),
('aud', 'australia', 'never possible', '14.00', 'always possible'),
('bgn', 'bulgaria', 'never possible', '14.00', 'always possible'),
('cad', 'canada', '15.30', 'always possible', 'always possible'),
('chf', 'switzerland', '10.00', 'always possible', 'always possible'),
('cnh', 'china (hong kong)', 'never possible', '14.00', 'always possible'),
('czk', 'czech republic', '11.00', 'always possible', 'always possible'),
('dkk', 'denmark', '15.30', 'always possible', 'always possible'),
('eur', 'euro area', '16.00', 'always possible', 'always possible'),
('gbp', 'united kingdom', '15.30', 'always possible', 'always possible'),
('hkd', 'hong kong', 'never possible', '14.00', 'always possible'),
('hrk', 'croatia', 'never possible', '14.00', 'always possible'),
('huf', 'hungary', '11.00', 'always possible', 'always possible'),
('ils', 'israel', 'never possible', '14.00', 'always possible'),
('jpy', 'japan', 'never possible', '15.30', 'always possible'),
('mxn', 'mexico', '11.00', 'always possible', 'always possible'),
('nok', 'norway', '15.00', 'always possible', 'always possible'),
('nzd', 'new zealand', 'never possible', '14.00', 'always possible'),
('pln', 'poland', '10.00', 'always possible', 'always possible'),
('ron', 'romania', 'never possible', '14.00', 'always possible'),
('rub', 'russia', 'never possible', '13.00', 'always possible'),
('rsd', 'serbia', 'never possible', 'never possible', 'always possible'),
('sar', 'saudi arabia', 'never possible', '14.00', 'always possible'),
('sek', 'sweden', '15.30', 'always possible', 'always possible'),
('sgd', 'singapore', 'never possible', '14.00', 'always possible'),
('thb', 'thailand', 'never possible', '9.00', 'always possible'),
('try', 'turkey', 'never possible', '14.00', 'always possible'),
('usd', 'united states', '16.00', 'always possible', 'always possible'),
('zar', 'south africa', 'never possible', '14.00', 'always possible');