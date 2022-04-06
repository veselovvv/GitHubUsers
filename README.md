# GitHubUsers
Android App using Kotlin, Clean Architecture, MVVM, ViewModel, LiveData, Coroutines, Retrofit, Gson, Picasso and CircleImageView. 
In this app you can see the list of GitHub users from this API - https://developer.github.com/v3/users/ and detailed information about any user.

The application has 2 screens. The first one contains the list of users (https://developer.github.com/v3/users/#get-all-users). 
Each item has an avatar, login and id. 
When you click on any user, you see the second screen - user details (https://developer.github.com/v3/users/#get-a-single-user).
Here you can see detailed information about the user. If a field is null, you will see text that there is no data for this field.
When the data is loading, you will see a progress indicator. In addition, there is a fail screen. You will see it if there is no connection, the service is not available, or another error has occurred.

![11](https://user-images.githubusercontent.com/76612421/157905253-1386027c-8287-4214-a487-e31016d3c939.PNG)
![12](https://user-images.githubusercontent.com/76612421/157905266-e41a814f-7035-42dd-ab19-b3b9993d9ed8.PNG)
