package edu.yale.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents a photographer
 */
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long personId;

    @Column
    private String title;

    @Column
    private String firstName;

    //private String lastName;

    //TODO
    private String lastname;

    //TODO remove
    private int age;

    @Column
    private String alias;

    @Column
    private String states;

    @Column
    private String cities;

    @Column
    private String nations;

    @Column
    private String lifedates;

    @Column
    private String workdates;

    @Column
    private String biobox;

    @Column
    private String photobox;

    @Column
    private String photofolders;

    @Column
    private String albumbox;

    @Column
    private String fragilebox;

    @Column
    private String audiovisualbox;

    @Column
    private String oversizebox;

    public Person() {
    }

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastname = lastName;
        this.age = age;
    }

    public Person(int age, String alias, String cities, String firstName, String lastname, String states) {
        this.age = age;
        this.alias = alias;
        this.cities = cities;
        this.firstName = firstName;
        this.lastname = lastname;
        this.states = states;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long studentId) {
        this.personId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getNations() {
        return nations;
    }

    public void setNations(String nations) {
        this.nations = nations;
    }

    public String getAlbumbox() {
        return albumbox;
    }

    public void setAlbumbox(String albumbox) {
        this.albumbox = albumbox;
    }

    public String getAudiovisualbox() {
        return audiovisualbox;
    }

    public void setAudiovisualbox(String audiovisualbox) {
        this.audiovisualbox = audiovisualbox;
    }

    public String getBiobox() {
        return biobox;
    }

    public void setBiobox(String biobox) {
        this.biobox = biobox;
    }

    public String getFragilebox() {
        return fragilebox;
    }

    public void setFragilebox(String fragilebox) {
        this.fragilebox = fragilebox;
    }

    public String getLifedates() {
        return lifedates;
    }

    public void setLifedates(String lifedates) {
        this.lifedates = lifedates;
    }

    public String getOversizebox() {
        return oversizebox;
    }

    public void setOversizebox(String oversizebox) {
        this.oversizebox = oversizebox;
    }

    public String getPhotobox() {
        return photobox;
    }

    public void setPhotobox(String photobox) {
        this.photobox = photobox;
    }

    public String getPhotofolders() {
        return photofolders;
    }

    public void setPhotofolders(String photofolders) {
        this.photofolders = photofolders;
    }

    public String getWorkdates() {
        return workdates;
    }

    public void setWorkdates(String workdates) {
        this.workdates = workdates;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", personId=" + personId +
                ", title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", alias='" + alias + '\'' +
                ", states='" + states + '\'' +
                ", cities='" + cities + '\'' +
                ", nations='" + nations + '\'' +
                ", lifedates='" + lifedates + '\'' +
                ", workdates='" + workdates + '\'' +
                ", biobox='" + biobox + '\'' +
                ", photobox='" + photobox + '\'' +
                ", photofolders='" + photofolders + '\'' +
                ", albumbox='" + albumbox + '\'' +
                ", fragilebox='" + fragilebox + '\'' +
                ", audiovisualbox='" + audiovisualbox + '\'' +
                ", oversizebox='" + oversizebox + '\'' +
                '}';
    }
}
