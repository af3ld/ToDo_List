# ToDo_List
requirements:
Colors don’t matter but it has to have the following specs:
The top is a TextView with bold text in 22sp centered horizontally.
The center is an EditText that is 12dp below the title TextView and 12dp above the bottom buttons. The text inside is centered, 18sp, and has an InputType that capitalizes sentences and uses autocomplete. It should completely fill the space. 
The buttons at the bottom are all equally sized. I would suggest setting the Style to @android:style/Widget.Holo.Button.Borderless.Small". This is accomplished by setting the style attribute (note: it’s not android:style, simply style) 
Every view is 16dp from the edges of the screen.

Functionally, the app should work as follows: 
The user is able to input text in the EditText. When the user hits the save button, their text is saved to the Preferences. I would suggest using the day as a key.
If the user hits either the forward or back button, the views update accordingly. If the user goes back from Tuesday to Monday, the bottom buttons become: Sunday, Save, Tuesday and the title becomes Tuesday.
If the user does not click Save, their text is lost. 
The EditText has a Hint that says “Your plans for ****” where **** is the current day. This updates as the user changes days. 
The saved changes should be permanent. If the user leaves the app and force closes it, upon opening it they should have their saved text back. 
When the user clicks the Save Button, a Toast should appear informing the user that the save was successful
There should be no hardcoded Strings in your XML! You will lose points!

Bonus points:
5: Change the IME Action for the EditText to something appropriate and handle its click. This should let the user Save from hitting the enter button on their soft keyboard.
10: Rather than using a TextView at the top, make it a Spinner. It should allow the user to update the day from its dropdown AND it needs to update properly if the user uses the bottom buttons. 

