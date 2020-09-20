/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Joke;


public class JokeDTO {
    
    private String joke;
    private String reference;
    private String type;

    public JokeDTO(Joke joke) {
        this.joke = joke.getJoke();
        this.reference = joke.getReference();
        this.type = joke.getType();
    }
    
    public JokeDTO(){
        
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
