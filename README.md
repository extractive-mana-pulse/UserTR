📱 Asgardia Team – Technical Task

This repository contains my solution for the technical assignment provided by Asgardia Team.
The project focuses on building a Kotlin Multiplatform Mobile application with clean architecture, offline support, and modern UI practices.

The implementation follows the main requirements described in the technical task while keeping the structure scalable and maintainable.

### 🚀 Project Overview

The goal of this project is: Fetches users and posts from a public API, Caches data locally, Supports offline mode, Allows marking posts as favorites, Demonstrates proper state handling (Loading / Success / Error), Shares business logic between Android and iOS, The goal of this project is to showcase architecture decisions, state management, and KMM integration rather than just UI implementation.

### 📹 Demo Videos

<details>
  <summary>Android app</summary>
  
https://github.com/user-attachments/assets/7dff0c32-1e23-4535-b6b5-a19e7fdff38e

</details>

<details>
  <summary>iOS app</summary>
  
https://github.com/user-attachments/assets/eb1dacac-b1e3-44ac-afba-b7680d345b47

</details>

### 📱 App Screenshots

<details>
  <summary>UserList Screen</summary>
  
  | Android | iOS |
  |------|----------|
  | <img width="260" alt="image" src="https://github.com/user-attachments/assets/5284e0dc-9ec0-4fa7-a3cb-14e5de8b8b6b" /> | <img width="260" alt="Simulator Screenshot - iPhone 17 - 2026-03-03 at 23 04 12" src="https://github.com/user-attachments/assets/6ba5d9c8-f1c1-4f9c-8bdc-35e8f3c68654" /> |

</details>

<details>
  <summary>PostList Screen</summary>
  
  | Android | iOS |
  |------|----------|
  | <img width="260" alt="image" src="https://github.com/user-attachments/assets/3b5532a2-a61b-452f-aaf4-a71edd2c6408" /> | <img width="260" alt="Simulator Screenshot - iPhone 17 - 2026-03-03 at 23 04 21" src="https://github.com/user-attachments/assets/dc0fae78-01a7-46ca-9515-cb3c66ce9986" /> |

</details>

<details>
  <summary>PostDetail Screen</summary>
  
  | Android | iOS |
  |------|----------|
  | <img width="260" alt="image" src="https://github.com/user-attachments/assets/d6442f26-f5b2-47e0-a8e2-e7fca86a3195" /> | <img width="260" alt="Simulator Screenshot - iPhone 17 - 2026-03-03 at 23 04 24" src="https://github.com/user-attachments/assets/e583bf2d-a467-4e30-a59c-07457ce3f473" /> |

</details>

<details>
  <summary>Favorite Screen</summary>
  
  | Android | iOS |
  |------|----------|
  | <img width="260" alt="image" src="https://github.com/user-attachments/assets/c7361677-8930-4bde-9667-b5c99d255d71" /> | <img width="260" alt="Simulator Screenshot - iPhone 17 - 2026-03-03 at 23 04 17" src="https://github.com/user-attachments/assets/1ea5fb5b-f412-4c58-a1bc-698f8eda0d8e" /> |

</details>

### 🧱 Tech Stack

- KMP & CMP
- Ktor 
- Room
- Jetpack Compose – UI (Android & iOS)
- Coroutines + Flow
- MVVM + Clean Architecture
- Dependency Injection (Koin)
- Navigation Compose

### 🌐 API

- _https://jsonplaceholder.typicode.com_

### 🗄 Offline-First Approach

All fetched data is **cached** locally. The app displays the last successful data in **offline mode**. Favorite posts are stored in a **separate local table**. Local database acts as the single source of truth.

### 📲 Screens

* Splash Screen
* User List Screen
* Post List Screen
* Post Detail Screen
* Favorites Screen

### Each screen properly handles:
* Loading state
* Success state
* Error state

### 🏗 Architecture

The project follows Clean Architecture principles:

presentation/
domain/
data/

### Clear separation of concerns

- Repository pattern
- Reactive UI state using Flow
- Scalable and testable structure
