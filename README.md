# SimpleChatApp

## Compilation
1. use `git clone https://github.com/btrautmann/SimpleChatApp.git`
2. run using Android Studio

I took this challenge as an opportunity to focus on (and learn) MVVM implementation as well as databinding. Therefore, every view (both Activities and even RecyclerViews) should have an associated ViewModel.  The benefit of this approach is that the logic sits in the ViewModel and not in the views themselves.  Due to this, the testing of the view becomes almost unnecessary, as it is uninteresitng. The meaningful testing happens in the ViewModel, which should be easily testable via unit tests.

After diving into MVVM and data binding, I got mixed up in Architecture Components as well, a recently released library from Android and one that definitely helps with lifecycle management/awareness.

A couple things I questioned on this approach:
- Should there be references to `Context` in the ViewModel? I vote no. The less Android-specific objects in the ViewModel, the easier it is to test without having to run instrumented tests. This means a plain old java ViewModel is the "best" ViewModel.
**Answer:** I later found out that when using data binding, there is a default variable called 'context' that can be passed to ViewModel methods. This helped me keep contexts out of the ViewModel classes.

- I had to implement a few of the `onClick()` calls within the view class, even though you could call directly from the xml layout to the ViewModel. The reason for this is that when the view binds to something more than a primitive, it can be hard to trigger an update to the UI (at least, it's not trivial). This happened particularly with RecyclerViews and calls that updated the items within them. This is related to issue #1.
**Answer:** I later learned of `MutableLiveData` which can be observed from the UI. When updated from within the ViewModel, the state change is broadcasted to the observers who can then modify the UI as they see fit. `onClick()`s CAN still be handled in the `Activity`, however, if subscription to the REST call needs to happen there. A good example of this is sending messages from the `ChatDetailActivity`, as we need to update the input layout when the message is finally sent, by clearing it. If updates don't like this need to be made, we can pass the user clicks directly to the ViewModel and handle subscription there.

That said, the basic MVVM approach is as follows:

`Activity` -> binds to a specific xml layout via `BindingUtil` -> xml layout declares a ViewModel, which allows calls to @{viewModel.someAttribute} for attributes such as visibility.

## REST Calls

The basic implementation of REST calls is as follows:

If the UI needs to be updated in a manner that isn't necessarily intuitive when done through binding, we subscribe to the call from the UI (`Activity` or `Fragment`). Otherwise we can subscribe directly in the ViewModel. The actual calls themselves are accessed through methods in the ViewModel. These methods call through to our `RestActions` class, which has all of our REST calls defined. Each call uses a `RestAction` which essentially takes care of the grunt work of each call (using `RestUtil` to check for any errors within the response, parse headers, etc.). The beauty of the the `RestAction` is that it returns a Single<Response>, and we can inject logic in the `doOnSuccess()` method if we want to add persistence, or anything else. `RestAction` passes the response back to the ViewModel. The ViewModel updates the underlying data and triggers any UI changes automatically via binding or the MutableLiveData method described above.

## Challenges with ViewModel

The beauty of extending Android's ViewModel is that they are lifecycle aware, and are maintaned even after configuration changes. This makes it very easy to hold onto your data when your Activity is restarted. The only problem with that is that you must extend ViewModel and you can no longer extend BaseObservable. This will be fixed in the future (allowing you to have the functionality of both in one class), but for now if you don't extend BaseObservable, you lose some of the magic of data binding you once had. You can no longer notify your bindings if data changes after the Activity is first loaded. This luckily did not impact me too much, but there are probably a few workarounds available at this point.

## Issues with this specific coding challenge

While the API was great (and the documentation was awesome), there were a few nuances that I noticed. First, authentication didn't really matter, so I didn't bother building it into the app. It'd be easy to build this into a Header parsing method within `RestUtil`, but since it didn't matter in any region it wasn't really a first priotity as testing it would be a pain. Secondly, the API would not persist any data in any region, so things like creating messages or chats were not meaningful. That is why you will see that creating a chat just "duplicates" the already existing chat that we get from fetching all chats. While this looks to be a poor UX or a bug in the current product, it would work with an API that would return the ACTUAL created message or chat instead of the already existing one. Lastly, pagination was similar in that no matter the page, the same data was returned. For this reason I didn't see it worthwhile to build in pagination into our `RecyclerView`s.

## What I would add

- I didn't really spend any time on UI design. I didn't think that was the point of this project, and I was personally more interested in the architecture and testability of the product. If I was to put more time into this, I would definitely increase the usability with a better design and more intuitive UX.

- Persistence would also be a critical update I would like to integrate into the app. Persistence would allow for a more seamless experience, instead of constantly loading data from the backend. Given the nature of the app (messaging), persistence is a must. This wouldn't be difficult to add (I would like to use an ORM instead of SQLite), I could inject persistence code into the RxJava flow within `RestAction`.




