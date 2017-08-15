package com.maxibi.testing;

/**
 * Created by User on 8/14/2017.
 */

public class Word {
    String bm, bi;

    public Word( String bm, String bi)
    {
        this.bm = bm;
        this.bi = bi;
    }

    //getter
    public String getBm(){  return bm;}
    public String getBi(){  return bi;}

    //setter
    public void setBm(String bm){   this.bm = bm;}
    public void setBi(String bi){   this.bi = bi;}
}
