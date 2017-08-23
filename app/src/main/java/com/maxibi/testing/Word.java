package com.maxibi.testing;

/**
 * Created by User on 8/14/2017.
 */

public class Word {
    String bm, bi, bookmark;
    int id;

    public Word( String bm, String bi, int id, String bookmark)
    {
        this.bm = bm;
        this.bi = bi;
        this.id = id;
        this.bookmark = bookmark;
    }

    //getter
    public String getBm(){  return bm;}
    public String getBi(){  return bi;}
    public String getBookmark() { return bookmark; }

    //setter
    public void setBm(String bm){   this.bm = bm;}
    public void setBi(String bi){   this.bi = bi;}
    public void setBookmark(String bookmark){ this.bookmark = bookmark; }
}
