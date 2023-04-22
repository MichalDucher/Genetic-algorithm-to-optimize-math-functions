# Genetic Algorithm

This code uses a genetic algorithm to optimize the function -x1^2 - x2^2 + 2.

## Usage

To use this code, simply run the `Main` class. The program will output the randomly generated parents, their children after crossover, and the value of the fitness function for each child.

## Implementation Details

The `Chromosome` class is used to represent a single chromosome in the genetic algorithm. It has a method for randomly generating its genes and another method for encoding and decoding the genes. The `Main` class contains the main logic for the genetic algorithm and uses the `Chromosome` class to represent individuals in the population.

The algorithm uses one-point crossover to create children from the parents. Each child's fitness is calculated using the fitness function, and the child with the highest fitness is selected to become the new parent in the next generation.

Feel free to use it however you like.
