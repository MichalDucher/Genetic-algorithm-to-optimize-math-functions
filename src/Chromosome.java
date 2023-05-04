import java.util.HashMap;
import java.util.Random;
public class Chromosome {
    private static int numberOfGenes;
    private static int decimalPlaces;
    private static int lowerLimit;
    private static int upperLimit;
    private static double minNumberOfBits;
    private double fitness;
    public String[] chromosome;
    public double[] vector;
    private static Random rand;

    public Chromosome(int n, int d, int a, int b){
        numberOfGenes = n;
        decimalPlaces = d;
        lowerLimit = a;
        upperLimit = b;
        chromosome = new String[numberOfGenes];
        vector = new double[numberOfGenes];
        rand = new Random();
        minNumberOfBits = log2((upperLimit - lowerLimit) * Math.pow(10, decimalPlaces)); //Min number od bits to encode the gene
    }


    public void randomChromosome(){
        Draw();
        Encode();
    }

    public double log2(double N){
        return Math.log(N) / Math.log(2);
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(String gene : this.chromosome){
            sb.append(gene);
        }
        return sb.toString();
    }

    private void Draw(){
        //Limit the decimal places to value of d
        for(int i = 0; i < numberOfGenes; i++) {
            vector[i] = (rand.nextDouble() * (upperLimit - lowerLimit) - upperLimit);
            vector[i] *= Math.pow(10, decimalPlaces);
            vector[i] = Math.round(vector[i]);
            vector[i] /= Math.pow(10, decimalPlaces);
        }
    }

    public void Encode(){
        double help;

        for(int i = 0; i < numberOfGenes; i++){
            StringBuilder sb = new StringBuilder();
            help = ((vector[i] - lowerLimit) * (Math.pow(2, minNumberOfBits) - 1)) / (upperLimit - lowerLimit);
            sb.append(Integer.toBinaryString((int)help));

            while(sb.length() < minNumberOfBits)
                sb.insert(0, '0');

            chromosome[i] = sb.toString();
        }
    }

    public void Decode(){
        double help;

        for(int i = 0; i < chromosome.length; i++){
            help = Integer.parseInt(chromosome[i], 2);
            vector[i] = lowerLimit + (help * (upperLimit - lowerLimit) / (Math.pow(2, minNumberOfBits) - 1)) ;

            vector[i] *= Math.pow(10, decimalPlaces);
            vector[i] = Math.round(vector[i]);
            vector[i] /= Math.pow(10, decimalPlaces);
        }
    }

    public void setFitness(double fitness){
        this.fitness = fitness;
    }

    public double getFitness(){
        return this.fitness;
    }

    public static Chromosome[] onePointCrossover(Chromosome p1, Chromosome p2){
        Chromosome child1 = new Chromosome(numberOfGenes, decimalPlaces, lowerLimit, upperLimit);
        Chromosome child2 = new Chromosome(numberOfGenes, decimalPlaces, lowerLimit, upperLimit);

        String parent1 = p1.toString();
        String parent2 = p2.toString();

        String c1;
        String c2;

        int crossoverPoint = rand.nextInt((int)minNumberOfBits*numberOfGenes);
        System.out.println(crossoverPoint);
        int n = 0;

        c1 = parent1.substring(0,  crossoverPoint).concat(parent2.substring(crossoverPoint));
        c2 = parent2.substring(0,  crossoverPoint).concat(parent1.substring(crossoverPoint));

        for(int i = 0; i < numberOfGenes; i++){
            child1.chromosome[i] = c1.substring(n, n + p1.chromosome[i].length());
            child2.chromosome[i] = c2.substring(n, n + p1.chromosome[i].length() );
            n += (int)minNumberOfBits;
        }
        child1.Decode();
        child2.Decode();
        Chromosome[] children = {child1, child2};
        Chromosome.childControl(children);


        return children;
    }

