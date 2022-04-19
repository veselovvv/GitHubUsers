# GitHubUsers
Android App using Kotlin, Clean Architecture, MVVM, ViewModel, LiveData, Coroutines, Retrofit, Gson, Picasso and CircleImageView. 
In this app you can see the list of GitHub users from this API - https://developer.github.com/v3/users/ and detailed information about any user.

The application has 2 screens. The first one contains the list of users (https://developer.github.com/v3/users/#get-all-users). 
Each item has an avatar, login and id. 
When you click on any user, you see the second screen - user details (https://developer.github.com/v3/users/#get-a-single-user).
Here you can see detailed information about the user. If a field is null, you will see text that there is no data for this field.
When the data is loading, you will see a progress indicator. In addition, there is a fail screen. You will see it if there is no connection, the service is not available, or another error has occurred.
In addition, you can search users and swipe to refresh data.

![1](https://user-images.githubusercontent.com/76612421/163990448-451a46fb-7d1a-453d-8d13-4c8a39736d74.PNG)
![2](https://user-images.githubusercontent.com/76612421/163990453-322f5140-0483-4d29-ab48-b1ce1f00d1e7.PNG)
![3](https://user-images.githubusercontent.com/76612421/163990459-654811e2-a480-4843-9d4b-80d5d21050ca.PNG)
![4](https://user-images.githubusercontent.com/76612421/163990467-c6d9e697-e0eb-4e5d-abc9-06841dbcf573.PNG)
![5](https://user-images.githubusercontent.com/76612421/163990472-891d57bf-7dc4-4da9-8494-ffd988d740de.PNG)
![6](https://user-images.githubusercontent.com/76612421/163990475-209a82f0-747f-46a5-862c-ff82279c415c.PNG)
![7](https://user-images.githubusercontent.com/76612421/163990478-06a08611-7f9b-4db1-a088-75d606f00538.PNG)
![8](https://user-images.githubusercontent.com/76612421/163990483-64a499c0-a383-4cde-92a4-8e1ab886d27c.PNG)
