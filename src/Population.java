import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class Population{
    private static int numberOfChromosomes;
    private static int numberOfGenes;
    private static int decimalPlaces;
    private static int lowerLimit;
    private static int upperLimit;
    private static double averageFitness;
    private static double minNumberOfBits;
    public String[] chromosome;
    public double[] fitnessValues;
    public double[] vector;
    private static Random rand;
    public List<Chromosome> population = new ArrayList<>();
    public Population(int n1, int n2, int d, int a, int b){
        numberOfChromosomes = n1;
        numberOfGenes = n2;
        decimalPlaces = d;
        lowerLimit = a;
        upperLimit = b;
        fitnessValues = new double[n1];

        for(int i = 0; i < numberOfChromosomes; i++) {
            Chromosome chromosome = new Chromosome(n2, d, a, b);
            chromosome.randomChromosome();
            this.population.add(chromosome);
        }
    }

    public void evaluate(Function<Double[], Double> f){
        int j = 0;
        for(Chromosome c : this.population){
            double[] vector = c.vector;
            Double[] args = new Double[vector.length];
            for(int i = 0; i < vector.length; i++){
                args[i] = vector[i];
            }
            double fitness = f.apply(args);
            c.setFitness(fitness);
            this.fitnessValues[j++] = fitness;
        }
    }

    public void showPopulation() {
        this.population.stream().forEach(System.out::println);
    }

    public double avgFitness(){
        double avg = 0;
        for(Chromosome c : this.population)
            avg += c.getFitness();

        averageFitness = avg / this.population.size();

        return avg / this.population.size();
    }

    public void showPopulationWithEval(){
        for(Chromosome c : this.population){
            System.out.println(c.toString() + " " + c.getFitness());
        }
    }

    public void showEval(){
        for(Chromosome c : this.population){
            System.out.println(c.getFitness());
        }
    }

    public int numberOfChromosomesBelowAvg(){
        int result = 0;
        for(Chromosome c : this.population){
            if(c.getFitness() < averageFitness)
                result ++;
        }
        return result;
    }

    public int numberOfChromosomesAboveAvg(){
        int result = 0;
        for(Chromosome c : this.population){
            if(c.getFitness() > averageFitness)
                result ++;
        }
        return result;
    }
}
