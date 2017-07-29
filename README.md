# SimpleChatApp

I took this challenge as an opportunity to focus on (and learn) MVVM implementation as well as databinding. Therefore, every view (both Activities and even RecyclerViews) should have an associated ViewModel.  The benefit of this approach is that the logic sits in the ViewModel and not in the views themselves.  Due to this, the testing of the view becomes almost unnecessary, as it is uninteresitng. The meaningful testing happens in the ViewModel, which should be easily testable via unit tests.

A couple things I am questioning on the approach:
- Should there be references to `Context` in the ViewModel? I vote no. The less Android-specific objects in the ViewModel, the easier it is to test without having to run instrumented tests. This means a plain old java ViewModel is the "best" ViewModel.
- I had to implement a few of the `onClick()` calls within the view class, even though you could call directly from the xml layout to the ViewModel. The reason for this is that when the view binds to something more than a primitive, it can be hard to trigger an update to the UI (at least, it's not trivial). This happened particularly with RecyclerViews and calls that updated the items within them. This is related to issue #1.

That said, the basic MVVM approach is as follows:

`Activity` -> binds to a specific xml layout via `BindingUtil` -> xml layout declares a ViewModel, which allows calls to @{viewModel.someAttribute} for attributes such as visibility.
