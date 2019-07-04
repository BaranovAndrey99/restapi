1.  DI.  
    2.1. Constructor based DI между ProductController и ProductService.  
    2.2. Constructor based DI между ProductService и ProductRepository.  
    2.3. Constructor based DI между ProductService и DataCheckingService.  
    2.4. Класс валидации переименован в DataCheckingServiceImpl и стал реализацией интерфейса  
        (как и другие сервисы). Методы checkExistenceOfName и checkExistenceOfType  
        реализованы в виде аннотаций для @Valid(@NameExistence и @TypeExistence).  
    2.5. Все классы "Impl" в сервисном слое вынесены в отдельный package.
    2.5. Реализации методов сервиса помечены аннотацией @Override.
2.  Тип возвращаемого значения метода getAllProducts() изменено с ArrayList на List.
3.  DTO.  
    3.1. @Entity Product вынесен в отдельный package. Для @RequestBody и ResponseEntity  
        cоздаются соответствующие классы в dto package.  
    3.2. Создан дополнительный @Service для dto-маппингда c Constructor based DI.  
    3.3. Для использования ModelMapper создается его бин(для этого создается конфиг.класс).  
4.  Весь Javadoc исправлен.
5.  Валидация.
    5.1. Удален пакет transfer. 
    5.2. Реализвана валидация DTO @Valid - проверка на null и проверка существования типа и  
        имени товара.
    5.3. Для валидации @Valid создан отдельный @ControllerAdvice.
6.  MainExceptionHandler перенесён в контроллеры и создает ResponseEntityDto, передавая  
        его в сервисы, а тот, в свою очередь, в контроллер.
7.  Создана сущность ResponseEntityDto для использования в качестве ResponseEntity -  
    унифицированный формат ответа на запрос.  
8.  Содержимое flyway.properties перенесено в application.properties.
9.  Файлы gradlew добавлены в gitignore.  