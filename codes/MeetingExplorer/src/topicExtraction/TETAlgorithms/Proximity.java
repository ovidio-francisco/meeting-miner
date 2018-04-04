//*****************************************************************************
// Author: Rafael Geraldeli Rossi
// E-mail: rgr.rossi at gmail com
// Last-Modified: January 29, 2015
// Description: 
//*****************************************************************************  

package topicExtraction.TETAlgorithms;

import topicExtraction.TETStructures.NeighborHash;
import java.util.Set;
import weka.core.Instance;

@SuppressWarnings("unused")
public class Proximity {
    
    public static double calcDistCosseno(NeighborHash instancia1, NeighborHash instancia2){
        double dist = 0;
        if(instancia1.getNeighbors().size() <= instancia2.getNeighbors().size()){
            dist = ComputeCosine(instancia1,instancia2);
        }else{
            dist = ComputeCosine(instancia2,instancia1);
        }
        return dist;
    }
    
    public static double ComputeCosineExp(NeighborHash instancia1, NeighborHash instancia2, double sigma){
        double dist = 0;
        if(instancia1.getNeighbors().size() <= instancia2.getNeighbors().size()){
            dist = ComputeCosine(instancia1,instancia2);
        }else{
            dist = ComputeCosine(instancia2,instancia1);
        }
        dist = 1 - dist;
        dist = Math.exp(((-1) * dist) / (2 * Math.pow(sigma, 2)));
        return dist;
    }
    
    public static double ComputeCosineExp(Instance instance1, Instance instance2, double sigma){
        double dist=0;
        dist = ComputeCosine(instance1,instance2);
        dist = 1 - dist;
        dist = Math.exp(((-1) * dist) / (2 * Math.pow(sigma, 2)));
        return dist;
    }
    
    
    public static double ComputeEuclideanExp(Instance instance1, Instance instance2, double sigma){
        double dist=0;
        dist = calcDistEuclidiana(instance1,instance2);
        dist = Math.exp(((-1) * dist) / (2 * Math.pow(sigma, 2)));
        return dist;
    }
    
    public static double ComputeVoteEuclidean(NeighborHash instancia1, NeighborHash instancia2, double sigma){
        double acm = 0;
        Set<Integer> atributos1 = instancia1.getNeighbors().keySet();
        Set<Integer> atributos2 = instancia2.getNeighbors().keySet();
        
        for(Integer atr1 : atributos1){
            if(instancia2.getNeighbors().containsKey(atr1)){
                acm += Math.pow((instancia1.getNeighbor(atr1) - instancia2.getNeighbor(atr1)), 2);
            }else{
                acm += Math.pow(instancia1.getNeighbor(atr1), 2);
            }
        }
        for(Integer atr2 : atributos2){
            if(!instancia1.getNeighbors().containsKey(atr2)){
                acm += Math.pow(instancia2.getNeighbor(atr2), 2);
            }
        }
        
        double euclidean = Math.sqrt(acm);
        double vote = 1 / euclidean;
        return vote;
    }
    
    
    public static double ComputeCosine(NeighborHash instancia1, NeighborHash instancia2){
        double dist=0;
        double numerator = 0;
        double den1 = 0;
        double den2 = 0;
        
        Set<Integer> atributos1 = instancia1.getNeighbors().keySet();
        for(Integer atr1 : atributos1){
            den1 += Math.pow(instancia1.getNeighbor(atr1),2);
        }
        if(den1 == 0){
            return 0;
        }
        
        Set<Integer> atributos2 = instancia2.getNeighbors().keySet();
        for(Integer atr2 : atributos2){
            den2 += Math.pow(instancia2.getNeighbor(atr2),2);
        }
        if(den2 == 0){
            return 0;
        }
        
        for(Integer atr1 : atributos1){
            if(instancia2.getNeighbors().containsKey(atr1)){
                numerator += instancia1.getNeighbor(atr1) * instancia2.getNeighbor(atr1);
            }
        }
        dist = (numerator / ((Math.sqrt(den1) * Math.sqrt(den2))));
        return dist;
    }
    
    public static double ComputeCosine(Instance instance1, Instance instance2){
        double dist=0;
        double numerator = 0;
        double den1 = 0;
        double den2 = 0;
        double numTerms = instance1.numAttributes() - 1;
        
        int numTerms1 = 0;
        int numTerms2 = 0;
        
        for(int term=0;term<numTerms;term++){
            numerator += instance1.value(term) * instance2.value(term);
            if(instance1.value(term) > 0){
                numTerms1++;
            }
            if(instance2.value(term) > 0){
                numTerms2++;
            }
            den1 += Math.pow(instance1.value(term),2);
            den2 += Math.pow(instance2.value(term),2);
        }
        if(den1 == 0){
            return 0;
        }
        if(den2 == 0){
            return 0;    
        }
        dist = (numerator / ((Math.sqrt(den1) * Math.sqrt(den2))));
        return dist;
    }
    
    public static double calcDistEuclidianaID(Instance instance1, Instance instance2){
        double dist=0;
        double acm = 0;
        double numTerms = instance1.numAttributes() - 1;
        
        int numTerms1 = 0;
        int numTerms2 = 0;
        
        for(int term=1;term<numTerms;term++){
            acm += Math.pow((instance1.value(term) - instance2.value(term)), 2);
        }
        
        if(acm > 0){
            dist = Math.sqrt(acm);
        }else{
            dist = 0;
        }
        return dist;
    }
    
    public static double calcDistEuclidiana(Instance instance1, Instance instance2){
        double dist=0;
        double acm = 0;
        double numTerms = instance1.numAttributes() - 1;
        
        int numTerms1 = 0;
        int numTerms2 = 0;
        
        for(int term=0;term<numTerms;term++){
            acm += Math.pow((instance1.value(term) - instance2.value(term)), 2);
        }
        
        if(acm > 0){
            dist = Math.sqrt(acm);
        }else{
            dist = 0;
        }
        return dist;
    }
    
}
