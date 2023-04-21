import java.util.Random;
public class Main {


    public static double f(double x1, double x2){
        return -Math.pow(x1,2) - Math.pow(x2, 2) + 2;
    }

    public static double log2(double N){
        return Math.log(N) / Math.log(2);
    }
    public static double[] Draw(int n, int d , int a, int b){
        double[] vector = new double[n];
        Random rand = new Random();
        for(int i = 0; i < n; i++) {
            vector[i] = (rand.nextDouble() * (b - a) - b);
            vector[i] *= Math.pow(10, d);
            vector[i] = Math.round(vector[i]);
            vector[i] /= Math.pow(10, d);
        }
        return vector;
    }

    public static String[] Encode(double[] v, int d, int a, int b){
        String[] vector = new String[v.length];
        double m = log2((b - a) * Math.pow(10, d));
        double help;

        for(int i = 0; i < v.length; i++){
            StringBuilder sb = new StringBuilder();
            help = ((v[i] - a) * (Math.pow(2, m) - 1)) / (b - a);
            sb.append(Integer.toBinaryString((int)help));

            while(sb.length() < m)
                sb.insert(0, '0');

            vector[i] = sb.toString();
        }
        return vector;
    }

    public static double[] Decode(String[] v, int d, int a, int b){
        double[] vector = new double[v.length];
        double m = log2((b - a) * Math.pow(10, d));
        double help;

        for(int i = 0; i < v.length; i++){
            help = Integer.parseInt(v[i], 2);
            vector[i] = a + (help * (b - a) / (Math.pow(2, m) - 1)) ;

            vector[i] *= Math.pow(10, d);
            vector[i] = Math.round(vector[i]);
            vector[i] /= Math.pow(10, d);
        }
        return vector;
    }

    public static String[][] OnePointCrossOver(String[] p1, String[] p2){
        String[][] childrens = new String[2][p1.length];
        Random rand  = new Random();
        String parent1 = "";
        String parent2 = "";

        for(int i = 0; i < p1.length; i++){
            parent1 += p1[i];
            parent2 += p2[i];
        }


        int crossoverPoint = rand.nextInt(parent1.length());
        System.out.println(crossoverPoint);
        int n = 0;
        String child1 = parent1.substring(0, crossoverPoint) + parent2.substring(crossoverPoint);
        String child2 = parent2.substring(0, crossoverPoint) + parent1.substring(crossoverPoint);

        for(int i = 0; i  < p1.length; i++){
            childrens[0][i] = child1.substring(n, n + p1[0].length());
            childrens[1][i] = child2.substring(n, n + p1[0].length());
        }
//        if(!isVectorValid(childrens[0], 5, a, b))

        return childrens;
    }

    public static boolean isVectorValid(String[] vector, int d, int a, int b){
        double[] help = Decode(vector, d, a, b);
        for(double h : help){
            if(h < a || h > b)
                return false;
        }
        return true;
    }

    public static String[] RandomBinaryVector(int n, int d, int a, int b){
        double[] help = Draw(n, d, a, b);
        String[] vector = Encode(help, d, a, b);

        return vector;
    }

    public static String fromVectorToChromosome(String[] vector){
        StringBuilder chromosome = new StringBuilder();

        for(String gen : vector)
            chromosome.append(gen);

        return chromosome.toString();
    }


    public static void main(String[] args) {
        int n = 2; //Number of genes for one chromosome
        int d = 5; //How many decimal places for one gene
        int a = -2; //Lower limit of the range for value of gene
        int b = 2; //Upper limit of the range for value of gene


        String[] parent1 = RandomBinaryVector(n, d, a, b);
        String[] parent2 = RandomBinaryVector(n, d, a, b);

        String[][] childrens = OnePointCrossOver(parent1, parent2);

        System.out.println("Parent 1: \t" + parent1[0] + " " + parent1[1]);
        System.out.println("Parent 2: \t" + parent2[0] + " " + parent2[1]);

        System.out.println("Child 1: \t" + childrens[0][0] + " " + childrens[0][1]);
        System.out.println("Child 2: \t" + childrens[1][0] + " " + childrens[1][1]);
    }
}