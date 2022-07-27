# Scala
Команда "gradle build" собирает проект.
Если собрать не получилось, то попробуйте "./gradlew build".

Команда "gradle test" запускает тесты.
Если запуск неудачен, то попробуйте "./gradlew test".

Все файлы с примерами для запуска лежат по пути "app/main/resources/examples".

Список доступных файлов:
   * fact.sc
   * firstExample.sc
   * secondExample.sc
   * HelloWorld.sc
</br>

Чтобы запустить Main введите в консоли "./gradlew run --args='[Options] [FileName]'"

    Options:
        --dump-tokens — вывести результат работы лексического анализатора
        --dump-ast — вывести AST
        --dump-asm — вывести ассемблер

Например: ./gradlew run --args='--dump-tokens HelloWorld.sc'

Чтобы сгенерировать исполняемый файл введите в консоли "./prog.sh [FileName]"

Чтобы запустить исполнемый файл введите в консоли "./a.out"
