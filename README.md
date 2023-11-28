# RuNumbersByWords

Библиотека для записи и склонения русских числительных из чисел.

### Как использовать

```java
Paradigm windowParadigm = new Paradigm(
        Gender.NEUTER, // Род - женский, мужской или средний
        "окно", // Одно окно (именительный падеж, единственное число)
        "окна", // Два окна (родительный падеж, единственное число)
        "окон"); // Пять окон (родительный падеж, множественное число)
RuNumbersByWords.incline(31, windowParadigm);
// тридцать одно окно
```

```java
Paradigm cupParadigm = new Paradigm(Gender.FEMININE,
        "кружка", "кружки", "кружек");
RuNumbersByWords.incline(4, cupParadigm);
// четыре кружки
```

Использование не ограничено одним словом:

```java
Paradigm circleOfHellParadigm = new Paradigm(Gender.MASCULINE,
        "круг ада",
        "круга ада",
        "кругов ада");
RuNumbersByWords.incline(9, circleOfHellParadigm);
// девять кругов ада
```

Для склонения только числительного есть перегрузки:

```java
RuNumbersByWords.incline(5);
// пять
RuNumbersByWords.incline(5_551);
// пять тысяч пятьсот пятьдесят один
RuNumbersByWords.incline(5_552, Gender.FEMININE);
// пять тысяч пятьсот пятьдесят две
RuNumbersByWords.incline(1, Gender.NEUTER);
// одно
```

Поддерживаются числа менее 1 миллиарда.