    public static Chromosome[] twoPointCrossover(Chromosome p1, Chromosome p2){
        Chromosome child1 = new Chromosome(numberOfGenes, decimalPlaces, lowerLimit, upperLimit);
        Chromosome child2 = new Chromosome(numberOfGenes, decimalPlaces, lowerLimit, upperLimit);

        String parent1 = p1.toString();
        String parent2 = p2.toString();

        String c1;
        String c2;

        Random rand = new Random();
        int crossoverPoint1 = rand.nextInt((int)minNumberOfBits*numberOfGenes);
        int crossoverPoint2 = rand.nextInt((int)minNumberOfBits*numberOfGenes);
        System.out.println(crossoverPoint1 + " " + crossoverPoint2);

        int n = 0;

        c1 = parent1.substring(0,  Math.min(crossoverPoint1, crossoverPoint2))
                .concat(parent2.substring(Math.min(crossoverPoint1, crossoverPoint2),
                        Math.max(crossoverPoint1, crossoverPoint2)))
                .concat(parent1.substring(Math.max(crossoverPoint1, crossoverPoint2)));

        c2 = parent2.substring(0,  Math.min(crossoverPoint1, crossoverPoint2))
                .concat(parent1.substring(Math.min(crossoverPoint1, crossoverPoint2),
                        Math.max(crossoverPoint1, crossoverPoint2)))
                .concat(parent2.substring(Math.max(crossoverPoint1, crossoverPoint2)));

        for(int i = 0; i < numberOfGenes; i++){
            child1.chromosome[i] = c1.substring(n, n + p1.chromosome[i].length());
            child2.chromosome[i] = c2.substring(n, n + p1.chromosome[i].length() );
            n += (int)minNumberOfBits;
        }
        child1.Decode();
        child2.Decode();
        Chromosome[] children = {child1, child2};
        Chromosome.childControl(children);


        return children;
    }

//    public static Chromosome[] pmxCrossover(Chromosome p1, Chromosome p2) {
//        Chromosome child1 = new Chromosome(numberOfGenes, decimalPlaces, lowerLimit, upperLimit);
//        Chromosome child2 = new Chromosome(numberOfGenes, decimalPlaces, lowerLimit, upperLimit);
//
//        String parent1 = p1.toString();
//        String parent2 = p2.toString();
//
//        StringBuilder c1 = new StringBuilder(parent2);
//        StringBuilder c2 = new StringBuilder(parent1);
//
//        HashMap<String, String> map = new HashMap<>();
//
//        int startSegment = rand.nextInt(parent1.length());
//        int endSegment = rand.nextInt(parent1.length());
//        if(startSegment > endSegment){
//            int help = startSegment;
//            startSegment = endSegment;
//            endSegment = help;
//        }
//
//        for(int i = startSegment; i < endSegment; i++){
//
//        }
//
//        Chromosome[] children = {child1, child2};
//
//        return children;
//    }


    public void mutate(double alpha){
        Random rand = new Random();
        char[] chromosome = this.toString().toCharArray();
        StringBuilder help = new StringBuilder();

        for(char c : chromosome){
            if(rand.nextDouble() < alpha)
                help.append(c == '0' ? '1' : '0');
            else
                help.append(c);
        }

        int n = 0;
        for(int i = 0; i < numberOfGenes; i++) {
            this.chromosome[i] = help.substring(n, n + (int)Math.ceil(minNumberOfBits));
            n += (int)Math.ceil(minNumberOfBits);
        }

        childControl(this);

        this.Decode();

    }

    public void inverse(){
        char[] chromosome = this.toString().toCharArray();

        int index = rand.nextInt(chromosome.length);
        if(index == chromosome.length - 1){
            char help = chromosome[index];
            chromosome[index] = chromosome[0];
            chromosome[0] = help;
        }else{
            char help = chromosome[index];
            chromosome[index] = chromosome[index + 1];
            chromosome[index + 1] = help;
        }

        String inversed = new String(chromosome);
        int n = 0;
        for(int i = 0; i < numberOfGenes; i++) {
            this.chromosome[i] = inversed.substring(n, n + (int)Math.ceil(minNumberOfBits));
            n += (int)Math.ceil(minNumberOfBits);
        }

        this.Decode();
        childControl(this);
    }

    private static void childControl(Chromosome[] children){
        for(Chromosome child: children){
            if(child.vector[0] < lowerLimit){
                child.vector[0] = lowerLimit;
                child.Encode();
            }
            if(child.vector[0] > upperLimit){
                child.vector[0] = upperLimit;
                child.Encode();
            }
            if(child.vector[1] < lowerLimit){
                child.vector[1] = lowerLimit;
                child.Encode();
            }
            if(child.vector[1] > upperLimit){
                child.vector[1] = upperLimit;
                child.Encode();
            }
        }
    }

    private static void childControl(Chromosome child){
        for(double gene : child.vector){
            if(gene > upperLimit) {
                gene = upperLimit;
                child.Encode();
            }
            if(gene < lowerLimit){
                gene = lowerLimit;
                child.Encode();
            }
        }
    }
}
