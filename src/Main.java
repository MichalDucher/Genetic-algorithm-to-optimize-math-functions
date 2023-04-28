public class Main {


    public static double f(double x1, double x2){
        return -Math.pow(x1,2) - Math.pow(x2, 2) + 2;
    }


    public static void main(String[] args) {
        int n1 = 20; //Number of chromosomes for population
        int n2 = 2; //Number of genes for one chromosome
        int d = 5; //How many decimal places for one gene
        int a = -2; //Lower limit of the range for value of gene
        int b = 2; //Upper limit of the range for value of gene

        /*Chromosome chromosome = new Chromosome(n, d, a, b);
        chromosome.randomChromosome();

        System.out.println("chromosome: \t\t\t" + chromosome);
        chromosome.inverse();
        System.out.println("chromosome inversed: \t" + chromosome);

        double valueF = f(chromosome.vector[0],chromosome.vector[1]);

        System.out.println("Value of f function for inversed chromosome: " + valueF);*/

        Population population = new Population(n1, n2, d, a, b);
        population.showPopulation();
    }
}