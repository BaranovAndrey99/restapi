1. Создана сущность KindOfProduct для храниня в бд.  
2. Создана сущность KindOfProductDto для получения тела запроса.  
3. Создан контроллер("/assortment") для обработки запросов для видов товаров.
4. Создан сервисный слой для работы с видами товаров и соответствующий сервис для работы с их DTO.
5. ProductCheckingFailedException - ошибка, возникающая при создании товара, имя или тип которого  
    недопустимы(не существуют в бд).  