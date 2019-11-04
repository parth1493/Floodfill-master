Android Technical Interview - Paint
Instructions
Given the base project, you need to implement displayCanvas() and floodFill() methods.

displayCanvas(): Given a the bi-dimensional canvas array of random colors, display the actual UI component of this canvas on the device screen. The view is set up (with id 'canvas') but not attached the the MainActivity. The canvas should take the entire width and height of the Canvas View, regardless of the number of rows and columns
floodFill(): The user should be able to input an x and y coordinate in the provided fields on the screen, and choose a color from the color picker. With these as inputs, implement the flood fill feature (think of the paint bucket tool in any photo editing software): Any pixel adjacent to the original pixel that is the same color as the pixel's original color should be changed to the new color, and this should also apply to its adjacent pixels (adjacent includes diagonals, so there are up to 8 adjacent pixels to one pixel). An example of flood fill can be found here, and a 'very educational' gif here. This method should be called when the Flood Fill button is pressed on the screen.
Things to Consider:

The entire project should be written in Kotlin.
No assumptions should be made on the inputs of the width and height fields.
The rows and columns variables should not be considered final. If they are changed at compile time, both the displaying of the canvas and the flood fill algorithm should still work.
You can change the parameters of the given methods if you deem it necessary.
The only thing expected is to fill out those 2 methods, but feel free to go further if wanted. Anything extra (such as tests) will be counted as bonus points!
Feel free to use StackOverflow or any other online resource as you would normally do (although please do not search for the answer directly!).
You may change the UI canvas view as long as you explain your reasoning.
For the sake of this exercise, you can assume that the list of possible colors is constant, and that they will be in the same order as the picker.
You should be using Git as you normally would in a project.
