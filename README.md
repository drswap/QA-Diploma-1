## Инструкция по запуску проекта

В рамках дипломного проекта требовалось автоматизировать тестирование комплексного сервиса покупки тура, взаимодействующего с СУБД и API Банка.

База данных хранит информацию о заказах, платежах, статусах карт, способах оплаты.

Для покупки тура есть два способа: с помощью карты и в кредит. В приложении используются два отдельных сервиса оплаты: OrdinaryPay 
и CreditPay.

### Тестовая документация включает:

- План тестирования;
- Отчёт по итогам тестирования
- Отчет по итогам автоматизации
  
### Запуск приложения и тестирование:

- Установить и запустить IntelliJ IDEA
- Установить и запустить Docker Desktop
- Перенести код проекта с Github в локальный репозиторий на IntelliJ IDEA
- Открыть проект в IntelliJ IDEA
- Запустить MySQL, PostgreSQL, NodeJS через терминал командой: docker-compose up
- В новой вкладке терминала запустить тестируемое приложение:
  
   - Для MySQL:

``` java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar ```

  - Для PostgreSQL:
    
``` java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar ```

Приложение должно открыться на порту 8080:

``` http://localhost:8080/ ```

- В новой вкладке терминала запустить тесты:

   - Для MySQL:
     
``` gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app ```

   - Для PostgreSQL:

 ``` gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app ```

## Формирование отчёта о тестировании

Для формирования отчётности через Allure в новой вкладке терминала вводим команду

``` gradlew allureServe ```
http://192.168.0.103:60298/index.html#
    
