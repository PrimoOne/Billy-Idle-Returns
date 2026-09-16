# Billy Idle Returns

**Billy Idle Returns** is a 2D action game originally created as a college project in Java.

The original game follows Billy through a series of levels where he collects gems, fights enemies, avoids hazards, and eventually faces an evil version of himself. It was built from scratch on top of a simple Java/Swing framework provided as part of the original project.

The game currently works, but the codebase is very much a product of its time. A lot of the game was built quickly to satisfy the requirements of the college project, and some of the original design decisions won't hold up well as the game grows.

This repository is an attempt to bring the project back to life and turn it into a proper foundation for a future 2D action game. The exact direction of the game hasn't been decided yet — for now, the focus is on cleaning up what already exists and making it easier to build on.

---

## How It Currently Works

The current game follows a simple MVC-style structure.

The **Model** is where most of the actual game happens. It keeps track of the player, enemies, bullets, swords, gems, tiles, spikes and other objects in the game world. It also handles things like movement, gravity, collision detection, combat, level transitions and the rules for completing each level.

The **Controller** handles keyboard input. It keeps track of which keys are being pressed, and the Model checks that input when updating the game.

The **Viewer** is responsible for drawing the game. It takes the current state of the Model and renders the appropriate sprites, animations, background and UI.

`MainWindow` ties everything together and runs the main game loop. Each iteration updates the game state and then redraws the screen.

In simplified terms:

```text
Keyboard
   ↓
Controller
   ↓
Model
   ↓
Game State
   ↓
Viewer
   ↓
Screen
```

The original implementation is fairly straightforward, but the Model has grown to contain a lot of unrelated responsibilities. Levels are also constructed directly in Java code, and many objects are represented using the same generic `GameObject` class.

That works for a small game, but it will become increasingly difficult to maintain as more content is added.

---

## Current File Structure

The current project is still largely based around the structure of the original college project:

```text
src/
├── MainWindow.java
├── Model.java
├── Viewer.java
├── Controller.java
├── GameObject.java
├── Point3f.java
├── Vector3f.java
└── util/
```

There is also a `res/` directory containing the game's sprites, backgrounds, sounds and other resources.

The important parts are:

* **`MainWindow.java`** — Starts the application and runs the game loop.
* **`Model.java`** — Contains most of the game's logic and state.
* **`Viewer.java`** — Handles rendering and animation.
* **`Controller.java`** — Handles keyboard input.
* **`GameObject.java`** — Represents most things that exist in the game.
* **`Point3f.java` / `Vector3f.java`** — Handle positions and movement.

This structure was fine for the original project, but `Model.java` in particular has become something of a catch-all for the game.

---

## Where We're Going

The goal isn't to build a massive game engine. It's to give this game a structure that makes future development easier.

The exact structure will evolve as the project does, but we're aiming for something closer to:

```text
src/
├── game/
├── entities/
├── world/
├── physics/
├── combat/
├── items/
├── input/
├── rendering/
├── audio/
└── levels/

res/
├── sprites/
├── audio/
├── backgrounds/
└── ...
```

The biggest changes will be separating the different responsibilities currently living inside `Model.java`, giving entities such as the player and enemies their own structure, and moving level design out of Java code and into data that can be loaded by the game.

The end goal is that creating a new level, enemy, item or weapon shouldn't require digging through the entire game to make it work.

For now, **the game itself is still being figured out**. The first priority is making the codebase something we can confidently build on.
