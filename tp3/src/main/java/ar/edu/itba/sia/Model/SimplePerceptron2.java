package ar.edu.itba.sia.Model;

import ar.edu.itba.sia.Activation.ActivationFunction;

import java.util.concurrent.ThreadLocalRandom;

public class SimplePerceptron2 {

    ActivationFunction actFunc;
    int entriesSize;
    double[][] inputData; //x
    double[] outputData; //y
    double[] weights; //w
    double learningRate; //n
    double error_eps;
    double minErr;
    double[] minWeights;

    public SimplePerceptron2(double error_eps, double learningRate, double[][] data, double[] outputData, ActivationFunction actFunc){
        this.error_eps = error_eps;
        this.actFunc = actFunc;
        this.learningRate = learningRate;
        this.outputData = outputData;
        entriesSize =1 + data[0].length;
        inputData = new double[data.length][entriesSize];

        minErr = Float.MAX_VALUE;

        for( int i=0; i<inputData.length; i++){

            for( int j=0; j<inputData[0].length; j++){
                if(j==0){
                    inputData[i][j] = 1;
                }
                else{
                    inputData[i][j] = data[i][j-1];
                }
            }
        }

        weights = new double[entriesSize];
        minWeights = new double[entriesSize];
        for( int i=0; i<entriesSize; i++){
            weights[i]=0;
        }
    }




    public void train(int limit, int sameBiasIterations){
        int iterations = 0;
        int n = 0;
        while(iterations < limit && minErr > error_eps){
            /*
            if(n>sameBiasIterations*inputData.length){
                n=0;
                reset();
            }*/
            for(int i=0; i<inputData.length; i++){

                double exitation = calculateExcitation(inputData[i]);
                double activation = calculateActivation(exitation);

                double error=0;

                switch (actFunc.getType()){
                    case SIGN:
                        updateWeights(activation, inputData[i], i);
                        error = calculateErrorSign();
                        break;
                    case LINEAR:
                        updateWeights(activation, inputData[i], i);
                        error = calculateErrorLin();
                        break;
                    case NONLINEAER:
                        updateWeightsNonLin(activation, inputData[i], i);
                        error = calculateErrorLin();
                        break;
                }


                if(Math.abs(error)<Math.abs(minErr)){
                    minErr = Math.abs(error);
                    for( int w=0; w<entriesSize; w++){
                        minWeights[w]=weights[w];
                    }
                }


            }
            iterations++;
            n++;
        }
        System.out.print("minerr " + minErr + " weights ");
        for(double d:weights){
            System.out.print(d + " ");
        }
        System.out.println();

    }

    public void reset(){
        for(int i = 0; i<weights.length; i++){
            this.weights[i] = 0;
        }
    }


    public void prediction(){

        for(int i=0; i<outputData.length; i++){
            double n = calculateExcitation(inputData[i]);
            System.out.println(" output " + outputData[i] +" prediction ---> " + actFunc.evaluate(n));
        }
    }





    private double calculateExcitation(double selected[]){
        double excitation = 0;
            for( int j=0; j<inputData[0].length; j++){
                excitation += weights[j] * selected[j];
            }
        return excitation;
    }

    private double calculateActivation(double exitation){
        return actFunc.evaluate(exitation);
    }

    private void updateWeights(double activation, double selected[], int index){

        for( int i=0; i< weights.length; i++){
            weights[i] += learningRate * (outputData[index] - activation) * inputData[index][i];
        }
    }

    private void updateWeightsNonLin(double activation, double selected[], int index){

        double exitation = calculateExcitation(selected);
        for( int i=0; i< weights.length; i++){
            weights[i] += learningRate * (outputData[index] - activation) * actFunc.evaluateDer(exitation) * inputData[index][i];
        }

    }


    private double calculateErrorSign() {
        double error = 0;
        for (int i = 0; i < inputData.length; i++) {
            double exitation = calculateExcitation(inputData[i]);
            double activation = calculateActivation(exitation);
            error += activation-outputData[i];
        }
        return error;
    }


    private float calculateErrorLin() {
        float error = 0;
        for (int i = 0; i < inputData.length; i++) {
            double exitation = calculateExcitation(inputData[i]);
            double activation = calculateActivation(exitation);
            error +=  Math.pow(activation-outputData[i],2);
        }
            error = 0.5f * error;
        return error;
    }


}

