import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population{
    private static int numberOfChromosomes;
    private static int numberOfGenes;
    private static int decimalPlaces;
    private static int lowerLimit;
    private static int upperLimit;
    private static double minNumberOfBits;
    public String[] chromosome;
    public double[] vector;
    private static Random rand;
    public List<Chromosome> population = new ArrayList<>();
    public Population(int n1, int n2, int d, int a, int b){
        numberOfChromosomes = n1;
        numberOfGenes = n2;
        decimalPlaces = d;
        lowerLimit = a;
        upperLimit = b;

        for(int i = 0; i < numberOfChromosomes; i++) {
            Chromosome chromosome = new Chromosome(n2, d, a, b);
            chromosome.randomChromosome();
            population.add(chromosome);
        }
    }

    public void showPopulation() {
        this.population.stream().forEach(System.out::println);
    }

    public void showEval(){

    }

}
