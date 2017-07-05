/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicExtraction.TETConfigurations;

import topicExtraction.TETParameters.Parameters_BisectingKMeans_NonParametric;
import topicExtraction.TETParameters.Parameters_KMeans_NonParametric;
import topicExtraction.TETParameters.Parameters_KMeans_Parametric;
import topicExtraction.TETParameters.Parameters_PLSA_NonParametric;
import topicExtraction.TETParameters.Parameters_PLSA_Parametric;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Gaia
 */
public class TopicExtractionConfiguration implements Serializable{
 
    private ArrayList<Integer> numTopics;
    
    private String dirEntrada;
    private String dirSaida;
    
    private boolean PLSA;
    private boolean LDAGibbs;
    private boolean KMeans;
    private boolean bisectingKMeans;
    
    Parameters_PLSA_Parametric parametersPLSAParametric;
    Parameters_PLSA_NonParametric parametersPLSANonParametric;
    Parameters_KMeans_Parametric parametersKMeansParametric;
    Parameters_KMeans_Parametric parametersBisectingKMeansParametric;
    Parameters_KMeans_NonParametric parametersKMeansNonParametric;
    Parameters_BisectingKMeans_NonParametric parametersBisectingKMeansNonParametric;
    
    private boolean autoNumTopics; //Defining the number of topics automatically
    
    public TopicExtractionConfiguration(){
        setDirEntrada("");
        setDirSaida("");
        
        setPLSA(false);
        setLDAGibbs(false);
        setKMeans(false);
        setBisectingKMeans(false);
        setParametersPLSAParametric(new Parameters_PLSA_Parametric());
        setParametersPLSANonParametric(new Parameters_PLSA_NonParametric());
        setParametersKMeansParametric(new Parameters_KMeans_Parametric());
        setParametersBisectingKMeansParametric(new Parameters_KMeans_Parametric());
        setParametersKMeansNonParametric(new Parameters_KMeans_NonParametric());
        setParametersBisectingKMeansNonParametric(new Parameters_BisectingKMeans_NonParametric());
        
        setNumTopics(new ArrayList<Integer>());
        
        setAutoNumTopics(false);
    }
    
    public boolean isAutoNumTopics(){
        return this.autoNumTopics;
    }
    
    public boolean isBisectingKMeans(){
        return this.bisectingKMeans;
    }
    
    public boolean isKMeans(){
        return this.KMeans;
    }
    
    public boolean isLDAGibbs(){
        return this.LDAGibbs;
    }
    
    public boolean isPLSA(){
        return this.PLSA;
    }
    
    public ArrayList<Integer> getNumTopics(){
        return this.numTopics;
    }
    
    public Integer getNumTopic(int pos){
        return this.numTopics.get(pos);
    }
    
    public Parameters_KMeans_Parametric getParametersBisectingKMeansParametric(){
        return this.parametersBisectingKMeansParametric;
    }
    
    public Parameters_KMeans_Parametric getParametersKMeansParametric(){
        return this.parametersKMeansParametric;
    }
    
    public Parameters_KMeans_NonParametric getParametersKMeansNonParametric(){
        return this.parametersKMeansNonParametric;
    }
    
    public Parameters_BisectingKMeans_NonParametric getParametersNonKMeansNonParametric(){
        return this.parametersBisectingKMeansNonParametric;
    }
    
    public Parameters_PLSA_NonParametric getParametersPLSANonParametric(){
        return this.parametersPLSANonParametric;
    }
    
    
    public Parameters_PLSA_Parametric getParametersPLSAParametric(){
        return this.parametersPLSAParametric;
    }
    
    public String getDirEntrada() {
        return dirEntrada;
    }

    public String getDirSaida() {
        return dirSaida;
    }
    
    public void addNumTopics(int numTopic){
        this.numTopics.add(numTopic);
    }
    
    public void setAutoNumTopics(boolean autoNumTopics){
        this.autoNumTopics = autoNumTopics;
    }
    
    public void setNumTopics(ArrayList<Integer> numTopics){
        this.numTopics = numTopics;
    }
    
    public void setParametersBisectingKMeansNonParametric(Parameters_BisectingKMeans_NonParametric parameters){
        this.parametersBisectingKMeansNonParametric = parameters;
    }
    
    public void setParametersKMeansNonParametric(Parameters_KMeans_NonParametric parameters){
        this.parametersKMeansNonParametric = parameters;
    }
    
    public void setParametersBisectingKMeansParametric(Parameters_KMeans_Parametric parameters){
        this.parametersBisectingKMeansParametric = parameters;
    }
    
    public void setParametersKMeansParametric(Parameters_KMeans_Parametric parameters){
        this.parametersKMeansParametric = parameters;
    }
    
    public void setParametersPLSAParametric(Parameters_PLSA_Parametric parameters){
        this.parametersPLSAParametric = parameters;
    }
    
    public void setParametersPLSANonParametric(Parameters_PLSA_NonParametric parameters){
        this.parametersPLSANonParametric = parameters;
    }
    
    public void setDirEntrada(String dirEntrada) {
        this.dirEntrada = dirEntrada;
    }

    public void setDirSaida(String dirSaida) {
        this.dirSaida = dirSaida;
    }
    
    public void setBisectingKMeans(boolean bisectingKMeans){
        this.bisectingKMeans = bisectingKMeans;
    }
    
    public void setKMeans(boolean KMeans){
        this.KMeans = KMeans;
    }
    
    public void setLDAGibbs(boolean LDAGibbs){
        this.LDAGibbs = LDAGibbs;
    }
    
    public void setPLSA(boolean PLSA){
        this.PLSA = PLSA;
    }
    
}
