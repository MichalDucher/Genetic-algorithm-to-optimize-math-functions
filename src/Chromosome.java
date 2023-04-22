import java.util.Random;
public class Chromosome {
    private static int numberOfGenes;
    private static int decimalPlaces;
    private static int lowerLimit;
    private static int upperLimit;
    private static double minNumberOfBits;
    public String[] chromosome;
    public double[] vector;
    public Chromosome(int n, int d, int a, int b){
        numberOfGenes = n;
        decimalPlaces = d;
        lowerLimit = a;
        upperLimit = b;
        chromosome = new String[numberOfGenes];
        vector = new double[numberOfGenes];
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
        for(String c : chromosome){
            sb.append(c);
        }
        return sb.toString();
    }

    private void Draw(){
        Random rand = new Random();

        //Limit the decimal places to value of d
        for(int i = 0; i < numberOfGenes; i++) {
            vector[i] = (rand.nextDouble() * (upperLimit - lowerLimit) - upperLimit);
            vector[i] *= Math.pow(10, decimalPlaces);
            vector[i] = Math.round(vector[i]);
            vector[i] /= Math.pow(10, decimalPlaces);
        }
    }

    private void Encode(){
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

    public static Chromosome[] onePointCrossover(Chromosome p1, Chromosome p2){
        Chromosome child1 = new Chromosome(numberOfGenes, decimalPlaces, lowerLimit, upperLimit);
        Chromosome child2 = new Chromosome(numberOfGenes, decimalPlaces, lowerLimit, upperLimit);

        String parent1 = p1.toString();
        String parent2 = p2.toString();

        String c1;
        String c2;

        Random rand = new Random();
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
        Chromosome.makeChildValid(children);


        return children;
    }

    private static void makeChildValid(Chromosome[] children){
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
}
