# bankapp-0.0.1-SNAPSHOT.jar

**BankApp** istifadəçilərin və hesabların idarə olunmasını təmin edən sadə bank tətbiqidir. Bu proyekt Spring Boot və PostgreSQL istifadə edərək hazırlanmışdır.

## Əsas xüsusiyyətlər

- **User CRUD əməliyyatları**
    - İstifadəçi yaratmaq, yeniləmək, silmək və statusuna görə list etmək.
    - Soft delete tətbiq olunur, yəni silinmiş istifadəçilər yalnız status ilə qeydə alınır.
    - Validation istifadə olunur (məsələn, email düzgünlüyü, unique email).

- **Account idarəetməsi**
    - İstifadəçi üçün hesab yaratmaq, balansı göstərmək.
    - Hesab statusları: ACTIVE, BLOCK, DELETE.
    - Hesaba **deposit** və **withdraw** əməliyyatları.
    - Hesablar arasında **transfer** əməliyyatı.
    - Hər istifadəçi üçün yalnız bir hesab ola bilər.

- **Transaction Log**
    - Hesab üzərində hər əməliyyat loglanır.
    - Deposit, withdraw və transfer əməliyyatları TransactionLog cədvəlində saxlanılır.
    - Loglar əməliyyat tarixi, məbləğ və tipini əhatə edir.

- **Exception Handling**
    - Global exception handler ilə bütün xəta mesajları JSON formatında qaytarılır.
    - Məsələn: NotFoundException, AlreadyActiveException, AlreadyBlockException, InsufficientBalanceException.

## Texnologiyalar

- **Backend:** Java 21, Spring Boot
- **Database:** PostgreSQL
- **Dependency Management:** Gradle
- **Validation:** Jakarta Validation (`@NotBlank`, `@Email`, `@Valid`)
- **Logging:** SLF4J

## Quraşdırma

1. Proyektin `.env` faylını yaradın və PostgreSQL parametrlərini əlavə edin:

```env
DB_URL=jdbc:postgresql://localhost:5432/bankapp
DB_USERNAME=your_username
DB_PASSWORD=your_password
