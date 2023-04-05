# OOP
Белозёров Семён

# I семестр

## 1. Знакомство с языком программирования Java

### 1.1. Пирамидальная сортировка
Реализовать классический алгоритм пирамидальной сортировки c набором тестов.

### 1.2. Определить наличие подстроки в файле
На вход подаётся имя файла и строка, которую надо найти. Строка может содержать буквы любого алфавита в кодировке UTF-8.
Реализовать функцию, определяющую индекс начала каждого вхождения заданной подстроки.

## 2. Контейнеры

### 2.1. Стек (stack)
Реализовать класс стек с поддержкой операций push (добавить элемент), pushStack(добавить элементы контейнера стек) pop(взять элемент), popStack(взять заданное количество элементов в виде объекта стек), и count(узнать количество элементов). Созданный контейнер можно использовать для любых типов, кроме примитивных.

### 2.2. Дерево (Tree)
Реализовать Generic-класс коллекции классического дерева. Дерево должно поддерживать добавление и удаление элементов, а также стандартный механизм итерирования элементов коллекции методом обхода в ширину и глубину с исключительной ситуацией ConcurrentModificationException.
Созданный класс должен реализовывать базовый интерфейс коллекции(java.util.Collection) с поддержкой Stream API. Используя Stream API из дерева строк получить список всех элементов, которые являются листами и содержат заданную подстроку.

## 3. ???

### 3.2. Зачетная книжка
Реализовать класс электронная зачетной книжки студента ФИТ и обеспечит ь следующие функции:

1) Текущий средний бал за все время обучения;
2) Может ли студент получить «красный» диплом с отличием;
3) Будет ли повышенная стипендия в этом семестре.

Требования для диплома с отличием:
- 75% оценок в приложении к диплому (последняя оценка) — «отлично»
- Нет в зачетной книжке итоговых оценок «удовлетворительно»
- Квалификационная работа защищена на «отлично»

## 4. Консольные приложения

### 4.1. Калькулятор
Реализовать калькулятор инженера для вещественных чисел. Пользователь вводит на стандартный поток ввода выражение в префиксном виде. Калькулятор вычисляет значение и   выводит на стандартный поток вывода. Кроме стандартных операций (+, -, *, /) есть набор функций (log, pow, sqrt, sin, cos).
С помощью динамической загрузки библиотеки(pІugіn) переопределить поведение базовых операций. Добавить поддержку работы с градусами и комплексными числами.

### 4.2. Записная книжка
Сделать записную книжку с набором функций доступных с командной строки:
- Добавить запись
- Удалить запись
- Вывести все записи, отсортированные по времени добавления
- Вывести отсортированные по времени добавления записи из заданного интервала времени, содержащие в заголовке ключевые слова. Данные записной книги сериализуются в файл формата JSON. Для работы с форматом JSON рекомендуется использовать библиотеки Jackson или Gson. Для анализа параметров командной строки, так же рекомендуется использовать сторонние библиотеки.


# II семестр

## 1. Многопоточные вычисления

### 1.1. Простые числа
У вас имеется массив целых чисел, необходимо определить есть ли в этом массиве хотя бы одно не простое (делится без остатка только на себя и единицу).
Необходимо предоставить три решения задачи: последовательное, параллельное решением с применением класса Thread с возможностью задания количества используемых потоков и параллельным решением с применением ParallelStream.
После выполнения программной реализации, необходимо подготовить тестовый пример с набор простых чисел, который продемонстрирует ускорение вычислений за счет использования многоядерной архитектуры центрального процессора. Полученные времена выполнения программ на созданном тестовом примере необходимо нанести на график (1 точка — последовательное исполнение, n точек — параллельное исполнение Thread для разного количества используемых ядер, 1 точка — параллельное исполнение ParallelStream).
Преподавателю семинарских занятий объяснить выбор тестового набора данных и полученный график, оценить долю времени последовательного исполнения программы.

## 2. Автоматизация производства
### 2.1. Пиццерия
У вас в штате есть «N» пекарей, занимающихся производством пиццы, с разным опытом работы и «M» курьеров, занимающихся доставкой пиццы заказчику, один склад готовой продукции размера «T». Производственный процесс выглядит следующим образом:

- поступает заказ на пиццу в общую очередь;
- пекарь нажимает на кнопку и берет заказ в исполнение;
- когда пицца готова, пекарь нажимает на кнопку с готовностью пиццы и пытается зарезервировать место на складе. Если склад полностью заполнен, пекарь ожидает свободное место;
- пекарь передает пиццу на склад и может продолжить работу;
- курьер после очередного заказа обращается на склад и берет одну или несколько пицц в доставку, но не больше объема своего багажника. Если склад пуст ожидает появления готовых пицц;
- после доставки очередной пиццы курьер отмечает что заказ выполнен;
- при выполнении очередного действия, система выводит на стандартный вывод сообщение: [номер заказа], [состояние];
- параметры работников пиццерии загружаем и файла формата JSON.


## 3. Графический пользовательский интерфейс
Необходимо реализовать в игрe с использованием Java FX. Наличие тестов для классов, отвечающих за элементы графического пользовательского интерфейса, не обязательно.
### 3.1. Игра «Змейка»

- змейка (упорядоченный набор связанных звеньев с явно выделенными концами – головой и хвостом) передвигается по полю «N» x «M»;
- в начале игры змейка состоит из одного звена;
- перемещение змейки состоит в добавлении одного звена к ее голове в требуемом направлении (в направлении ее движения) и удалении одного звена хвоста;
- если при перемещении змейки ее голова натыкается на препятствие, то игра проиграна;
- в каждый момент времени на игровом поле находится «T» элементов еды, занимающей одну клетку поля;
- если при перемещении голова змейки натыкается на еду, то змейка ее «съедает» и вырастает на одно звено, а для выполнения предыдущего правила на поле в произвольном свободном месте автоматически появляется новая порция еды;
- выигрыш состоит в достижении змейкой длины в «L» звена.
