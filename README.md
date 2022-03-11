# GitHubUsers
Android App using Kotlin, Clean Architecture, MVVM, ViewModel, LiveData, Coroutines, Retrofit, Gson, Picasso and CircleImageView. 
In this app you can see the list of GitHub users from this API - https://developer.github.com/v3/users/ and detailed information about any user.

The application has 2 screens. The first one contains the list of users (https://developer.github.com/v3/users/#get-all-users). 
Each item has an avatar, login and id. When you click on any user, you see the second screen - user details (https://developer.github.com/v3/users/#get-a-single-user).
Here you can see detailed information about the user. If a field is null, you will see text that there is no data for this field.
When the data is loading, you will see a progress indicator. 
In addition, there is a fail screen. You will see it if there is no connection, the service is not available, or another error has occurred.

![1](https://user-images.githubusercontent.com/76612421/157825703-380081b7-db67-40c7-a026-563341817f1d.PNG)
![2](https://user-images.githubusercontent.com/76612421/157825712-661b033e-a769-4121-89b2-cf32e8e59e3d.PNG)
![3](https://user-images.githubusercontent.com/76612421/157825720-906dffa7-a3a0-435c-80e0-9730d403d74f.PNG)
