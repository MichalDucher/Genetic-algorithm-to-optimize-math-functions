
public class Main {


    public static double f(double x1, double x2){
        return -Math.pow(x1,2) - Math.pow(x2, 2) + 2;
    }

    public static void main(String[] args) {
        int n = 2; //Number of genes for one chromosome
        int d = 5; //How many decimal places for one gene
        int a = -2; //Lower limit of the range for value of gene
        int b = 2; //Upper limit of the range for value of gene

        Chromosome parent1 = new Chromosome(n, d, a, b);
        Chromosome parent2 = new Chromosome(n, d, a, b);
        parent1.randomChromosome();
        parent2.randomChromosome();
        System.out.println("Parent 1: \t" + parent1);
        System.out.println("Parent 2: \t" + parent2);

        Chromosome[] children = Chromosome.onePointCrossover(parent1, parent2);

        System.out.println("Child 1: \t" + children[0]);
        System.out.println("Child 2: \t" + children[1]);
        System.out.println("Decimal value of child 1: \n\t" + children[0].vector[0] + " " + children[0].vector[1]);
        System.out.println("Decimal value of child 2: \n\t" + children[1].vector[0] + " " + children[1].vector[1]);

        double valueF1 = f(children[0].vector[0], children[0].vector[1]);
        double valueF2 = f(children[1].vector[0], children[1].vector[1]);

        System.out.println("Value of f function for child 1: " + valueF1);
        System.out.println("Value of f function for child 2: " + valueF2);

    }
}