# Simple Frustration Game Simulation

## Overview

This project implements a simulation of the "Simple Frustration" board game. The simulation adheres to the basic game rules and incorporates several variations for higher marks. In this game, two players (Red and Blue) move a piece along a board that comprises 18 main positions and a Tail of 3 positions (total 21 steps). The players take turns rolling dice and moving clockwise along their individual paths.

**Key Functional Requirements:**
- Each turn outputs the current player, dice roll, positions before and after the move, and the running turn total.
- The game ends when a player reaches or lands exactly on the END (depending on variations).
- Variations include:
    - **Exact Landing Rule:** A player must land exactly on the END to win; overshooting causes a bounce-back.
    - **Hit Sends Home:** When a player lands on an occupied square, the opponent is sent back to HOME.
    - **Dice Variations:** Support for using a single die or two dice.
    - **(Advanced) Large Board & Four-Player Mode:** The design is modular to allow these extensions.
    - **(Advanced) Undo Feature:** The design anticipates an Undo operation via the Command design pattern.

## Project Structure

The code is organized into the following packages:

- **board**
    - `GameBoard.java`: Defines the board interface.
    - `VariationGameBoard.java`: Implements the board with configurable variations.
    - `PlayerColor.java`: An enum for distinguishing players.
    - **Strategy Interfaces and Implementations:**
        - `EndRule.java`, `DefaultEndRule.java`, `ExactLandingEndRule.java`: Encapsulate the logic for handling moves that reach or overshoot the END.
        - `HitRule.java`, `HitIgnoredRule.java`, `HitSendHomeRule.java`: Encapsulate how to handle hits (when one player lands on the opponent).

- **dice**
    - `DiceShaker.java`: An interface for dice rolling.
    - `SingleDiceShaker.java`: Returns random values simulating a 6-sided die.
    - `FixedDiceShaker.java`: Returns predetermined dice values for simulating specific scenarios.

- **main**
    - `VariationMain.java`: Drives the simulation using a fixed dice sequence and configured variations.

## Software Design Techniques

### Object-Oriented Principles
- **Encapsulation:**  
  Each class encapsulates its own state and behavior. For example, `VariationGameBoard` manages the board state (player positions and turn counts) and delegates move adjustment to strategy objects.

- **Single Responsibility Principle:**  
  Every class is designed to do one specific task:
    - **EndRule implementations** only calculate new positions based on the dice roll.
    - **HitRule implementations** are solely responsible for handling the outcome when a hit occurs.
    - **DiceShaker** classes are only responsible for providing dice values.

- **Polymorphism and Inheritance:**  
  The use of interfaces such as `EndRule`, `HitRule`, and `DiceShaker` allows the system to choose different strategies at runtime (for example, switching between an exact landing rule and a default rule).

### Design Patterns
- **Strategy Pattern:**  
  The variations for handling the END rule and HIT rule are implemented as strategies.
    - `EndRule` is an interface with implementations such as `DefaultEndRule` (for overshooting wins) and `ExactLandingEndRule` (requiring exact landing).
    - `HitRule` is an interface with implementations like `HitIgnoredRule` (ignores hits) and `HitSendHomeRule` (sends the opponent home).

  This pattern provides flexibility to change the game behavior without modifying the board class directly.

- **Decorator Pattern (Anticipated):**  
  Although not fully implemented in this version, the design can be extended using the Decorator pattern to switch between a single-die and two-dice configuration seamlessly.

- **Command Pattern (Anticipated for Undo):**  
  The design anticipates an Undo feature by suggesting that each move could be encapsulated in a command object (e.g., an `AdvanceCommand`) that records state changes and can reverse them.

## Terminology and Conventions
- **Encapsulation, Inheritance, Polymorphism:**  
  We use interfaces and concrete classes to ensure that each component of the game (board, dice, rules) is modular and interchangeable.

- **Strategy Pattern:**  
  This pattern is used to encapsulate algorithms (the rules for landing exactly or handling hits) and make them interchangeable at runtime.

- **Code Conventions:**  
  The code adheres to Java coding standards with proper naming conventions, use of Javadoc comments, and consistent formatting for readability and maintainability.

## Running the Simulation

To run the simulation:
1. **Compile all Java files:**  
   Ensure that the packages (`board`, `dice`, and `main`) are in your classpath.
2. **Run the Main Class:**  
   Execute `main.VariationMain` to see a simulation run with the following configuration:
    - **Exact Landing Rule:** Enabled
    - **Hit Sends Home:** Enabled
    - **Dice:** Uses a fixed sequence (e.g., {12,12,7,11,3,3}) for demonstration.

**Sample Output:**
