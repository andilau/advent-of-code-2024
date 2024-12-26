# Advent of Code Starter

This project contains solutions to the [Advent of Code 2024](https://adventofcode.com/2024) challenge in [Kotlin](https://kotlinlang.org/).
Advent of Code is an Advent calendar of small programming puzzles by [Eric Wastl](http://was.tl/).

## Solutions

- Day 1: [Historian Hysteria](https://adventofcode.com/2024/day/1) -- [Day1.kt](https://github.com/andilau/advent-of-code-2024/blob/main/src/main/kotlin/days/Day1.kt)
- Day 2: [Red-Nosed Reports](https://adventofcode.com/2024/day/2) -- [Day2.kt](https://github.com/andilau/advent-of-code-2024/blob/main/src/main/kotlin/days/Day2.kt)
- Day 3: [Mull It Over](https://adventofcode.com/2024/day/3) -- [Day3.kt](https://github.com/andilau/advent-of-code-2024/blob/main/src/main/kotlin/days/Day3.kt)
- Day 4: [Ceres Search](https://adventofcode.com/2024/day/4) -- [Day4.kt](https://github.com/andilau/advent-of-code-2024/blob/main/src/main/kotlin/days/Day4.kt)
- Day 5: [Print Queue](https://adventofcode.com/2024/day/5) -- [Day5.kt](https://github.com/andilau/advent-of-code-2024/blob/main/src/main/kotlin/days/Day5.kt)
- Day 6: [Guard Gallivant](https://adventofcode.com/2024/day/6) -- [Day6.kt](https://github.com/andilau/advent-of-code-2024/blob/main/src/main/kotlin/days/Day6.kt)
- Day 7: [Bridge Repair](https://adventofcode.com/2024/day/7) -- [Day7.kt](https://github.com/andilau/advent-of-code-2024/blob/main/src/main/kotlin/days/Day7.kt)
- Day 8: [Resonant Collinearity](https://adventofcode.com/2024/day/8) -- [Day8.kt](https://github.com/andilau/advent-of-code-2024/blob/main/src/main/kotlin/days/Day8.kt)
- Day 10: [Hoof It](https://adventofcode.com/2024/day/10) -- [Day10.kt](https://github.com/andilau/advent-of-code-2024/blob/main/src/main/kotlin/days/Day10.kt)
- Day 12: [Garden Groups](https://adventofcode.com/2024/day/12) -- [Day12.kt](https://github.com/andilau/advent-of-code-2024/blob/main/src/main/kotlin/days/Day12.kt)
- Day 14: [Restroom Redoubt](https://adventofcode.com/2024/day/14) -- [Day14.kt](https://github.com/andilau/advent-of-code-2024/blob/main/src/main/kotlin/days/Day14.kt)
- Day 18: [RAM Run](https://adventofcode.com/2024/day/18) -- [Day18.kt](https://github.com/andilau/advent-of-code-2024/blob/main/src/main/kotlin/days/Day18.kt)
- Day 22: [Monkey Market](https://adventofcode.com/2024/day/22) -- [Day22.kt](https://github.com/andilau/advent-of-code-2024/blob/main/src/main/kotlin/days/Day22.kt)
- Day 23: [LAN Party](https://adventofcode.com/2024/day/23) -- [Day23.kt](https://github.com/andilau/advent-of-code-2024/blob/main/src/main/kotlin/days/Day23.kt) 1/2
- Day 25: [Day 25: Code Chronicle](https://adventofcode.com/2024/day/25) -- [Day25.kt](https://github.com/andilau/advent-of-code-2024/blob/main/src/main/kotlin/days/Day25.kt)

### Features

* Gradle setup so you can run a specific day or all days on the command line (see **Running**)
* Timings for each part of each day
* Input for each day automatically exposed in String and List form
* [JUnit 5](https://junit.org/junit5/) and [AssertJ](https://assertj.github.io/doc/) test libraries included (see **Testing**)
* Starter .gitignore

### Running

Project is already setup with gradle. To run the app:

* Navigate to top-level directory on the command line
* Run `./gradlew run` to run all days
* Run `./gradlew run --args $DAY` where `$DAY` is an integer to run a specific day
* Run `./gradlew run --args "$DAY1 $DAY2 $ANOTHERDAY"` to run a subset of days

### Testing

Project includes JUnit and AssertJ and a stub unit test to get you going. To run all tests:

* Navigate to top-level directory on the command line
* Run `./gradlew test`
* Add `--info`, `--debug` or `--stacktrace` flags for more output

By default, instantiations of `Day` classes in tests will use the input files in `src/test/resources`, _not_ those
in `src/main/resources`. This hopefully gives you flexibility - you could either just copy the real input
into `src/test/resources` if you want to test the actual answers, or you could add a file of test data based on the
examples given on the Advent of Code description for the day. The stub `Day1Test` class shows a test of the
functionality of `Day1` where the test input differs from the actual input.

### Architecture

* Inputs go into `src/main/resources` and follow the naming convention `input_day_X.txt`
* Solutions go into `src/main/kotlin/days` and implement the `Puzzle` interface
* Solutions follow the naming convention `DayX`
* It is assumed all solutions will have two parts
* It is assumed that the puzzle input is provided through the primary constructor
* You can use the `InputReader` methods for reading input
* To get started simply replace `src/main/resources/input_day_1.txt` with the real input and the solutions in `Day1` with your own