1. В Product и ProductDto добавлено новое поле price - цена товара.
2. Стандартные методы репозитория реализованы в сервисном слое, определение  
    таких же собственных методов репозитория удалено.  
    Например, вместо findProductById используется имеющийся в CrudRepository findById.
3. Возможности репозитория расширены добавлением интерфейса 
    ProductRepositoryCriteriaExtension и его реализации 
    ProductRepositoryCriteriaExtensionImpl. Расширенные возможности: Criteria API.
4. Реализована работа с транзакциями.
    Методы POST, PUT контроллера ProductController в качестве тела запроса могут принимать список объектов.  
    Сервисный класс ProductService обозначен как @Transactional и его методы создания товаров, обновления и удаления  
    выполняются в отдельных транзакцях без возможности выполняться параллельно с другими транзакциями  
    (другие приостанавливаются), а методы для чтения могут забирать из бд только сохраненные данные с возможностью  
    выполняться в других транзакциях или вне транзакции.  
5. Переименованы маппинги контроллеров.