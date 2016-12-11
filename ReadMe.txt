1. Application A1 consists of a single activity containing two read-only text views and two buttons. The
buttons, when selected, will broadcast two different intents (e.g., Hotels vs. Restaurants) depending
on the button pressed. The text views describe the meaning of the buttons to the device user. Both
broadcasts are ordered broadcasts.
2. Application A2 just receives the intents sent by A1; A2 does not contain any activities. Whenever an
intent is received, A2 displays a toast message on the device’s display. The toast message indicates
whether the broadcast sender was selecting Hotels or Restaurants.
3. Application A3 also receives the intents. Depending on the kind of intent that was received, A3 will
launch one of two activities. The first activity displays information about at least 6 hotels in the city of
Chicago, Illinois. The second activity shows at least 6 restaurants in Chicago. Both activities consist
of two fragments, whose behavior is described below. In addition, application A3 maintains an options
menu and an action bar. The action bar shows the name of the application (your choice) and the
overflow area. The options menu allows a device user to switch between hotels and restaurants. The
options menu should be clearly accessible from the overflow area.
Each of the two activities in A3 contains two fragments. The first fragment displays a list of hotels or restaurants
(whichever applies). The device user may select any point of interest from the list; the currently selected
item is highlighted. The second fragment shows a picture of the selected hotel or restaurant.
When the device is in portrait mode, the two fragments are displayed on different screens. First, the
device will show only the first fragment. When the user selects an item, the the first fragment disappears and
the second fragment is shown. Pressing the “back” soft button on the device, will return the device to the
original configuration (first fragment only), thereby allowing the user to select a different point of interest.
When the device is in landscape mode, application A3 initially shows only the first fragment across the entire
width of the screen. As soon as a user selects an item, the first fragment is “shrunk” to about 1/3 of the screen’s
width. This fragment will appear in the left-hand side of the screen, with the second fragment taking up the
remaining 2/3 of the display on the right. Again, pressing the “back” button will return the application to its
initial configuration. The action bar should be displayed at all times regardless of whether the device is in
portrait or landscape mode.
Finally, the state of application A3 should be retained across device rotations, e.g., when the device is
switched from landscape to portrait configuration and vice versa. This means that the selected list item (in the
first fragment) and the page displayed in the second fragment will be kept during configuration changes.
As for the order of execution of A2 and A3’s receivers, you should configure all three apps in such a way
that the receiver in A2 is always executed before a receiver in A3, after A1 sends a broadcast.