# Rating Tool Bukkit Plugin
### 📜 Description / Описание:
This solution is designed for Bukkit/Paper servers in minecraft that need rating. 
Данное решение предназначено для Bukkit/Paper серверов в minecraft, которым нужен рейтинг.

### ⚠ Attention / Внимание ⚠:
```yaml
// For this plugin to work, you need a database exclusively MySQL 
// Для работы этого плагина нужна база данных исключительно MySQL
```


### 🎀 Importing to your project // Импорт в ваш проект:
#### Maven:
1. Install the project on your device / Установите проект себе на устройство
2. Via maven run install / Через maven запустите install
3. Add this to your pom.yml: / Добавьте в свой pom.yml это:
```xml
<dependency>
    <groupId>ru.vidoskim</groupId>
    <artifactId>rating</artifactId>
    <version>1.0-SNAPSHOT</version>
    <scope>provided</scope>
</dependency>
```

### 🎈 Use / Использование:

## 🎨 Futures / Возможности
### 👤 RatingUser:
- User model, stores **only** username and rating.
- Модель юзера, хранит в себе **только** username и рейтинг.
### 🔧 RatingUserService:
- Contains basic methods for working with the user model.
- Содержит основные методы для работы с моделью юзера.