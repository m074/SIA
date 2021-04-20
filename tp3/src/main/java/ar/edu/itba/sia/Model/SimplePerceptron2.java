package ar.edu.itba.sia.Model;

import ar.edu.itba.sia.Activation.ActivationFunction;

public class SimplePerceptron2 {

    ActivationFunction actFunc;
    int entriesSize;
    double[][] inputData; //x
    double[] outputData; //y
    double[] weights; //w
    double learningRate; //n

    double minErr;
    double[] minWeights;

    public SimplePerceptron2(double error_eps, double learningRate, double[][] data, double[] outputData, ActivationFunction actFunc){
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

        while(iterations < limit){


            for(int i=1; i<inputData.length; i++){

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
        }
        System.out.println("minerr " + minErr + " wheigts " + minWeights[0] + " " + minWeights[1] + " " + minWeights[2] + " " + minWeights[3]);

    }



    public void prediction(){

        for(int i=0; i<outputData.length; i++){
            double n = calculateExcitation(inputData[i]);
            System.out.println(" output " + actFunc.evaluate(outputData[i]) +" prediction ---> " + actFunc.evaluate(n));
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
            weights[i] += learningRate * (actFunc.evaluate(outputData[index]) - activation) * actFunc.evaluateDer(exitation) * inputData[index][i];
        }

    }


    private double calculateErrorSign() {
        double error = 0;
        for (int i = 1; i < inputData.length; i++) {
            double exitation = calculateExcitation(inputData[i]);
            double activation = calculateActivation(exitation);
            error += activation-outputData[i];
        }
        return error;
    }


    private float calculateErrorLin() {
        float error = 0;
        for (int i = 1; i < inputData.length; i++) {
            double exitation = calculateExcitation(inputData[i]);
            double activation = calculateActivation(exitation);
            error +=  Math.pow(activation-actFunc.evaluate(outputData[i]),2);
        }
            error = 0.5f * error;
        return error;
    }


}

