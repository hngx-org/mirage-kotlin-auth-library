# Using the AuthLibrary Kotlin Library in Your Project

The AuthLibrary is a Kotlin library designed to simplify user authentication and related tasks in your Android or Kotlin-based project for the HNG Internship program. In this documentation, you will learn how to integrate and use the **AuthLibrary** in your project effectively.

# Installation

To use the AuthLibrary in your project, you need to include it as a dependency and also add the Maven Artifact Repository. You can do this by adding the following line to your project's **build.gradle** file (usually located in the **app** module):

```
dependencies {
    implementation ("com.github.hngx-org:mirage-kotlin-auth-library:3.0.0")
}
```

Then, add the Maven Artifact Repository inside your **dependencyResolutionManagement** in your **settings.gradle** file

```
maven {
            url = uri("https://www.jitpack.io")
        }
```

# Initializing the Library

Before you can use the AuthLibrary, you must initialize it by setting up the **ApiService** and the **DataStoreRepository**.

## Initializing the ApiService

The **ApiService** is responsible for making HTTP requests to your authentication API. To initialize it, you can use the **createAuthService** function provided by AuthLibrary:

`val apiService = AuthLibrary.createAuthService()`


## Initializing the DataStoreRepository

The **DataStoreRepository** is used to manage user preferences, specifically storing and retrieving user IDs. To initialize it, you can use the **createDataStoreRepository** function provided by AuthLibrary:

`val dataStoreRepository = AuthLibrary.createDataStoreRepository(context)`

_Ensure that you have a valid Context object to pass to the createDataStoreRepository function._

# Authentication Actions

The AuthLibrary provides functions to perform various authentication actions.

## Sign Up

- Note: The name field is a username. It shouldn't contain spaces. (name = JohnDoe) not (name = John Doe)
- Password is alphanumeric and should not contain any special characters. It requires a minimum of 8 characters.
- Email should be a valid email format

To register a new user, you should use the **SignupRepository**. You need to provide an instance of the **ApiService** to this repository:

```
val signupRepository = AuthLibrary.createSignupRepository(apiService)

val signupRequest = SignupRequest(
    name = "JohnDoe",
    email = "johndoe@example.com",
    password = "password123",
    confirm_password = "password123"
)

val result: ApiResponse<AuthResponse> = signupRepository.signup(signupRequest)
```

## LogIn

To log in a user, use the **LoginRepository**. You need to provide an instance of the **ApiService** and the **DataStoreRepository** to this repository:

```
val loginRepository = AuthLibrary.createLoginRepository(apiService, dataStoreRepository)

val loginRequest = LoginRequest(
    email = "johndoe@example.com",
    password = "password123"
)

val result: ApiResponse<AuthResponse> = loginRepository.login(loginRequest)
```

## Log out

To log out a user, use the **LogoutRepository**. You only need to provide an instance of the **ApiService** to this repository:

```
val logoutRepository = AuthLibrary.createLogoutRepository(apiService)

val result: ApiResponse<LogoutResponse> = logoutRepository.logout()
```

## Fetching User Profile

To fetch the user profile, use the **ProfileRepository**. We've passed back the cookie in the profile and you also get that. To fetch profile you only need to provide an instance of the **ApiService** to this repository:

```
val profileRepository = AuthLibrary.createProfileRepository(apiService)

val result: ApiResponse<AuthResponse> = profileRepository.profile()
```

To get the cookie, you have to create a variable with the type of String, then call the RetrofitClient to get cookie

```
var cookies: String = "";

cookies = RetrofitClient.getCookiesForUrl().toString()

```

## Response Handling

Responses from the API are wrapped in the **ApiResponse** class to provide a consistent way to handle success and error cases.

```
when (result) {
    is ApiResponse.Success -> {
        val data = result.data
        // Handle success data
    }
    is ApiResponse.Error -> {
        val errorMessage = result.message
        // Handle error message
    }
}
```

**_Do not forget to add internet permission in your manifest_**


# Contributors

@shegsbass 
@aniekan12 
@technicaldee 
