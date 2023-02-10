# ORI Planner

developed by: Justin Jordan

platform: Android

version: 0.1.1 pre-alpha

## A New hORIzon

I don't know about you, but I'm about as scatter-brained as it gets!
I'm constantly making a mess, I forget to feed myself, and I'm still waiting for Tile to make a glasses accessory.
Now I'm no scientologist: I can't fix your noggin, or mine for that matter.
However, I understand what it's like to feel a bit overwhelmed by life, and I made a little something to help.

## ORIginal Design

ORI Planner is designed for us scatter-brains.
Instead of a bORIng checklist, ORI divides our to-do lists into three parts:

- Sprints
- Tasks
- Goals

### Sprints: reORIent

Sprints are goals set at the beginning of the day.
Ideally, while sipping your morning coffee, you can reflect on what you want to achieve by the day's end and record it in ORI.
It could be a responsibility, like you just remembered it's trash day, or you know the floor needs vacuumed before family arrives for dinner tonight.
It could also be a personal goal, such as hitting the gym or giving someone a compliment.
Think of Sprints like RUNNING towards a daily success.

### Tasks: priORItize

Tasks are non-urgent responsibilities with a real deadline.
This is the most general function of a to-do app.
You can record future tasks such as Christmas shopping, getting an oil change for your car, or filing your taxes.
This app can also function as a calendar app, reminding you about doctor's appointments, lunch with friends, and important birthdays.
Think of Tasks like WALKING towards an eventual success.

### Goals: memORIes

Goals are a little unique.
Instead of a single achievable item, Goals are meant to hold multiple Tasks and display them as a group.
This helps to make large, daunting tasks more manageable.
You can also use these goals to visualize the steps needed to achieve something great.
For example, hit your fitness goals by getting a gym membership, exercising thrice in a week (using Sprints to motivate yourself), and learning a new healthy recipe.
Think of Goals like HIKING.
It will take a lot of WALKING, but you'll eventually end up wherever you want to go.

## Fellow Learners

This app was a product of passion, but it was also an opportunity to learn how to develop android applications.
In this section, I'll be taking notes about what I've learned in hopes of helping someone else understand.

### Navigating the Code

To get to the bulk of the code, the navigation path is:

> app > src > main > java > com > yyttrium > oriplanner

Here, you'll find two folders, labeled *data* and *ui*, and three loose files.

### Tools

Everything here was written in Kotlin, aside from some minor tweaks in XML and basic queries in SQL.
The app uses Jetpack, which is a suite of tools provided by Google for Android development.
The tools used in this app include:

- Compose, a complete UI framework that helps adhere to Material 3 Design standards
- Room, which generates and queries a local database, and sets up for web queries in the future
- Hilt, to manage the icky dependency injection in the background (and my hero)
- Lifecycle, which dynamically queries only necessary data and takes a load off the hardware
- Navigation, to handle changes in the UI and pass information between views
