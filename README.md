## Overview
This is a source code for a game of pong. 2 players should play this game. 
Controls: 

- `w`, `s` for first player; 
- `up arrow`, `down arrow` for second player

## Getting Started
The project uses java with javafx libraries.

The project is made using VScode IDE. To recreate same environment with Vscode: 

- Install javafx sdk from https://gluonhq.com/products/javafx/
- Reference libraries from lib folder of downloaded sdk (using vscode)
- Add configuration file in vscode. `Run > Add Configuration`, then press `Java`
- After line 17 in `.vscode/launch.json` add `"vmArgs": "--module-path [path-to-your-lib-folder] --add-modules javafx.controls,javafx.fxml",`
  - For example `"vmArgs": "--module-path C:/Users/erlan.bazarov_2023/Desktop/CS/OOP/openjfx-11.0.2_windows-x64_bin-sdk/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml",`

## Features

- [x] Create design
- [x] Create moving physics for the ball
- [x] Create collision physics for the players and ball
- [x] Create controls for player1 and player2
- [x] Track who won and show winning player on screen

E.B.T
