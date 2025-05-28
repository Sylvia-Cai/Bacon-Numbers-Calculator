# Bacon Numbers Calculator 🎬➡️🥓

A Java implementation for calculating Hollywood actors' Bacon Numbers - the degrees of separation from Kevin Bacon based on film collaborations.

## Features ✨
- Builds actor collaboration graph from movie data
- Computes Bacon Numbers using BFS algorithm
- Interactive query system for actor-specific paths
- Generates statistical distribution of Bacon Numbers
- Handles disconnected actors (infinite Bacon Numbers)

## Project Structure 📂

bacon-numbers/
├── data/
│ └── IMDB_Input.txt # Movie dataset (Format: Movie/Artist1/Artist2...)
├── src/
│ ├── model/ # Data models
│ │ ├── GraphBuilder.java
│ │ ├── BaconCalculator.java
│ │ └── PathStep.java
│ ├── utils/ # Utilities
│ │ └── DistributionPrinter.java
│ └── Main.java # Program entry
└── README.md

## Installation & Usage 🚀
### Method 1: Using IntelliJ IDEA
1. Clone this repository
2. Open project in IntelliJ
3. Set working directory to project root
4. Run `Main.java`

### Method 2: Command Line
```bash
# Compile
javac -d out src/*.java src/model/*.java src/utils/*.java

# Run (from project root directory)
java -cp out Main

## Output 📊
Bacon Number Distribution:
| Bacon number | Frequency |
|---|---|
| 0            | 1         |
| 1            | 1,324     |
| 2            | 70,717    |
| 3            | 40,862    |
| 4            | 1,591     |
| 5            | 125       |
**infinity**
620

Enter actor names (one per line, type 'E' to exit):
Woodward, Edward
'Woodward, Edward' has a Bacon number of 3
'Woodward, Edward' was in "'Breaker' Morant (1980)" with 'Kiefel, Russell'
'Kiefel, Russell' was in "Queen of the Damned (2002)" with 'Townsend, Stuart'
'Townsend, Stuart' was in "Trapped (2002)" with 'Bacon, Kevin'