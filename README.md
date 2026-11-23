Sports Club Management App



1\. Users



The app is designed for the coaches and club staff. So they can manage players and team information using papers records, or messaging applications



Coaches and club staff can use the application to manage teams, keep track of player, record training sessions and matches, and get information about individual players.



2\. Core Task



The core task of this app is to help coaches and club staff to manage teams and their players in one place.



The application makes it easier to add and update player information, assign players to teams, and view the players belonging to a particular team



3\. Data



The main types of data:

* Team: stores information about each sports team
* Player: stores information about individual players
* Training session: stores information about training sessions for a team
* Match: stores information about matches



A team can have multiple players. This relationships is used by the core task because the app allows to view and manage the players belonging to each team.

Training sessions and matches are also associated with a team



4\. Out of Scope



1. Online payments - The app will not manage the online payments
2. User accounts creation - The app will no implement login, registration, and user accounts creation



#### Development Environment



Android Studio version: Build #AI-261.25134.95.2612.15914620, built on July 22, 2026

JDK version: JDK 21

Programming language: Kotlin

Framework: Jetpack Compose

External API: Open-Meteo Weather API



###### Build Steps

1. Extract the submitted ZIP file
2. Open android Studio
3. Select Open and choose extracted project folder
4. Install any missing android SDK components
5. Select Build -> Sync Gradle Project in left corner
6. Wait for the build to finish



###### Run Steps

1. Open the project in Android Studio
2. Start the Android emulator or connect a physical Android device
3. Click the run
4. The home screen provides navigation to the teams, matches, weather, and statistics



#### Network and Device Features



###### Weather API



1. Connect your emulator or physical device to the internet
2. Launch the application and open the Weather screen
3. The loading state is happening until application get weather information from the Open-Meteo API
4. Then returned the weather information
5. Disconnect the internet connection and go to the weather screen and click retry to see the error-handling and retry function



###### Share Match



1. Open the Matches screen
2. Add a match and select that match
3. Tap the Share Match button



###### Add to Calendar



1. Go to the added match
2. you can see Add to Calendar button
3. click it you can save that match to your calendar



###### Dark Mode Preference



1. In the home screen dark mode switch
2. enable or disable the dark mode switch
3. See the change in the app's appearance
4. Close and reopen the app to verify still preference is retained





&#x20;

