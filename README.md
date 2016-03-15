# HeroAdventureHelper
Sample app for sopra

This application has been developed considering the MVP architecture and using the model repository to obtain, store and retrieve Internet resources.

All information is obtained from the network and stored in memory and on disk.
While the app is alive all information is obtained from memory. Once the application is closed and reopens the information is obtained from disk and reloaded into memory for the proper functioning of the app,

The MVP separates the logic from view. 
The model view presenter (MVP) is used in this development through three model classes. The view (Fragments and Activities), presenters and models.

The clean architecture is achieved by attaching the MVP with the Model Repository.

The normal flow for any process is, View -> Presenter -> Interactor -> Repository -.> (Memory, disk or Internet)

View: Represents the information.
Presenter: Business Logic.
Interactor: Validates and adapts information
Repository: Decide where you get the information. Disc, memory or internet.

All data is retrieved from repository and validate into interactors classes. 
The bussines logic is inside presenters which uses interactors for retrieve data.
When the data is validate and retrieved, the presenter send and make a Fragment or activity show this data into her view.

Libraries used to develop:


/* Used to snackbar messages */

compile 'com.android.support:design:23.2.0'

/* Used to support over 64k methods */

compile('com.android.support:multidex:1.0.1') {
   exclude group: 'com.android.support', module: 'suppor-v4'
}
/* Used for load hero cards, town cards and citicen cards*/

compile 'com.android.support:cardview-v7:23.2.0'

/* Advanced and flexible list view*/

compile 'com.android.support:recyclerview-v7:23.2.0'

/* used for serialize json data into objects*/

compile 'com.google.code.gson:gson:2.5'

/* Image loader library*/

compile 'com.github.bumptech.glide:glide:3.7.0'

/* Object serialize and save into disk using shared preferences*/

compile 'com.github.pwittchen:prefser:2.0.5'

/* Helper library to avoid boilerplate on binding views, or applying onclick listeners*/

compile 'com.jakewharton:butterknife:7.0.1'

/* Library included in retrofit*/

compile 'io.reactivex:rxandroid:1.1.0'

/* Helper library to avoid memory leaks.*/

compile 'com.squareup.leakcanary:leakcanary-android:1.3.1'

/* Library to donwload and parse json and make API REST calls*/

compile 'com.squareup.retrofit2:retrofit:2.0.0-beta4'

/* Used by retrofit for conversión json into objects*/

compile 'com.squareup.retrofit2:converter-gson:2.0.0-beta4'

/* Used by retrofit */

compile 'com.squareup.okhttp3:logging-interceptor:3.1.1'

/* Used by retrofit */

compile 'com.squareup.okhttp3:okhttp:3.1.1'

