# Dog Breeds App

An app that would use the Dog Breeds API to allow users to browse through the list of dog breeds,
their pictures, and add pictures to favorites that is developed based on Clean architecture.


## Clean architecture

- Data Layer:
  - This package contains classes related to data management, including data sources, repositories, mappers, and API clients.
- Domain Layer: 
  - This package contains the domain model objects, use cases, and interfaces defining business logic.
- Presentation Layer:
  - This package contains classes related to the UI layer, including screens, components, and view models.

    
## Color Palette
| Color              | Hex                                                                |
|--------------------| ------------------------------------------------------------------ |
| Primary Color      | ![#205fa6](http://via.placeholder.com/10/303F9F/303F9F) #205fa6 |
| OnPrimary Color    | ![#ffffff](http://via.placeholder.com/10/1A237E/1A237E) #ffffff |
| Secondary Color    | ![#555f71](http://via.placeholder.com/10/FFA61C/FFA61C) #555f71 |
| OnSecondary Color  | ![#ffffff](http://via.placeholder.com/10/FF8741/FF8741) #ffffff |
| Surface Color      | ![#fdfbff](http://via.placeholder.com/10/FFFFFF/FFFFFF) #fdfbff |
| OnSurface Color    | ![#1b1b1d](http://via.placeholder.com/10/FFFFFF/FFFFFF) #1b1b1d |
| Tertiary Color     | ![#1b1b1d](http://via.placeholder.com/10/FFFFFF/FFFFFF) #6e5676 |
| OnTertiary Color   | ![#1b1b1d](http://via.placeholder.com/10/FFFFFF/FFFFFF) #ffffff |
| Background Color   | ![#1b1b1d](http://via.placeholder.com/10/FFFFFF/FFFFFF) #fdfbff |
| OnBackground Color | ![#1b1b1d](http://via.placeholder.com/10/FFFFFF/FFFFFF) #1b1b1d |


## Download APK

## Demo


## Technical Topics

These are some of the technical topics of the application. Read more about them in the sections
below.

- Dependency Injection Using Hilt
- Retrofit
- Moshi
- Structured Concurrency
- Compose UiToolkit
- String Obfuscation(doing)
- Background Resource Usage optimization
- Automated Test (Ui Test, Unit Test, Scenario Test)


## Dependency Injection Using Hilt

Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual
dependency injection in your project.
Doing manual dependency injection requires you to construct every class and its dependencies by hand,
and to use containers to reuse and manage dependencies.

## Retrofit

A type-safe HTTP client for Android and Java

## Moshi

Moshi is a modern JSON library for Android, Java and Kotlin. It makes it easy to parse JSON into 
Java and Kotlin classes:

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
inMemoryDatabase, MockWebServer
using Mockk library mocked some dependencies 


## TODO 

- Complete unit test, replace fake object instead of mock and other stubs
- Complete scenario test,
- Handle sub breeds,
- Refactor data classes