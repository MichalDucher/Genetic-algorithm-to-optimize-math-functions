import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        int n1 = 20; //Number of chromosomes for population
        int n2 = 2; //Number of genes for one chromosome
        int d = 5; //How many decimal places for one gene
        int a = -2; //Lower limit of the range for value of gene
        int b = 2; //Upper limit of the range for value of gene

        Function<Double[], Double> fun = (arg) -> -Math.pow(arg[0], 2) - Math.pow(arg[1],2) + 2; //Lambda unction to optimize

        Population population = new Population(n1, n2, d, a, b); //Creating a new population of random chromosomes

        population.evaluate(fun);   //Evaluating function on every unit of population
        population.showPopulationWithEval();
        System.out.println("Average fitness value: \n" + population.avgFitness());
        System.out.println("Number of chromosomes below average: \n" + population.numberOfChromosomesBelowAvg());
        System.out.println("Number of chromosomes above average: \n" + population.numberOfChromosomesAboveAvg());
    }
}