# Purpose: Data mining algorithm - k-means clustering, chaining of AI modules and robotics
Pseudo-Isochromatic Plates (PIPs) are used to test color vision/blindness. In a PIP, a character or number is rendered in one color set against a background of dots of other colors meant to be indistinguishable by someone who is color-blind. Someone who is not color-blind can pick off and identify the character or number rendered on a plate, whereas someone who is color-blind cannot.

I will program a robot to scan a PIP and identify the characters and numbers rendered in each color on the plate. The robot will read the array of pixels on a PIP using a light sensor. Since light sensors sense the amount of reflected light and not the color of pixels, they may struggle to distinguish colors that are close together on the color spectrum (e.g., gray and black). So, I will have your robot use k-means clustering algorithm to improve the clarity of the PIP read by the robot using its light sensor.

# PIP: 

For our purposes, the plate will be a 24 X 24 grid of pixels, each pixel being a 2 X 2 LEGO brick. One or more characters or numbers will be rendered in each color. The PIP will contain bricks of various colors, including white, black, red, green, blue, yellow, tan, gray and orange.

# Robot: 

The robot collects the data by scanning the PIP in a grid fashion, reading one pixel at a time. Every time it reads a pixel, it saves the (row, column) coordinates of the pixel along with the reading of the light sensor for the pixel as a three-tuple in an array.

# Algorithm:

K-means clustering algorithm clusters the data points chromatically, not spatially, i.e., it calculates the distance of each pixel from a selected mean not using (row, column) coordinates, but light sensor reading.

#The result of running the algorithm is a series of pixel clusters. Each pixel cluster is:

Normalized so that the top left corner is (0,0)

Fed as input to the rule-based forward reasoning expert system you developed for Project 4 to determine the character it represents.

The corresponding character is displayed on the robot's screen.
