package edu.yale.domain;

import javax.persistence.*;

/**
 * Represents a photographer
 */
@Entity
@Table (name = "Palmquist")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "ID")
    private long personId;

    @Column
    private String title;

    @Column (name  = "Full_Name")
    private String fullName;

    @Column (name  = "`Also known as Description`")
    private String alias;

    @Column (name = "State")
    private String states;

    @Column (name = "Cities")
    private String cities;

    @Column (name  = "Nations")
    private String nations;

    @Column (name  = "`Life Dates`")
    private String lifedates;

    @Column (name  = "`Work Dates`")
    private String workdates;

    @Column (name  = "`Bio Box`")
    private String biobox;

    @Column (name  = "`Photo Box`")
    private String photobox;

    @Column (name  = "`Photo Folders`")
    private String photofolders;

    @Column (name  = "`Album Box`")
    private String albumbox;

    @Column (name  = "`Fragile Photo Box`")
    private String fragilebox;

    @Column (name  = "`Audiovisual Box`")
    private String audiovisualbox;

    @Column (name  = "`Oversize Photo Box`")
    private String oversizebox;

    // TODO (move fields elsewhere)
    @Transient
    private String titleOption; // for the drop down

    @Transient
    private String fullNameOption;

    @Transient
    private String cityOption;

    @Transient
    private String stateOption;

    @Transient
    private String nationOption;

    @Transient
    private String aliasOption;


    public Person() {
    }

    public Person(String fullName) {
        this.fullName = fullName;
    }

    public Person(int age, String alias, String cities, String fullName, String states) {
        this.alias = alias;
        this.cities = cities;
        this.fullName = fullName;
        this.states = states;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long studentId) {
        this.personId = studentId;
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

    public String getTitleOption() {
        return titleOption;
    }

    public void setTitleOption(String titleOption) {
        this.titleOption = titleOption;
    }

    public String getFullNameOption() {
        return fullNameOption;
    }

    public void setFullNameOption(String fullNameOption) {
        this.fullNameOption = fullNameOption;
    }

    public String getCityOption() {
        return cityOption;
    }

    public void setCityOption(String cityOption) {
        this.cityOption = cityOption;
    }

    public String getStateOption() {
        return stateOption;
    }

    public void setStateOption(String stateOption) {
        this.stateOption = stateOption;
    }

    public String getNationOption() {
        return nationOption;
    }

    public void setNationOption(String nationOption) {
        this.nationOption = nationOption;
    }

    public String getAliasOption() {
        return aliasOption;
    }

    public void setAliasOption(String aliasOption) {
        this.aliasOption = aliasOption;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", title='" + title + '\'' +
                ", fullName='" + fullName + '\'' +
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
                ", titleOption='" + titleOption + '\'' +
                ", fullNameOption='" + fullNameOption + '\'' +
                ", cityOption='" + cityOption + '\'' +
                ", stateOption='" + stateOption + '\'' +
                ", nationOption='" + nationOption + '\'' +
                '}';
    }
}
