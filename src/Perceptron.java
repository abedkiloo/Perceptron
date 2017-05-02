import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by abed on 4/28/17.
 */
public class Perceptron {
    public static ArrayList<ArrayList<Double>> sensor_Values = new ArrayList<ArrayList<Double>>();
    public static ArrayList<Double> desired_Output = new ArrayList<>();
    public static ArrayList<ArrayList<Double>> clusters = new ArrayList<ArrayList<Double>>();
    public static ArrayList<Double> weight = new ArrayList<>();
    public static double threshold = 0.5;
    public static double network = 0.5;
    public static double error_Rate = 0.0;
    public static double correction = 0.0;`
    public static double learning_Rate = 0.1;
    static Double trainingset[][] = {
            {1.0, 0.0, 0.0},
            {1.0, 0.0, 1.0}
//            {1, 1, 0},
//            {1, 1, 1},
    };
    static double sum = 0.0;
    public static ArrayList<ArrayList<Double>> intial_Weights = new ArrayList<ArrayList<Double>>();


    public static void main(String[] args) {

        weight.add(0.0);
        weight.add(0.0);
        weight.add(0.0);


        desired_Output.add(1.0);
        desired_Output.add(1.0);
//        desired_Output.add(1);
//        desired_Output.add(0);

        for (int i = 0; i < trainingset.length; i++) {
            ArrayList<Double> list = new ArrayList<>();
            list.addAll(Arrays.asList(trainingset[i]));
            sensor_Values.add(list);
        }
        System.out.println("Training Set");
        print_array(sensor_Values);

        System.out.println("Initial Weight");
        print_array(intial_Weights);
        populate_Cluster();
        System.out.println("Clusters");
        print_array(clusters);
        sum_of_Cluster();
        System.out.println("Sum " + sum);

        network();
        error_Rate();
        correction();
        get_Final_Weights();



    }

    public static void print_array(ArrayList<ArrayList<Double>> wholeArray) {
        for (ArrayList<Double> line : wholeArray) {
            for (Double rValue : line) {
                System.out.print(rValue + "  ");
            }
            System.out.println("");
        }
    }

    public static void populate_Cluster() {

        for (int i = 0; i < sensor_Values.size(); i++) {
            ArrayList<Double> line = sensor_Values.get(i);
            ArrayList<Double> input_weight = new ArrayList<>();
            for (int j = 0; j < line.size(); j++) {
                double lineValue = line.get(j);
                double weight = intial_Weights.get(i).get(j);
                input_weight.add(lineValue * weight);

            }
            clusters.add(input_weight);
        }

    }

    public static void sum_of_Cluster() {
        sum = 0.0;
        for (int i = 0; i < clusters.size(); i++) {
            for (int j = 0; j < clusters.get(i).size(); j++) {
                sum += clusters.get(i).get(j);
            }
        }

    }

    public static void network() {
        if (sum > threshold) {
            network = 1;
        } else {
            network = 0;
        }
        System.out.println("Network: "+network);
    }
    public static void error_Rate(){
        error_Rate=desired_Output.get(0)-network;
        System.out.println("Error Rate :"+error_Rate);
    }

    public static void correction() {
        correction=learning_Rate*error_Rate;
        System.out.println("Correction : "+ correction);
    }
    public static void get_Final_Weights(){
        weight.clear();
        for(int i=0;i<trainingset[0].length;i++){
            weight.add(sensor_Values.get(0).get(i)*correction);
        }
        System.out.println("New Weights");
        for (Double rValue : weight) {
            System.out.print(rValue + "  ");
        }

    }
}
