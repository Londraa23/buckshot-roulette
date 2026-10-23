# Buckshot Roulette (Java CLI)

A console clone of *Buckshot Roulette*, made in Java for learning purposes. You face the Computer in a turn-based game of luck, memory and nerve: a shotgun loaded with live and blank shells, and only one of you walks away.

> This is a fan-made project and is not affiliated with the original game by Mike Klubnika.

## Requirements

- Java 21 or higher (the code uses `List.removeFirst()`)

## How to run

```bash
javac Main.java
java Main
```

Or open the project in your IDE and run `Main`.

## How to play

- You face the Computer in **3 rounds**. In each round, both of you start with the same number of hearts.
- The shotgun is loaded with **live** and **blank** shells in a random order. You know how many of each there are, but not the order.
- On your turn you choose who to shoot: **yourself** or **the Computer**.
  - **Live shell:** whoever gets shot loses one heart, and the turn passes.
  - **Blank shell:** nobody gets hurt.
    - If you shot yourself, you shoot again.
    - If you shot your enemy, the turn passes.
- When the shotgun is empty, it is reloaded.
- The first one to reach 0 hearts loses the round.
- Win all 3 rounds to win the game and earn cash.

## Rounds

| Round | Hearts | Shells per load | Live / Blank |
|-------|--------|-----------------|--------------|
| 1     | 2      | 4               | 2 / 2        |
| 2     | 4      | 6               | 3 / 3        |
| 3     | 5      | 8               | 4 / 4        |

The values live in the `ROUND_CONFIG` array, so difficulty can be tweaked without touching the game logic.

## Tips

- Count the shells: once all the live ones are out, the rest are blanks.
- If you are sure the next shell is blank, shooting yourself gives you an extra turn.
- If there are more live shells than blanks left, shooting the Computer is the safer choice.

## Roadmap

- [ ] Items (magnifying glass, handcuffs, saw, beer...), each one in its own class - Not yet, but soon
- [ ] Save cash to a file so it persists between sessions - Not yet, but soon
- [ ] Smarter Computer decisions based on the remaining shells - Not yet, but soon
- [ ] Split the game into classes (`Game`, `Shotgun`, `Player`) Not yet, but soon

## Author

Made by [Londraa23](https://github.com/Londraa23).
