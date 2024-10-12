# Note App

This app allows users to create, edit, and manage notes with the added functionality of setting reminders. 
Users will receive notifications as reminders for their notes. 
The app is developed following the principles of Clean Architecture to ensure scalability, maintainability, and separation of concerns.


## Clean architecture

- Data Layer:
  - This package contains classes related to data management, including data sources, repositories, mappers, and API clients.
- Domain Layer: 
  - This package contains the domain model objects, use cases, and interfaces defining business logic.
- Presentation Layer:
  - This package contains classes related to the UI layer, including screens, components, and view models.

    
## Color Palette
| Color              | Hex                                                             |
|--------------------| --------------------------------------------------------------- |
| Primary Color      | ![#2284F9](http://via.placeholder.com/10/303F9F/303F9F) #2284F9 |



## Download APK

## Demo

## Technical Topics

These are some of the technical topics of the application. Read more about them in the sections
below.

- Dependency Injection Using Hilt
- Room Database
- Structured Concurrency
- Compose UiToolkit
- String Obfuscation(doing)
- Background Resource Usage optimization
- Automated Test (Ui Test, Unit Test, Scenario Test)
- Alarm Manager


## Dependency Injection Using Hilt

Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual
dependency injection in your project.
Doing manual dependency injection requires you to construct every class and its dependencies by hand,
and to use containers to reuse and manage dependencies.

## Room

The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.
In particular, Room provides the following benefits:

- Compile-time verification of SQL queries.
- Convenience annotations that minimize repetitive and error-prone boilerplate code.
- Streamlined database migration paths.


## Structured Concurrency

Structured concurrency is a programming paradigm that provides a structured and deterministic 
approach to managing concurrent tasks and their lifecycles

The key principles of structured concurrency include:
- Scopes: Scopes are used to define the boundaries within which concurrent tasks are executed.
Scopes ensure that all tasks within a scope are properly managed and completed before the scope exits.
- Task Hierarchies: Structured concurrency enforces a hierarchical relationship between tasks,
where child tasks are spawned within a parent task. This ensures that child tasks are properly tracked 
and managed by their parent task.
- Task Cancellation: Structured concurrency provides a mechanism for propagating task cancellation.
When a parent task is canceled, all of its child tasks are automatically canceled, preventing resource leaks and allowing for proper cleanup.

This is what I mean by the term ***Structured Concurrency***.
Handled by using ***Kotlin Coroutines*** cancellation APIs.

## Compose UiToolkit
Building out the application with Compose Ui toolkit. 
The Jetpack Compose UI toolkit provides a modern and efficient way to build user interfaces in Android applications.
By adding custom components, you can create reusable UI elements that can be easily used in multiple screens, 
improving code reUsability and maintainability.
Based on UDF pattern implemented main component and screens

## Automated Test

Developed unit tests for important functionalities. 
Setup base module using Hilt Dependency injection framework to provide test objects , such as 
inMemoryDatabase
using Mockk library mocked some dependencies 

## Alarm Manager
Alarms give you a way to perform time-based operations outside the lifetime of your application.

## TODO 
- add splash screen to app
- add animation to components, such as transition or showing components.
- handle automated permission handler on ui test.
- Complete unit test, replace fake object instead of mock and other stubs.
- Complete scenario test to show dialogs(date picker and time picker)