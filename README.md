# CronConverter

Программа позволяет преобразовывать список дат со временем в cron (формат от Spring Framework cron). Список харнится в файле. 

По реализации:
Реализовать интерфейс DatesToCronConverter (включает два метода convert() и getImplementationInfo()). 
Метод convert() на вход принимает список дат (порядок не важен) формата (“yyyy-MM-dd'T’HH:mm:ss”), на выход строка вида “0 * * * * MON”. 
Метод getImplementationInfo() - возвращает информацию о реализации интерфейса (ФИО, имя класса реализации, пакет, ссылка на github).
Класс реализации находиться в пакете формата com.lastname.firstname, где lastname и firstname Ваша фамилия и имя соответственно.

Готовое решение также запаковано в jar архиве


Примеры: 
Input:
	"2022-01-25T08:00:00"
	"2022-01-25T08:30:00"
	"2022-01-25T09:00:00"
	"2022-01-25T09:30:00"
	"2022-01-26T08:00:00"
	"2022-01-26T08:30:00"
	"2022-01-26T09:00:00"
	"2022-01-26T09:30:00"

	Output:
	"0 0/30 8-9 * * *"

2. Input:
	"2022-01-24T19:53:00"
"2022-01-24T19:54:00"
"2022-01-24T19:55:00"
"2022-01-24T19:56:00"
"2022-01-24T19:57:00"
"2022-01-24T19:58:00"
"2022-01-24T19:59:00"
"2022-01-24T20:00:00"
"2022-01-24T20:01:00"
"2022-01-24T20:02:00"

Output: 
"0 * * * * MON"
