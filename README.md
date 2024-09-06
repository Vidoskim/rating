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
3. Add this to your pom.yml / Добавьте в свой pom.yml это:
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

## ⛑ Examples / Примеры:

### Example join listener / Пример ивента захода:
```java
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.vidoskim.rating.model.RatingUser;
import ru.vidoskim.rating.service.RatingUserService;

@RequiredArgsConstructor
public class ExampleListener implements Listener {
    private final RatingUserService ratingUserService;

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        // Get the user variable / Получаем переменную юзера
        // By the way, the variable is never null, so you can not worry (in case of absence of a player in the database - the plugin itself will create it)
        // Переменная кстати никогда не бывает null, так что можете не волноваться (в случае отсутствия игрока в БД - плагин сам его создаст)
        RatingUser user = ratingUserService.getUser(player);
        
        // Dropping to the starting rating / Сбрасываем на стартовый рейтинг
        ratingUserService.resetPlayerRating(player);
        
        // Setting player rating / Устанавливаем рейтинг игроку
        ratingUserService.setPlayerRating(player, 0);
        
        // Adding player rating / Добавляем игроку рейтинг
        ratingUserService.addPlayerRating(player, 1);
        
        // Taking player rating / Забираем у игрока рейтинг
        ratingUserService.takePlayerRating(player, 1);
    }
}
```