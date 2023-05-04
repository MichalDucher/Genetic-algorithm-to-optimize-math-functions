import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        int n1 = 20; //Number of chromosomes for population
        int n2 = 2; //Number of genes for one chromosome
        int d = 5; //How many decimal places for one gene
        int a = -2; //Lower limit of the range for value of gene
        int b = 2; //Upper limit of the range for value of gene

        Function<Double[], Double> fun = (arg) -> -Math.pow(arg[0], 2) - Math.pow(arg[1],2) + 2; //Lambda unction to optimize

        Population p1 = new Population(n1, n2, d, a, b); //Creating a new population

        p1.randomChromosomes(); //Seting random chromosomes in population

        p1.evaluate(fun);   //Evaluating function on every chromosome of population
        System.out.println("Population 1: \n======================================================");
        p1.showPopulationWithEval();

        System.out.println("Population 2: \n======================================================");
        Population p2 = p1.roulettePopulation(); //New population created by roulette method
        p2.evaluate(fun);
        p2.showPopulationWithEval();
    }
}