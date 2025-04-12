📱 Android Clean Architecture Project

This project is structured based on Clean Architecture principles, designed for scalability, testability, and modularity. It's suitable for large-scale applications with multiple features.

🧱 Layers Overview

       +---------------------------+
       |       Presentation        |
       |  (ViewModel, UI, State)   |
       +------------▲--------------+
                    │
                    │ uses
                    ▼
       +---------------------------+
       |          Domain           |
       |  (UseCases, Entities,     |
       |   Interfaces / Contracts) |
       +------------▲--------------+
                    │
         implements │
                    ▼
       +---------------------------+
       |           Data            |
       | (Repositories, Mappers,   |
       |  API, DB, Cache, etc.)    |
       +---------------------------+

🗂️ Project Structure (Login Feature Example)

login/
├── data/
│   ├── model/
│   │   └── UserDto.kt
│   ├── mapper/
│   │   └── UserMapper.kt
│   ├── repository/
│   │   └── LoginRepositoryImpl.kt
│   └── source/
│       └── remote/AuthApi.kt
│
├── domain/
│   ├── entity/
│   │   └── UserEntity.kt
│   ├── model/
│   │   └── RequestLoginParams.kt
│   ├── repository/
│   │   └── LoginRepository.kt
│   └── use_case/
│       └── RequestLoginUseCase.kt
│
└── presentation/
    ├── LoginViewModel.kt
    ├── LoginActions.kt
    ├── LoginEvents.kt
    ├── LoginState.kt
    └── LoginScreen.kt

🔄 Flow Explanation
LoginViewModel triggers RequestLoginUseCase
RequestLoginUseCase calls LoginRepository
LoginRepositoryImpl makes an API call via AuthApi
Result is mapped from UserDto to UserEntity
State is updated and presented on the UI

✅ Module Dependencies
presentation ➡️ depends on domain
data ➡️ depends on domain
domain ➡️ pure Kotlin module (no external dependencies)

⚙️ Technologies Used
Jetpack Compose / ViewModel
Hilt for Dependency Injection
Retrofit for networking
Kotlin Coroutines / Flow
Modular architecture with Gradle

📌 Best Practices
Usecase should contain pure business logic
Repository interfaces in domain to decouple data source
All mapping logic lives in mapper classes
UI components never access data layer directly

🧪 Testability
Domain and UseCases are easily unit-testable
Repository can be mocked
UI tests can be done with Jetpack Compose testing libraries

🔐 Secure
Sensitive data (e.g., tokens) are kept away from UI
Usecases validate input and handle errors properly