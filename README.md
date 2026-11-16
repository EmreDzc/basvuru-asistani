# Başvuru Asistanı

Tüketicilerin şikâyet başvurularını, gerekli belgelerini ve
başvuru durumunu takip etmesini sağlayan REST API.

## Teknolojiler
- Java 21, Spring Boot 4
- Spring Data JPA, PostgreSQL

## API
| Metot | Yol             | Açıklama                 |
|-------|-----------------|--------------------------|
| POST  | /api/cases      | Yeni şikâyet oluşturur   |
| GET   | /api/cases      | Tüm şikâyetleri listeler |
| GET   | /api/cases/{id} | Tek şikâyeti getirir     |

## Yol haritası
- [x] PostgreSQL ile kalıcı kayıt
- [ ] Kullanıcı girişi (JWT)
- [ ] Belge yükleme
- [ ] Testler
